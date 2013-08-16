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

<%@ include file="/html/sites-quota/init.jsp"%>


<liferay-portlet:renderURL var="portletURL">
	<portlet:param name="tabs2" value="${tabs2}" />
</liferay-portlet:renderURL>

<liferay-ui:tabs names="sites,sites-users" param="tabs2" url="${portletURL}"/>
			
<liferay-ui:search-container searchContainer="${searchContainer}">
	
	<liferay-ui:search-container-results results="${searchContainer.getResults()}" total="${searchContainer.getTotal()}" />
	
	<liferay-ui:search-container-row className="org.lsug.quota.model.Quota" keyProperty="quotaId" modelVar="quota">
			
	<liferay-ui:search-container-column-text name="site">

		<%= GroupLocalServiceUtil.getGroup(quota.getClassPK()).getDescriptiveName(locale) %>

	</liferay-ui:search-container-column-text>

	<liferay-ui:search-container-column-text name="quota-status"
				value="${quota.getQuotaStatus()}" />

	<liferay-ui:search-container-column-text name="quota-alert"
		value="${quota.getQuotaAlert()}" />

	<liferay-ui:search-container-column-text name="quota-assigned" 
		value="${quota.getQuotaAssigned()}" orderable="true" 
		orderableProperty="quotaAssigned" />

	<liferay-ui:search-container-column-text name="quota-used"
		value="${quota.getQuotaUsed()}" orderable="true"
		orderableProperty="quotaUsed" />

	<liferay-ui:search-container-column-text name="edit">
				
		<portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/html/sites-quota/edit.jsp" />
			<portlet:param name="quotaId" value="${quota.getQuotaId()}" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" url="${editURL}" />
				
	</liferay-ui:search-container-column-text>
			
	</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator />
			
</liferay-ui:search-container>

