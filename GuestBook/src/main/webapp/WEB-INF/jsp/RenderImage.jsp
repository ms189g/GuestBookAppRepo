<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Render Image</title>
</head>
<h1 align="center">${message}</h1>
<body style="background-image: url('/images/Background.jpg');">
	<form method="post" action="/guestbook/backToDashboard/">
		<center>
			<img src=data:image/jpeg;base64,${binaryData} /> 
		</center>
	</form>
</body>
</html>