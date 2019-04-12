<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Locations For Report</title>
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




	<%@include file="dataTablesHeader.jsp"%>


	<script type="text/javascript">
		function submitform() {

			document.forms['changeDetails'].action = 'addReportLocation';
			document.forms['changeDetails'].submit();

		}
	</script>
	
	
	<form:form method="POST" modelAttribute="addReportLocations" id="changeDetails">
	<div class="row">
	<form:hidden path="month"/>
	<form:hidden path="year"/>
	</div>
<c:choose>
			<c:when test="${addedLocations != null}">
	<div class="row">
	
            <h3>Choose to remove Locations:</h3>
            </div>
             <c:forEach varStatus="us" var="location" items="${addedLocations}" >
             <div class="row">
             <div class="form-control">
             <div class="col-lg-offset-2 col-lg-10">
             <form:checkbox path="locations" value="${location.locationId}"/>  ${location.locationId}
             </div>
             </div>
             </div>
             </c:forEach>
</c:when>

</c:choose>
<c:choose>
<c:when test="${pendingLocation != null}">
<div class="row">
            <h3>Choose to Add Locations:</h3>
            </div>
             <c:forEach varStatus="us" var="location" items="${pendingLocation}" >
             <div class="row">
              <div class="form-control">
             <div class="col-lg-offset-2 col-lg-10">
             <form:checkbox path="addingLocations" value="${location.locationId}"/> ${location.locationId} 
             </div>
             </div>
             </div>
             </c:forEach>
</c:when>
</c:choose>
<div class="row">
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">

				<input type="button" class="btn btn-info" onclick="submitform()"
					value="Save Details">
			</div>
		</div>
		</div>
	</form:form>	

</body>
</html>