<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


</head>

<body>


	<script type="text/javascript">
	
	
	
	function submitform() {
		
		
		document.forms['changeDetails'].action = 'saveMeterDetails';
		document.forms['changeDetails'].submit();

	}
</script>

	<%@include file="authheader.jsp"%>
	<%@include file="dataTablesHeader.jsp"%>
	
	
		<script type="text/javascript">
			$(function() {
				$('.date-picker').datepicker({
					yearRange : '1950:2100',
					changeMonth : true,
					changeYear : true,
					showButtonPanel : false,
					dateFormat : "dd/mm/yy"

				});
			});
		</script>

	<form:form method="POST"
		modelAttribute="changeMeterSnippet" id="changeDetails">
<div class="row">
<div class="alert alert-success lead">
  ${error}</div>

</div>
		<div class="row">
		
		
			<div class="form-control ">
				<label class="col-lg-2">Meter Sr No</label> <b>${changeMeterSnippet.meterMaster.meterSrNo}</b>
			</div>
		</div>
		<form:hidden path="meterMaster" value="${changeMeterSnippet.meterMaster.meterSrNo}"></form:hidden>
		<form:hidden path="oldMeterLocationMap" value="${changeMeterSnippet.oldMeterLocationMap.id}"></form:hidden>
		<div class="row">
			<div class="form-control ">
				End Date
				<form:input type="text" cssClass="date-picker" path="endDate" 
					class="form-control input-sm"  required="true" />
			</div>
		</div>






		<%@include file="locationFilterView.jsp"%>




		<div class="row">
			<div class="form-control ">
				<label class="col-lg-2">Start Date of Meter At New Location</label>
				<form:input type="text" cssClass="date-picker" path="startDate" name="startDate"
					class="form-control input-sm" />
			</div>
		</div>


		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
			
				<input type="button" class="btn btn-info" onclick="submitform()"
					value="Save Details">
			</div>
		</div>

	</form:form>


</body>
</html>