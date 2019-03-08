<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
		$(document).ready(function() {
			$('#irdetails').DataTable({
				dom : 'Bfrtip',
				"paging" : true,
				"ordering" : true,
				buttons : [ 'copyHtml5', 'excelHtml5', 'csvHtml5', 'pdfHtml5' ],
			 orientation: 'landscape',
             pageSize: 'A4'
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

			<thead>


				<tr>
				    <th>Property </th>
				    <th>Value</th>
				
				</tr>
			</thead>

			<c:forEach items="${irdetails}"
				var="irDetail" varStatus="indexStatus">

				<tr>
				
				<c:forEach items="${irdetail}"
				var="irDetailo" varStatus="indexStatus1">
				<td>${irDetailo.key }</td>
				<td>${irDetailo.value }</td>
				</c:forEach>
				
				   </tr>
					
					
			</c:forEach>
		</table>



	</div>
</div>
</body>
</html>