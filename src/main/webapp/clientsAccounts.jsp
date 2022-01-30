<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients accounts</title>
</head>
<body>

<c:choose>
    <c:when test="${allAccounts ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Bank</th>
                <th>Currency</th>
                <th>Amount Of Money</th>

            </tr>
            <c:forEach items="${allAccounts}" var="account">
                <tr>
                    <td>${account.id}</td>
                    <td>${account.clientName}</td>
                    <td>${account.clientSurname}</td>
                    <td>${account.bankName}</td>
                    <td>${account.currencyName}</td>
                    <td>${account.amountOfMoney}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found accounts"/>
    </c:otherwise>
</c:choose>


<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>