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
							<div class="app-title">Substation:${dailyTransactionModel.locationMaster.substationMaster.stationName}</div>
							<div class="app-title">Division:${dailyTransactionModel.locationMaster.substationMaster.divisionMaster.divisionname}
							</div>
							<div class="app-title">Circle:${dailyTransactionModel.locationMaster.substationMaster.circleMaster.circleName}</div>

						</div>



					</div>
				</div>

				<table class="table table-striped table-bordered table-hover">
					<tr>
						<td>Meter Plant No</td>
						<td>Location Id</td>
						<td>Date</td>
						<td>Import Wh</td>
						<td>Export Wh</td>
						<td>Remarks</td>
					</tr>


					<c:forEach items="${dailyTransactionModel.dailyTransactions}"
						var="dailyTransactionForMeter" varStatus="indexStatus">


						<tr>
							<td>${dailyTransactionForMeter.location.locationId}</td>


							<td>
								${dailyTransactionForMeter.location.meterMaster.meterSrNo}</td>

							<td><fmt:formatDate
									value="${dailyTransactionForMeter.transactionDate}"
									pattern="dd-MM-yyyy HH-mm-ss" /></td>



							<td><spring:bind
									path="dailyTransactions[${indexStatus.index}].txnId">
									<input type="hidden"
										name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}"/>" />
								</spring:bind> <spring:bind
									path="dailyTransactions[${indexStatus.index}].location.locationId">
									<input type="hidden"
										name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}"/>" />
								</spring:bind> <spring:bind
									path="dailyTransactions[${indexStatus.index}].transactionDate">
									<input type="hidden"
										name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}"/>" />
								</spring:bind> <spring:bind
									path="dailyTransactions[${indexStatus.index}].importWHF">
									<input type="number"
										name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}"/>" />
								</spring:bind></td>
							<td><spring:bind
									path="dailyTransactions[${indexStatus.index}].exportWHF">
									<input type="number"
										name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}"/>" />
								</spring:bind></td>
							<td><spring:bind
									path="dailyTransactions[${indexStatus.index}].remarks">
									<textarea name="<c:out value="${status.expression}"/>"
										id="<c:out value="${status.expression}"/>"><c:out
											value="${status.value}" />
															</textarea>
								</spring:bind></td>
						</tr>

					</c:forEach>
				</table>

				<div class="row">
					<div class="form-actions floatRight">
					<input type="button" onClick="saveOrUpdate()" value="Save"
											class="btn btn-primary btn-sm" />
						
					</div>
				</div>
			</form:form>


		</div>
	</div>



</body>
</html>