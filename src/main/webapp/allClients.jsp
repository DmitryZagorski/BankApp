<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All clients</title>
</head>
<body>

<c:choose>
    <c:when test="${allClients ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Bank</th>
            </tr>
            <c:forEach items="${allClients}" var="client">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.clientName}</td>
                    <td>${client.clientSurname}</td>>
                    <td>${client.bankName}</td>>
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