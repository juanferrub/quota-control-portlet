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

package org.lsug.quota.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import org.lsug.quota.model.QuotaDailyLog;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing QuotaDailyLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see QuotaDailyLog
 * @generated
 */
public class QuotaDailyLogCacheModel implements CacheModel<QuotaDailyLog>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{quotaDailyLogId=");
		sb.append(quotaDailyLogId);
		sb.append(", dayAnalyzed=");
		sb.append(dayAnalyzed);
		sb.append(", quotaId=");
		sb.append(quotaId);
		sb.append(", quotaAssigned=");
		sb.append(quotaAssigned);
		sb.append(", quotaUsed=");
		sb.append(quotaUsed);
		sb.append(", quotaStatus=");
		sb.append(quotaStatus);
		sb.append("}");

		return sb.toString();
	}

	public QuotaDailyLog toEntityModel() {
		QuotaDailyLogImpl quotaDailyLogImpl = new QuotaDailyLogImpl();

		quotaDailyLogImpl.setQuotaDailyLogId(quotaDailyLogId);

		if (dayAnalyzed == Long.MIN_VALUE) {
			quotaDailyLogImpl.setDayAnalyzed(null);
		}
		else {
			quotaDailyLogImpl.setDayAnalyzed(new Date(dayAnalyzed));
		}

		quotaDailyLogImpl.setQuotaId(quotaId);
		quotaDailyLogImpl.setQuotaAssigned(quotaAssigned);
		quotaDailyLogImpl.setQuotaUsed(quotaUsed);
		quotaDailyLogImpl.setQuotaStatus(quotaStatus);

		quotaDailyLogImpl.resetOriginalValues();

		return quotaDailyLogImpl;
	}

	public long quotaDailyLogId;
	public long dayAnalyzed;
	public long quotaId;
	public long quotaAssigned;
	public long quotaUsed;
	public int quotaStatus;
}