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

		<div class="card">
			<div class="card-header app-title">Upload New Files</div>
			<div class="card-body">
				<form:form method="POST"
					action="uploadZipPM?${_csrf.parameterName}=${_csrf.token}"
					modelAttribute="fileModel" enctype="multipart/form-data">
					<table>
						<tr>

							<td><input type="file" name="files" accept="application/zip"
								multiple="multiple" /></td>

							<td><input type="submit" value="Upload" /></td>
						</tr>
					</table>


				</form:form>

			</div>
			<div class="card-footer"></div>
		</div>

		<div class="card">
			<div class="table-responsive">


				<c:if test="${fileModel !=null}">
					<table class="table table-striped table-bordered table-hover">
						<tr>
							<th>Sr. No</th>
							<th>ProcessingStatus</th>
							<th>Location</th>
							<th>Meter</th>
							<th>File Uploaded</th>
							<th>File Generation Date Time</th>
							<th>Action</th>


						</tr>
						<c:forEach items="${fileModel.filesUploadedDetail}"
							var="fileDetails" varStatus="indexStatus">

							<%@include file="fileDetailsStatusSnippet.jsp"%>
							<td>
								Substation:${fileDetails.location.substationMaster.stationName}

								Division:${fileDetails.location.divisionMaster.divisionname}
								Circle:${fileDetails.location.circleMaster.circleName}</td>
							<td>Meter
								Location:${fileDetails.location.feederMaster.feederName}
								Boundary
								Type:${fileDetails.location.boundaryTypeMaster.boundaryType}
								${fileDetails.location.locationId} Meter Details(Sr No:
								${fileDetails.meterMaster.meterSrNo})(Make:${fileDetails.meterMaster.meterType})


							</td>
							<td>${fileDetails.userfileName}</td>
							<td><fmt:formatDate value="${fileDetails.transactionDate}"
									pattern="dd/MM/yyyy HH:mm:ss" /></td>
							<td><%@include file="fileDetailsActionSnippet.jsp"%>
							</td>



						</c:forEach>
					</table>
				</c:if>

			</div>
		</div>

		<c:if test="${ssMeterLocations !=null}">

			<div class="card">
				<div class="card-header  app-title">
				
				<span class="lead">File already uploaded <c:if
				test="${not empty reportMonthYearDate}">in the month of <fmt:formatDate
					value="${reportMonthYearDate}" pattern="MMM,yyyy" />
			</c:if></span></div>
				<div class="card-body">				<div class="card">
						<div class="table-responsive">



							<table class="table table-striped table-bordered table-hover">
								<tr>
									<th>Sr. No.</th>

									<th>Meter Sr. No.</th>
									<th>Meter Type/Make</th>
									<th>Location ID</th>
									<th>Boundary Type</th>

									<th style="width: 150px;">Meter Location</th>
									<th>Boundary</th>

									<th style="width: 350px;"></th>


								</tr>
								<c:forEach items="${ssMeterLocations}" var="locDetails"
									varStatus="indexStatus">
									<tr>
										<td>${indexStatus.index+1 }</td>



										<td>${locDetails.locationMaster.meterMaster.meterSrNo}</td>
										<td>${locDetails.locationMaster.meterMaster.meterType}</td>
										<td>${locDetails.locationMaster.locationId}</td>
										<td>${locDetails.locationMaster.boundaryTypeMaster.boundaryType}</td>


										<td>${locDetails.locationMaster.feederMaster.feederName}</td>
										<td>${locDetails.locationMaster.boundaryTypeMaster.boundaryType}</td>


										<td><c:if
												test="${fn:length(locDetails.fileMasters) gt 0}">
												<button data-toggle="collapse"
													data-target="#demo${indexStatus.index+1 }">FileDetails</button>
											</c:if> <c:if test="${fn:length(locDetails.fileMasters) lt 1}">
											No File Found	</c:if></td>
									</tr>
									<c:if test="${fn:length(locDetails.fileMasters) gt 0}">
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td colspan="6">

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
														<c:forEach items="${locDetails.fileMasters}"
															var="fileDetails" varStatus="indexStatus1">
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
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>