<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose the bank you need</title>
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
                    <td><a href="/removeBankServlet?bankId=${bank.id}">Choose bank</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found banks"/>
    </c:otherwise>
</c:choose>

</body>
</html>
