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
%>


<liferay-ui:header	title="server-quota-history" backURL="<%= backURL %>" />

<%-- TODO: show error messages --%>
<%-- <liferay-ui:error key="" message="" /> --%>


<liferay-ui:tabs
   names="server-quota-history-graph,server-quota-history-log"
  refresh="<%= false %>">

	<liferay-ui:section>

		<div id="demoLineChart">
		</div>

		<script type="text/javascript" charset="utf-8">
		AUI().ready(
				'aui-chart',
				'datatype',
				'aui-delayed-task',
				function(A) {

					LineChart = {
						expenses: [
							{ month: 'Enero', perc: 0.00},
							{ month: 'Febrero', perc: 23.14},
							{ month: 'Marzo', perc: 80.00},
							{ month: 'Abril', perc: 78.00},
							{ month: 'Mayo', perc: 31.00}
						],
						series: [
							{displayName: '%', yField: 'perc'}
						],
						formatCurrencyAxisLabel: function(value) {
							return A.DataType.Number.format(
									value,
									{
										suffix: ' %',
										thousandsSeparator: ',',
										decimalPlaces: 2
									}
							);
						},

						getDataTipText: function(item, index, series) {
							var toolTipText = item.month;

							toolTipText += '\n' + LineChart.formatCurrencyAxisLabel(item[series.yField]);

							return toolTipText;
						},
						currencyAxis: (new A.Chart.NumericAxis())
					};

					LineChart.dataSource = new A.DataSource.Local({
								source: LineChart.expenses
							}
					).plug(
							{
								fn: A.DataSourceJSONSchema,
								cfg: {
									resultFields: ['month', 'rent']
								}
							}
					);

					LineChart.currencyAxis.labelFunction = LineChart.formatCurrencyAxisLabel;

					LineChart.chart = new A.LineChart(
							{
								dataSource: LineChart.dataSource,
								series: LineChart.series,
								xField: 'month',
								yAxis: LineChart.currencyAxis,
								width: '100%',
								stacked:true,
								height: 300,
								dataTipFunction: LineChart.getDataTipText
							}
					).render('#demoLineChart');


				}
		);

		</script>
	</liferay-ui:section>

	<liferay-ui:section>
		log table
	</liferay-ui:section>

  </liferay-ui:tabs>

