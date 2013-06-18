<%
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
%>

<%@ include file="/html/sites-quota/init.jsp" %>

<%
	Quota quota = (Quota) request.getAttribute( "quota" );
	String backURL = ParamUtil.getString(request, "backURL");
	long quotaId = BeanParamUtil.getLong(quota, request, "quotaId");
	long classPK = BeanParamUtil.getLong(quota, request, "classPK");

	BigDecimal bdAssignedGB = new BigDecimal((double)quota.getQuotaAssigned()/ (QuotaConstants.BYTES_TO_GB));

	double assignedGB = bdAssignedGB.setScale(3,BigDecimal.ROUND_UP).doubleValue();

	String quotaAssignedString = String.valueOf(assignedGB);
%>

<portlet:actionURL name="saveQuota" var="editQuotaURL" />

<liferay-ui:header backURL="<%= backURL %>" title="sites-quota.edit.title" />


<%-- TODO: show error messages --%>
<%-- <liferay-ui:error key="" message="" /> --%>

<aui:form action="<%= editQuotaURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= quotaId == 0 ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="quotaId" type="hidden" value="<%= quotaId %>" />
	<aui:input name="classPK" type="hidden" value="<%= classPK %>" />

	<%-- TODO: add the "quotaAlertStatus" in the service --%>

	<aui:input name="sites-quota.edit.assigned"  value="<%=quotaAssignedString%>" />

	<aui:input name="sites-quota.edit.alert" value="<%= quota.getQuotaAlert() %>" />

	<aui:input name="sites-quota.edit.status" type="checkbox" value="<%= quota.getQuotaStatus() == QuotaStatus.ACTIVE %>" />

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>