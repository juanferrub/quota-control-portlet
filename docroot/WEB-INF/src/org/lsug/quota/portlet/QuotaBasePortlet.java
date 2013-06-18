/**
 * Copyright (c) 2013 Liferay Spain User Group All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.lsug.quota.portlet;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.lsug.quota.model.Quota;
import org.lsug.quota.model.QuotaDailyLog;
import org.lsug.quota.server.util.QuotaBaseVO;
import org.lsug.quota.server.util.QuotaDailyLogVO;
import org.lsug.quota.server.util.ServerQuotaVO;
import org.lsug.quota.service.QuotaDailyLogLocalServiceUtil;
import org.lsug.quota.service.QuotaLocalServiceUtil;
import org.lsug.quota.util.QuotaConstants;

/**
 * Base Quota portlet
 */
public abstract class QuotaBasePortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		final String cmd = ParamUtil.getString(renderRequest, Constants.CMD,
			StringPool.BLANK);

		if (cmd.equals(Constants.UPDATE)) {
			editQuota(renderRequest, renderResponse);
		}
		else if (cmd.equals(Constants.PREVIEW)) {
			showHistory(renderRequest, renderResponse);
		}
		else {
			listQuotas(renderRequest, renderResponse);
		}
	}

	protected void listQuotas(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		int cur = ParamUtil.getInteger(renderRequest, "cur", 0);
		int paramDelta = ParamUtil.getInteger(renderRequest, "delta", 10);
		PortletURL portletURL = renderResponse.createRenderURL();

		SearchContainer<ServerQuotaVO> searchContainer = new SearchContainer<ServerQuotaVO>(
			renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, cur,
			paramDelta, portletURL, null, null);
		searchContainer.setDelta(paramDelta);
		searchContainer.setDeltaConfigurable(false);

		List<QuotaBaseVO> listQuotas = Collections.EMPTY_LIST;

		try {
			listQuotas = getQuotas(renderRequest, cur, paramDelta);
		}
		catch (SystemException e) {
			_log.error(e);
		}
		catch (PortalException e) {
			_log.error(e);
		}

		renderRequest.setAttribute("searchContainer", searchContainer);
		renderRequest.setAttribute("list", listQuotas);
		renderRequest.setAttribute("count", listQuotas.size());
		super.doView(renderRequest, renderResponse);
	}

	protected void editQuota(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		Quota quota = null;

		try {
			quota = QuotaLocalServiceUtil.getQuota(ParamUtil.getLong(
				renderRequest, "quotaId"));
		}
		catch (PortalException e) {
			_log.error(e);
		}
		catch (SystemException e) {
			_log.error(e);
		}

		renderRequest.setAttribute("quota", quota);

		include(getEditPage(), renderRequest, renderResponse);
	}

	protected void showHistory(
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		Quota quota = null;

		final long quotaId = ParamUtil.getLong(renderRequest, "quotaId", 0);

		try {
			quota = QuotaLocalServiceUtil.getQuota(
					ParamUtil.getLong(renderRequest, "quotaId"));

			List<QuotaDailyLog> quotaDailyLogList =
				new ArrayList<QuotaDailyLog>();

			int start = -1;
			int end = -1;

			quotaDailyLogList.addAll(QuotaDailyLogLocalServiceUtil.
				getQuotaDailyLogsByQuotaId(quotaId));

			QuotaDailyLog quotaDailyLogToday =
				QuotaDailyLogLocalServiceUtil.createQuotaDailyLog(0);

			quotaDailyLogToday.setQuotaAssigned(quota.getQuotaAssigned());
			quotaDailyLogToday.setQuotaStatus(quota.getQuotaStatus());
			quotaDailyLogToday.setQuotaUsed(quota.getQuotaUsed());
			quotaDailyLogToday.setDayAnalyzed(new Date());

			quotaDailyLogList.add(quotaDailyLogToday);

			JSONArray logsJSON = JSONFactoryUtil.createJSONArray();

			List<QuotaDailyLogVO> quotaDailyLogVOList =
				new ArrayList<QuotaDailyLogVO>();

			Locale loc = renderRequest.getLocale();

			for (QuotaDailyLog log : quotaDailyLogList) {
				QuotaDailyLogVO quotaDailyLogVO = new QuotaDailyLogVO(log, loc);

				quotaDailyLogVOList.add(quotaDailyLogVO);
				logsJSON.put(getJSONObject(log, quotaDailyLogVO));
			}

			renderRequest.setAttribute("history",logsJSON);
			renderRequest.setAttribute("list", quotaDailyLogVOList);
		}
		catch (PortalException e) {
			_log.error(e);
		}
		catch (SystemException e) {
			_log.error(e);
		}

		renderRequest.setAttribute("quota", quota);

		include(QuotaConstants.PATH_HISTORY, renderRequest, renderResponse);
	}

	public void saveQuota(ActionRequest req, ActionResponse res)
			throws PortalException, SystemException {
		Quota requestQuota = getQuotaFromRequest(req);
		QuotaLocalServiceUtil.updateQuota(requestQuota);
	}

	private JSONObject getJSONObject(QuotaDailyLog log,
			QuotaDailyLogVO quotaDailyLogVO) {
		JSONObject logJSON = JSONFactoryUtil.createJSONObject();

		logJSON.put("day", quotaDailyLogVO.getDate());
		double percent = (double)log.getQuotaUsed()/log.getQuotaAssigned();
		logJSON.put("perc", percent );
		logJSON.put("usage",
			((double)log.getQuotaUsed()/(QuotaConstants.BYTES_TO_GB)));
		return logJSON;
	}

	protected abstract List<QuotaBaseVO> getQuotas(
		RenderRequest req, int cur, int paramDelta)
		throws PortalException, SystemException;

	protected abstract String getEditPage();

	protected abstract Quota getQuotaFromRequest(ActionRequest req)
		throws PortalException, SystemException;

	private Log _log = LogFactoryUtil.getLog(QuotaBasePortlet.class.getName());
}