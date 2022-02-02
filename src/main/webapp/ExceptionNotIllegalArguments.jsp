<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not illegal arguments</title>
</head>
<body>
<h1> You entered wrong data.</h1>
<H1> Try to enter all data one more time...</H1>
<br>
<input type="button" onclick="history.back();" value="Return to previous page"/>
<br>
<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>
</body>
</html>