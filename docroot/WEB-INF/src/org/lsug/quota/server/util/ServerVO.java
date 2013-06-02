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

package org.lsug.quota.server.util;

import com.liferay.portal.service.UserLocalServiceUtil;
import org.lsug.quota.model.Quota;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import java.util.Locale;

public class ServerVO extends QuotaBaseVO {



	private Locale locale;

	public ServerVO(Quota q,Locale loc)
			throws SystemException, PortalException {

		Company c = CompanyLocalServiceUtil.getCompany(q.getClassPK());

		setQuotaId(q.getQuotaId());
		setLoc(loc);
		setNameInstance(c.getName());
		setNumUsers(UserLocalServiceUtil.getCompanyUsersCount(c.getCompanyId()));
		setUsed(q.getQuotaUsed());
		setAssigned(q.getQuotaAssigned());
		setActive(q.getQuotaStatus());
		setAlarm(q.getQuotaAlert());
	}

	public String getNameInstance() {
		return _nameInstance;
	}

	public int getNumUsers() {
		return _numUsers;
	}

	public void setNameInstance(String nameInstance) {
		_nameInstance = nameInstance;
	}

	public void setNumUsers(int numUsers) {
		_numUsers = numUsers;
	}

	private String _nameInstance;
	private int _numUsers;



}
