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
<h1>GuestBook Register Form</h1>

<form method="post" action="registerUser">
			<table style="with: 50%">
				<tr>
					<td>First Name: </td>
					<td><input type="text"  name="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name: </td>
					<td><input type="text"  name="lastName" /></td>
				</tr>
				<tr>
					<td>Age: </td>
					<td><input type="text"  name="age" /></td>
				</tr>
				<tr>
					<td>UserName: </td>
					<td><input type="text"  name="userName" /></td>
				</tr>
					<tr>
					<td>Password: </td>
					<td><input type="password"  name="password" /></td>
				</tr>
				<tr>
					<td>Address: </td>
					<td><textarea rows="5"  cols="40" name="address">Enter your address..</textarea></td>
				</tr>
			</table>
			<input type="submit" value="Submit" align="right"/>
		</form>
		</center>
</body>
</html>