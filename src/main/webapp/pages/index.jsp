<jsp:useBean id="balance" type="ru.levelp.java.junior.haw.BalanceBean" scope="request" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="/styles/main.css">
  </head>
  <body>
    Hello, ${header["User-Agent"]}

    <c:forEach var="i" begin="1" end="${balance.balanceQuantity}">
      <p>(${i}) Balance: ${balance.rootUserBalance}</p>
    </c:forEach>

    <c:if test="${not empty(header['Referer'])}">
      <p>${header['Referer']}</p>
    </c:if>

    <p>User: ${user}</p>

    <p>
      <a href="/admin/emit">Emit money</a>
    </p>
  </body>
</html>
