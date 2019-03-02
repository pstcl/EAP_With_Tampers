<%@page import="org.pstcl.ea.util.EAUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">



<%-- <c:url value='/previewOilReport-${oilReport.id}' /> --%>


<title>Loss Report</title>
</head>

<body onload="myFunction()" style="margin: 0;">
	<%@include file="authheader.jsp"%>



	<nav class="navbar navbar-expand-lg navbar-light bg-faded ">

		<div id="navbarNavAltMarkup" class="navbar-collapse collapse">


			<ul class="navbar-nav  mr-auto">

				<li><a class="nav-link"
					href="javascript:window.location='getLossReport?month=${monthOfReport}&year=${yearOfReport}'"><span
					class="glyphicon glyphicon-plus"></span>Consolidated Loss Report</a>

				</li><li><a class="nav-link"
					href="javascript:window.location='getReport?type=<%=EAUtil.LOSS_REPORT_CRITERIA_G_T%>&month=${monthOfReport}&year=${yearOfReport}'"><span
					class="glyphicon glyphicon-plus"></span>Report (G-T)</a>


				</li><li><a class="nav-link"
					href="javascript:window.location='getReport?type=<%=EAUtil.LOSS_REPORT_CRITERIA_I_T_%>&month=${monthOfReport}&year=${yearOfReport}'"><span
					class="glyphicon glyphicon-plus"></span>Report (I-T)</a>

				</li><li><a class="nav-link"
					href="javascript:window.location='getReport?type=<%=EAUtil.LOSS_REPORT_CRITERIA_T_D_220_66_%>&month=${monthOfReport}&year=${yearOfReport}'"><span
					class="glyphicon glyphicon-plus"></span>Report (T-D)(220/66)</a>


				</li><li><a class="nav-link"
					href="javascript:window.location='getReport?type=<%=EAUtil.LOSS_REPORT_CRITERIA_T_D_132_11_%>&month=${monthOfReport}&year=${yearOfReport}'"><span
					class="glyphicon glyphicon-plus"></span>Report (T-D)(132/11)</a>

				</li><li><a class="nav-link"
					href="javascript:window.location='getReport?type=<%=EAUtil.LOSS_REPORT_CRITERIA_T_D_132_33_%>&month=${monthOfReport}&year=${yearOfReport}'"><span
					class="glyphicon glyphicon-plus"></span>Report (T-D)(132/33)</a>

				</li><li><a class="nav-link"
					href="javascript:window.location='getReport?type=<%=EAUtil.LOSS_REPORT_CRITERIA_T_D_132_66%>&month=${monthOfReport}&year=${yearOfReport}'"><span
					class="glyphicon glyphicon-plus"></span>Report (T-D)(132/66)</a>

				</li><li><a class="nav-link"
					href="javascript:window.location='getReport?type=<%=EAUtil.LOSS_REPORT_CRITERIA_INDEPENDENT_%>&month=${monthOfReport}&year=${yearOfReport}'"><span
					class="glyphicon glyphicon-plus"></span>Report (T-D)(Independent)</a>


			</ul>
		</div>


	</nav>


	<%@include file="dataTablesHeader.jsp"%>



	<script type="text/javascript">
		$(document).ready(function() {
			$('#dailyTxnTable').DataTable({
				dom : 'Bfrtip',
				"paging" : false,
				"ordering" : false,
				buttons : [ 'copyHtml5', 'excelHtml5', 'csvHtml5', {
					extend : 'pdfHtml5',
					orientation : 'landscape',
					pageSize : 'LEGAL'
				} ],
				orientation : 'landscape',
				pageSize : 'A4'
			});

		});
	</script>

	<div class="container-fluid">


		<span class="lead">Report Data for ${lossReportModel.criteria}
			for the month of <fmt:formatDate value="${reportMonthYearDate}"
				pattern="MMM,yyyy" />
		</span>
		<div class="card"></div>
		<div id="da111ilyTxnTable_wrapper" class="table-responsive ">


			<table id="dailyTxnTable"
				class="table table-striped table-bordered table-hover">

				<thead>
					<tr>


						<th>Serial No</th>
						<th >Location Id</th>
						<th>Utility Name</th>
						<th>Boundary Type</th>
						<th>Station Name</th>
						<th>Line/Transformer Name</th>
						<th>Voltage Level</th>
						<th>Meter Serial No.</th>
						<th>Meter Category</th>
						<th>External MF</th>
						<th>Sign</th>
						<th style="align-content: right;" >Import Wh</th>


						<th style="align-content: flex-end;">Export Wh</th>


						<th>Energy Imported at Boundary point (MWh)</th>
						<th>Energy Exported at Boundary point (MWh)</th>


						<th>Difference bw Import and Export (MWh)</th>
						<th>Net MWh=(Export-Import)*MF/1000000</th>

						<th width="100">Remarks</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lossReportModel.lossReportEntities}"
						var="lossReportEntity" varStatus="indexStatus">
						



							<c:choose>
								<c:when test="${ lossReportEntity.location != null}">

									<c:choose>
										<c:when
											test="${ lossReportEntity.exportWHFCount < lossReportEntity.daysInMonthCount}">
											<tr class="bg-warning">
											<td ><i class="fa fa-warning"
												style="font-size: 18px; color: red;"> 

												${lossReportEntity.location.lossReportOrder}
												</i>
												</td>
										</c:when>
										<c:otherwise>
											<tr><td>${lossReportEntity.location.lossReportOrder}</td>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
	<tr>								<td></td>
								</c:otherwise>
							</c:choose>
							<td><a
								href="javascript:window.location='getReportTransactions-${lossReportEntity.location.locationId}?month=${monthOfReport}&year=${yearOfReport}'">

									${lossReportEntity.location.locationId} </a> <c:if
									test="${ lossReportEntity.location == null}">
									TOTAL
									</c:if></td>



							<td>${lossReportEntity.location.utiltiyName}</td>
							<td>${lossReportEntity.location.boundaryTypeMaster.boundaryType}</td>
							<td>${lossReportEntity.location.substationMaster.stationName}</td>
							<td>${lossReportEntity.location.feederMaster.feederName}</td>
							<td>${lossReportEntity.location.voltageLevel}</td>
							<td>${lossReportEntity.location.meterMaster.meterSrNo}</td>

							<td>${lossReportEntity.location.meterMaster.meterCategory}</td>
							<td>${lossReportEntity.location.externalMF}</td>
							<td>${lossReportEntity.location.netWHSign}</td>


							<th style="text-align:right">${lossReportEntity.importWHFSum}</th>
							<th style="text-align:right">${lossReportEntity.exportWHFSum}</th>

							<th style="text-align:right">${lossReportEntity.importBoundaryPtMWH}</th>
							<th style="text-align:right">${lossReportEntity.exportBoundaryPtMWH}</th>

							<td align="right">${lossReportEntity.boundaryPtImportExportDifferenceMWH}</td>
							<td align="right">${lossReportEntity.netMWH}</td>

							<td>No of Days of Export
								Data:${lossReportEntity.exportWHFCount} No of Days of Import
								Data:${lossReportEntity.importWHFCount} No of Days in the Month
								Data:${lossReportEntity.daysInMonthCount}</td>

						</tr>


					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>





</body>
</html>