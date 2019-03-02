<%@page import="org.pstcl.ea.util.EAUtil"%>
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
	function saveOrUpdate() {
		document.forms['saveDailyTxForm'].submit();
	}
	function submitReadings() {
		document.forms['saveDailyTxForm'].action = 'approveDailyTxSS'
		document.forms['saveDailyTxForm'].submit();
	}
	function unlockReadings() {
		document.forms['saveDailyTxForm'].action = 'unLockDailyTxSS'
		document.forms['saveDailyTxForm'].submit();
	}
	function lockReadings() {
		document.forms['saveDailyTxForm'].action = 'lockDailyTxSS'
		document.forms['saveDailyTxForm'].submit();
	}
	function setReportDate() {
		document.forms['saveDailyTxForm'].action = "setDateDailyTxSS";
		document.forms['saveDailyTxForm'].submit();

	}

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
	<script type="text/javascript">
		$(function() {
			$('.date-picker').datepicker({
				yearRange : '2018:2020',
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
		<span class="lead">Energy Meters Master Data</span>

		<div class="table-responsive">

			<form:form id="saveDailyTxForm" action="saveDailyTxSS" method="POST"
				modelAttribute="dailyTransactionModel" class="form-horizontal">
				<div class="card">


					<div class="card-body row">
						<div class="col-md">
							<div class="app-title">Substation:${currentUser.substationMaster.stationName}</div>
							<div class="app-title">Division:${currentUser.substationMaster.divisionMaster.divisionname}
							</div>
							<div class="app-title">Circle:${currentUser.substationMaster.circleMaster.circleName}</div>

						</div>
						


					</div>
				</div>

				<c:forEach items="${dailyTransactionModel.dailyTransactions}"
					var="dailyTransactionForMeter" varStatus="indexStatus">


					<div class="card">


						<div class="card-body">
							<div class="row">





								<div class="col-md-3">
									<img class="row card-img-report"
										src="<c:url value='/static/img/meter1.jpg' />" alt="Equipment">
									<div class="sub-title row">Bay
										:${dailyTransactionForMeter.location.feederMaster.feederName}&nbsp;&nbsp;(${dailyTransactionForMeter.location.boundaryTypeMaster.boundaryType})
									</div>
									<div class="sub-title row">
										Make:${dailyTransactionForMeter.location.meterMaster.meterType}
										<a href="#" class="row btn btn-light btn-sm"
											data-toggle="modal"
											data-target="#meterModal${indexStatus.index}">More
											details..</a>
									</div>





								</div>








								<spring:bind
									path="dailyTransactions[${indexStatus.index}].txnId">
									<input type="hidden"
										name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}"/>" />
								</spring:bind>
								<spring:bind
									path="dailyTransactions[${indexStatus.index}].location.locationId">
									<input type="hidden"
										name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}"/>" />
								</spring:bind>
								<spring:bind
									path="dailyTransactions[${indexStatus.index}].transactionDate">
									<input type="hidden"
										name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}"/>" />
								</spring:bind>
								<c:choose>

									<c:when
										test="${dailyTransactionForMeter.transactionStatus eq 75||dailyTransactionForMeter.transactionStatus eq 25||dailyTransactionForMeter.transactionStatus eq -100}">

										<div class="col-md-3">


											<div class="form-control col-md-12">
												<label class="col-md-6 form-control-label">Import
													Wh(F)</label>
												<div class="col-md-12">
													<spring:bind
														path="dailyTransactions[${indexStatus.index}].importWHF">
														<input type="number"
															name="<c:out value="${status.expression}"/>"
															id="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}"/>" />
													</spring:bind>
												</div>
											</div>
										</div>

										<div class="col-md-3">
											<div class="form-control col-md-12">
												<label class="col-md-6 form-control-label">Export
													Wh(F)</label>
												<div class="col-md-12">
													<spring:bind
														path="dailyTransactions[${indexStatus.index}].exportWHF">
														<input type="number"
															name="<c:out value="${status.expression}"/>"
															id="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}"/>" />
													</spring:bind>

												</div>
											</div>
										</div>

										<div class="col-md-3">


											<div class="form-control col-md-12">
												<label class="col-md-6 form-control-label">Remarks</label>
												<div class="col-md-12">
													<spring:bind
														path="dailyTransactions[${indexStatus.index}].remarks">
														<textarea name="<c:out value="${status.expression}"/>"
															id="<c:out value="${status.expression}"/>"><c:out
																value="${status.value}" />
															</textarea>
													</spring:bind>

												</div>
											</div>


										</div>
									</c:when>
									<c:otherwise>
										<spring:bind
											path="dailyTransactions[${indexStatus.index}].importWHF">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												id="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}"/>" />
										</spring:bind>
										<spring:bind
											path="dailyTransactions[${indexStatus.index}].exportWHF">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												id="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}"/>" />
										</spring:bind>
										<spring:bind
											path="dailyTransactions[${indexStatus.index}].remarks">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												id="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}"/>">
											</textarea>
										</spring:bind>

										<div class="col-md-3">
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
										</div>
										<div class="col-md-3">
										
											<div class="app-title row">
												Remarks
												<div class="sub-title row">${dailyTransactionForMeter.remarks}</div>
											</div>


											<c:choose>
												<c:when
													test="${dailyTransactionForMeter.transactionStatus eq 50}">
													<span> <i class="fa fa-warning"
														style="font-size: 18px; color: orange;"> Data locked
															by JE. Contact concerned AE/AEE for unlocking</i>
													</span>


												</c:when>
												<c:when
													test="${dailyTransactionForMeter.transactionStatus eq 100}">
													<span> <i class="fa fa-warning"
														style="font-size: 18px; color: green;"> Data Approved</i>
													</span>

												</c:when>
											</c:choose>
										</div>
									</c:otherwise>
								</c:choose>
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
				<div class="row">
					<div class="form-actions floatRight">
						<sec:authorize access="hasAnyRole('ROLE_SS_AE')">

							<c:choose>

								<c:when test="${dailyTransactionModel.transactionStatus eq 50}">
									<input type="button" onClick="submitReadings()"
										value="Approve Meter Readings" class="btn btn-primary btn-sm" />

									<input type="button" onClick="unlockReadings()"
										value="Unlock For Updation" class="btn btn-primary btn-sm" />
								</c:when>

							</c:choose>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_SS_JE')">
							<div class="row">

								<c:choose>
									<c:when
										test="${dailyTransactionModel.transactionStatus eq -100}">
										<input type="button" onClick="saveOrUpdate()" value="Save"
											class="btn btn-primary btn-sm" />
									</c:when>

									<c:when test="${dailyTransactionModel.transactionStatus eq 75}">
										<input type="button" onClick="saveOrUpdate()" value="Update"
											class="btn btn-primary btn-sm" />
									</c:when>

									<c:when test="${dailyTransactionModel.transactionStatus eq 25}">
										<input type="button" onClick="lockReadings()"
											value="Lock Meter Readings" class="btn btn-primary btn-sm" />
										<input type="button" onClick="saveOrUpdate()" value="Update"
											class="btn btn-primary btn-sm" />
									</c:when>

								</c:choose>


							</div>
						</sec:authorize>
					</div>
				</div>
			</form:form>


		</div>
	</div>



</body>
</html>