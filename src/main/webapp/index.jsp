<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="controllers.WelcomeController" %>
<%@ page import="java.util.Collection ,
         model.Menu" %>
<html>
<head>
    <title>${requestScope.get(WelcomeController.WELCOME_KEY)}</title>
</head>
<body>


<form action="/Welcome" method="post">
    <br><input type="text" name="nick" placeholder="nickname"></br>
    <br><input type="password" name="pass" placeholder="password"></br>
    <br><input type="submit" name="button" value="Enter"></br>
    <br><a href="/registration">Registration</a>
</form>

<form action="/Welcome" method="get">
    <br><input type="submit" name="button" value="Enter"></br>
</form>
<%
   Menu menu = (Menu)request.getAttribute(WelcomeController.ALL_MENU_KEY);
%>

<%= menu==null %>
<table>
    <tr>
        <th>id</th>
        <th>price</th>
        <th>cook_time</th>
        <th>description</th>
    </tr>


</table>

<form action="/" method="post" >
   <br> <input name="name_id" placeholder="name_ID"/></br>
    <br> <input name="price" type="number" placeholder="price"/></br>
    <br> <input name="description"  placeholder="description"/></br>
    <br> <input type="submit" name="bn" value="add"/> </br>
</form>

<form action="/" method="post" name="rem">
    <br> <input name="name id" placeholder="id"/></br>
    <br> <input type="submit" name="bn" value="remove"/> </br>
</form>


</body>
</html>
