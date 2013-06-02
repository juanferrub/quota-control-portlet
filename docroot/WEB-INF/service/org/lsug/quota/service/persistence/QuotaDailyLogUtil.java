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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.lsug.quota.model.QuotaDailyLog;

import java.util.List;

/**
 * The persistence utility for the quota daily log service. This utility wraps {@link QuotaDailyLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuotaDailyLogPersistence
 * @see QuotaDailyLogPersistenceImpl
 * @generated
 */
public class QuotaDailyLogUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(QuotaDailyLog quotaDailyLog) {
		getPersistence().clearCache(quotaDailyLog);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<QuotaDailyLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<QuotaDailyLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<QuotaDailyLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static QuotaDailyLog update(QuotaDailyLog quotaDailyLog,
		boolean merge) throws SystemException {
		return getPersistence().update(quotaDailyLog, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static QuotaDailyLog update(QuotaDailyLog quotaDailyLog,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(quotaDailyLog, merge, serviceContext);
	}

	/**
	* Caches the quota daily log in the entity cache if it is enabled.
	*
	* @param quotaDailyLog the quota daily log
	*/
	public static void cacheResult(
		org.lsug.quota.model.QuotaDailyLog quotaDailyLog) {
		getPersistence().cacheResult(quotaDailyLog);
	}

	/**
	* Caches the quota daily logs in the entity cache if it is enabled.
	*
	* @param quotaDailyLogs the quota daily logs
	*/
	public static void cacheResult(
		java.util.List<org.lsug.quota.model.QuotaDailyLog> quotaDailyLogs) {
		getPersistence().cacheResult(quotaDailyLogs);
	}

	/**
	* Creates a new quota daily log with the primary key. Does not add the quota daily log to the database.
	*
	* @param quotaDailyLogId the primary key for the new quota daily log
	* @return the new quota daily log
	*/
	public static org.lsug.quota.model.QuotaDailyLog create(
		long quotaDailyLogId) {
		return getPersistence().create(quotaDailyLogId);
	}

	/**
	* Removes the quota daily log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param quotaDailyLogId the primary key of the quota daily log
	* @return the quota daily log that was removed
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog remove(
		long quotaDailyLogId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException {
		return getPersistence().remove(quotaDailyLogId);
	}

	public static org.lsug.quota.model.QuotaDailyLog updateImpl(
		org.lsug.quota.model.QuotaDailyLog quotaDailyLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(quotaDailyLog, merge);
	}

	/**
	* Returns the quota daily log with the primary key or throws a {@link org.lsug.quota.NoSuchQuotaDailyLogException} if it could not be found.
	*
	* @param quotaDailyLogId the primary key of the quota daily log
	* @return the quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog findByPrimaryKey(
		long quotaDailyLogId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException {
		return getPersistence().findByPrimaryKey(quotaDailyLogId);
	}

	/**
	* Returns the quota daily log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param quotaDailyLogId the primary key of the quota daily log
	* @return the quota daily log, or <code>null</code> if a quota daily log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog fetchByPrimaryKey(
		long quotaDailyLogId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(quotaDailyLogId);
	}

	/**
	* Returns all the quota daily logs where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @return the matching quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.lsug.quota.model.QuotaDailyLog> findByQuotaId(
		long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByQuotaId(quotaId);
	}

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
	public static java.util.List<org.lsug.quota.model.QuotaDailyLog> findByQuotaId(
		long quotaId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByQuotaId(quotaId, start, end);
	}

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
	public static java.util.List<org.lsug.quota.model.QuotaDailyLog> findByQuotaId(
		long quotaId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByQuotaId(quotaId, start, end, orderByComparator);
	}

	/**
	* Returns the first quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog findByQuotaId_First(
		long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException {
		return getPersistence().findByQuotaId_First(quotaId, orderByComparator);
	}

	/**
	* Returns the first quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog fetchByQuotaId_First(
		long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByQuotaId_First(quotaId, orderByComparator);
	}

	/**
	* Returns the last quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog findByQuotaId_Last(
		long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException {
		return getPersistence().findByQuotaId_Last(quotaId, orderByComparator);
	}

	/**
	* Returns the last quota daily log in the ordered set where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog fetchByQuotaId_Last(
		long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByQuotaId_Last(quotaId, orderByComparator);
	}

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
	public static org.lsug.quota.model.QuotaDailyLog[] findByQuotaId_PrevAndNext(
		long quotaDailyLogId, long quotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException {
		return getPersistence()
				   .findByQuotaId_PrevAndNext(quotaDailyLogId, quotaId,
			orderByComparator);
	}

	/**
	* Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or throws a {@link org.lsug.quota.NoSuchQuotaDailyLogException} if it could not be found.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @return the matching quota daily log
	* @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog findByQuotaIdDayAnalyzed(
		long quotaId, java.util.Date dayAnalyzed)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException {
		return getPersistence().findByQuotaIdDayAnalyzed(quotaId, dayAnalyzed);
	}

	/**
	* Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @return the matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog fetchByQuotaIdDayAnalyzed(
		long quotaId, java.util.Date dayAnalyzed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByQuotaIdDayAnalyzed(quotaId, dayAnalyzed);
	}

	/**
	* Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog fetchByQuotaIdDayAnalyzed(
		long quotaId, java.util.Date dayAnalyzed, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByQuotaIdDayAnalyzed(quotaId, dayAnalyzed,
			retrieveFromCache);
	}

	/**
	* Returns all the quota daily logs.
	*
	* @return the quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.lsug.quota.model.QuotaDailyLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<org.lsug.quota.model.QuotaDailyLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<org.lsug.quota.model.QuotaDailyLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the quota daily logs where quotaId = &#63; from the database.
	*
	* @param quotaId the quota ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByQuotaId(long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByQuotaId(quotaId);
	}

	/**
	* Removes the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; from the database.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @return the quota daily log that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.lsug.quota.model.QuotaDailyLog removeByQuotaIdDayAnalyzed(
		long quotaId, java.util.Date dayAnalyzed)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaDailyLogException {
		return getPersistence().removeByQuotaIdDayAnalyzed(quotaId, dayAnalyzed);
	}

	/**
	* Removes all the quota daily logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of quota daily logs where quotaId = &#63;.
	*
	* @param quotaId the quota ID
	* @return the number of matching quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByQuotaId(long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByQuotaId(quotaId);
	}

	/**
	* Returns the number of quota daily logs where quotaId = &#63; and dayAnalyzed = &#63;.
	*
	* @param quotaId the quota ID
	* @param dayAnalyzed the day analyzed
	* @return the number of matching quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByQuotaIdDayAnalyzed(long quotaId,
		java.util.Date dayAnalyzed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByQuotaIdDayAnalyzed(quotaId, dayAnalyzed);
	}

	/**
	* Returns the number of quota daily logs.
	*
	* @return the number of quota daily logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static QuotaDailyLogPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (QuotaDailyLogPersistence)PortletBeanLocatorUtil.locate(org.lsug.quota.service.ClpSerializer.getServletContextName(),
					QuotaDailyLogPersistence.class.getName());

			ReferenceRegistry.registerReference(QuotaDailyLogUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(QuotaDailyLogPersistence persistence) {
	}

	private static QuotaDailyLogPersistence _persistence;
}