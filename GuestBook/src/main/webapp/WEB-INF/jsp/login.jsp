<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuestBook Login</title>
</head>
<body style="background-image: url('/images/Background.jpg');">
	<h1 align="center">${message}</h1>
	<form method="post" action="validateUser">
		<center>
			<table border="1" width="30%" cellpadding="3">
				<thead>
					<tr>
						<th colspan="2">Login Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>User Name</td>
						<td><input type="text" name="userName" value="" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td><input type="reset" value="Reset" /></td>
						<td><input type="submit" value="Login" /></td>
					</tr>
					<tr>
						<td colspan="2">Yet Not Registered!! <a href="addUser">Register
								Here</a></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
</body>
</html>