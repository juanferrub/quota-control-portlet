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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.lsug.quota.service.QuotaDailyLogLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class QuotaDailyLogClp extends BaseModelImpl<QuotaDailyLog>
	implements QuotaDailyLog {
	public QuotaDailyLogClp() {
	}

	public Class<?> getModelClass() {
		return QuotaDailyLog.class;
	}

	public String getModelClassName() {
		return QuotaDailyLog.class.getName();
	}

	public long getPrimaryKey() {
		return _quotaDailyLogId;
	}

	public void setPrimaryKey(long primaryKey) {
		setQuotaDailyLogId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_quotaDailyLogId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("quotaDailyLogId", getQuotaDailyLogId());
		attributes.put("dayAnalyzed", getDayAnalyzed());
		attributes.put("quotaId", getQuotaId());
		attributes.put("quotaAssigned", getQuotaAssigned());
		attributes.put("quotaUsed", getQuotaUsed());
		attributes.put("quotaStatus", getQuotaStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long quotaDailyLogId = (Long)attributes.get("quotaDailyLogId");

		if (quotaDailyLogId != null) {
			setQuotaDailyLogId(quotaDailyLogId);
		}

		Date dayAnalyzed = (Date)attributes.get("dayAnalyzed");

		if (dayAnalyzed != null) {
			setDayAnalyzed(dayAnalyzed);
		}

		Long quotaId = (Long)attributes.get("quotaId");

		if (quotaId != null) {
			setQuotaId(quotaId);
		}

		Long quotaAssigned = (Long)attributes.get("quotaAssigned");

		if (quotaAssigned != null) {
			setQuotaAssigned(quotaAssigned);
		}

		Long quotaUsed = (Long)attributes.get("quotaUsed");

		if (quotaUsed != null) {
			setQuotaUsed(quotaUsed);
		}

		Integer quotaStatus = (Integer)attributes.get("quotaStatus");

		if (quotaStatus != null) {
			setQuotaStatus(quotaStatus);
		}
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

	public BaseModel<?> getQuotaDailyLogRemoteModel() {
		return _quotaDailyLogRemoteModel;
	}

	public void setQuotaDailyLogRemoteModel(
		BaseModel<?> quotaDailyLogRemoteModel) {
		_quotaDailyLogRemoteModel = quotaDailyLogRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			QuotaDailyLogLocalServiceUtil.addQuotaDailyLog(this);
		}
		else {
			QuotaDailyLogLocalServiceUtil.updateQuotaDailyLog(this);
		}
	}

	@Override
	public QuotaDailyLog toEscapedModel() {
		return (QuotaDailyLog)Proxy.newProxyInstance(QuotaDailyLog.class.getClassLoader(),
			new Class[] { QuotaDailyLog.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		QuotaDailyLogClp clone = new QuotaDailyLogClp();

		clone.setQuotaDailyLogId(getQuotaDailyLogId());
		clone.setDayAnalyzed(getDayAnalyzed());
		clone.setQuotaId(getQuotaId());
		clone.setQuotaAssigned(getQuotaAssigned());
		clone.setQuotaUsed(getQuotaUsed());
		clone.setQuotaStatus(getQuotaStatus());

		return clone;
	}

	public int compareTo(QuotaDailyLog quotaDailyLog) {
		long primaryKey = quotaDailyLog.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		QuotaDailyLogClp quotaDailyLog = null;

		try {
			quotaDailyLog = (QuotaDailyLogClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = quotaDailyLog.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{quotaDailyLogId=");
		sb.append(getQuotaDailyLogId());
		sb.append(", dayAnalyzed=");
		sb.append(getDayAnalyzed());
		sb.append(", quotaId=");
		sb.append(getQuotaId());
		sb.append(", quotaAssigned=");
		sb.append(getQuotaAssigned());
		sb.append(", quotaUsed=");
		sb.append(getQuotaUsed());
		sb.append(", quotaStatus=");
		sb.append(getQuotaStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("org.lsug.quota.model.QuotaDailyLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>quotaDailyLogId</column-name><column-value><![CDATA[");
		sb.append(getQuotaDailyLogId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dayAnalyzed</column-name><column-value><![CDATA[");
		sb.append(getDayAnalyzed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quotaId</column-name><column-value><![CDATA[");
		sb.append(getQuotaId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quotaAssigned</column-name><column-value><![CDATA[");
		sb.append(getQuotaAssigned());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quotaUsed</column-name><column-value><![CDATA[");
		sb.append(getQuotaUsed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quotaStatus</column-name><column-value><![CDATA[");
		sb.append(getQuotaStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _quotaDailyLogId;
	private Date _dayAnalyzed;
	private long _quotaId;
	private long _quotaAssigned;
	private long _quotaUsed;
	private int _quotaStatus;
	private BaseModel<?> _quotaDailyLogRemoteModel;
}