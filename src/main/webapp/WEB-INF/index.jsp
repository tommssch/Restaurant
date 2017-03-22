<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="controllers.WelcomeController" %>
<%@ page import="java.util.Collection ,
         model.Menu" %>
<%@ page import="model.User" %>
<html>
<head>
    <title>${requestScope.get(WelcomeController.WELCOME_KEY)}</title>
</head>
<body>

<%
    Collection<Menu> menu = (Collection<Menu>) request.getAttribute(WelcomeController.ALL_MENU_KEY);
    Menu md =(Menu) request.getAttribute(WelcomeController.MENU_ID);
    Collection<User> usr=(Collection<User>) request.getAttribute(WelcomeController.GET_ALL_USERS);
%>

<h3>НАШИ ПОЛЬЗОВАТЕЛИ</h3>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>lastname</th>
        <th>fathername</th>
        <th>dob</th>
        <th>email</th>
        <th>nickname</th>
        <th>password</th>
        <th>city</th>
        <th>street</th>
        <th>house</th>
        <th>flat</th>
        <th>telephone</th>
    </tr>
    <tr>
        <td>
            <%
                for (User us:usr) {
            %><tr>
                 <td><%=us.getId()%> </td>
                 <td><%=us.getName()%> </td>
                 <td><%=us.getLastname()%> </td>
                 <td><%=us.getFathername()%> </td>
                 <td><%=us.getDob()%> </td>
                 <td><%=us.getEmail()%> </td>
                 <td><%=us.getNickname()%> </td>
                 <td><%=us.getPassword_hash()%> </td>
                 <td><%=us.getAddress().getCity()%> </td>
                 <td><%=us.getAddress().getStreet()%> </td>
                 <td><%=us.getAddress().getHouse()%> </td>
                 <td><%=us.getAddress().getFlat()%> </td>
                 <td><%=us.getTelephone()%> </td>
            </tr>
            <%
               }
             %>
        </td>
    </tr>
</table>
Прошу,меню!!!!!!
<table>
    <tr>
        <th>id</th>
        <th>price</th>
        <th>cooktime</th>
        <th>description</th>
    </tr>

    <tr>
        <td>
        <%
            for(Menu menu1:menu){
        %>
            <tr>
                <td><%=menu1.getId()%></td>
                <td><%=menu1.getPrice()%></td>
                <td><%=menu1.getCooktime()%></td>
                <td><%=menu1.getDescription()%></td>
            </tr>
        <%
            }
        %>
        </td>
    </tr>
</table>
-------------------------------------------
<%
    if(md==null){
%>
    <h2>IS NULL</h2>
<% }
    else{
%>
<br> <%=md.getId()%></br>
<br> <%=md.getPrice()%></br>
<br> <%=md.getCooktime()%></br>
<br> <%=md.getDescription()%></br>
<% }%>
</body>
</html>
