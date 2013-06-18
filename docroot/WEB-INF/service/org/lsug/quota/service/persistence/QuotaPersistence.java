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

import org.lsug.quota.model.Quota;

/**
 * The persistence interface for the quota service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuotaPersistenceImpl
 * @see QuotaUtil
 * @generated
 */
public interface QuotaPersistence extends BasePersistence<Quota> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QuotaUtil} to access the quota persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the quota in the entity cache if it is enabled.
	*
	* @param quota the quota
	*/
	public void cacheResult(org.lsug.quota.model.Quota quota);

	/**
	* Caches the quotas in the entity cache if it is enabled.
	*
	* @param quotas the quotas
	*/
	public void cacheResult(java.util.List<org.lsug.quota.model.Quota> quotas);

	/**
	* Creates a new quota with the primary key. Does not add the quota to the database.
	*
	* @param quotaId the primary key for the new quota
	* @return the new quota
	*/
	public org.lsug.quota.model.Quota create(long quotaId);

	/**
	* Removes the quota with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param quotaId the primary key of the quota
	* @return the quota that was removed
	* @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota remove(long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	public org.lsug.quota.model.Quota updateImpl(
		org.lsug.quota.model.Quota quota, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the quota with the primary key or throws a {@link org.lsug.quota.NoSuchQuotaException} if it could not be found.
	*
	* @param quotaId the primary key of the quota
	* @return the quota
	* @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota findByPrimaryKey(long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Returns the quota with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param quotaId the primary key of the quota
	* @return the quota, or <code>null</code> if a quota with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota fetchByPrimaryKey(long quotaId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the quota where classNameId = &#63; and classPK = &#63; or throws a {@link org.lsug.quota.NoSuchQuotaException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching quota
	* @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota findByClassNameIdClassPK(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Returns the quota where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching quota, or <code>null</code> if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota fetchByClassNameIdClassPK(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the quota where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching quota, or <code>null</code> if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota fetchByClassNameIdClassPK(
		long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the quotas where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @return the matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findByClassNameId(
		long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the quotas where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param start the lower bound of the range of quotas
	* @param end the upper bound of the range of quotas (not inclusive)
	* @return the range of matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findByClassNameId(
		long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the quotas where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param start the lower bound of the range of quotas
	* @param end the upper bound of the range of quotas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findByClassNameId(
		long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first quota in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching quota
	* @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota findByClassNameId_First(
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Returns the first quota in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching quota, or <code>null</code> if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota fetchByClassNameId_First(
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last quota in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching quota
	* @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota findByClassNameId_Last(long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Returns the last quota in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching quota, or <code>null</code> if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota fetchByClassNameId_Last(
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the quotas before and after the current quota in the ordered set where classNameId = &#63;.
	*
	* @param quotaId the primary key of the current quota
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next quota
	* @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota[] findByClassNameId_PrevAndNext(
		long quotaId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Returns all the quotas where parentQuotaId = &#63;.
	*
	* @param parentQuotaId the parent quota ID
	* @return the matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findByParentQuotaId(
		long parentQuotaId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the quotas where parentQuotaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentQuotaId the parent quota ID
	* @param start the lower bound of the range of quotas
	* @param end the upper bound of the range of quotas (not inclusive)
	* @return the range of matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findByParentQuotaId(
		long parentQuotaId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the quotas where parentQuotaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentQuotaId the parent quota ID
	* @param start the lower bound of the range of quotas
	* @param end the upper bound of the range of quotas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findByParentQuotaId(
		long parentQuotaId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first quota in the ordered set where parentQuotaId = &#63;.
	*
	* @param parentQuotaId the parent quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching quota
	* @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota findByParentQuotaId_First(
		long parentQuotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Returns the first quota in the ordered set where parentQuotaId = &#63;.
	*
	* @param parentQuotaId the parent quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching quota, or <code>null</code> if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota fetchByParentQuotaId_First(
		long parentQuotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last quota in the ordered set where parentQuotaId = &#63;.
	*
	* @param parentQuotaId the parent quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching quota
	* @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota findByParentQuotaId_Last(
		long parentQuotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Returns the last quota in the ordered set where parentQuotaId = &#63;.
	*
	* @param parentQuotaId the parent quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching quota, or <code>null</code> if a matching quota could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota fetchByParentQuotaId_Last(
		long parentQuotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the quotas before and after the current quota in the ordered set where parentQuotaId = &#63;.
	*
	* @param quotaId the primary key of the current quota
	* @param parentQuotaId the parent quota ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next quota
	* @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota[] findByParentQuotaId_PrevAndNext(
		long quotaId, long parentQuotaId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Returns all the quotas.
	*
	* @return the quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the quotas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of quotas
	* @param end the upper bound of the range of quotas (not inclusive)
	* @return the range of quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findAll(int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the quotas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of quotas
	* @param end the upper bound of the range of quotas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of quotas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.lsug.quota.model.Quota> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the quota where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the quota that was removed
	* @throws SystemException if a system exception occurred
	*/
	public org.lsug.quota.model.Quota removeByClassNameIdClassPK(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.lsug.quota.NoSuchQuotaException;

	/**
	* Removes all the quotas where classNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByClassNameId(long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the quotas where parentQuotaId = &#63; from the database.
	*
	* @param parentQuotaId the parent quota ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByParentQuotaId(long parentQuotaId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the quotas from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of quotas where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public int countByClassNameIdClassPK(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of quotas where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @return the number of matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public int countByClassNameId(long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of quotas where parentQuotaId = &#63;.
	*
	* @param parentQuotaId the parent quota ID
	* @return the number of matching quotas
	* @throws SystemException if a system exception occurred
	*/
	public int countByParentQuotaId(long parentQuotaId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of quotas.
	*
	* @return the number of quotas
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}