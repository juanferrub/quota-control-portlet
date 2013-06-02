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

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

import org.lsug.quota.NoSuchQuotaDailyLogException;
import org.lsug.quota.model.QuotaDailyLog;
import org.lsug.quota.service.base.QuotaDailyLogLocalServiceBaseImpl;

/**
 * The implementation of the quota daily log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.lsug.quota.service.QuotaDailyLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.lsug.quota.service.base.QuotaDailyLogLocalServiceBaseImpl
 * @see org.lsug.quota.service.QuotaDailyLogLocalServiceUtil
 */
public class QuotaDailyLogLocalServiceImpl
	extends QuotaDailyLogLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.lsug.quota.service.QuotaDailyLogLocalServiceUtil} to access the quota daily log local service.
	 */

	public QuotaDailyLog getQuotaLevelFromYesterday(long quotaId, Date day)
			throws SystemException, SystemException {
		QuotaDailyLog quotaDailyLog = null;
		try {
			quotaDailyLog = quotaDailyLogPersistence.findByQuotaIdDayAnalyzed(
				quotaId, day);
		} catch (NoSuchQuotaDailyLogException e) {
		}

		return quotaDailyLog;
	}
}