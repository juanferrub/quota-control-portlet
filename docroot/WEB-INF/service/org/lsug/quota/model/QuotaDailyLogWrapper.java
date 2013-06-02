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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link QuotaDailyLog}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       QuotaDailyLog
 * @generated
 */
public class QuotaDailyLogWrapper implements QuotaDailyLog,
	ModelWrapper<QuotaDailyLog> {
	public QuotaDailyLogWrapper(QuotaDailyLog quotaDailyLog) {
		_quotaDailyLog = quotaDailyLog;
	}

	public Class<?> getModelClass() {
		return QuotaDailyLog.class;
	}

	public String getModelClassName() {
		return QuotaDailyLog.class.getName();
	}

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

	/**
	* Returns the primary key of this quota daily log.
	*
	* @return the primary key of this quota daily log
	*/
	public long getPrimaryKey() {
		return _quotaDailyLog.getPrimaryKey();
	}

	/**
	* Sets the primary key of this quota daily log.
	*
	* @param primaryKey the primary key of this quota daily log
	*/
	public void setPrimaryKey(long primaryKey) {
		_quotaDailyLog.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the quota daily log ID of this quota daily log.
	*
	* @return the quota daily log ID of this quota daily log
	*/
	public long getQuotaDailyLogId() {
		return _quotaDailyLog.getQuotaDailyLogId();
	}

	/**
	* Sets the quota daily log ID of this quota daily log.
	*
	* @param quotaDailyLogId the quota daily log ID of this quota daily log
	*/
	public void setQuotaDailyLogId(long quotaDailyLogId) {
		_quotaDailyLog.setQuotaDailyLogId(quotaDailyLogId);
	}

	/**
	* Returns the day analyzed of this quota daily log.
	*
	* @return the day analyzed of this quota daily log
	*/
	public java.util.Date getDayAnalyzed() {
		return _quotaDailyLog.getDayAnalyzed();
	}

	/**
	* Sets the day analyzed of this quota daily log.
	*
	* @param dayAnalyzed the day analyzed of this quota daily log
	*/
	public void setDayAnalyzed(java.util.Date dayAnalyzed) {
		_quotaDailyLog.setDayAnalyzed(dayAnalyzed);
	}

	/**
	* Returns the quota ID of this quota daily log.
	*
	* @return the quota ID of this quota daily log
	*/
	public long getQuotaId() {
		return _quotaDailyLog.getQuotaId();
	}

	/**
	* Sets the quota ID of this quota daily log.
	*
	* @param quotaId the quota ID of this quota daily log
	*/
	public void setQuotaId(long quotaId) {
		_quotaDailyLog.setQuotaId(quotaId);
	}

	/**
	* Returns the quota assigned of this quota daily log.
	*
	* @return the quota assigned of this quota daily log
	*/
	public long getQuotaAssigned() {
		return _quotaDailyLog.getQuotaAssigned();
	}

	/**
	* Sets the quota assigned of this quota daily log.
	*
	* @param quotaAssigned the quota assigned of this quota daily log
	*/
	public void setQuotaAssigned(long quotaAssigned) {
		_quotaDailyLog.setQuotaAssigned(quotaAssigned);
	}

	/**
	* Returns the quota used of this quota daily log.
	*
	* @return the quota used of this quota daily log
	*/
	public long getQuotaUsed() {
		return _quotaDailyLog.getQuotaUsed();
	}

	/**
	* Sets the quota used of this quota daily log.
	*
	* @param quotaUsed the quota used of this quota daily log
	*/
	public void setQuotaUsed(long quotaUsed) {
		_quotaDailyLog.setQuotaUsed(quotaUsed);
	}

	/**
	* Returns the quota status of this quota daily log.
	*
	* @return the quota status of this quota daily log
	*/
	public int getQuotaStatus() {
		return _quotaDailyLog.getQuotaStatus();
	}

	/**
	* Sets the quota status of this quota daily log.
	*
	* @param quotaStatus the quota status of this quota daily log
	*/
	public void setQuotaStatus(int quotaStatus) {
		_quotaDailyLog.setQuotaStatus(quotaStatus);
	}

	public boolean isNew() {
		return _quotaDailyLog.isNew();
	}

	public void setNew(boolean n) {
		_quotaDailyLog.setNew(n);
	}

	public boolean isCachedModel() {
		return _quotaDailyLog.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_quotaDailyLog.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _quotaDailyLog.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _quotaDailyLog.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_quotaDailyLog.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _quotaDailyLog.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_quotaDailyLog.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new QuotaDailyLogWrapper((QuotaDailyLog)_quotaDailyLog.clone());
	}

	public int compareTo(org.lsug.quota.model.QuotaDailyLog quotaDailyLog) {
		return _quotaDailyLog.compareTo(quotaDailyLog);
	}

	@Override
	public int hashCode() {
		return _quotaDailyLog.hashCode();
	}

	public com.liferay.portal.model.CacheModel<org.lsug.quota.model.QuotaDailyLog> toCacheModel() {
		return _quotaDailyLog.toCacheModel();
	}

	public org.lsug.quota.model.QuotaDailyLog toEscapedModel() {
		return new QuotaDailyLogWrapper(_quotaDailyLog.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _quotaDailyLog.toString();
	}

	public java.lang.String toXmlString() {
		return _quotaDailyLog.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_quotaDailyLog.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public QuotaDailyLog getWrappedQuotaDailyLog() {
		return _quotaDailyLog;
	}

	public QuotaDailyLog getWrappedModel() {
		return _quotaDailyLog;
	}

	public void resetOriginalValues() {
		_quotaDailyLog.resetOriginalValues();
	}

	private QuotaDailyLog _quotaDailyLog;
}