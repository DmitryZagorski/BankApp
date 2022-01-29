<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Removing all clients</title>
</head>
<body>

<c:choose>
    <c:when test="${allClients ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${allClients}" var = "client">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.name}</td>
                    <td>${client.surname}</td>
                    <td>${client.statusId}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="/removeAllClientsServlet"method="get">
            <input type="submit" value="Remove all clients">
        </form>
    </c:when>
    <c:otherwise>
        <c:out value="Not found clients"/>
    </c:otherwise>
</c:choose>

<c:if test="${message ne null}">message</c:if>

</body>
</html>