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

<c:choose>
	<c:when test="${fileDetails.processingStatus eq -100}">
		<tr>
			<td>${indexStatus.index+1 }</td>
			<td class="table-warning"><span> <i class="fa fa-warning"
					style="font-size: 18px; color: red;"> Error:File not readable </i>
			</span></td>
	</c:when>
	<c:when test="${fileDetails.processingStatus eq -500}">
		<tr >
			<td>${indexStatus.index+1 }</td>
			<td class="table-warning"><span> <i class="fa fa-warning"
					style="font-size: 18px; color: red;"> Error:File does not
						contain valid meter no</i>
			</span></td>
	</c:when>
	<c:when
		test="${fileDetails.processingStatus eq -200 or fileDetails.processingStatus eq -300 }">
		<tr >
			<td>${indexStatus.index+1 }</td>
			<td class="table-warning"><span> <i class="fa fa-warning"
					style="font-size: 18px; color: red"><i
						class="fas fa-exclamation-triangle"></i> Error:Meter not found in
						Master Data</i>
			</span></td>
	</c:when>


	<c:when test="${fileDetails.processingStatus eq 100}">
		<tr>
			<td>${indexStatus.index+1 }</td>
			<td class="table-success"><c:choose>
					<c:when test="${fileDetails.fileActionStatus eq 25}">
						<span> <i class="fa fa-warning"
							style="font-size: 18px; color: yellow"> Please approve the
								file </i></span>
					</c:when>
					<c:otherwise>
						<span> <i class="fa fa-warning"
							style="font-size: 18px; color: yellow"> Pending Processing </i>
						</span>
					</c:otherwise>
				</c:choose></td>
	</c:when>
	<c:when test="${fileDetails.processingStatus eq 200}">
		<tr>
			<td>${indexStatus.index+1 }</td>
			<td class="bg-success"><span> <i class="fa fa-warning"
					style="font-size: 18px; color: White"> Text File Processed</i>
			</span></td>
	</c:when>

</c:choose>