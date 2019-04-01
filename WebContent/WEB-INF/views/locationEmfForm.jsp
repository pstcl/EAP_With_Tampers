<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

	<script type="text/javascript">
		function submitform() {

			document.forms['changeDetails'].action = 'changeLocationEmf';
			document.forms['changeDetails'].submit();

		}
	</script>



	<%@include file="dataTablesHeader.jsp"%>


<script type="text/javascript">
function showDiv(divId, element)
{
    document.getElementById(divId).style.display = element.value == "yes" ? 'block' : 'none';
}
</script>

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

	<div class="row">
		<div class="alert alert-success lead">${error}</div>
	</div>
	<form:form method="POST" modelAttribute="changeLocationEmf"
		id="changeDetails">

	



			<form:hidden path="locationMaster"
				value="${changeLocationEmf.locationMaster.locationId}"></form:hidden>
	<div class="row">
			<div class="form-control ">
				<label class="col-lg-4"><b>Location Id</b></label> <b>${changeLocationEmf.locationMaster.locationId}</b>
			</div>
		</div>



		<c:choose>
			<c:when test="${changeLocationEmf.oldLocationEmf != null}">
				<form:hidden path="oldLocationEmf"
					value="${changeLocationEmf.oldLocationEmf.id}"></form:hidden>

				<div class="row">
					<div class="form-control ">

						<label class="col-lg-4">Recent Emf Used In Calculations</label> <b>${changeLocationEmf.oldLocationEmf.externalMF}</b>
					</div>
				</div>

<div class="row">
			<div class="form-control ">
				<label class="col-lg-4"><b>Net WH Sign</b></label> <b>${changeLocationEmf.oldLocationEmf.netWHSign}</b>
			</div>
		</div>

				<div class="row">
					<div class="form-control ">
						<label class="col-lg-2">Ending Date for Emf Used Till Now
							In Calculations</label>
						<form:input type="text" cssClass="date-picker" path="endDate"
							class="form-control input-sm" required="true" />
					</div>
				</div>
			</c:when>

			<c:when test="${changeLocationEmf.oldLocationEmf == null}">
				<div class="row">
					<div class="alert alert-info lead">No Recent Emf Available
						for Calculations</div>

				</div>
			</c:when>
		</c:choose>
		
		<div class="row">
		<div class="form-control">
		<form:select path="setNewEmf" id="setNewEmf" onchange="showDiv('hidden_div', this)">
		<option value="no">No</option>
		<option value="yes">Yes</option>
		</form:select>
		</div>
		</div>
		<div id="hidden_div" style="display: none;">
		<div class="row">
			<div class="form-control">
				<label class="col-lg-2">Enter New Emf for Location: </label>
				<form:input type="text" path="externalMF"
					class="form-control input-sm"></form:input>
			</div>
		</div>

        <div class="row">
		<div class="form-control">
		<form:select path="netWHSign" >
		<option value='-1'>-1</option>
		<option value='1'>1</option>
		</form:select>
		</div>
		</div>
		
		<div class="row">
			<div class="form-control ">
				<label class="col-lg-2">New Emf Start Date At Location</label>
				<form:input type="text" cssClass="date-picker" path="startDate"
					name="startDate" class="form-control input-sm" />
			</div>
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