<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client operations</title>
</head>
<body>
<br>
<br>

<form action="beforeAddingClientServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Add new client">
    </div>
</form>
<br>
<form action="allClientsBeforeRemovingServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Remove client">
    </div>
</form>
<br>
<form action="allClientsBeforeTheirRemovingServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Remove all clients">
    </div>
</form>
<br>
<form action="allClientsToFindAccountsServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Find bank accounts of client">
    </div>
</form>
<br>
<form action="allDataToAddAccountServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Add bank account of client">
    </div>
</form>
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
