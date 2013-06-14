package org.lsug.quota.server.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Locale;

import org.lsug.quota.model.Quota;
public class SiteQuotaVO extends QuotaBaseVO {

	public SiteQuotaVO(Quota q, Locale loc)
			throws PortalException, SystemException {
		Group group = GroupLocalServiceUtil.getGroup(q.getClassPK());

		setQuotaId(q.getQuotaId());
		setLoc(loc);
		setSiteName(group.getName());
		setNumUsers(UserLocalServiceUtil.getGroupUsersCount(group.getGroupId()));
		setUsed(q.getQuotaUsed());
		setAssigned(q.getQuotaAssigned());
		setActive(q.getQuotaStatus());
		setAlarm(q.getQuotaAlert());
	}

	public String getSiteName() {
		return _siteName;
	}

	public int getNumUsers() {
		return _numUsers;
	}

	public void setSiteName(String siteName) {
		_siteName = siteName;
	}

	public void setNumUsers(int numUsers) {
		_numUsers = numUsers;
	}

	private String _siteName;
	private int _numUsers;
}