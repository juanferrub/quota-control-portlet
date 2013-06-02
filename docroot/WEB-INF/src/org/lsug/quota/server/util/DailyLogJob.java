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

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import java.util.Date;
import java.util.List;

import org.lsug.quota.model.Quota;
import org.lsug.quota.model.QuotaDailyLog;
import org.lsug.quota.service.QuotaDailyLogLocalServiceUtil;
import org.lsug.quota.service.QuotaLocalServiceUtil;
import org.lsug.quota.util.QuotaUtil;
public class DailyLogJob implements MessageListener {

	@Override
	public void receive(Message arg0) throws MessageListenerException {
		List<Quota> quotaList = null;
		try {
			Date dayToAnalyze = QuotaUtil.removeTime(new Date());

			quotaList = QuotaLocalServiceUtil.getQuotas(-1, -1);

			for (Quota q : quotaList) {
				QuotaDailyLog quotaLog =
					QuotaDailyLogLocalServiceUtil.getQuotaLevelFromYesterday(
						q.getQuotaId(), dayToAnalyze);

				if (quotaLog == null) {
					analyzeQuota(q, dayToAnalyze);
				}
			}
		} catch (SystemException e) {
			_log.error(e);
		}
	}

	private void analyzeQuota(Quota q, Date dayToAnalyze) {
		try {
			long quotaLogNextId = CounterLocalServiceUtil.increment(
				QuotaDailyLog.class.getName());

			QuotaDailyLog quotaLogForYesterday = QuotaDailyLogLocalServiceUtil.
				createQuotaDailyLog(quotaLogNextId);
			quotaLogForYesterday.setQuotaId(q.getQuotaId());
			quotaLogForYesterday.setDayAnalyzed(dayToAnalyze);
			quotaLogForYesterday.setQuotaAssigned(q.getQuotaAssigned());
			quotaLogForYesterday.setQuotaStatus(q.getQuotaStatus());
			quotaLogForYesterday.setQuotaUsed(q.getQuotaUsed());

			QuotaDailyLogLocalServiceUtil.addQuotaDailyLog(
				quotaLogForYesterday);
		} catch (SystemException e) {
			_log.error(e);
		}
	}

	Log _log = LogFactoryUtil.getLog(DailyLogJob.class.getName());
}