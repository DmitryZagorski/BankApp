<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Removing bank</title>
</head>
<body>

<c:choose>
    <c:when test="${allBanks ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>CommissionForIndividual</th>
                <th>CommissionForEntity</th>
            </tr>
            <c:forEach items="${allBanks}" var="bank">
                <tr>
                    <td>${bank.id}</td>
                    <td>${bank.name}</td>
                    <td>${bank.commissionForIndividual}</td>
                    <td>${bank.commissionForEntity}</td>
                    <td><a href="/removeBankServlet?bankId=${bank.id}">Remove bank</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found banks"/>
    </c:otherwise>
</c:choose>
<c:if test="${removedBankId ne null}">Bank with id ${removedBankId} was removed successfully</c:if>

<form action="/applicationFunctions.jsp">
    <div class="col-sm-12">
        <input class="send_btn" type="submit" value="Return to main menu">
    </div>
</form>

</body>
</html>
