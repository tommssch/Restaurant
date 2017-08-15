<%@ page import="model.User" %>
<%@ page import="controllers.WelcomeController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controllers.MyPageController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friends</title>
    <link rel="stylesheet" href="/friends/css/friends.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans:700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Russo+One" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Tinos:400i|Vollkorn:400i" rel="stylesheet">
</head>
<body>
<%User t_user = (User) request.getAttribute(WelcomeController.USER_INF);
 ArrayList<User> friends = (ArrayList<User>) request.getAttribute(MyPageController.FRIENDS);%>

<div class="wrapper">
    <div class="menu">
        <nav>
            <ul>
                <form method="get" name="mypage" action="/MyPage">
                    <br><input type="submit" value="Моя страница" >
                    <input hidden name="param" value="1" >
                    <input hidden name="nick" value="<%=t_user.getNickname()%>">
                </form>
                <form method="get" name="friends" action="/MyPage">
                    <br><input type="submit" value="Друзья" >
                    <input hidden name="param" value="2" >
                    <input hidden name="nick" value="<%=t_user.getNickname()%>">
                </form>
                <form method="get" name="message" action="/MyPage">
                    <br><input type="submit" value="Сообщения" >
                    <input hidden name="param" value="3" >
                    <input hidden name="nick" value="<%=t_user.getNickname()%>">
                </form>
                <form method="get" name="menu" action="/MyPage">
                    <br><input type="submit" value="Меню" >
                    <input hidden name="param" value="4" >
                    <input hidden name="nick" value="<%=t_user.getNickname()%>">
                </form>
                <form method="get" name="order" action="/MyPage">
                    <br><input type="submit" value="Оформить заказ" >
                    <input hidden name="param" value="5" >
                    <input hidden name="nick" value="<%=t_user.getNickname()%>">
                </form>
            </ul>
        </nav>
    </div>
    <div class="content">
        <h1>Friends:</h1>
        <% for(User friend:friends){ %>
        <form action="/Friends" method="get">
            <input style="width: 200px;height: 75px" type="submit" value="<%=friend.getNickname()%>" name="fr_nick">
            <input hidden name="us_nick" value="<%=t_user.getNickname()%>">
        </form>
        <% } %>
    </div>

</body>
</html>
