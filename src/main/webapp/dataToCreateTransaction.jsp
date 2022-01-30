<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding of transaction</title>
</head>
<body>

<h1>Adding of transaction</h1>
<br>
<form action="/addTransactionServlet" method="get">
    <br>
    <td>Sender bank account:</td>
    <br>
    <c:forEach items="${senderAccounts}" var="account">
        <div class="col-md-12 text-center">
            <input type="radio" id=${account.id} name="accountId"
                   value="${account.id}">${account.id}</input><br>
        </div>
    </c:forEach>
    <br>
    <td>Recipient bank account:</td>
    <br>
    <c:forEach items="${allAccounts}" var="recipientAccount">
        <div class="col-md-12 text-center">
            <input type="radio" id=${recipientAccount.id} name="recipientAccountId"
                   value="${recipientAccount.id}">${recipientAccount.id}</input><br>
        </div>
    </c:forEach>
    <br>
    <label for="amountOfMoney">Amount of money:</label><br>
    <input type="text" id="amountOfMoney" name="amountOfMoney">
    <br>
    <input type="submit" value="Add transaction">
</form>

<c:if test="${clientName ne null}">Client ${clientName} was added successfully</c:if>

<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>
