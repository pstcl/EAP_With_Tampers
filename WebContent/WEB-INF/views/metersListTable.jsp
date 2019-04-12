<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<script type="text/javascript">
	function viewDetails(modalid) {
		$(modelid).modal('toggle');
	}
</script>
<%@include file="header.jsp"%>

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


	<div class="table-responsive">




		<c:forEach items="${energyMeters}" var="meterObject"
			varStatus="indexStatus">


			<div class="card">


				<div class="card-body">
					<div class="row">
						<div class="col-1">${indexStatus.index+1}</div>
						<div class="col-1">
							<img class="card-img-report"
								src="<c:url value='/static/img/meter1.jpg' />" alt="Equipment">
						</div>



						<div class="col-3">
							<div class="app-title">Substation:${meterObject.locationMaster.substationMaster.stationName}</div>

							<div class="sub-title">Division:${meterObject.locationMaster.divisionMaster.divisionname}
							</div>
							<div class="sub-title">Circle:${meterObject.locationMaster.circleMaster.circleName}</div>
						</div>
						<div class="col-3">
							<div class="app-title row">Meter Location</div>
							<div class="sub-title row">Feeder:${meterObject.locationMaster.feederMaster.feederName}
							</div>
							<div class="sub-title row">Boundary
								Type:${meterObject.locationMaster.boundaryTypeMaster.boundaryType}
							</div>
							<div class="sub-title row">${meterObject.locationMaster.locationId}</div>
						</div>
						<div class="col-3">
							<div class="app-title row">Meter Details</div>
							<div class="sub-title row">${meterObject.locationMaster.meterMaster.meterSrNo}</div>

							<div class="sub-title row">${meterObject.locationMaster.meterMaster.meterType}</div>

							<a href="#" class="btn btn-primary" data-toggle="modal"
								data-target="#meterModal${indexStatus.index}">More..</a>




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
							<h4 class="modal-title">Meter Details</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<table class="table table-striped table-bordered table-hover">


								<tr>
									<th>Location Id</th>
									<td>${meterObject.locationMaster.locationId}</td>
								</tr>
								<tr>
									<th>utiltiyName</th>

									<td>${meterObject.locationMaster.utiltiyName}</td>
								</tr>

								<tr>
									<th>Division Name</th>
									<td>${meterObject.locationMaster.substationMaster.divisionMaster.divisionname}</td>
								</tr>

								<tr>
									<th>Station Name</th>

									<td>${meterObject.locationMaster.substationMaster.stationName}</td>
								</tr>


								<tr>
									<th>Device Type</th>
									<td>${meterObject.locationMaster.deviceTypeMaster.deviceType}</td>
								</tr>

								<tr>
									<th>Feeder Name</th>

									<td>${meterObject.locationMaster.feederMaster.feederName}</td>
								</tr>

								<tr>
									<th>Boundary Type</th>

									<td>${meterObject.locationMaster.boundaryTypeMaster.boundaryType}</td>
								</tr>

								<tr>
									<th>Voltage Level</th>

									<td>${meterObject.locationMaster.voltageLevel}</td>
								</tr>

								<tr>
									<th>Status</th>
									<td>${meterObject.locationMaster.location_status}</td>
								</tr>

								<tr>
									<th>Meter Serial No.</th>

									<td>${meterObject.meterSrNo}</td>
								</tr>

								<tr>
									<th>Meter Type</th>

									<td>${meterObject.meterType}</td>
								</tr>

								<tr>
									<th>Meter Category</th>
									<td>${meterObject.meterCategory}</td>
								</tr>

								<tr>
									<th>Meter Make</th>
									<td>${meterObject.meterMake}</td>
								</tr>

								<tr>
									<th>External CT Ratio</th>
									<td>${meterObject.locationMaster.externalCTRation}</td>
								</tr>

								<tr>
									<th>External PT Ratio</th>
									<td>${meterObject.locationMaster.externalPTRation}</td>
								</tr>

								<tr>
									<th>External MF</th>
									<td>${meterObject.locationMaster.externalMF}</td>
								</tr>

								<tr>
									<th>CT Accuracy</th>
									<td>${meterObject.CTAccuracy}</td>
								</tr>
								<tr>
									<th>PT Accuracy</th>
									<td>${meterObject.PTAccuracy}</td>
								</tr>
								<tr>
									<th>Installation Date</th>
									<td>${meterObject.installedDate}</td>
								</tr>
								<tr>
									<th>Deactivation Date</th>
									<td>${meterObject.deactivateddate}</td>
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