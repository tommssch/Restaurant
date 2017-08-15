<%@ page import="model.User" %>
<%@ page import="controllers.WelcomeController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controllers.FriendsController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
          %>
<html>
<head>
    <title>Friend</title>
    <link rel="stylesheet" type="text/css" href="/friend_page/css/friend_page.css"/>
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans:700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Russo+One" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Tinos:400i|Vollkorn:400i" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <% User t_usr = (User) request.getAttribute(WelcomeController.USER_INF);
        User friend= (User) request.getAttribute(FriendsController.FRIEND_INF);%>
    <div class="menu">
        <nav>
            <ul>
                <form method="get" name="mypage" action="/MyPage">
                     <br><input type="submit" value="Моя страница" >
                     <input hidden name="param" value="1">
                     <input hidden name="nick" value="<%=t_usr.getNickname()%>">
                </form>
                <form method="get" name="friends" action="/MyPage">
                    <br><input type="submit" value="Друзья" >
                    <input hidden name="param" value="2" >
                    <input hidden name="nick" value="<%=t_usr.getNickname()%>">
                </form>
                <form method="get" name="message" action="/MyPage">
                     <br><input type="submit" value="Сообщения" >
                    <input hidden name="param" value="3" >
                    <input hidden name="nick" value="<%=t_usr.getNickname()%>">
                </form>
                <form method="get" name="menu" action="/MyPage">
                     <br><input type="submit" value="Меню" >
                    <input hidden name="param" value="4" >
                    <input hidden name="nick" value="<%=t_usr.getNickname()%>">
                </form>
                <form method="get" name="order" action="/MyPage">
                     <br><input type="submit" value="Оформить заказ" >
                    <input hidden name="param" value="5" >
                    <input hidden name="nick" value="<%=t_usr.getNickname()%>">
                </form>
            </ul>
        </nav>
    </div>
<div class="content">
    <H3>Статус:Друг</H3>
    <H3>Информация о пользователе:</H3>
        <br><%=friend.getName()%>
        <br><%=friend.getLastname()%>
        <br><%=friend.getFathername()%>
        <br><%=friend.getNickname()%>
        <br><%=friend.getDob()%>
        <br><%=friend.getAddress().getCity()%>
        <br><%=friend.getAddress().getStreet()%>
        <br><%=friend.getAddress().getHouse()%>
        <br><%=friend.getAddress().getFlat()%>
        <br><%=friend.getTelephone()%>
        <br><%=friend.getEmail()%>
    </div>
</div>

</body>
