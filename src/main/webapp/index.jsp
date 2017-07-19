<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>$Title$</title>
</head>
<body>
Hello, ${header["User-Agent"]}

<c:forEach var="i" items="${headerValues.entrySet()}">
    <p>${i.key} =
        <c:forEach var="j" varStatus="status" items="${i.value}">
            ${j}<c:if test="${not status.last}">,</c:if>
        </c:forEach>
    </p>
</c:forEach>

<p>
    <a href="emit.jsp">Emit money</a>
</p>
<p>
    <a href="view-balance.form">View root balance</a>
</p>
</body>
</html>
