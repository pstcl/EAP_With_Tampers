<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Meter Details</title>
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

function initAllList(){
	$('meterMake').find('option').remove().end();
	$('meterType').find('option').remove().end();
	$('meterCategory').find('option').remove().end();
	
	$.post("/EAP/getMeterListModel", {
		
	}, populateAllList).done(function() {
		//alert( "Employee Added" );
	}).fail(function(data, status, er) {
		alert("Couldn't load location information !" + data + er);
	});
	
}

function populateAllList(data, status){
	$.each(data.meterMake, function(idx, obj) {
		//alert(obj.circleName);
		$('#meterMake').append(
				$("<option></option>").attr("value", obj)
						.text(obj));
	});
	$.each(data.meterCategory, function(idx, obj) {
		//alert(obj.circleName);
		$('#meterCategory').append(
				$("<option></option>").attr("value", obj)
						.text(obj));
	});
	$.each(data.meterType, function(idx, obj) {
		//alert(obj.circleName);
		$('#meterType').append(
				$("<option></option>").attr("value", obj)
						.text(obj));
	});
}
</script>
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


<script>
		$(document).ready(function() {
		
			initAllList();
		});
	</script>


	<%@include file="dataTablesHeader.jsp"%>


	<script type="text/javascript">
		function submitform() {

			document.forms['changeDetails'].action = 'addMeterDetails';
			document.forms['changeDetails'].submit();

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
	

	<%@include file="dataTablesHeader.jsp"%>
	<div class="row">
		<div class="alert alert-success lead">${meter.error}</div>
	</div>
	<form:form method="POST" modelAttribute="meter"
		id="changeDetails">
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Meter Sr No</b></label>
        <form:input path="meterSrNo" />
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Meter Type</b></label>
        
        <form:select id="meterType" path="meterType" >
	</form:select>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Meter Make</b></label>
        
        <form:select id="meterMake" path="meterMake" >
	</form:select>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Meter Category</b></label>
        
        <form:select id="meterCategory" path="meterCategory" >
	</form:select>
		</div>
		</div>
		
		
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Grid Loss Factor</b></label>
        <form:input path="gridLossFactor" type="number"/>
		</div>
		</div>
		
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Installed Date</b></label>
        <form:input type="text" cssClass="date-picker" path="installedDate"
					name="installedDate" class="form-control input-sm" />
		</div>
		</div>
		
        <div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Internal MF</b></label>
        <form:input path="internalMF" type="number"/>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>CT Accuracy</b></label>
        <form:input path="CTAccuracy" type="text"/>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>PT Accuracy</b></label>
        <form:input path="PTAccuracy" type="text"/>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Internal CT Ratio</b></label>
        <form:input path="internalCTRatio" type="text"/>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Internal PT Ratio</b></label>
        <form:input path="internalPTRatio" type="text"/>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Id</b></label>
        <form:input path="id" type="number"/>
        </div>
		</div>
		
		<input type="button" class="btn btn-info" onclick="submitform()"
					value="Save Details">
</form:form>
</body>
</html>