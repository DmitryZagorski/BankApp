<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients accounts</title>
</head>
<body>

<c:choose>
    <c:when test="${clientsAccounts ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>CurrencyId</th>
                <th>AmountOfMoney</th>
                <th>BankId</th>
            </tr>
            <c:forEach items="${clientsAccounts}" var = "account">
                <tr>
                    <td>${account.id}</td>
                    <td>${account.currencyId}</td>
                    <td>${account.amountOfMoney}</td>
                    <td>${account.bankId}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found accounts"/>
    </c:otherwise>
</c:choose>

</body>
</html>