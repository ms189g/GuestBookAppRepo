<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
<script type="text/javascript">

function editUserNowFxn(id, source) {

	document.getElementById("editUser").value=id;
	
	if(source.localeCompare("edit") !=0){
		document.getElementById("formId").action="deleteUser";
	}
	else {
		document.getElementById("formId").action="editUser";
	}
	
}

</script>
</head>
<body style="background-image: url('/images/Background.jpg');">
<form method="post" action="addEvent" id="formId" enctype="multipart/form-data">
<input type="hidden" name="userId" value="${userName}" />
<input type="hidden" name="firstName" value="${firstName}" />
<input type="hidden" name="lastName" value="${lastName}" />

<div align="right">
	<a href="/guestbook/"><b>Log Off</b></a>
</div>

<center>
			<h1><b>Welcome, ${firstName} ${lastName}</b></h1>
			<h3 color:#FF0000">${userMessage}</h3>
			<table border="1" width="30%" cellpadding="3">
				<thead>
					<tr>
						<th colspan="2">Post an Event</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Event Name</td>
						<td><input type="text"  name="eventName" value="" /></td>
					</tr>
					<tr>
						<td>Event Date</td>
						<td><input type="date"  id="birthday" name="eventDate"></td>
					</tr>
					<tr>
						<td>Notes</td>
						<td><textarea rows="5"  cols="40" name="notes">Add Notes to your Event...</textarea></td>
					</tr>
					<tr>
						<td>Upload an Image</td>
						<td><input type="file"  id="img" name="img" accept="image/*"></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="submit" align="right" value="Post" /></td>
					</tr>
				</tbody>
			</table>
</center>
		
    <div align="center">
        <table border="1" cellpadding="5" width="60%">
            <caption><h2>List of Events</h2></caption>
            <tr>
                <th>Id</th>
                <th>Event Name</th>
                <th>Event Date</th>
                <th>Notes</th>
                <th>Image</th>
            </tr>
            <c:forEach var="user" items="${allUserEvents}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.eventName}" /></td>
                    <td><c:out value="${user.eventDate}" /></td>
                    <td><c:out value="${user.notes}" /></td>
                    <c:if test="${user.fileName != null}">  
                    <td>
                    
                    <a href="/guestbook/readImage?userId=${user.id}">View Image</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
  </form>
</body>
</html>