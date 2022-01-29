<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All functions of application:</title>
</head>
<body>
<br>
<br>
<form action="/bankFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Bank operations">
    </div>
</form>
<form action="/clientFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Client operations">
    </div>
</form>
<form action="/transactionFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Transaction operations">
    </div>
</form>

</body>
</html>
