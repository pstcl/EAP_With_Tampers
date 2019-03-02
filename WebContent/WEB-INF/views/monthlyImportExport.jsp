<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<title>Energy Meters</title>
</head>

<body onload="myFunction()" style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<%@include file="dataTablesHeader.jsp"%>

	<div class="sticky-top">
		<nav aria-label="breadcrumb" class="sticky-top">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="javascript:window.location='home'">Home</a></li>

				<li class="breadcrumb-item active" aria-current="page">Energy
					Import Export</li>
			</ol>
		</nav>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {

			$('.modal-content').resizable({
				//alsoResize: ".modal-dialog",
				minHeight : 300,
				minWidth : 600
			});
			$('.modal-dialog').draggable();
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#monthlyTxnTableForExport').DataTable({
				dom : 'Bfrtip',
				buttons : [ 'copyHtml5', 'excelHtml5', 'csvHtml5', 'pdfHtml5' ],
				"paging": false,
				 "searching": false,
				 "ordering": false,
			        "info":     false
			});

		});
	</script>

	<div class="container-fluid">
		<c:if test="${not empty success}">
			<div class="alert alert-success lead">${success}</div>
		</c:if>
		<span class="lead">Energy Meters Master Data</span>


		<c:set var="colspanval" scope="page"
			value="${(2*fn:length(monthlyDataModel.locationList))+1}" />
		

		<table id="monthlyTxnTableForExport" style="width: 900px;"
			class="table table-striped table-bordered table-hover" hidden="true">

			<thead>

				<tr>
					<th style="align-content: center;">Monthly Log Sheet</th>


					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>

				</tr>

			</thead>
			<tbody>
				<tr>
					<th>Notes:</th>


					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>1. Meter readings should be noted as per its display and
						should not be multiplied by any factor.</th>


					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>2. Any change in CTR during the month along with date and
						time should be noted in the register.</th>

					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>3. PT fuse failure date and timings should be noted.</th>

					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>4. Any anomaly observed concerning the meter/ meter
						readings should be noted.</th>

					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>5. Import Wh (F) and Export Wh (F) readings shall be
						noted down at 00:00 hrs of the first day of month and 24:00 hrs of
						the last day of the month.</th>

					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>

				<tr>
					<td style="width: 150px;">Name of the Substation:</td>
					<td>${monthlyDataModel.substationMaster.stationName}</td>
					<td>Month:</td>
					<td><fmt:formatDate value="${monthlyDataModel.dateForDisplay}"
							pattern="MMMM" /></td>
					<td>Year:</td>
					<td>${monthlyDataModel.year}</td>
					<td></td>



				</tr>
				<tr>
					<td style="width: 150px;">Circle:</td>
					<td>${monthlyDataModel.substationMaster.circleMaster.circleName}</td>
					<td>Division:</td>
					<td>${monthlyDataModel.substationMaster.divisionMaster.divisionname}</td>
					<td>Contact No. of concerned:</td>
					<td>${monthlyDataModel.substationMaster.substationContactNo}</td>
					<td></td>
				<tr>
					<td style="width: 150px;">Name of the Feeder/Transformer</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${meterLocation.feederMaster.feederName}</td>
						<td></td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Meter Code</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${meterLocation.locationId}</td>.
							<td></td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Meter Sr. No.</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${meterLocation.meterMaster.meterSrNo}</td>
						<td></td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Meter Type</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${meterLocation.meterMaster.meterType}</td>
						<td></td>
					</c:forEach>
				</tr>

				<tr>
					<td style="width: 150px;">Connected CTR at the beginning of
						the month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.montlhyTxns[meterLocation].connectedCTRStart}</td>
						<td></td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Import Wh (F) reading at 00:00 hrs
						of first day of month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.montlhyTxns[meterLocation].importReadingStart}</td>
						<td></td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Export Wh (F) reading at 00:00 hrs
						of first day of month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.montlhyTxns[meterLocation].exportReadingStart}</td>
						<td></td>
					</c:forEach>
				</tr>

				<tr>
					<td style="width: 150px;">Day</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>P Day Import</td>
						<td>P Day Export</td>
					</c:forEach>
				</tr>


				<c:forEach var="i" begin="1"
					end="${monthlyDataModel.lastDateOfMonth}">
					<tr>
						<td>${i}</td>
						<c:forEach items="${monthlyDataModel.locationList}"
							var="meterLocation" varStatus="indexStatus">
							<td>${monthlyDataModel.dailyDataForSS[i].dailyTransactionForMonth[meterLocation].importWHF}</td>
							<td>${monthlyDataModel.dailyDataForSS[i].dailyTransactionForMonth[meterLocation].exportWHF}</td>
						</c:forEach>
					</tr>
				</c:forEach>
				<tr>
					<td style="width: 150px;">Import Wh (F) reading at 24:00 hrs
						of last day of month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.montlhyTxns[meterLocation].importReadingEnd}</td>
						<td></td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Export Wh (F) reading at 24:00 hrs
						of first day of month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.montlhyTxns[meterLocation].exportReadingEnd}</td>
						<td></td>
					</c:forEach>
				</tr>

				<tr>
					<td style="width: 150px;">Difference in import Wh (F) at the
						beginning and end of the month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.montlhyTxns[meterLocation].netImport}</td>

						<td></td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Difference in export Wh (F) at the
						beginning and end of the month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.montlhyTxns[meterLocation].netExport}</td>
						<td></td>
					</c:forEach>
				</tr>

				<tr>
					<td style="width: 150px;">Previous month Cumulative Import Wh
					</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.previousMonthTxns[meterLocation].netImport}</td>
						<td></td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Previous month Cumulative export Wh
					</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>${monthlyDataModel.previousMonthTxns[meterLocation].netExport}</td>
						<td></td>
					</c:forEach>


				</tr>
			</tbody>
		</table>


		<table id="monthlyTxnTable" style="width: 900px;"
			class="table table-striped table-bordered table-hover">

			<thead>
			</thead>
			<tbody>
				<tr>
					<th colspan="${colspanval}" style="align-content: center;">Monthly
						Log Sheet</th>
				</tr>
				<tr>
					<th colspan="${colspanval}">Notes:</th>
				</tr>
				<tr>
					<th colspan="${colspanval}">1. Meter readings should be noted
						as per its display and should not be multiplied by any factor.</th>
				</tr>
				<tr>
					<th colspan="${colspanval}">2. Any change in CTR during the
						month along with date and time should be noted in the register.</th>
				</tr>
				<tr>
					<th colspan="${colspanval}">3. PT fuse failure date and
						timings should be noted.</th>
				</tr>
				<tr>
					<th colspan="${colspanval}">4. Any anomaly observed concerning
						the meter/ meter readings should be noted.</th>
				</tr>
				<tr>
					<th colspan="${colspanval}">5. Import Wh (F) and Export Wh (F)
						readings shall be noted down at 00:00 hrs of the first day of
						month and 24:00 hrs of the last day of the month.</th>
				</tr>

				<tr>
					<td style="width: 150px;">Name of the Substation:</td>
					<td colspan="2">${monthlyDataModel.substationMaster.stationName}</td>
					<td>Month:</td>
					<td><fmt:formatDate value="${monthlyDataModel.dateForDisplay}"
							pattern="MMMM" /></td>
					<td>Year:</td>
					<td>${monthlyDataModel.year}</td>



				</tr>
				<tr>
					<td style="width: 150px;">Circle:</td>
					<td colspan="2">${monthlyDataModel.substationMaster.circleMaster.circleName}</td>
					<td>Division:</td>
					<td>${monthlyDataModel.substationMaster.divisionMaster.divisionname}</td>
					<td>Contact No. of concerned:</td>
					<td>${monthlyDataModel.substationMaster.substationContactNo}</td>
				<tr>
					<td style="width: 150px;">Name of the Feeder/Transformer</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${meterLocation.feederMaster.feederName}</td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Meter Code</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${meterLocation.locationId}</td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Meter Sr. No.</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${meterLocation.meterMaster.meterSrNo}</td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Meter Type</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${meterLocation.meterMaster.meterType}</td>
					</c:forEach>
				</tr>

				<tr>
					<td style="width: 150px;">Connected CTR at the beginning of
						the month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.montlhyTxns[meterLocation].connectedCTRStart}</td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Import Wh (F) reading at 00:00 hrs
						of first day of month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.montlhyTxns[meterLocation].importReadingStart}</td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Export Wh (F) reading at 00:00 hrs
						of first day of month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.montlhyTxns[meterLocation].exportReadingStart}</td>
					</c:forEach>
				</tr>

