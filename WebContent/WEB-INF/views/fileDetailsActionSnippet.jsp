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
<c:if
	test="${fileDetails.processingStatus ne 100 and fileDetails.processingStatus ne -100 and fileDetails.txnId ne null}">
	<div class="dropdown">
		<button class="btn btn-primary dropdown-toggle" type="button"
			data-toggle="dropdown">
			Options <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a class="dropdown-item" target="blank"
				href="<c:url value='/viewTxtFile-${fileDetails.txnId}?p=2' />">View
					Text File </a></li>
		</ul>
	</div>

</c:if>
<c:if
	test="${fileDetails.processingStatus eq 100 and fileDetails.txnId ne null}">


	<div class="dropdown">
		<button class="btn btn-primary dropdown-toggle" type="button"
			data-toggle="dropdown">
			Options <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a class="dropdown-item"
				href="<c:url value='/viewRepoFileData-${fileDetails.txnId}?p=2' />">Preview
					File Data</a></li>
			<li><a class="dropdown-item" target="blank"
				href="<c:url value='/viewTxtFile-${fileDetails.txnId}?p=2' />">View
					Text File </a></li>

			<c:choose>

				<c:when test="${fileDetails.fileActionStatus eq 25}">
					<li><a class="dropdown-item"
						href="<c:url value='/approveRepoFile-${fileDetails.txnId}?p=2' />">Approve
							and Submit</a></li>

					<li><a class="dropdown-item"
						href="<c:url value='/removeRepoFile-${fileDetails.txnId}?p=2' />">Remove
							File</a></li>




				</c:when>
				<c:when test="${fileDetails.fileActionStatus eq 100}">
					<sec:authorize
						access="hasAnyRole('ROLE_SLDC_USER','ROLE_SLDC_ADMIN')">

						<li><a class="dropdown-item"
							href="<c:url value='/processRepoFile-${fileDetails.txnId}' />">Process
								File</a></li>
								
						<li><a class="dropdown-item"
							href="<c:url value='/processFileTamper-${fileDetails.txnId}' />">Extract Tamper Log
								</a></li>
								
								
					</sec:authorize>
				</c:when>

			</c:choose>

		</ul>
	</div>
</c:if>
