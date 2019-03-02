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
		document.forms['saveMonthlyTxForm'].submit();
	}
	function submitReadings() {
		document.forms['saveMonthlyTxForm'].action = 'approveMonthlyTxSS'
		document.forms['saveMonthlyTxForm'].submit();
	}
	function unlockReadings() {
		document.forms['saveMonthlyTxForm'].action = 'unLockMonthlyTxSS'
		document.forms['saveMonthlyTxForm'].submit();
	}
	function lockReadings() {
		document.forms['saveMonthlyTxForm'].action = 'lockMonthlyTxSS'
		document.forms['saveMonthlyTxForm'].submit();
	}

	function viewDetails(modalid) {
		$(modelid).modal('toggle');

	}
</script>
<style type="text/css">
input {
	text-align: right;
}

colgroup .importData {
	text-decoration: underline;
	font-weight: bolder;
}

colgroup .exportData {
	text-decoration: underline;
	font-weight: bolder;
}
</style>

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
		<span class="lead">Monthly Data for Substation meters</span>
		<div class="card">


			<div class="card-body row">
				<div class="col-md">
					<div class="app-title">Substation:${currentUser.substationMaster.stationName}</div>
					<div class="app-title">Division:${currentUser.substationMaster.divisionMaster.divisionname}
					</div>
					<div class="app-title">Circle:${currentUser.substationMaster.circleMaster.circleName}</div>

				</div>
				<div class="col-md app-title">
					Month

					<fmt:formatDate
						value="${monthlyTransactionEntryModel.dateForDisplay}"
						pattern="MMMM" />
					Year :${monthlyTransactionEntryModel.year}
				</div>
			</div>
		</div>
		<div class="table-responsive">



			<form:form id="saveMonthlyTxForm" action="saveMonthlyTxSS"
				method="POST" modelAttribute="monthlyTransactionEntryModel"
				class="form-horizontal">
				<table id="dailyTxnTable"
					class="table table-striped table-bordered table-hover">

					<thead>

						<tr>

							<th></th>


							<th></th>

							<th>Connected CTR</th>
							<th>Net Wh</th>
							<th>Net Energy</th>
							<th>Remarks</th>
							<th>Status</th>

						</tr>

					</thead>
					<tbody>

						<c:forEach
							items="${monthlyTransactionEntryModel.monthlyTransactions}"
							var="monthlyTransactionForMeter" varStatus="indexStatus">



							<tr>










								<td><spring:bind
										path="monthlyTransactions[${indexStatus.index}].txnId">
										<input type="hidden"
											name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" />
									</spring:bind> <spring:bind
										path="monthlyTransactions[${indexStatus.index}].location.locationId">
										<input type="hidden"
											name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" />
									</spring:bind> <spring:bind
										path="monthlyTransactions[${indexStatus.index}].monthOfYear">
										<input type="hidden"
											name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" />
									</spring:bind> <spring:bind
										path="monthlyTransactions[${indexStatus.index}].year">
										<input type="hidden"
											name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" />
									</spring:bind>
									<div style="">
										<a href="#" class="row btn btn-light btn-sm"
											data-toggle="modal"
											data-target="#meterModal${indexStatus.index}"> <img
											class="row card-img-report"
											src="<c:url value='/static/img/meter1.jpg' />"
											alt="Equipment" /> Details..
										</a>
										<div class="sub-title">Bay
											:${monthlyTransactionForMeter.location.feederMaster.feederName}&nbsp;&nbsp;(${monthlyTransactionForMeter.location.boundaryTypeMaster.boundaryType})
										</div>
										<div class="sub-title">
											Make:${monthlyTransactionForMeter.location.meterMaster.meterType}


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
														<table
															class="table table-striped table-bordered table-hover">


															<tr>
																<th>Location Id</th>
																<td>${monthlyTransactionForMeter.location.locationId}</td>
															</tr>
															<tr>
																<th>utiltiyName</th>

																<td>${monthlyTransactionForMeter.location.utiltiyName}</td>
															</tr>

															<tr>
																<th>Division Name</th>
																<td>${monthlyTransactionForMeter.location.substationMaster.divisionMaster.divisionname}</td>
															</tr>

															<tr>
																<th>Station Name</th>

																<td>${monthlyTransactionForMeter.location.substationMaster.stationName}</td>
															</tr>


															<tr>
																<th>Device Type</th>
																<td>${monthlyTransactionForMeter.location.deviceTypeMaster.deviceType}</td>
															</tr>

															<tr>
																<th>Feeder Name</th>

																<td>${monthlyTransactionForMeter.location.feederMaster.feederName}</td>
															</tr>

															<tr>
																<th>Boundary Type</th>

																<td>${monthlyTransactionForMeter.location.boundaryTypeMaster.boundaryType}</td>
															</tr>

															<tr>
																<th>Voltage Level</th>

																<td>${monthlyTransactionForMeter.location.voltageLevel}</td>
															</tr>

															<tr>
																<th>Status</th>
																<td>${monthlyTransactionForMeter.location.location_status}</td>
															</tr>

															<tr>
																<th>Meter Serial No.</th>

																<td>${monthlyTransactionForMeter.location.meterMaster.meterSrNo}</td>
															</tr>

															<tr>
																<th>Meter Type</th>

																<td>${monthlyTransactionForMeter.location.meterMaster.meterType}</td>
															</tr>

															<tr>
																<th>Meter Category</th>
																<td>${monthlyTransactionForMeter.location.meterMaster.meterCategory}</td>
															</tr>

															<tr>
																<th>Meter Make</th>
																<td>${monthlyTransactionForMeter.location.meterMaster.meterMake}</td>
															</tr>

															<tr>
																<th>External CT Ratio</th>
																<td>${monthlyTransactionForMeter.location.externalCTRation}</td>
															</tr>

															<tr>
																<th>External PT Ratio</th>
																<td>${monthlyTransactionForMeter.location.externalPTRation}</td>
															</tr>

															<tr>
																<th>External MF</th>
																<td>${monthlyTransactionForMeter.location.externalMF}</td>
															</tr>

															<tr>
																<th>CT Accuracy</th>
																<td>${monthlyTransactionForMeter.location.meterMaster.CTAccuracy}</td>
															</tr>
															<tr>
																<th>PT Accuracy</th>
																<td>${monthlyTransactionForMeter.location.meterMaster.PTAccuracy}</td>
															</tr>
															<tr>
																<th>Installation Date</th>
																<td>${monthlyTransactionForMeter.location.meterMaster.installedDate}</td>
															</tr>
															<tr>
																<th>Deactivation Date</th>
																<td>${monthlyTransactionForMeter.location.meterMaster.deactivateddate}</td>
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
									</div></td>


								<c:choose>

									<c:when
										test="${monthlyTransactionForMeter.transactionStatus eq 175||monthlyTransactionForMeter.transactionStatus eq 125||monthlyTransactionForMeter.transactionStatus eq -200}">


										<td>
											<table>
												<tr>
													<th></th>
													<th>Import Wh (F) Reading</th>
													<th>Export Wh (F) Reading</th>
												</tr>
												<tr>
													<td>Start (At 0000hrs of the First Day)</td>
													<td><spring:bind
															path="monthlyTransactions[${indexStatus.index}].importReadingStart">
															<input type="number"
																name="<c:out value="${status.expression}"/>"
																id="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" />
														</spring:bind></td>
													<td>
														<div class="col-md-6">
															<spring:bind
																path="monthlyTransactions[${indexStatus.index}].exportReadingStart">
																<input type="number"
																	name="<c:out value="${status.expression}"/>"
																	id="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}"/>" />
															</spring:bind>

														</div>
													</td>

												</tr>
												<tr>
													<td>End (At 2400hrs of the Last Day)</td>
													<td><spring:bind
															path="monthlyTransactions[${indexStatus.index}].importReadingEnd">
															<input type="number"
																name="<c:out value="${status.expression}"/>"
																id="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" />
														</spring:bind></td>

													<td>
														<div class="col-md-6">
															<spring:bind
																path="monthlyTransactions[${indexStatus.index}].exportReadingEnd">
																<input type="number"
																	name="<c:out value="${status.expression}"/>"
																	id="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}"/>" />
															</spring:bind>

														</div>
													</td>

												</tr>

												<tr>
													<th>Net Monthly</th>
													<td><label class="col-md-5 form-control-label">
															${monthlyTransactionForMeter.netImport} </label></td>
													<td>${monthlyTransactionForMeter.netExport}</td>
												</tr>
											</table>
										</td>



										<td>
											<div class="col-md-6">
												<spring:bind
													path="monthlyTransactions[${indexStatus.index}].connectedCTRStart">
													<input type="number"
														name="<c:out value="${status.expression}"/>"
														id="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}"/>" />
												</spring:bind>

											</div>
										</td>

										<td>${monthlyTransactionForMeter.netMWH}</td>
										<td>${monthlyTransactionForMeter.netEnergyMWH}</td>

										<td>
											<div class="col-md-9">
												<spring:bind
													path="monthlyTransactions[${indexStatus.index}].remarks">
													<textarea class="form-control"
														name="<c:out value="${status.expression}"/>"
														id="<c:out value="${status.expression}"/>"><c:out
															value="${status.value}" />
															</textarea>
												</spring:bind>

											</div>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<table>
												<tr>
													<th></th>
													<th>Import Wh (F) Reading</th>
													<th>Export Wh (F) Reading</th>
												</tr>
												<tr>
													<td width="300px;"><spring:bind
															path="monthlyTransactions[${indexStatus.index}].importReadingStart">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																id="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" />
														</spring:bind> <spring:bind
															path="monthlyTransactions[${indexStatus.index}].exportReadingStart">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																id="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" />
														</spring:bind> <spring:bind
															path="monthlyTransactions[${indexStatus.index}].importReadingEnd">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																id="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" />
														</spring:bind> <spring:bind
															path="monthlyTransactions[${indexStatus.index}].connectedCTRStart">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																id="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" />
														</spring:bind> <spring:bind
															path="monthlyTransactions[${indexStatus.index}].exportReadingEnd">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																id="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" />
														</spring:bind> <spring:bind
															path="monthlyTransactions[${indexStatus.index}].remarks">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																id="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" />

														</spring:bind> Start (At 0000hrs of the First Day)</td>

													<th width="200px;">${monthlyTransactionForMeter.importReadingStart}</th>
													<th width="200px;">${monthlyTransactionForMeter.exportReadingStart}</th>




												</tr>
												<tr>
													<td>End (At 2400hrs of the Last Day)</td>
													<th>${monthlyTransactionForMeter.importReadingEnd}</th>

													<th>${monthlyTransactionForMeter.exportReadingEnd}</th>
												</tr>

												<tr>
													<td>Net Monthly</td>
													<th>${monthlyTransactionForMeter.netImport}</th>
													<th>${monthlyTransactionForMeter.netExport}</th>


												</tr>
											</table>
										</td>


										<th>${monthlyTransactionForMeter.connectedCTRStart}</th>

										<th>${monthlyTransactionForMeter.netMWH}</th>
										<th>${monthlyTransactionForMeter.netEnergyMWH}</th>
										<td>${monthlyTransactionForMeter.remarks}</td>
										<td><c:choose>
												<c:when
													test="${monthlyTransactionForMeter.transactionStatus eq 150}">
													<span> <i class="fa fa-warning"
														style="font-size: 18px; color: orange;"> Data locked
															by JE. Contact concerned AE/AEE for unlocking</i>
													</span>


												</c:when>
												<c:when
													test="${monthlyTransactionForMeter.transactionStatus eq 200}">
													<span> <i class="fa fa-warning"
														style="font-size: 18px; color: green;"> Data Approved</i>
													</span>

												</c:when>
											</c:choose></td>
									</c:otherwise>
								</c:choose>

							</tr>

							<tr>
								<td colspan="5"></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<div class="row">
					<div class="form-actions floatRight">
						<sec:authorize access="hasAnyRole('ROLE_SS_AE')">

							<c:choose>

								<c:when
									test="${monthlyTransactionEntryModel.transactionStatus eq 150}">
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
										test="${monthlyTransactionEntryModel.transactionStatus eq -200}">
										<input type="button" onClick="saveOrUpdate()" value="Save"
											class="btn btn-primary btn-sm" />
									</c:when>

									<c:when
										test="${monthlyTransactionEntryModel.transactionStatus eq 175}">
										<input type="button" onClick="saveOrUpdate()" value="Update"
											class="btn btn-primary btn-sm" />
									</c:when>

									<c:when
										test="${monthlyTransactionEntryModel.transactionStatus eq 125}">
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