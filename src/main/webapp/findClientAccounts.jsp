<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients Of Bank</title>
</head>
<body>

<c:choose>
    <c:when test="${clients ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.name}</td>
                    <td>${client.surname}</td>
                    <td>${client.statusId}</td>
                    <td><a href="/FindClientsAccounts?clientId=${client.id}">Choose client</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found clients"/>
    </c:otherwise>
</c:choose>

<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>

