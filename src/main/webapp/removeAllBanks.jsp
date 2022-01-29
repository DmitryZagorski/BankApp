<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Removing all banks</title>
</head>
<body>

<c:choose>
    <c:when test="${allBanks ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
            </tr>
            <c:forEach items="${allbanks}" var = "bank">
                <tr>
                    <td>${bank.id}</td>
                    <td>${bank.name}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="/removeAllBanksServlet"method="get">
            <input type="submit" value="Remove all banks">
        </form>
    </c:when>
    <c:otherwise>
        <c:out value="Not found banks"/>
    </c:otherwise>
</c:choose>

<c:if test="${message ne null}">message</c:if>

</body>
</html>