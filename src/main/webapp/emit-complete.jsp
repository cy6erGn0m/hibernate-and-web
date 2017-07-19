<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="balance" type="ru.levelp.java.junior.haw.BalanceBean2" scope="request" />

<html>
<head>
    <title>Money emission</title>
</head>
<body>
    <h1>Success</h1>

    ${requestScope["amount"]}

    <p>Emitted ${balance.delta}, current: ${balance.value}</p>
</body>
</html>
