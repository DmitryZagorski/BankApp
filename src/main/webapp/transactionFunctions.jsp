<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>
<br>

<form action="transactionChooseClientServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Add new transaction">
    </div>
</form>
<br>
<form action="clientToFindTransactionServlet">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Find transactions of client by date">
    </div>
</form>

</body>
</html>
