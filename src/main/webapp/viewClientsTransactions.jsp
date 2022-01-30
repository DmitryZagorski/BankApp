<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients transactions</title>
</head>
<body>
<br>
<h1>Clients transactions</h1>

<c:choose>
    <c:when test="${transactions ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Client name</th>
                <th>Client surname</th>
                <th>Sender bank account</th>
                <th>Recipient bank account</th>
                <th>Currency</th>
                <th>Amount of money</th>
                <th>Creation date</th>
            </tr>
            <c:forEach items="${transactions}" var="transaction">
                <tr>
                    <td>${transaction.id}</td>
                    <td>${transaction.clientName}</td>
                    <td>${transaction.clientSurname}</td>
                    <td>${transaction.senderBankAccountId}</td>
                    <td>${transaction.recipientBankAccountId}</td>
                    <td>${transaction.currencyName}</td>
                    <td>${transaction.amountOfMoney}</td>
                    <td>${transaction.creationDate}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found banks"/>
    </c:otherwise>
</c:choose>

<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>
