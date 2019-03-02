<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


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

				<li class="breadcrumb-item active" aria-current="page">File
					Master</li>
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
		<span class="lead">Locations for which no data is available for
			the month of <fmt:formatDate value="${monthOfReport}"
				pattern="MMM,yyyy" />
		</span>
		<div class="card"></div>
		<div class="table-responsive">

			<table class="table table-striped table-bordered table-hover">
				<tr>
					<th>Sr. No.</th>
					<th style="width: 150px;">Substation</th>

					<th style="width: 150px;">Location</th>
					<th style="width: 150px;">Meter Details</th>
					<th>Boundary</th>

					<th style="width: 350px;">Files Uploaded for the Period</th>
					<sec:authorize
						access="hasAnyRole('ROLE_SLDC_USER','ROLE_SLDC_ADMIN')">
						<th>Add Data Manually</th>
					</sec:authorize>


				</tr>
				<c:forEach items="${pendingLocList}" var="locDetails"
					varStatus="indexStatus">
					<tr>
						<td>${indexStatus.index+1 }</td>
						<td>${locDetails.locationMaster.substationMaster.stationName}</td>
						<td>${locDetails.locationMaster.locationId}</td>

						<td>${locDetails.locationMaster.meterMaster.meterSrNo}</td>
						<td>${locDetails.locationMaster.boundaryTypeMaster.boundaryType}</td>
						<td><c:if test="${fn:length(locDetails.fileMasters) gt 0}">
								<button data-toggle="collapse"
									data-target="#demo${indexStatus.index+1 }">FileDetails</button>
							</c:if> <c:if test="${fn:length(locDetails.fileMasters) lt 1}">
											No File Found	</c:if></td>

						<sec:authorize
							access="hasAnyRole('ROLE_SLDC_USER','ROLE_SLDC_ADMIN')">
							<td><a
								title="${locDetails.locationMaster.locationId}"
								href="javascript:window.location='addPendingLocData-${locDetails.locationMaster.locationId}?month=${month}&year=${year}'">
									Add Data </a></td>
						</sec:authorize>
					</tr>
					<c:if test="${fn:length(locDetails.fileMasters) gt 0}">
						<tr>
							<td></td>
							<td colspan="8">

								<div id="demo${indexStatus.index+1 }" class="collapse in">

									<table class="table">
										<tr>
											<th>File Name</th>
											<th>File Generation Date Time</th>
											<th>Date of Uploading</th>
											<th>No. of Daily(24 hr) Records</th>
											<th>No. of Load Survey(15 min) Records in File</th>
											<th>Uploaded By</th>
											<th>Approved By</th>
											<th>ProcessingStatus</th>
											<th>Action</th>


										</tr>
										<c:forEach items="${locDetails.fileMasters}" var="fileDetails"
											varStatus="indexStatus1">
											<%@include file="substationHomeFileToolkit.jsp"%>
										</c:forEach>
									</table>
								</div>
							</td>
						</tr>
					</c:if>

				</c:forEach>
			</table>



		</div>
	</div>




</body>
</html>