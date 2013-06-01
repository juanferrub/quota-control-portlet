package org.lsug.quota.server.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.lsug.quota.model.Quota;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import org.lsug.quota.model.QuotaStatus;

import java.text.NumberFormat;
import java.util.Locale;

public class ServerVO {

	private String active;

	private int alert;

	private long classPK;

	private String nameInstance;

	private int numUsers;

	private double quotaAssigned;

	private long quotaId;

	private double quotaUsed;

	public ServerVO(Quota q,Locale loc)
			throws SystemException, PortalException {
		Company c = CompanyLocalServiceUtil.getCompany(q.getClassPK());

		quotaId = q.getQuotaId();

		nameInstance = c.getName();
		numUsers = UserLocalServiceUtil.getCompanyUsersCount(c.getCompanyId());

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);

		quotaUsed = q.getQuotaUsed() / (1024 * 1024 * 1024);
		quotaAssigned = q.getQuotaAssigned() / (1024 * 1024 * 1024);

		if(q.getQuotaStatus() == QuotaStatus.ACTIVE) {
			active = LanguageUtil.get(loc,"enabled");
		}
		else {
			active = LanguageUtil.get(loc,"disabled");
		}

		alert = q.getQuotaAlert();
	}

	public String getActive() {
		return active;
	}

	public String getAlert() {
		return alert + " %";
	}


	public long getClassPK() {
		return classPK;
	}

	public String getNameInstance() {
		return nameInstance;
	}

	public int getNumUsers() {
		return numUsers;
	}

	public String getQuotaUsed() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);

		return nf.format(quotaUsed)+" GB";
	}

	public String getQuotaUsedPercent() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5);

		if(quotaAssigned == 0) {
			return "0 %";
		}

		return nf.format(quotaUsed / quotaAssigned)+ " %";
	}

	public long getQuotaId() {
		return quotaId;
	}

	public String getQuotaAssigned() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);

		return nf.format(quotaAssigned)+" GB";
	}
}
