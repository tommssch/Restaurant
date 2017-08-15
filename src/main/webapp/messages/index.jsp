<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page import="controllers.WelcomeController" %>
<%@ page import="model.Message" %>
<%@ page import="controllers.MyPageController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/messages/css/message.css"/>
    <title>Messages</title>
</head>
<body>
<% ArrayList<User> users=(ArrayList<User>) request.getAttribute(MyPageController.USERS_SEND);
   ArrayList<Message> messages_to=(ArrayList<Message>) request.getAttribute(MyPageController.ALL_MESS);
   User t_user = (User) request.getAttribute(WelcomeController.USER_INF);%>

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
        <form action="/Message" method="post">
            <p>Выберите пользователя: <select name="to">
                <% for (User usr:users) {%>
                <option><%= usr.getNickname()%></option>
                <%}%>
            </select></p>
            <br><textarea type="text" rows="10" cols="80" name="message" maxlength="1000" placeholder="Enter message"></textarea>
            <input hidden name="nick" value="<%=t_user.getNickname()%>">
            <input type="submit"  name="button" value="Send" class="text"/>
        </form>

        <br>
        <table>
            <tr>
                <th>Date Send</th>
                <th>From</th>
                <th>To</th>
                <th>Message</th>
            </tr>
                <%
                    for(int i=messages_to.size()-1;i>-1;i--){
                %>
            <tr>
                <td align="center" width="300"><%=messages_to.get(i).getSend_datetime()%></td>
                <td align="center" width="100"><%=messages_to.get(i).getUser_from().getNickname()%></td>
                <td align="center" width="100"><%=messages_to.get(i).getUser_to().getNickname()%></td>
                <td align="center" width="300"><%=messages_to.get(i).getMessage()%></td>
            </tr>
                <%} %>
    </div>
</div>
</body>
</html>
