package controllers;

import dao.MessageDao;
import dao.UserDao;
import model.Message;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet("/Message")
public class MessageSendController extends HttpServlet {
    private MessageDao msgd;
    private UserDao ud;
    @Override
    public void init(ServletConfig config) throws ServletException {
        msgd=(MessageDao) config.getServletContext().getAttribute("MessageDao");
        ud=(UserDao) config.getServletContext().getAttribute("UserDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        User user_to=((ArrayList<User>)ud.get_cond("nickname",req.getParameter("to"))).get(0);
        ArrayList<User> t_user= (ArrayList<User>)ud.get_cond("nickname",req.getParameter("nick"));
        ArrayList<User> users_send =(ArrayList<User>)ud.getAll();


        if(t_user.size()!=0)
            users_send.remove(users_send.indexOf(t_user.get(0)));

        String message=req.getParameter("message");
        if(!message.equals(""))
            msgd.save(new Message(0,t_user.get(0),user_to,message,LocalDateTime.now()));

        ArrayList<Message> messages=(ArrayList<Message>) msgd.getAll_cond("user_to_id",t_user.get(0).getId());
        messages.addAll(msgd.getAll_cond("user_from_id",t_user.get(0).getId()));
        messages.sort(Comparator.comparing(Message::getSend_datetime));

        req.setAttribute(MyPageController.ALL_MESS,messages);
        req.setAttribute(MyPageController.USERS_SEND,users_send);
        req.setAttribute(WelcomeController.USER_INF,t_user.get(0));

        req.getRequestDispatcher("/messages/").forward(req,resp);
    }
}
