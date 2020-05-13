<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GuestBook Registration Form</title>
</head>
<body style="background-image: url('/images/Background.jpg');">

<center>
<h1>Edit User Event Details</h1>

<form method="post" action="updateUser">
			
			<input type="hidden" name="id" value="${userDetails.id}" />
			
			<table style="width: 50%">
				<tr>
					<td>User Name: </td>
					<td><input type="text" style="background-color:rgba(0, 0, 0, 0);" name="userName" value="${userDetails.userName}"/></td>
				</tr>
				<tr>
					<td>Event Name: </td>
					<td><input type="text" style="background-color:rgba(0, 0, 0, 0);" name="eventName" value="${userDetails.eventName}"/></td>
				</tr>
				<tr>
					<td>Event Date: </td>
					<td><input type="date" style="background-color:rgba(0, 0, 0, 0);" name="eventDate" value="${userDetails.eventDate}"/></td>
				</tr>
				<tr>
					<td>Event Notes: </td>
					<td><textarea rows="5" style="background-color:rgba(0, 0, 0, 0);" cols="40" name="notes">${userDetails.notes}</textarea>
					</td>
				</tr>
				<tr>
					<td>Image FileName: </td>
					<td><input type="text" style="background-color:rgba(0, 0, 0, 0);" name="fileName" value="${userDetails.fileName}"/></td>
				</tr>
			</table>
			<br/><br/>
			<input type="submit" value="Submit" align="center"/>
		</form>
		</center>
</body>
</html>