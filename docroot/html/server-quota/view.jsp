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

<%@ include file="/html/init.jsp"%>

<portlet:renderURL  var="viewServerQuotas"/>

<c:if test="${not empty searchContainer}">
	<liferay-ui:search-container searchContainer="${searchContainer}" delta="${paramDelta}" emptyResultsMessage="empty.message" iteratorURL="${portletURL}">
	<liferay-ui:search-container-results results="${list}" total="${count}" />
	<liferay-ui:search-container-row className="org.lsug.quota.server.util.ServerVO" 
		escapedModel="<%= true %>" modelVar="serverVO">

	<liferay-ui:search-container-column-text name="name" value="${serverVO.nameInstance }" />

	<liferay-ui:search-container-column-text name="numUsers" value="${serverVO.numUsers }" />
	<liferay-ui:search-container-column-text name="quota-status" value="${serverVO.active }" />

	<liferay-ui:search-container-column-text name="quota-alert" value="${serverVO.alert }" />

	<liferay-ui:search-container-column-text name="quota-assigned" value="${serverVO.quotaAssigned}" />

	<liferay-ui:search-container-column-text name="quota-used" value="${serverVO.quotaUsed}" />

	<liferay-ui:search-container-column-text name="quota-used-percent" value="${serverVO.quotaUsedPercent}" />

	<liferay-ui:search-container-column-text>

		<liferay-ui:icon-menu>
			<portlet:renderURL var="editServerURL">
				<portlet:param name="<%=Constants.CMD%>" value="<%=Constants.UPDATE%>"/>
				<portlet:param name="backURL" value="${viewServerQuotas}"/>
				<portlet:param name="quotaId" value="${serverVO.quotaId }"/>
				<portlet:param name="classPK" value="${serverVO.classPK }"/>
			</portlet:renderURL>

			<portlet:renderURL var="showServerHistoryURL">
				<portlet:param name="<%=Constants.CMD%>" value="<%=Constants.PREVIEW%>"/>
				<portlet:param name="backURL" value="${viewServerQuotas}"/>
				<portlet:param name="quotaId" value="${serverVO.quotaId }"/>
				<portlet:param name="classPK" value="${serverVO.classPK }"/>
			</portlet:renderURL>

			<liferay-ui:icon image="edit" url="${editServerURL}" />
			<liferay-ui:icon image="history" url="${showServerHistoryURL}" />

		</liferay-ui:icon-menu>

	</liferay-ui:search-container-column-text> 

	</liferay-ui:search-container-row>
		 <liferay-ui:search-iterator searchContainer="${searchContainer}" paginate="<%= true %>"/>
	</liferay-ui:search-container>

</c:if>