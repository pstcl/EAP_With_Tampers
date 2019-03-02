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
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<!-- Site Properties -->
<title>Change Your Password</title>

<!-- Stylesheets -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<script
	src="${pageContext.servletContext.contextPath}/static/js/jquery-3.3.1.min.js"
	type="text/javascript"></script>

<script
	src="${pageContext.servletContext.contextPath}/static/js/jqueryUI.min.js"></script>

<script
	src="${pageContext.servletContext.contextPath}/static/js/jquery.dataTables.min.js"></script>



<script
	src="${pageContext.servletContext.contextPath}/static/js/popper.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/js/bootstrap.min.js"></script>

<link
	href="<c:url value='/static/css/datepicker.min.css?vol=2511811.211' />"
	rel="stylesheet" type="text/css" media="all" />

<link
	href="<c:url value='/static/css/dataTables.bootstrap.min.css?vol=251181.2111' />"
	rel="stylesheet" type="text/css" media="all" />

<link
	href="<c:url value='/static/css/bootstrap.min.css?vol=251181.2111' />"
	rel="stylesheet"></link>




</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-faded">
			<a class="navbar-brand" href="javascript:window.location='home'">
				<img alt="PSTCL" width="70px;" height="50px;"
				src="<c:url value='/static/img/pstcl.png' />">
			</a>


			<div id="navbarNavAltMarkup" class="navbar-collapse collapse">


				<ul class="navbar-nav  mr-auto">
					<li><h3>
							<span class="navbar-text"><spring:message
									code="sldc.energyaccounts.title"></spring:message></span>
						</h3></li>
				</ul>
			</div>
		</nav>

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">

				<spring:message code="sldc.energyaccounts.pwdChange.label"></spring:message>

				<hr>
			</div>
		</div>
		<form:form action="firstLoginPwdChange" id="firstLoginPwdChangeForm"  modelAttribute="passwordEntity" method="POST" class="form-horizontal">


			<spring:message code="sldc.energyaccounts.login.username"
				var="usernameLabel" />
			<spring:message code="sldc.energyaccounts.login.password"
				var="passwordLabel" />

			<spring:message code="sldc.energyaccounts.login.newPassword"
				var="newPassword" />
			<spring:message code="sldc.energyaccounts.login.repeatNewPassword"
				var="repeatNewPassword" />


			
			<div class="input-group input-sm">
				<label class="input-group-addon" for="username"><i
					class="fa fa-user"></i></label>
				<form:input type="text" class="form-control" id="username"
					path="username" placeholder="${usernameLabel}" readonly="true" />
				<div class="has-error">
					<form:errors path="username" class="alert alert-danger" />
				</div>
			</div>
			<div class="input-group input-sm">
				<label class="input-group-addon" for="userPassword"><i
					class="fa fa-lock"></i></label>
				<form:input type="password" class="form-control" id="userPassword"
					path="userPassword" placeholder="${passwordLabel}"  />

				<div class="has-error">
					<form:errors path="userPassword" class="alert alert-danger" />
				</div>
			</div>



			<div class="input-group input-sm">
				<label class="input-group-addon" for="newPassword1"><i
					class="fa fa-lock"></i></label>
				<form:input type="password" class="form-control" id="newPassword1"
					path="newPassword1" placeholder="${newPassword}"  />

				<div class="has-error">
					<form:errors path="newPassword1" class="alert alert-danger" />
				</div>
			</div>
			<div class="input-group input-sm">
				<label class="input-group-addon" for="newPassword2"><i
					class="fa fa-lock"></i></label>
				<form:input type="password" class="form-control" id="newPassword2"
					path="newPassword2" placeholder="${repeatNewPassword}"  />

				<div class="has-error">
					<form:errors path="newPassword2" class="alert alert-danger" />
				</div>
			</div>


			<div class="form-actions">
				<input id="btnSubmit" type="button"
					class="btn btn-block btn-primary btn-default" value="Submit">
			</div>
		</form:form>
	</div>
	<script type="text/javascript">
    $(function () {
        $("#btnSubmit").click(function () {
            var password = $("#newPassword1").val();
            var confirmPassword = $("#newPassword2").val();
            if (password != confirmPassword) {
                alert("Passwords do not match.");
             
            }
            else
            {
    		document.forms['firstLoginPwdChangeForm'].submit();
            }
        });
    });
</script>

</body>
</html>