<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Render Image</title>
<script type="text/javascript">

function loadImage(){
	
	var img = document.createElement('img');
	img.src = 'data:image/jpeg;base64,' + ${binaryData};
	document.body.appendChild(img);
	
}

</script>
</head>
<h1 align="center">${message}</h1>
<body style="background-image: url('/images/Background.jpg');" onload="loadImage()">
</body>
</html>