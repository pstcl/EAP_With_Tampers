<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="myFunction()" style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<%@include file="dataTablesHeader.jsp"%>



	<script type="text/javascript">
		$(document).ready(
				function() {
					$('#irdetails').DataTable(
							{
								dom : 'Bfrtip',
								"paging" : true,
								"ordering" : false,
								buttons : [ 'copyHtml5', 'excelHtml5',
										'csvHtml5', 'pdfHtml5' ],
								orientation : 'landscape',
								pageSize : 'A4'
							});

				});
	</script>

	<div class="container-fluid">


		<span class="lead">IR Report Data for the month of <fmt:formatDate
				value="${reportMonthYearDate}" pattern="MMM,yyyy" />
		</span>

		<div id="da111ilyTxnTable_wrapper" class="table-responsive ">



			<table id="irdetails"
				class="table table-striped table-bordered table-hover">



				<c:forEach items="${irdetails}" var="irDetail"
					varStatus="indexStatus">


					<thead>


						<tr>
							<th>Circle</th>
							<td>${irDetail.location.circleMaster.circleName}</td>
						</tr>
						<tr>
							<th>Division</th>
							<td>${irDetail.location.substationMaster.divisionMaster.divisionname}</td>
						</tr>
						<tr>
							<th>Substation</th>
							<td>${irDetail.location.substationMaster.stationName}</td>

						</tr>
						<tr>
							<th>Location</th>
							<td>${irDetail.location.locationId}</td>

						</tr>
						<tr>
							<th>Meter Sr No</th>
							<td>${irDetail.location.meterMaster.meterSrNo}</td>

						</tr>

					</thead>
					<c:set var="object" value="${irDetail}" />

					<c:if test="${not empty object['class'].declaredFields}">
						<c:forEach var="field" items="${object['class'].declaredFields}">
							<c:catch>
								<c:if test="${! fn:containsIgnoreCase(field.name, 'txn')  && (! fn:containsIgnoreCase(field.name, 'loc'))}">

									<tr>
										<td>${field.name}</td>
										<td>${object[field.name]}</td>
									</tr>
								</c:if>
							</c:catch>
						</c:forEach>

					</c:if>

					<%-- 						<c:forEach items="${irdetail}" var="irDetailo" --%>
					<%-- 							varStatus="indexStatus1"> --%>
					<%-- 							<td>${irDetailo.key }</td> --%>
					<%-- 							<td>${irDetailo.value }</td> --%>
					<%-- 						</c:forEach> --%>

					</tr>


				</c:forEach>
			</table>
					



		</div>
	</div>
</body>
</html>