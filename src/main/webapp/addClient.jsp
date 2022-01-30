<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding of client</title>
</head>
<body>

<form action="/addClientServlet" method="get">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="surname">Surname:</label><br>
    <input type="text" id="surname" name="surname"><br>
    <td>Status:</td>
    <br>
    <c:forEach items="${allStatuses}" var="status">
        <div class="col-md-12 text-center">
            <input type="radio" id=${status.id} name="statusId"
                   value="${status.id}">${status.name}</input><br>
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
    <input type="text" id="amountOfMoney" name="amountOfMoney">
    <br>
    <td>Bank:</td>
    <br>
    <c:forEach items="${allBanks}" var="bank">
        <div class="col-md-12 text-center">
            <input type="radio" id=${bank.name} name="bankId"
                   value="${bank.id}">${bank.name}</input><br>
        </div>
    </c:forEach>
    <input type="submit" value="Add client">
</form>

<c:if test="${clientName ne null}">Client ${clientName} was added successfully</c:if>

<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>
