<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="controllers.WelcomeController" %>
<%@ page import="java.util.Collection ,
         model.Menu" %>
<html>
<head>
    <title>${requestScope.get(WelcomeController.WELCOME_KEY)}</title>
</head>
<body>

<form action="/" method="post">
    <p>Пожалуйста, введите Никнейм и пароль</p>
    <br><input type="text" name="nick" placeholder="nickname" required></br>
    <br><input type="password" name="pass" placeholder="password" required></br>
    <br><input type="submit" name="button" value="Enter"></br>
    <br><input type="button" onclick='location.href="/registration/"' value="Registration"/></a>
    <br><span style="color: red"><%=request.getAttribute("err")%></span></br>
</form>
<%
   Collection<Menu> menu = (Collection<Menu>)request.getAttribute(WelcomeController.ALL_MENU_KEY);
%>

<h3> Наше Меню!</h3>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>cook time</th>
        <th>description</th>
    </tr>
    <%
        for(Menu menu1:menu){
    %>
    <tr>
        <td align="center" width="100"><%=menu1.getId()%></td>
        <td align="center" width="200"><%=menu1.getName()%></td>
        <td align="center" width="100"><%=menu1.getPrice()%></td>
        <td align="center" width="100"><%=menu1.getCooktime()%></td>
        <td align="center" width="300"><%=menu1.getDescription()%></td>
    </tr>
    <%} %>

</table>
</body>
