<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients for transaction</title>
</head>
<body>

<c:choose>
    <c:when test="${clients ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Bank</th>
            </tr>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${client.clientId}</td>
                    <td>${client.clientName}</td>
                    <td>${client.clientSurname}</td>>
                    <td>${client.bankName}</td>>
                    <td><a href="/transactionDataServlet?clientId=${client.clientId}">Choose client</a></td>
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