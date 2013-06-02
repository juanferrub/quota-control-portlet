/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package org.lsug.quota.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.lsug.quota.service.http.QuotaDailyLogServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.lsug.quota.service.http.QuotaDailyLogServiceSoap
 * @generated
 */
public class QuotaDailyLogSoap implements Serializable {
	public static QuotaDailyLogSoap toSoapModel(QuotaDailyLog model) {
		QuotaDailyLogSoap soapModel = new QuotaDailyLogSoap();

		soapModel.setQuotaDailyLogId(model.getQuotaDailyLogId());
		soapModel.setDayAnalyzed(model.getDayAnalyzed());
		soapModel.setQuotaId(model.getQuotaId());
		soapModel.setQuotaAssigned(model.getQuotaAssigned());
		soapModel.setQuotaUsed(model.getQuotaUsed());
		soapModel.setQuotaStatus(model.getQuotaStatus());

		return soapModel;
	}

	public static QuotaDailyLogSoap[] toSoapModels(QuotaDailyLog[] models) {
		QuotaDailyLogSoap[] soapModels = new QuotaDailyLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static QuotaDailyLogSoap[][] toSoapModels(QuotaDailyLog[][] models) {
		QuotaDailyLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new QuotaDailyLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new QuotaDailyLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static QuotaDailyLogSoap[] toSoapModels(List<QuotaDailyLog> models) {
		List<QuotaDailyLogSoap> soapModels = new ArrayList<QuotaDailyLogSoap>(models.size());

		for (QuotaDailyLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new QuotaDailyLogSoap[soapModels.size()]);
	}

	public QuotaDailyLogSoap() {
	}

	public long getPrimaryKey() {
		return _quotaDailyLogId;
	}

	public void setPrimaryKey(long pk) {
		setQuotaDailyLogId(pk);
	}

	public long getQuotaDailyLogId() {
		return _quotaDailyLogId;
	}

	public void setQuotaDailyLogId(long quotaDailyLogId) {
		_quotaDailyLogId = quotaDailyLogId;
	}

	public Date getDayAnalyzed() {
		return _dayAnalyzed;
	}

	public void setDayAnalyzed(Date dayAnalyzed) {
		_dayAnalyzed = dayAnalyzed;
	}

	public long getQuotaId() {
		return _quotaId;
	}

	public void setQuotaId(long quotaId) {
		_quotaId = quotaId;
	}

	public long getQuotaAssigned() {
		return _quotaAssigned;
	}

	public void setQuotaAssigned(long quotaAssigned) {
		_quotaAssigned = quotaAssigned;
	}

	public long getQuotaUsed() {
		return _quotaUsed;
	}

	public void setQuotaUsed(long quotaUsed) {
		_quotaUsed = quotaUsed;
	}

	public int getQuotaStatus() {
		return _quotaStatus;
	}

	public void setQuotaStatus(int quotaStatus) {
		_quotaStatus = quotaStatus;
	}

	private long _quotaDailyLogId;
	private Date _dayAnalyzed;
	private long _quotaId;
	private long _quotaAssigned;
	private long _quotaUsed;
	private int _quotaStatus;
}