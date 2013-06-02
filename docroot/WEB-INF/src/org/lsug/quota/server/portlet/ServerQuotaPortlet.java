package org.lsug.quota.server.portlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactory;
import org.lsug.quota.NoSuchQuotaException;
import org.lsug.quota.model.Quota;
import org.lsug.quota.model.QuotaDailyLog;
import org.lsug.quota.server.util.QuotaDailyLogVO;
import org.lsug.quota.server.util.ServerVO;
import org.lsug.quota.service.QuotaDailyLogLocalServiceUtil;
import org.lsug.quota.service.QuotaLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import org.lsug.quota.util.QuotaUtil;

public class ServerQuotaPortlet extends com.liferay.util.bridges.mvc.MVCPortlet {

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
			System.out.println(cmd);
			listServerQuotas(renderRequest, renderResponse);
		}

	}

	private void listServerQuotas(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		int cur = ParamUtil.getInteger(renderRequest, "cur", 0);
		int paramDelta = ParamUtil.getInteger(renderRequest, "delta", 10);
		PortletURL portletURL = renderResponse.createRenderURL();

		SearchContainer<ServerVO> searchContainer = new SearchContainer<ServerVO>(
				renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM,
				cur, paramDelta, portletURL, null, null);
		searchContainer.setDelta(paramDelta);
		searchContainer.setDeltaConfigurable(false);

		List<Quota> listQuotas = new ArrayList<Quota>();
		List<ServerVO> listServerVOs = new ArrayList<ServerVO>();
		int listQuotasCount = 0;
		try {
			listQuotasCount = QuotaLocalServiceUtil.getQuotasCount();

			List<Company> listCompany = CompanyLocalServiceUtil.getCompanies();

			listQuotasCount = listCompany.size();
			ServerVO serverVO = null;
			for (Company company : listCompany) {
				Quota quota = QuotaLocalServiceUtil
					.getQuotaByClassNameIdClassPK(
						PortalUtil.getClassNameId(Company.class.getName()),
						company.getCompanyId());
				serverVO = new ServerVO(quota,renderRequest.getLocale());

				listServerVOs.add(serverVO);
			}
		} catch (SystemException e) {
			_log.error(e);// TODO: Exceptions control
		} catch (PortalException e) {
			_log.error(e);// TODO: Exceptions control
		}
		renderRequest.setAttribute("searchContainer", searchContainer);
		renderRequest.setAttribute("list", listServerVOs);
		renderRequest.setAttribute("count", listQuotasCount);
		super.doView(renderRequest, renderResponse);
	}

	private void editQuota(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		Quota quota = null;
		final long quotaId = ParamUtil.getLong(renderRequest, "quotaId", 0);

		try {
			quota = QuotaLocalServiceUtil.getQuota(ParamUtil.getLong(
				renderRequest, "quotaId"));
		} catch (PortalException e) {
			_log.error(e);
		} catch (SystemException e) {
			_log.error(e);
		}

		renderRequest.setAttribute("quota", quota);

		include("/html/server-quota/edit_quota.jsp", renderRequest,
				renderResponse);
	}

	private void showHistory(
			RenderRequest renderRequest,RenderResponse renderResponse)
		throws IOException, PortletException {
		Quota quota = null;

		final long quotaId = ParamUtil.getLong(renderRequest, "quotaId", 0);

		try {
			quota = QuotaLocalServiceUtil.getQuota(
				ParamUtil.getLong(renderRequest, "quotaId"));

			List<QuotaDailyLog> quotaDailyLogList =
					new ArrayList<QuotaDailyLog>();
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
		} catch (PortalException e) {
			_log.error(e);
		} catch (SystemException e) {
			_log.error(e);
		}

		renderRequest.setAttribute("quota", quota);

		include("/html/server-quota/history/history_server.jsp", renderRequest,
				renderResponse);

	}

	public void saveServerQuota(final ActionRequest req,
			final ActionResponse res) throws SystemException, PortalException {

		final String cmd = ParamUtil.getString(req, Constants.CMD,
				StringPool.BLANK);

		final long quotaId = ParamUtil.getLong(req, "quotaId");
		final long classPK = ParamUtil.getLong(req, "classPK");
		final boolean quotaStatus = ParamUtil.getBoolean(req, "server-quota.edit.status");
		final long quotaAssigned  =
			(long)(ParamUtil.getDouble(req, "server-quota.edit.assigned")*1024*1024*1024);
		final int quotaAlert = ParamUtil.getInteger(req, "server-quota.edit.alert");

		Quota quota = QuotaLocalServiceUtil.getQuota(quotaId);
		quota.setQuotaStatus(quotaStatus ? 1 : 0);
		quota.setQuotaAssigned(quotaAssigned);
		quota.setQuotaAlert(quotaAlert);

		QuotaLocalServiceUtil.updateQuota(quota);
	}

	private JSONObject getJSONObject(QuotaDailyLog log, QuotaDailyLogVO quotaDailyLogVO) {
		JSONObject logJSON = JSONFactoryUtil.createJSONObject();

		logJSON.put("day", quotaDailyLogVO.getDate());
		double percent = (double)log.getQuotaUsed()/log.getQuotaAssigned();
		logJSON.put("perc", percent );
		logJSON.put("usage",((double)log.getQuotaUsed()/(1024*1024*1024)));
		return logJSON;
	}

	private Log _log =
		LogFactoryUtil.getLog(ServerQuotaPortlet.class.getName());
}
