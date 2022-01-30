<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients Of Bank</title>
</head>
<body>
<br>

<form action="/addClientsAccountServlet" method="get">
    <td>Client:</td>
    <br>
    <c:forEach items="${allClients}" var="client">
        <div class="col-md-12 text-center">
            <input type="radio" id=${client.name} name="clientId"
                   value="${client.id}">${client.name}</input><br>
        </div>
    </c:forEach>
    <br>
    <td>Bank:</td>
    <br>
    <c:forEach items="${allBanks}" var="bank">
        <div class="col-md-12 text-center">
            <input type="radio" id=${bank.name} name="bankId"
                   value="${bank.id}">${bank.name}</input><br>
        </div>
    </c:forEach>
    <br>
    <td>Currency:</td>
    <br>
    <c:forEach items="${allCurrency}" var="currency">
        <div class="col-md-12 text-center">
            <input type="radio" id=${currency.name} name="currencyId"
                   value="${currency.id}">${currency.name}</input><br>
        </div>
    </c:forEach>
    <label for="amountOfMoney">Amount of money:</label><br>
    <input type="text" id="amountOfMoney" name="amountOfMoney"><br><br>
    <input type="submit" value="Add bank account">
</form>

<c:if test="${message ne null}">${message}</c:if>
<br>
<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>

