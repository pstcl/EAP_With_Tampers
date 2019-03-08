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

	<nav class="navbar navbar-expand-lg navbar-light bg-faded ">

		<div id="navbarNavAltMarkup" class="navbar-collapse collapse">

			<div class="navbar-header">
				<a class="navbar-brand" href="#">File Master</a>
			</div>
			<ul class="navbar-nav  mr-auto">

				<div class="dropdown nav-link">
					<button class="nav-link dropdown-toggle" type="button"
						data-toggle="dropdown">
						All Files <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a class="nav-link"
							href="javascript:window.location='filesInRepoS?month=2&year=2019'"><span
								class="glyphicon glyphicon-plus"></span>February,2019</a></li>

						<li><a class="nav-link"
							href="javascript:window.location='filesInRepoS?month=1&year=2019'"><span
								class="glyphicon glyphicon-plus"></span>January,2019</a></li>

						<li><a class="nav-link"
							href="javascript:window.location='filesInRepoS?month=12&year=2018'"><span
								class="glyphicon glyphicon-plus"></span>December,2018</a></li>
					</ul>
				</div>



				<div class="dropdown nav-link">
					<button class="nav-link dropdown-toggle" type="button"
						data-toggle="dropdown">
						Files Pending Processing <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a class="nav-link"
							href="javascript:window.location='pendingRepoFiles?month=2&year=2019'"><span
								class="glyphicon glyphicon-plus"></span>February,2019</a></li>
<li><a class="nav-link"
							href="javascript:window.location='pendingRepoFiles?month=1&year=2019'"><span
								class="glyphicon glyphicon-plus"></span>January,2019</a></li>
					<li><a class="nav-link"
							href="javascript:window.location='pendingRepoFiles?month=12&year=2018'"><span
								class="glyphicon glyphicon-plus"></span>December,2018</a></li>
						
	
						
					</ul>
				</div>

				<sec:authorize access="hasRole('ROLE_SLDC_ADMIN')">



					<div class="dropdown nav-link">
						<button class="nav-link dropdown-toggle" type="button"
							data-toggle="dropdown">
							View Corrupt Files <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">

							<li><a class="nav-link"
								href="javascript:window.location='corruptRepoFiles?month=2&year=2019'"><span
									class="glyphicon glyphicon-plus"></span>February,2019</a></li>

						



							<li><a class="nav-link"
								href="javascript:window.location='corruptRepoFiles?month=&year='"><span
									class="glyphicon glyphicon-plus"></span>Files Without Date</a></li>
						</ul>
					</div>


					<div class="dropdown nav-link">
						<button class="nav-link dropdown-toggle" type="button"
							data-toggle="dropdown">
							Process Corrupt Files <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">


							<li><a class="nav-link" href=""><span
									class="glyphicon glyphicon-plus"></span>February,2019: Link
									will be enabled soon</a></li>

						

							<li><a class="nav-link"
								href="javascript:window.location='processCorruptRepoFiles?month=12&year=2018'"><span
									class="glyphicon glyphicon-plus"></span>Files Without Date</a></li>
						</ul>
					</div>

					<div class="dropdown nav-link">
						<button class="nav-link dropdown-toggle" type="button"
							data-toggle="dropdown">
							Process Files <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">

							<li><a class="nav-link" href=""><span
									class="glyphicon glyphicon-plus"></span>February,2019: Link
									will be enabled soon</a></li>

<!-- 							<li><a class="nav-link" -->
<%-- 								href="<c:url value='/processRepoFiles?month=1&year=2019' />"><span --%>
<!-- 									class="glyphicon glyphicon-plus"></span>January,2019</a></li> -->

<!-- 							<li><a class="nav-link" -->
<%-- 								href="<c:url value='/processRepoFiles?month=12&year=2018' />"><span --%>
<!-- 									class="glyphicon glyphicon-plus"></span>December,2018</a></li> -->


						</ul>
					</div>

					<div class="dropdown nav-link">
						<button class="nav-link dropdown-toggle" type="button"
							data-toggle="dropdown">
							Process Instant Reg <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">

							<li><a class="nav-link" href=""><span
									class="glyphicon glyphicon-plus"></span>February,2019: Link
									will be enabled soon</a></li>

							<li><a class="nav-link"
								href="<c:url value='/processInstantRegisters?month=1&year=2019' />"><span
									class="glyphicon glyphicon-plus"></span>January,2019</a></li>

							<li><a class="nav-link"
								href="<c:url value='/processInstantRegisters?month=12&year=2018' />"><span
									class="glyphicon glyphicon-plus"></span>December,2018</a></li>


						</ul>
					</div>





				</sec:authorize>
			</ul>
		</div>
	</nav>




	<div class="container-fluid">
		<span class="lead">File generated by energy meters <c:if
				test="${not empty reportMonthYearDate}">in the month of <fmt:formatDate
					value="${reportMonthYearDate}" pattern="MMM,yyyy" />
			</c:if></span>
		<div class="card"></div>
		<div class="table-responsive">


			<table class="table table-striped table-bordered table-hover">
				<tr>
					<th>Sr. No</th>
					<th>ProcessingStatus</th>
					<th>Location</th>
					<th>Meter</th>


					<th>File Generation Date Time</th>

					<th>No. of Daily(24 hr) Records</th>
					<th>No. of Load Survey(15 min) Records in File</th>


					<th>Actions</th>
					<th>FileName</th>
				</tr>
				<c:forEach items="${fileModel.filesUploadedDetail}"
					var="fileDetails" varStatus="indexStatus">

					<%@include file="fileDetailsStatusSnippet.jsp"%>
					<td>
						Substation:${fileDetails.location.substationMaster.stationName}

						Division:${fileDetails.location.divisionMaster.divisionname}
						Circle:${fileDetails.location.circleMaster.circleName}</td>
					<td>Meter
						Location:${fileDetails.location.feederMaster.feederName} Boundary
						Type:${fileDetails.location.boundaryTypeMaster.boundaryType}
						${fileDetails.location.locationId} Meter Details(Sr No:
						${fileDetails.meterMaster.meterSrNo})(Make:${fileDetails.meterMaster.meterType})


					</td>


					<td><fmt:formatDate value="${fileDetails.transactionDate}"
							pattern="dd/MM/yyyy HH:mm:ss" /></td>

					<td>${fileDetails.dailyRecordCount}</td>
					<td>${fileDetails.surveyRecordCount}</td>

					<td><%@include file="fileDetailsActionSnippet.jsp"%>
					</td>
					<td>${fileDetails.userfileName }</td>
				</c:forEach>
			</table>



		</div>
	</div>




</body>
</html>