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

import java.util.Locale;
import java.text.NumberFormat;

import com.liferay.portal.kernel.language.LanguageUtil;
import org.lsug.quota.model.QuotaStatus;

public class QuotaBaseVO  {

	public String getUsed() {
		NumberFormat nf = NumberFormat.getInstance(_loc);
		nf.setMaximumFractionDigits(3);

		return nf.format(_used)+" GB";
	}

	public String getUsedPercent() {
		NumberFormat nf = NumberFormat.getInstance(_loc);
		nf.setMaximumFractionDigits(5);

		if(_assigned == 0) {
			return "0 %";
		}

		return nf.format(_used / _assigned)+ " %";
	}

	public String getPercent() {
		NumberFormat nf = NumberFormat.getInstance(_loc);
		nf.setMaximumFractionDigits(5);

		if(_assigned == 0) {
			return "0";
		}

		return nf.format(_used / _assigned);
	}

	public long getQuotaId() {
		return _quotaId;
	}

	public String getAssigned() {
		NumberFormat nf = NumberFormat.getInstance(_loc);
		nf.setMaximumFractionDigits(3);

		return nf.format(_assigned)+" GB";
	}

	public String getActive() {
		if(_active) {
			return LanguageUtil.get(_loc,"enabled");
		}
		else {
			return LanguageUtil.get(_loc, "disabled");
		}
	}

	public String getAlarm() {
		return _alarm + " %";
	}

	public void setActive(int active) {
		_active = active == QuotaStatus.ACTIVE;
	}

	public void setAlarm(long alarm) {
		_alarm = alarm;
	}

	public void setAssigned(long assigned) {
		_assigned = ((double)assigned / (1024 * 1024 * 1024));
	}

	public void setLoc(Locale loc) {
		_loc = loc;
	}

	public void setQuotaId(long quotaId) {
		_quotaId = quotaId;
	}

	public void setUsed(long used) {
		_used = ((double)used / (1024 * 1024 * 1024));
	}

	private boolean _active;

	private long _alarm;

	private double _assigned;

	private long _quotaId;

	private Locale _loc;

	private double _used;

}
