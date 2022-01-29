<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank operations</title>
</head>
<body>
<br>
<br>

<form action="addBankServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Add new bank">
    </div>
</form>
<br>
<form action="">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Remove bank">
    </div>
</form>
<br>
<form action="">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Remove all banks">
    </div>
</form>
<br>
<form action="">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Find customers of bank">
    </div>
</form>



</body>
</html>
