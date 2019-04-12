
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

			document.forms['changeDetails'].action = 'selectMonthForReportLocations';
			document.forms['changeDetails'].submit();

		}
	</script>
	
	<div class="row">
		<div class="alert alert-success lead">${error}</div>
	</div>
	<form:form method="POST" modelAttribute="addReportLocations" id="changeDetails">
	<div class="row">
	
		<div class="col-lg-2">
		Select Month :
		</div>
			<div class="form-group">
	<form:select path="month">
	<option value='1'>January</option>
	<option value='2'>February</option>
	<option value='3'>March</option>
	<option value='4'>April</option>
	<option value='5'>May</option>
	<option value='6'>June</option>
	<option value='7'>July</option>
	<option value='8'>August</option>
	<option value='9'>September</option>
	<option value='10'>October</option>
	<option value='11'>November</option>
	<option value='12'>December</option>
	</form:select>
	</div>
	</div>
	<div class="row">
		
		<div class="col-lg-offset-2 col-lg-2">
		Select Year (YYYY) :
		</div>
			<div class="form-group">
	<form:input path="year" type="number"  required="required"/>
	</div>
	</div>
	<div class="row">
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">

				<input type="button" class="btn btn-info" onclick="submitform()"
					value="Choose locations For My Selected Month">
			</div>
		</div>
		</div>
	</form:form>	
	

</body>
</html>

</body>
</html>