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

package org.lsug.quota.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.lsug.quota.model.QuotaDailyLog;

/**
 * The persistence interface for the quota daily log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuotaDailyLogPersistenceImpl
 * @see QuotaDailyLogUtil
 * @generated
 */
public interface QuotaDailyLogPersistence extends BasePersistence<QuotaDailyLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QuotaDailyLogUtil} to access the quota daily log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the quota daily log in the entity cache if it is enabled.
	*
	* @param quotaDailyLog the quota daily log
	*/
	public void cacheResult(org.lsug.quota.model.QuotaDailyLog quotaDailyLog);

	/**
	* Caches the quota daily logs in the entity cache if it is enabled.
	*
	* @param quotaDailyLogs the quota daily logs
	*/
	public void cacheResult(
		java.util.List<org.lsug.quota.model.QuotaDailyLog> quotaDailyLogs);

	/**
	* Creates a new quota daily log with the primary key. Does not add the quota daily log to the database.
	*
	* @param quotaDailyLogId the primary key for the new quota daily log
	* @return the new quota daily log
	*/
	public org.lsug.quota.model.QuotaDailyLog create(long quotaDailyLogId);

	/**
	* Removes the quota daily log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param quotaDailyLogId the primary key of the quota daily log
	* @return the quota daily log that was removed
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog remove(long quotaDailyLogId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException;

	public org.lsug.quota.model.QuotaDailyLog updateImpl(
		org.lsug.quota.model.QuotaDailyLog quotaDailyLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the quota daily log with the primary key or throws a {@link org.lsug.quota.NoSuchQuotaDailyLogException} if it could not be found.
	*
	* @param quotaDailyLogId the primary key of the quota daily log
	* @return the quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog findByPrimaryKey(
		long quotaDailyLogId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException;

	/**
	* Returns the quota daily log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param quotaDailyLogId the primary key of the quota daily log
	* @return the quota daily log, or <code>null</code> if a quota daily log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog fetchByPrimaryKey(
		long quotaDailyLogId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the quota daily logs where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @return the matching quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.QuotaDailyLog> findByQuotaId(
		long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the quota daily logs where quotaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param quotaId the quota ID
	* @param start the lower bound of the range of quota daily logs
	* @param end the upper bound of the range of quota daily logs (not inclusive)
	* @return the range of matching quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.QuotaDailyLog> findByQuotaId(
		long quotaId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the quota daily logs where quotaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param quotaId the quota ID
	* @param start the lower bound of the range of quota daily logs
	* @param end the upper bound of the range of quota daily logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.QuotaDailyLog> findByQuotaId(
		long quotaId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog findByQuotaId_First(
		long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException;

	/**
	* Returns the first quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog fetchByQuotaId_First(
		long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog findByQuotaId_Last(long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException;

	/**
	* Returns the last quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog fetchByQuotaId_Last(
		long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the quota daily logs before and after the current quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaDailyLogId the primary key of the current quota daily log
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog[] findByQuotaId_PrevAndNext(
		long quotaDailyLogId, long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException;

	/**
	* Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or throws a {@link org.lsug.quota.NoSuchQuotaDailyLogException} if it could not be found.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @return the matching quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog findByQuotaIdDayAnalyzed(
		long quotaId, java.util.Date dayAnalyzed)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException;

	/**
	* Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @return the matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog fetchByQuotaIdDayAnalyzed(
		long quotaId, java.util.Date dayAnalyzed)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog fetchByQuotaIdDayAnalyzed(
		long quotaId, java.util.Date dayAnalyzed, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the quota daily logs.
	*
	* @return the quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.QuotaDailyLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the quota daily logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of quota daily logs
	* @param end the upper bound of the range of quota daily logs (not inclusive)
	* @return the range of quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.QuotaDailyLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the quota daily logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of quota daily logs
	* @param end the upper bound of the range of quota daily logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.QuotaDailyLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the quota daily logs where quotaId = &#63; from the database.
	*
	* @param quotaId the quota ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByQuotaId(long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; from the database.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @return the quota daily log that was removed
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.QuotaDailyLog removeByQuotaIdDayAnalyzed(
		long quotaId, java.util.Date dayAnalyzed)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException;

	/**
	* Removes all the quota daily logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of quota daily logs where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @return the number of matching quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByQuotaId(long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of quota daily logs where quotaId = &#63; and dayAnalyzed = &#63;.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @return the number of matching quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByQuotaIdDayAnalyzed(long quotaId,
		java.util.Date dayAnalyzed)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of quota daily logs.
	*
	* @return the number of quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}