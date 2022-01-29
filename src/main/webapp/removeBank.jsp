<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Removing bank</title>
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
                    <td><a href="/removeBankServlet?bankId=${bank.id}">Remove bank</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found banks"/>
    </c:otherwise>
</c:choose>
<c:if test="${removedBankId ne null}">Bank with id ${removedBankId} was removed successfully</c:if>

</body>
</html>
