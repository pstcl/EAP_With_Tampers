<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script type="text/javascript">
	function viewDetails(modalid) {
		$(modelid).modal('toggle');

	}
</script>



<%-- <c:url value='/previewOilReport-${oilReport.id}' /> --%>


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
			$('#dailyTxnTable').DataTable(  {
				dom: 'Bfrtip',
		        buttons: [
		            'copyHtml5',
		            'excelHtml5',
		            'csvHtml5',
		            'pdfHtml5'
		        ]
		    } );
			 
		});
	</script>
	<script type="text/javascript">
		$(function() {
			$('.date-picker').datepicker({
				yearRange : '2010:2100',
				changeMonth : true,
				changeYear : true,
				showButtonPanel : false,
				dateFormat : "dd/mm/yy"

			});
		});
	</script>
	<div class="container-fluid">
		<c:if test="${not empty success}">
			<div class="alert alert-success lead">${success}</div>
		</c:if>
		<span class="lead">Daily Transactions Data for Dated:<fmt:formatDate
				value="${dailyTransactionModel.transactionDate}"
				pattern="dd/MM/yyyy" />





		</span>
		



		<div id="da111ilyTxnTable_wrapper" class="table-responsive ">


			<table id="dailyTxnTable"
				class="table table-striped table-bordered table-hover">

				<thead>
					<tr>

						
						<th>Serial No</th>
						<th>Location Id</th>
						<th>Utility Name</th>
						<th>Circle</th>
						<th>Division Name</th>
						<th>Station Name</th>
						<th>Device Type</th>
						<th>Feeder Name</th>
						<th>Boundary Type</th>
						<th>Voltage Level</th>
						<th>Status</th>
						<th>Meter Serial No.</th>
						<th>Meter Type</th>
						<th>Meter Category</th>
						<th>Meter Make</th>
						<th>External CT Ratio</th>
						<th>External PT Ratio</th>
						<th>External MF</th>
						<th>CT Accuracy</th>
						<th>PT Accuracy</th>
						<th>Installation Date</th>
						<th>Deactivation Date</th>
						<th>Date</th>
						<th>Import</th>
						<th>Export</th>
						<th>Net MWh</th>
						<th>Net Energy MWh</th>
						<th>Remarks</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dailyTransactionModel.dailyTransactions}"
						var="dailyTransactionForMeter" varStatus="indexStatus">





						<tr>
							
							<td>${indexStatus.index+1}</td>
							<td>${dailyTransactionForMeter.location.locationId}</td>

							<td>${dailyTransactionForMeter.location.utiltiyName}</td>
							<td>${dailyTransactionForMeter.location.circleMaster.circleName}</td>
							<td>${dailyTransactionForMeter.location.substationMaster.divisionMaster.divisionname}</td>

							<td>${dailyTransactionForMeter.location.substationMaster.stationName}</td>
							<td>${dailyTransactionForMeter.location.deviceTypeMaster.deviceType}</td>

							<td>${dailyTransactionForMeter.location.feederMaster.feederName}</td>
							<td>${dailyTransactionForMeter.location.boundaryTypeMaster.boundaryType}</td>

							<td>${dailyTransactionForMeter.location.voltageLevel}</td>
							<td>${dailyTransactionForMeter.location.location_status}</td>
							<td>${dailyTransactionForMeter.location.meterMaster.meterSrNo}</td>

							<td>${dailyTransactionForMeter.location.meterMaster.meterType}</td>
							<td>${dailyTransactionForMeter.location.meterMaster.meterCategory}</td>
							<td>${dailyTransactionForMeter.location.meterMaster.meterMake}</td>
							<td>${dailyTransactionForMeter.location.externalCTRation}</td>
							<td>${dailyTransactionForMeter.location.externalPTRation}</td>
							<td>${dailyTransactionForMeter.location.externalMF}</td>
							<td>${dailyTransactionForMeter.location.meterMaster.CTAccuracy}</td>
							<td>${dailyTransactionForMeter.location.meterMaster.PTAccuracy}</td>

							<td><fmt:formatDate
									value="${dailyTransactionForMeter.location.meterMaster.installedDate}"
									pattern="dd/MM/yyyy" /></td>
							<td><fmt:formatDate
									value="${dailyTransactionForMeter.location.meterMaster.deactivateddate}"
									pattern="dd/MM/yyyy" /></td>


							<td><fmt:formatDate
									value="${dailyTransactionForMeter.transactionDate}"
									pattern="dd/MM/yyyy" /></td>
							<td>${dailyTransactionForMeter.importWHF}</td>
							<td>${dailyTransactionForMeter.exportWHF}</td>
							<td>${dailyTransactionForMeter.netMWH}</td>
							<td>${dailyTransactionForMeter.netEnergyMWH}</td>
							<td>${dailyTransactionForMeter.remarks}</td>
						</tr>



					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>











</body>
</html>