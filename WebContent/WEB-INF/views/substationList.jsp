<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script type="text/javascript">
	function getMeterData(locationid, meterDiv) {
		$('#' + meterDiv).css('display', 'none');
		$.ajax({
			url : "/EAP/getMeterDetails",
			type : "GET",
			data : {
				locationid : locationid
			},
			success : function(response) {
				$('#' + meterDiv).replaceWith(response);

			},
			error : function(data, status, er) {
				alert("Error" + data + er);
			}
		});

	}
</script>

<script type="text/javascript">
	function viewDetails(url) {
		$('#myModal').modal('toggle');
		document.getElementById('substationIFrame').src = url;

	}
</script>


<%-- <c:url value='/previewOilReport-${oilReport.id}' /> --%>


<title>Sub Station Master</title>
</head>

<body onload="myFunction()" style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<div class="sticky-top">
		<nav aria-label="breadcrumb" class="sticky-top">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="javascript:window.location='home'">Home</a></li>

				<li class="breadcrumb-item active" aria-current="page">Sub
					Station Master</li>
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


		<div class="table-responsive">



			<table class="table table-striped table-bordered table-hover">
				<col width="20">
				<col width="80">
				<col width="80">
				<col width="80">
				<col width="800">

				<tr>
					<th>Sr. No.</th>
					<th>Circle</th>
					<th>Division</th>
					<th>Substation</th>
					<th>Meter Details</th>


				</tr>
				<c:forEach items="${substationList}" var="substation"
					varStatus="indexStatusSubstationList">

					<tr>

						<td>${indexStatusSubstationList.index+1}</td>
						<td>${substation.circleMaster.circleName}</td>
						<td>${substation.divisionMaster.divisionname}</td>
						<td>${substation.stationName}</td>

						<td>
							<table class="table table-striped table-bordered table-hover">

								<tr>
									<th>Meter Sr. No.</th>
									<th>Meter Type/Make</th>
									<th>Location</th>
									<th>Boundary Type</th>


								</tr>

								<c:forEach items="${substation.locationMasters}" var="location"
									varStatus="indexStatus">


									<tr>

										<td>${location.meterMaster.meterSrNo}</td>
										<td>${location.meterMaster.meterType}</td>
										<td>${location.feederMaster.feederName}</td>
										<td>${location.boundaryTypeMaster.boundaryType}</td>

										<td><button data-toggle="collapse"
												data-target="#demo${indexStatus.index+1 }"
												onclick="getMeterData('${location.locationId}','demo${indexStatusSubstationList.index}_${indexStatus.index+1 }')">Meter
												Details</button></td>

									</tr>
									<tr>
										<td><div
												id="demo${indexStatusSubstationList.index}_${indexStatus.index+1 }"
												class="collapse in"></div></td>
									</tr>

								</c:forEach>
							</table>
				</c:forEach>
			</table>
			<div class="modal fade" id="myModal">
				<div class="modal-dialog modal-lg">
					<div class="modal-content"
						style="width: 600px; align-self: center;">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">Substation Details</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<iframe id="substationIFrame" width="100%" height="100%"></iframe>
						</div>


						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>

					</div>
				</div>
			</div>


		</div>
	</div>



</body>
</html>