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

	<div class="container-fluid">
		<c:if test="${not empty success}">
			<div class="alert alert-success lead">${success}</div>
		</c:if>
		<span class="lead">Energy Meters Master Data</span>
		<div class="card"></div>
		<div class="table-responsive">

		
				<c:forEach items="${dailyTransactionModel.dailyTransactions}"
					var="dailyTransactionForMeter" varStatus="indexStatus">


					<div class="card">


						<div class="card-body">
							<div class="row">
								<div class="col-1">
									<img class="card-img-report"
										src="<c:url value='/static/img/meter1.jpg' />" alt="Equipment">
								</div>
								<div class="col-2">
									<div class="app-title">Substation:${dailyTransactionForMeter.location.substationMaster.stationName}</div>

									<div class="sub-title">Division:${dailyTransactionForMeter.location.divisionMaster.divisionname}
									</div>
									<div class="sub-title">Circle:${dailyTransactionForMeter.location.circleMaster.circleName}</div>
								</div>
								<div class="col-2">
									<div class="app-title row">Meter Location</div>
									<div class="sub-title row">Feeder:${dailyTransactionForMeter.location.feederMaster.feederName}
									</div>
									<div class="sub-title row">Boundary
										Type:${dailyTransactionForMeter.location.boundaryTypeMaster.boundaryType}
									</div>
									<div class="sub-title row">${dailyTransactionForMeter.location.locationId}</div>
								</div>
								<div class="col-2">
									<div class="app-title row">Meter Details</div>
									<div class="sub-title row">${dailyTransactionForMeter.location.meterMaster.meterSrNo}</div>

									<div class="sub-title row">${dailyTransactionForMeter.location.meterMaster.meterType}</div>

									<a href="#" class="btn btn-primary" data-toggle="modal"
										data-target="#meterModal${indexStatus.index}">More..</a>




								</div>


								<div class="col-2">
									<div class="app-title row">
										Date
										<fmt:formatDate
											value="${dailyTransactionForMeter.transactionDate}"
											pattern="dd/MM/yyyy" />
									</div>
									<div class="app-title row">
										Import:
										<div class="sub-title row">${dailyTransactionForMeter.importWHF}</div>
									</div>
									<div class="app-title row">
										Export:
										<div class="sub-title row">${dailyTransactionForMeter.exportWHF}</div>
									</div>
									<div class="app-title row">
										Net MWh:
										<div class="sub-title row">${dailyTransactionForMeter.netMWH}</div>
									</div>
									
									<div class="app-title row">
										Net Energy MWh:
										<div class="sub-title row">${dailyTransactionForMeter.netEnergyMWH}</div>
									</div>
									
									<div class="app-title row">
										Remarks
										<div class="sub-title row">${dailyTransactionForMeter.remarks}</div>
									</div>
								</div>



							</div>

						</div>
					</div>


					<div class="modal fade" id="meterModal${indexStatus.index}">
						<div class="modal-dialog modal-lg">
							<div class="modal-content"
								style="width: 600px; align-self: center;">

								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title">Meter Location Details</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<!-- Modal body -->
								<div class="modal-body">
									<table class="table table-striped table-bordered table-hover">


										<tr>
											<th>Location Id</th>
											<td>${dailyTransactionForMeter.location.locationId}</td>
										</tr>
										<tr>
											<th>utiltiyName</th>

											<td>${dailyTransactionForMeter.location.utiltiyName}</td>
										</tr>

										<tr>
											<th>Division Name</th>
											<td>${dailyTransactionForMeter.location.substationMaster.divisionMaster.divisionname}</td>
										</tr>

										<tr>
											<th>Station Name</th>

											<td>${dailyTransactionForMeter.location.substationMaster.stationName}</td>
										</tr>


										<tr>
											<th>Device Type</th>
											<td>${dailyTransactionForMeter.location.deviceTypeMaster.deviceType}</td>
										</tr>

										<tr>
											<th>Feeder Name</th>

											<td>${dailyTransactionForMeter.location.feederMaster.feederName}</td>
										</tr>

										<tr>
											<th>Boundary Type</th>

											<td>${dailyTransactionForMeter.location.boundaryTypeMaster.boundaryType}</td>
										</tr>

										<tr>
											<th>Voltage Level</th>

											<td>${dailyTransactionForMeter.location.voltageLevel}</td>
										</tr>

										<tr>
											<th>Status</th>
											<td>${dailyTransactionForMeter.location.location_status}</td>
										</tr>

										<tr>
											<th>Meter Serial No.</th>

											<td>${dailyTransactionForMeter.location.meterMaster.meterSrNo}</td>
										</tr>

										<tr>
											<th>Meter Type</th>

											<td>${dailyTransactionForMeter.location.meterMaster.meterType}</td>
										</tr>

										<tr>
											<th>Meter Category</th>
											<td>${dailyTransactionForMeter.location.meterMaster.meterCategory}</td>
										</tr>

										<tr>
											<th>Meter Make</th>
											<td>${dailyTransactionForMeter.location.meterMaster.meterMake}</td>
										</tr>

										<tr>
											<th>External CT Ratio</th>
											<td>${dailyTransactionForMeter.location.externalCTRation}</td>
										</tr>

										<tr>
											<th>External PT Ratio</th>
											<td>${dailyTransactionForMeter.location.externalPTRation}</td>
										</tr>

										<tr>
											<th>External MF</th>
											<td>${dailyTransactionForMeter.location.externalMF}</td>
										</tr>

										<tr>
											<th>CT Accuracy</th>
											<td>${dailyTransactionForMeter.location.meterMaster.CTAccuracy}</td>
										</tr>
										<tr>
											<th>PT Accuracy</th>
											<td>${dailyTransactionForMeter.location.meterMaster.PTAccuracy}</td>
										</tr>
										<tr>
											<th>Installation Date</th>
											<td>${dailyTransactionForMeter.location.meterMaster.installedDate}</td>
										</tr>
										<tr>
											<th>Deactivation Date</th>
											<td>${dailyTransactionForMeter.location.meterMaster.deactivateddate}</td>
										</tr>


									</table>
								</div>

								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
								</div>

							</div>
						</div>
					</div>

				</c:forEach>
				

		</div>
	</div>



</body>
</html>