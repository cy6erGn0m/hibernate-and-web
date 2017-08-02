<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/login" method="POST">
    <div>
        <label>
            Login
            <input type="text" name="login">
        </label>
    </div>

    <div>
        <label>
            <input type="password" name="password">
        </label>
    </div>

    <security:csrfInput/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <div>
        <input type="submit" value="Login">
    </div>
</form:form>
</body>
</html>
