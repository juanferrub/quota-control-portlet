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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;

import org.lsug.quota.model.Quota;
import org.lsug.quota.server.util.QuotaBaseVO;
import org.lsug.quota.server.util.SiteQuotaVO;
import org.lsug.quota.service.QuotaLocalServiceUtil;
import org.lsug.quota.util.QuotaConstants;

public class SitesQuotaPortlet extends QuotaBasePortlet {

	@Override
	protected List<QuotaBaseVO> getQuotas(RenderRequest req, int start, int end)
			throws PortalException, SystemException {
		ThemeDisplay themeDisplay =
			(ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);

		long companyId = themeDisplay.getCompanyId();

		List<QuotaBaseVO> sitesQuotas = new ArrayList<QuotaBaseVO>();

		List<Quota> sitesQuotasList = QuotaLocalServiceUtil.getSitesQuotas(
			companyId, start, end);

		for (Quota quota : sitesQuotasList) {
			sitesQuotas.add(new SiteQuotaVO(quota, req.getLocale()));
		}

		return sitesQuotas;
	}

	@Override
	protected String getEditPage() {
		return QuotaConstants.PATH_SITES_EDIT_QUOTA;
	}

	@Override
	protected Quota getQuotaFromRequest(ActionRequest req)
			throws PortalException, SystemException {
		long quotaId = ParamUtil.getLong(req, "quotaId");

		boolean quotaStatus = ParamUtil.getBoolean(
			req, "sites-quota.edit.status");

		long quotaAssigned =
			(long)(ParamUtil.getDouble(req, "sites-quota.edit.assigned") *
			QuotaConstants.BYTES_TO_GB);

		int quotaAlert = ParamUtil.getInteger(req, "sites-quota.edit.alert");

		Quota quota = QuotaLocalServiceUtil.getQuota(quotaId);

		quota.setQuotaStatus(quotaStatus ? 1 : 0);
		quota.setQuotaAssigned(quotaAssigned);
		quota.setQuotaAlert(quotaAlert);

		return quota;
	}
}