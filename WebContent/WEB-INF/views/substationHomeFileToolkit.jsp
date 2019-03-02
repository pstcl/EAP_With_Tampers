<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tr>
	<td>${fileDetails.userfileName}</td>


	<td><fmt:formatDate value="${fileDetails.transactionDate}"
			pattern="dd/MM/yyyy  HH:mm:ss" /></td>
	<td><fmt:formatDate value="${fileDetails.createDateTime}"
			pattern="dd/MM/yyyy  HH:mm:ss" /></td>

	<td>${fileDetails.dailyRecordCount}</td>
	<td>${fileDetails.surveyRecordCount}</td>
	<td>${fileDetails.uploadedBy.userNameLabel}</td>
	<td>${fileDetails.approvedBy.userNameLabel}</td>



	<c:choose>
		<c:when test="${fileDetails.processingStatus eq -400}">

			<td><span> <i class="fa fa-warning"
					style="font-size: 18px; color: red;"> File Deleted by User </i>
			</span></td>
		</c:when>
		<c:when test="${fileDetails.processingStatus eq -100}">

			<td><span> <i class="fa fa-warning"
					style="font-size: 18px; color: red;"> Error:File not readable </i>
			</span></td>
		</c:when>
		<c:when
			test="${fileDetails.processingStatus eq -200 or fileDetails.processingStatus eq -300 }">

			<td><span> <i class="fa fa-warning"
					style="font-size: 18px; color: red"> Error:Meter not found in
						Master Data</i>
			</span></td>
		</c:when>
		<c:when test="${fileDetails.processingStatus eq 100}">

			<td><c:choose>
					<c:when test="${fileDetails.fileActionStatus eq 25}">


						<span> <i class="fa fa-warning"
							style="font-size: 18px; color: blue">Please Approve the File</i>
						</span>

					</c:when>
					<c:otherwise>
						<span> <i class="fa fa-warning"
							style="font-size: 18px; color: blue"> Pending Processing </i>
						</span>
					</c:otherwise>
				</c:choose></td>
		</c:when>



		<c:when test="${fileDetails.processingStatus eq 200}">

			<td>Text File Processed</td>
		</c:when>

	</c:choose>

	<td><%@include file="fileDetailsActionSnippet.jsp"%>

	</td>



</tr>