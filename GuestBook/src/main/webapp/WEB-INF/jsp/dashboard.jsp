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
	
	if(source.localeCompare("delete") ==0){
		document.getElementById("formId").action="deleteUser";
	}
	else if(source.localeCompare("edit") ==0) {
		document.getElementById("formId").action="editUser";
	}
	else if(source.localeCompare("approve") ==0) {
		document.getElementById("formId").action="approveRecord";
	}
}

</script>
</head>
<body style="background-image: url('/images/Background.jpg');">
<form method="post" action="editUser" id="formId">
<input type="hidden" name="userId" id="editUser" />
<div align="right">
	<a href="/guestbook/">Log Off</a>
</div>

    <div align="center">
        <table border="1" cellpadding="5" width="70%">
            <h2><b>Welcome, ${firstName} ${lastName}</b></h2>
            <caption><h2>List of users</h2></caption>
            <h3 style="color:#FF0000">${message}</h3>
            <tr>
                <th>Id</th>
                <th>User Name</th>
                <th>Event Name</th>
                <th>Event Date</th>
                <th>Notes</th>
                <th>Image File</th>
                <th>Status</th>
                <th>Edit Record</th>
                <th>Delete Record</th>
                <th>Approve Record</th>
            </tr>
            <c:forEach var="user" items="${allUsersList}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.userName}" /></td>
                    <td><c:out value="${user.eventName}" /></td>
                    <td><c:out value="${user.eventDate}" /></td>
                    <td><c:out value="${user.notes}" /></td>
                    <td><c:out value="${user.fileName}" /></td>
                    <td><c:out value="${user.isApproved}" /></td>
                    <td><input type="submit" value="Edit" onclick="editUserNowFxn(${user.id},'edit')"></td>
                    <td><input type="submit" value="Delete" onclick="editUserNowFxn(${user.id},'delete')"></td>
                    <c:if test="${user.isApproved == 'Not Approved'}">  
                    <td><input type="submit" value="Approve" onclick="editUserNowFxn(${user.id},'approve')"></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
  </form>
</body>
</html>