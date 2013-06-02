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

<%@ include file="/html/server-quota/init.jsp" %>

<%
	Quota quota = (Quota) request.getAttribute( "quota" );
	String backURL = ParamUtil.getString(request, "backURL");
	String history =  (String) request.getAttribute( "history" ).toString();
%>

<liferay-ui:header	title="server-quota.history.title" backURL="<%= backURL %>" />

<%-- TODO: show error messages --%>
<%-- <liferay-ui:error key="" message="" /> --%>

<liferay-ui:tabs
   names="server-quota.history.history-percent,server-quota.history.history-usage,server-quota.history.history-log"
  refresh="<%= false %>">

	<liferay-ui:section>
		<%@ include file="quota_percent.jspf" %>
	</liferay-ui:section>
	<liferay-ui:section>
		<%@ include file="quota_usage.jspf" %>
	</liferay-ui:section>
	<liferay-ui:section>
		<%@ include file="quota_logs.jspf" %>
	</liferay-ui:section>

  </liferay-ui:tabs>

