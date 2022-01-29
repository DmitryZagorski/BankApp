<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding bank</title>
</head>
<body>

<form action="/addBankServlet"method="get">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="commissionForIndividual">Commission For Individual:</label><br>
    <input type="text" id="commissionForIndividual" name="commissionForIndividual"><br><br>
    <label for="commissionForEntity">Commission For Entity:</label><br>
    <input type="text" id="commissionForEntity" name="commissionForEntity"><br>
    <input type="submit" value="Add bank">
</form>

<c:if test="${bankName ne null}">Bank ${bankName} was added successfully</c:if>

<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>


</body>
</html>
