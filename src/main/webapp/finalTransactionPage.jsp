<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transaction</title>
</head>
<body>
<br>
<h1> Transaction </h1>
<br>
<br>
<c:out value="Transaction id: ${id}"/>
<br>
<c:out value="Client: ${clientName} ${clientSurname}"/>
<br>
<c:out value="Sender Bank Account: ${senderAccountId}"/>
<br>
<c:out value="Recipient Bank Account: ${recipientAccountId}"/>
<br>
<c:out value="Currency: ${currency}"/>
<br>
<c:out value="Amount of money: ${amountOfMoney}"/>
<br>
<c:out value="Creation date: ${creationDate}"/>
<br>
<br>
<br>
<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>