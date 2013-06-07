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

package org.lsug.quota.service.impl;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import org.lsug.quota.NoSuchQuotaException;
import org.lsug.quota.QuotaExceededException;
import org.lsug.quota.model.Quota;
import org.lsug.quota.model.QuotaStatus;
import org.lsug.quota.service.QuotaLocalServiceUtil;
import org.lsug.quota.service.base.QuotaLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the quota local service. <p> All custom service methods
 * should be put in this class. Whenever methods are added, rerun ServiceBuilder
 * to copy their definitions into the
 * {@link org.lsug.quota.service.QuotaLocalService} interface. <p> This is a
 * local service. Methods of this service will not have security checks based on
 * the propagated JAAS credentials because this service can only be accessed
 * from within the same VM. </p>
 * 
 * @author Brian Wing Shun Chan
 * @see org.lsug.quota.service.base.QuotaLocalServiceBaseImpl
 * @see org.lsug.quota.service.QuotaLocalServiceUtil
 */
public class QuotaLocalServiceImpl extends QuotaLocalServiceBaseImpl {

	public Quota addQuota(
			long classNameId, long classPK, int quotaAlert, long quotaAssigned,
			long quotaUsed, int quotaStatus)
		throws SystemException {

		long quotaId = counterLocalService.increment(Quota.class.getName());
		Quota quota = quotaPersistence.create(quotaId);

		quota.setClassNameId(classNameId);
		quota.setClassPK(classPK);
		quota.setQuotaAlert(quotaAlert);
		quota.setQuotaAssigned(quotaAssigned);
		quota.setQuotaUsed(quotaUsed);
		quota.setQuotaStatus(quotaStatus);

		if (classNameId == PortalUtil.getClassNameId(Company.class)) {
			quota.setParentQuotaId(0);
		}
		else if (classNameId == PortalUtil.getClassNameId(Group.class)) {
			try {
				Group group = GroupLocalServiceUtil.getGroup(classPK);
				Quota companyQuota = getCompanyQuota(group.getCompanyId());
				quota.setParentQuotaId(companyQuota.getQuotaId());
			}
			catch (PortalException e) {
			}
		}

		return addQuota(quota);
	}

	public boolean checkAlerts(long groupId, long userId)
			throws PortalException, SystemException {

		final Group group = GroupLocalServiceUtil.getGroup(groupId);

		Quota groupQuota = getGroupQuota(groupId);
		Quota companyQuota = getCompanyQuota(groupId);

		return groupQuota.isExceeded() || companyQuota.isExceeded();
	}

	public Quota getCompanyQuota(long companyId) throws SystemException {
		Quota quota = null;

		try {
			quota = getQuotaByClassNameIdClassPK(
					PortalUtil.getClassNameId(Company.class), companyId);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return quota;
	}

	public Quota getGroupQuota(long groupId)  {
		Quota quota = null;

		try {
			quota = getQuotaByClassNameIdClassPK(
					PortalUtil.getClassNameId(Group.class), groupId);
		} catch (SystemException e) {
			e.printStackTrace();
		}

		return quota;
	}

	public Quota getQuotaByClassNameIdClassPK(
		final long classNameId, final long classPK)
		throws SystemException {

		try {
			return getQuotaPersistence().findByClassNameIdClassPK(
				classNameId, classPK);
		} catch (NoSuchQuotaException e) {
			long quotaUsed = getInitialDiskUsage(classNameId,classPK);
			return addQuota(classNameId,classPK,0,0,quotaUsed,0);
		}
	}

	public long getDLFileEntryTotalSize(long dlFileEntryId)
			throws SystemException {
		long val = 0;
		int status = 0;
		//FIXME create a finder
		List<DLFileVersion> dlFileVersionList = DLFileVersionLocalServiceUtil.getFileVersions(dlFileEntryId,status);
		for(DLFileVersion dlFileVersion : dlFileVersionList) {
			val +=  dlFileVersion.getSize();
		}

		return val;
	}

	public List<Quota> getSitesQuotas(long companyId,int start,int end) throws SystemException {
		List<Quota> quotaList = new ArrayList<Quota>();

		Quota companyQuota = getCompanyQuota(companyId);
		List<Quota> result =
				quotaPersistence.findByParentQuotaId(
						companyQuota.getQuotaId(),start,end);

		return result;
	}

	public List<Quota> getSitesQuotas(
			long companyId, int start, int end, OrderByComparator orderByComparator)
			throws PortalException, SystemException {

		Quota companyQuota = getCompanyQuota(companyId);
		List<Quota> result =
			quotaPersistence.findByParentQuotaId(
				companyQuota.getQuotaId(),start,end);

		return result;
	}

	public boolean hasQuota(long groupId, long userId, long size)
			throws PortalException, SystemException {

		final Group group = GroupLocalServiceUtil.getGroup(groupId);

		Quota groupQuota = getGroupQuota(groupId);
		Quota companyQuota = getCompanyQuota(groupId);

		return groupQuota.hasFreeMB(size) && companyQuota.hasFreeMB(size);
	}

	public void decreaseQuotaUsage(long groupId, long userId, long size)
			throws PortalException, SystemException, NoSuchQuotaException,
			QuotaExceededException {

		final Group group = GroupLocalServiceUtil.getGroup(groupId);

		updateQuota(
			PortalUtil.getClassNameId(Group.class),
			group.getGroupId(), -size);

		updateQuota(
			PortalUtil.getClassNameId(Company.class),
			group.getCompanyId(), -size);
	}

	public void increaseQuotaUsage(long groupId, long userId, long size)
			throws PortalException, SystemException, NoSuchQuotaException,
			QuotaExceededException {

		final Group group = GroupLocalServiceUtil.getGroup(groupId);

		updateQuota(
				PortalUtil.getClassNameId(Group.class),
				group.getGroupId(), size);

		updateQuota(
				PortalUtil.getClassNameId(Company.class),
				group.getCompanyId(), size);
	}

	public Quota updateQuota(
			final long classNameId, final long classPK, final long fileSize)
			throws NoSuchQuotaException, SystemException {

		Quota quota = getQuotaByClassNameIdClassPK(classNameId, classPK);

		quota.setQuotaUsed(quota.getQuotaUsed() + fileSize);

		return updateQuota(quota);
	}

	public Quota updateQuota(
			long quotaId, long classNameId, long classPK, int quotaAlert,
			long quotaAssigned, long quotaUsed, int quotaStatus)
			throws NoSuchQuotaException, SystemException {

		Quota quota = quotaPersistence.fetchByPrimaryKey(quotaId);

		quota.setClassNameId(classNameId);
		quota.setClassPK(classPK);
		quota.setQuotaAlert(quotaAlert);
		quota.setQuotaAssigned(quotaAssigned);
		quota.setQuotaUsed(quotaUsed);
		quota.setQuotaStatus(QuotaStatus.INACTIVE);

		return quotaPersistence.update(quota, false);
	}

	private long getInitialDiskUsage(long classNameId,long classPk) throws SystemException {
		long size = 0;

		String filter;
		if (PortalUtil.getClassNameId(Company.class) == classNameId) {
			filter = "companyId";
		}
		else {
			filter = "groupId";
		}
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
				DLFileVersion.class);
		dynamicQuery.add(
			PropertyFactoryUtil.forName(filter).eq(classPk));

		List<DLFileVersion> dlFileVersionList =
			DLFileVersionLocalServiceUtil.dynamicQuery(dynamicQuery);

		for(DLFileVersion dlFileVersion : dlFileVersionList) {
			size += dlFileVersion.getSize();
		}

		return size;
	}

}