<tr>
					<td style="width: 150px;">Day</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td>P Day Import</td>
						<td>P Day Export</td>
					</c:forEach>
				</tr>

				<c:forEach var="i" begin="1"
					end="${monthlyDataModel.lastDateOfMonth}">
					<tr>
						<td>${i}</td>
						<c:forEach items="${monthlyDataModel.locationList}"
							var="meterLocation" varStatus="indexStatus">
							<td>${monthlyDataModel.dailyDataForSS[i].dailyTransactionForMonth[meterLocation].importWHF}</td>
							<td>${monthlyDataModel.dailyDataForSS[i].dailyTransactionForMonth[meterLocation].exportWHF}</td>
						</c:forEach>
					</tr>
				</c:forEach>
				<tr>
					<td style="width: 150px;">Import Wh (F) reading at 24:00 hrs
						of last day of month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.montlhyTxns[meterLocation].importReadingEnd}</td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Export Wh (F) reading at 24:00 hrs
						of first day of month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.montlhyTxns[meterLocation].exportReadingEnd}</td>
					</c:forEach>
				</tr>

				<tr>
					<td style="width: 150px;">Difference in import Wh (F) at the
						beginning and end of the month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.montlhyTxns[meterLocation].netImport}</td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Difference in export Wh (F) at the
						beginning and end of the month</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.montlhyTxns[meterLocation].netExport}</td>
					</c:forEach>
				</tr>

				<tr>
					<td style="width: 150px;">Previous month Cumulative Import Wh
					</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.previousMonthTxns[meterLocation].netImport}</td>
					</c:forEach>
				</tr>
				<tr>
					<td style="width: 150px;">Previous month Cumulative export Wh
					</td>
					<c:forEach items="${monthlyDataModel.locationList}"
						var="meterLocation" varStatus="indexStatus">
						<td colspan="2">${monthlyDataModel.previousMonthTxns[meterLocation].netExport}</td>
					</c:forEach>
				</tr>
			</tbody>
		</table>



	</div>
</body>
</html>