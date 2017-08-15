<%@ page import="model.User" %>
<%@ page import="controllers.WelcomeController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Menu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" type="text/css" href="/order/css/order.css"/>
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans:700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Russo+One" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Tinos:400i|Vollkorn:400i" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <% User usr = (User) request.getAttribute(WelcomeController.USER_INF);
        ArrayList<Menu> menus=(ArrayList<Menu>) request.getAttribute(WelcomeController.ALL_MENU_KEY);%>
    <div class="menu">
        <nav>
            <ul>
                <form method="get" name="mypage" action="/MyPage">
                    <br><input type="submit" value="Моя страница" >
                    <input hidden name="param" value="1">
                    <input hidden name="nick" value="<%=usr.getNickname()%>">
                </form>
                <form method="get" name="friends" action="/MyPage">
                    <br><input type="submit" value="Друзья" >
                    <input hidden name="param" value="2" >
                    <input hidden name="nick" value="<%=usr.getNickname()%>">
                </form>
                <form method="get" name="message" action="/MyPage">
                    <br><input type="submit" value="Сообщения" >
                    <input hidden name="param" value="3" >
                    <input hidden name="nick" value="<%=usr.getNickname()%>">
                </form>
                <form method="get" name="menu" action="/MyPage">
                    <br><input type="submit" value="Меню" >
                    <input hidden name="param" value="4" >
                    <input hidden name="nick" value="<%=usr.getNickname()%>">
                </form>
                <form method="get" name="order" action="/MyPage">
                    <br><input type="submit" value="Оформить заказ" >
                    <input hidden name="param" value="5" >
                    <input hidden name="nick" value="<%=usr.getNickname()%>">
                </form>
            </ul>
        </nav>

    </div>
    <div class="content">
        <table>
            <tr>
                <th>name</th>
                <th>price</th>
                <th>cook time</th>
                <th>description</th>
                <th>take it</th>
            </tr>
            <% int i=0;
                for(Menu menu:menus){%>
            <tr>
                <td align="center" width="200"><%=menu.getName()%></td>
                <td align="center" width="100"><%=menu.getPrice()%></td>
                <td align="center" width="100"><%=menu.getCooktime()%></td>
                <td align="center" width="300"><%=menu.getDescription()%></td>
                <td>
                        <p>Количество:</p><input type="text" form="order" pattern="[1-9]{2}" name="num_of<%=i%>">
                        <input hidden name="order_nm<%=i++%>" value="<%=menu.getName()%>">
                </td>
            </tr>
            <%}%>
        </table>
        <form action="/Order" id="order" method="post">
            <input hidden name="user" value="<%=usr.getId()%>">
            <input type="submit" value="ORDER" style="width: 150px; height:40px">
        </form>
    </div>
</div>

</body>
