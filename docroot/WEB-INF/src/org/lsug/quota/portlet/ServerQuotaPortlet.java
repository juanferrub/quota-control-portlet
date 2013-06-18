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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;


import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;

import org.lsug.quota.model.Quota;
import org.lsug.quota.server.util.QuotaBaseVO;
import org.lsug.quota.server.util.ServerQuotaVO;
import org.lsug.quota.service.QuotaLocalServiceUtil;
import org.lsug.quota.util.QuotaConstants;

/**
 * ServerQuotaPortlet
 * Manages the quotas of the server companies
 */
public class ServerQuotaPortlet extends QuotaBasePortlet {

	@Override
	protected String getEditPage() {
		return QuotaConstants.PATH_SERVER_EDIT_QUOTA;
	}

	@Override
	protected List<QuotaBaseVO> getQuotas(RenderRequest req, int start, int end)
			throws PortalException, SystemException {

		List<Quota> serverQuotas =
			QuotaLocalServiceUtil.getServerQuotas(start,end);

		List<QuotaBaseVO> serverQuotasVO =
				new ArrayList<QuotaBaseVO>(serverQuotas.size());

		for (Quota q : serverQuotas) {
			serverQuotasVO.add(new ServerQuotaVO(q, req.getLocale()));
		}

		return serverQuotasVO;
	}

	@Override
	protected Quota getQuotaFromRequest(ActionRequest req)
			throws PortalException, SystemException {
		long quotaId = ParamUtil.getLong(req, "quotaId");
		boolean quotaStatus = ParamUtil.getBoolean(
				req, "server-quota.edit.status");
		long quotaAssigned =
			(long)(ParamUtil.getDouble(req, "server-quota.edit.assigned")
			* QuotaConstants.BYTES_TO_GB);
		int quotaAlert = ParamUtil.getInteger(req, "server-quota.edit.alert");

		Quota quota = QuotaLocalServiceUtil.getQuota(quotaId);

		quota.setQuotaStatus(quotaStatus ? 1 : 0);
		quota.setQuotaAssigned(quotaAssigned);
		quota.setQuotaAlert(quotaAlert);

		return quota;
	}

	private Log _log = LogFactoryUtil.getLog(
		ServerQuotaPortlet.class.getName());
}