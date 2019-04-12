<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="myFunction()" style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<div class="sticky-top">
		<nav aria-label="breadcrumb" class="sticky-top">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="javascript:window.location='home'">Home</a></li>

				<li class="breadcrumb-item"><a href="javascript:window.location='substationMaster'">Sub Station Master</a></li>

				<li class="breadcrumb-item active" aria-current="page">Successfully Changed mapping</li>
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


	<%@include file="dataTablesHeader.jsp"%>


	<script>
		$(document).ready(function() {
			function disableBack() {
				window.history.forward()
			}

			window.onload = disableBack();
			window.onpageshow = function(evt) {
				if (evt.persisted)
					disableBack()
			}
		});
	</script>


	<div class="table-responsive">



		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Location Id</th>
					<th>External MF</th>
					<th>Start Date</th>
					<th>End Date</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="location"
					varStatus="indexStatusSubstationList">
					<tr>
						<td>${location.locationMaster.locationId}</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3"
								value="${location.externalMF}" /> MF</td>
						<td><fmt:formatDate value="${location.startDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${location.endDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>

					</tr>


				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>