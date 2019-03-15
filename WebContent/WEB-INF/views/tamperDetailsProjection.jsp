<%@page import="org.pstcl.ea.util.EAUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">



<%-- <c:url value='/previewOilReport-${oilReport.id}' /> --%>


<title>Loss Report Data for the month of <fmt:formatDate
		value="${reportMonthYearDate}" pattern="MMM,yyyy" />
</title>
</head>

<body onload="myFunction()" style="margin: 0;">
	<%@include file="authheader.jsp"%>

	<%@include file="dataTablesHeader.jsp"%>



	<script type="text/javascript">
		$(document).ready(function() {
			$('#dailyTxnTable').DataTable({
				dom : 'Bfrtip',
				"paging" : false,
				"ordering" : false,
				buttons : [ 'copyHtml5', 'excelHtml5', 'csvHtml5', {
					extend : 'pdfHtml5',
					pageSize : 'A4'
				} ],
				
				pageSize : 'A4'
			});

		});
	</script>
	<div class="card"></div>
	<div id="da111ilyTxnTable_wrapper" class="table-responsive ">

		<span class="lead">Tamper Report Data for the month of <fmt:formatDate
				value="${reportMonthYearDate}" pattern="MMM,yyyy" />
		</span>

		<table id="dailyTxnTable"
			class="table table-striped table-bordered table-hover">

			<thead>


				<tr>
				</tr>




				<th>Sr. No.</th>

				<th>Location</th>
				<th>Tamper Count</th>
				<th>Station Name</th>
				<th>Division Name</th>
				<th>Circle Name</th>
				<th>Boundary Type</th>
				<th>Device Type</th>
				<th>Feeder Name</th>
				<th>Meter</th>


				</tr>
			</thead>

			<c:forEach items="${ tamperDetailsProjectionModel}"
				var="tamperDetails" varStatus="indexStatus">

				<tr>
					<td>${indexStatus.index +1}</td>

					<td><a
						href="javascript:window.location='getLocationTampers-${tamperDetails.locationMaster.locationId}?month=${monthOfReport}&year=${yearOfReport}'">

							${tamperDetails.locationMaster.locationId} </a></td>
					<td>${tamperDetails.count}</td>

					<td>${tamperDetails.locationMaster.substationMaster.stationName}</td>
					<td>${tamperDetails.locationMaster.substationMaster.divisionMaster.divisionname}</td>

					<td>${tamperDetails.locationMaster.circleMaster.circleName}</td>
					<td>${tamperDetails.locationMaster.boundaryTypeMaster.boundaryType}
					</td>
					<td>${tamperDetails.locationMaster.deviceTypeMaster.deviceType}</td>

					<td>${tamperDetails.locationMaster.feederMaster.feederName}</td>
					<td>Meter
						Location:${tamperDetails.locationMaster.feederMaster.feederName}
						${tamperDetails.locationMaster.locationId} <br>Meter
						Details(Sr No:
						${tamperDetails.locationMaster.meterMaster.meterSrNo})(Make:${tamperDetails.locationMaster.meterMaster.meterType})


					</td>

				</tr>

			</c:forEach>
			<tr>
				<td><b><a
						href="javascript:window.location='getAllLocationTampers?month=${monthOfReport}&year=${yearOfReport}'">

							All Locations</a></b></td>
			</tr>
		</table>

	</div>

</body>



</body>
</html>