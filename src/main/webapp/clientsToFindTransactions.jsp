<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find transaction</title>
</head>
<body>

<h1>Find transaction</h1>
<br>
<form action="/findTransactionsServlet" method="get">
    <br>
    <td>Client:</td>
    <br>
    <c:forEach items="${allClients}" var="client">
        <div class="col-md-12 text-center">
            <input type="radio" id=${client.clientId} name="clientId"
                   value="${client.clientId}">${client.clientName}</input><br>
        </div>
    </c:forEach>
    <br>
    <label for="creationDate">Date before:</label><br>
    <input type="date" id="creationDate" name="creationDate">
    <br>
    <input type="submit" value="Find transactions">
</form>
<br>
<br>
<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>
