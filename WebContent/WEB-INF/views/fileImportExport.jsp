<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script type="text/javascript">
	function viewDetails(modalid) {
		$(modelid).modal('toggle');

	}
</script>

<%-- <c:url value='/previewOilReport-${oilReport.id}' /> --%>


<title>Daily Data for File-${ cmriModel.fileMaster.userfileName}</title>
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
				buttons : [ 'copyHtml5', 'excelHtml5', 'csvHtml5', 'pdfHtml5' ]
			});

		});
	</script>
	<div class="sticky-top">
		<nav aria-label="breadcrumb" class="sticky-top">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="javascript:window.location='home'">Home</a></li>

				<li class="breadcrumb-item active" aria-current="page">Energy
					Import Export</li>
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
		<span class="lead">Energy Meters Master Data for File-${ cmriModel.fileMaster.userfileName},
			Generated at:<fmt:formatDate
								value="${cmriModel.fileMaster.transactionDate}"
								pattern="dd/MM/yyyy HH:mm:ss" /></span>
		<div class="card"></div>

		<div id="da111ilyTxnTable_wrapper" class="table-responsive ">



			<table id="dailyTxnTable"
				class="table table-striped table-bordered table-hover">

				<thead>
					<tr>
						<th>Date Time of Reading</th>
						<th>Record No.</th>
						<th>Import(Wh)</th>
						<th>Export(Wh)</th>
						<th>Remarks</th>
					</tr>
				</thead>
				<c:forEach items="${cmriModel.dailyTransactions}"
					var="dailyTransactionForMeter" varStatus="indexStatus">

					<tr>
						<td><fmt:formatDate
								value="${dailyTransactionForMeter.transactionDate}"
								pattern="dd/MM/yyyy HH:mm:ss" /></td>
						<td>${dailyTransactionForMeter.recordNo}</td>
						<td>${dailyTransactionForMeter.importWHF}</td>
						<td>${dailyTransactionForMeter.exportWHF}</td>
						<td>${dailyTransactionForMeter.remarks}</td>
				</c:forEach>
			</table>

			<div class="row">
				<div class="form-actions floatRight">
					<sec:authorize
						access="hasAnyRole('ROLE_SS_JE','ROLE_SS_AE','ROLE_SR_XEN','ROLE_SE')">
						<c:choose>

							<c:when test="${cmriModel.fileMaster.fileActionStatus eq 25}">

								<a class="btn btn-info"
									href="<c:url value='/approveRepoFile-${cmriModel.fileMaster.txnId}?p=1' />">Approve
									and Submit</a>

								<a class="btn btn-info"
									href="<c:url value='/removeRepoFile-${cmriModel.fileMaster.txnId}?p=1' />">Remove
									File</a>
							</c:when>

						</c:choose>
					</sec:authorize>
				</div>
			</div>


		</div>
	</div>


</body>
</html>