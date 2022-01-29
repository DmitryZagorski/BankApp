<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank operations</title>
</head>
<body>
<br>
<br>

<form action="/addBank.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Add bank">
    </div>
</form>
<br>
<form action="allBanksBeforeRemoveServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Remove bank">
    </div>
</form>
<br>
<form action="allBanksBeforeRemovingAllBanksServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Remove all banks">
    </div>
</form>
<br>
<form action="beforeFindClientsOfBankServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Find clients of bank">
    </div>
</form>



</body>
</html>
