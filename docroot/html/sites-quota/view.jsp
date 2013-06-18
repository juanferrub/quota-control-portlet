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

<portlet:renderURL var="viewSitesQuotas" />

<c:if test="${not empty searchContainer}">
	<liferay-ui:search-container delta="${paramDelta}" emptyResultsMessage="empty.message" iteratorURL="${portletURL}" searchContainer="${searchContainer}">
		<liferay-ui:search-container-results results="${list}" total="${count}" />
		<liferay-ui:search-container-row className="org.lsug.quota.server.util.SiteQuotaVO"
										 escapedModel="<%= true %>" modelVar="siteVO">

			<liferay-ui:search-container-column-text name="name" value="${siteVO.siteName }" />

			<liferay-ui:search-container-column-text name="site-quota.num-users" value="${siteVO.numUsers }" />

			<liferay-ui:search-container-column-text name="site-quota.status" value="${siteVO.active }" />

			<liferay-ui:search-container-column-text name="site-quota.alarm-percent" value="${siteVO.alarm }" />

			<liferay-ui:search-container-column-text name="site-quota.assigned" value="${siteVO.assigned}" />

			<liferay-ui:search-container-column-text name="site-quota.used" value="${siteVO.used}" />

			<liferay-ui:search-container-column-text name="site-quota.percent" value="${siteVO.usedPercent}" />

			<liferay-ui:search-container-column-text>

				<liferay-ui:icon-menu>
					<portlet:renderURL var="editSiteURL">
						<portlet:param name="<%=Constants.CMD%>" value="<%=Constants.UPDATE%>" />
						<portlet:param name="backURL" value="${viewSitesQuotas}" />
						<portlet:param name="quotaId" value="${siteVO.quotaId }" />
					</portlet:renderURL>

					<portlet:renderURL var="showSiteHistoryURL">
						<portlet:param name="<%=Constants.CMD%>" value="<%=Constants.PREVIEW%>" />
						<portlet:param name="backURL" value="${viewSitesQuotas}" />
						<portlet:param name="quotaId" value="${siteVO.quotaId }" />
					</portlet:renderURL>

					<liferay-ui:icon image="edit" url="${editSiteURL}" />
					<liferay-ui:icon image="history" url="${showSiteHistoryURL}" />

				</liferay-ui:icon-menu>

			</liferay-ui:search-container-column-text>

		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator paginate="<%= true %>" searchContainer="${searchContainer}" />
	</liferay-ui:search-container>

</c:if>