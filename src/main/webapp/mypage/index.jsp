<%@ page import="model.User" %>
<%@ page import="controllers.WelcomeController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
          %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="/mypage/css/mypage.css"/>
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans:700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Russo+One" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Tinos:400i|Vollkorn:400i" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <% User usr = (User) request.getAttribute(WelcomeController.USER_INF);%>
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
    <H3>Моя страница</H3>
    <H3>Информация о пользователе:</H3>
        <br><%=usr.getName()%>
        <br><%=usr.getLastname()%>
        <br><%=usr.getFathername()%>
        <br><%=usr.getNickname()%>
        <br><%=usr.getDob()%>
        <br><%=usr.getAddress().getCity()%>
        <br><%=usr.getAddress().getStreet()%>
        <br><%=usr.getAddress().getHouse()%>
        <br><%=usr.getAddress().getFlat()%>
        <br><%=usr.getTelephone()%>
        <br><%=usr.getEmail()%>
    </div>
</div>

</body>
