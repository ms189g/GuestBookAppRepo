<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body style="background-image: url('/images/Background.jpg');">
	<form method="post" action="/guestbook/backToDashboard/">
		<center>
			<div class="container">
				<div class="starter-template">
					<h1>${updateMessage}</h1>
					<input type="submit" value="Back To Dashboard" size="20" />
				</div>

			</div>
		</center>
	</form>
</body>

</html>