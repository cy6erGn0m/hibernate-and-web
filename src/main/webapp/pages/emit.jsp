<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Emit money</title>
</head>
<body>
    <form method="post" action="/admin/emit">
        <label>
            Amount:
            <input type="text" name="amount"/>
        </label>

        <security:csrfInput/>

        <input type="submit">
    </form>
</body>
</html>

