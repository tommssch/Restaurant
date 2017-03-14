<%@ page contentType="text/html;charset=UTF-8" language="java"
import="controllers.WelcomeController" %>
<html>
<head>
    <title>${requestScope.get(WelcomeController.WELCOME_KEY)}</title>
</head>
<body>

<%=request.getAttribute(WelcomeController.WELCOME_KEY)%>
Hello ,World!!!
</body>
</html>
