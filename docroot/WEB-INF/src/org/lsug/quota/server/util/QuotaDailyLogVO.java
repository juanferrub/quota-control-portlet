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

import org.lsug.quota.model.QuotaDailyLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QuotaDailyLogVO extends QuotaBaseVO {

	public QuotaDailyLogVO(QuotaDailyLog qdl,Locale locale) {

		setActive(qdl.getQuotaStatus());
		setUsed(qdl.getQuotaUsed());
		setQuotaId(qdl.getQuotaId());
		setLoc(locale);
		setAssigned(qdl.getQuotaAssigned());
		setDate(qdl.getDayAnalyzed());
	}

	public String getDate() {
		DateFormat sdf = null;
		if (getQuotaId() != 0) {
			sdf = new SimpleDateFormat("dd/MM/yyyy");
		}
		else {
			sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		}
		return sdf.format(_date);
	}

	public void setDate(Date date) {
		_date = date;
	}

	private Date _date;


}