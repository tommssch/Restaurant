package controllers;

import dao.MenuDao;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet("/MyPage")
public class MyPageController extends HttpServlet {

    private UserDao ud;
    public static final String ALL_MESS="MESSAGES";
    public static final String USERS_SEND="SEND";
    public static final String FRIENDS="FRIENDS";
    private MessageDao msgd;
    private MenuDao menud;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ud= (UserDao) config.getServletContext().getAttribute("UserDao");
        msgd= (MessageDao) config.getServletContext().getAttribute("MessageDao");
        menud= (MenuDao) config.getServletContext().getAttribute("MenuDao");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<User> t_user= (ArrayList<User>)ud.get_cond("nickname",req.getParameter("nick"));
        ArrayList<User> users =(ArrayList<User>)ud.getAll();

        if(t_user.size()!=0)
            users.remove(users.indexOf(t_user.get(0)));

        switch (req.getParameter("param")){
            case "1":
                req.setAttribute(WelcomeController.USER_INF,t_user.get(0));
                req.getRequestDispatcher("/mypage/").forward(req,resp);
                break;
            case "2":
                req.setAttribute(WelcomeController.USER_INF,t_user.get(0));
                req.setAttribute(FRIENDS,users);
                req.getRequestDispatcher("/friends/").forward(req,resp);
                break;
            case "3":
                ArrayList<Message> messages=(ArrayList<Message>) msgd.getAll_cond("user_to_id",t_user.get(0).getId());
                messages.addAll(msgd.getAll_cond("user_from_id",t_user.get(0).getId()));
                messages.sort(Comparator.comparing(Message::getSend_datetime));
                req.setAttribute(WelcomeController.USER_INF,t_user.get(0));
                req.setAttribute(USERS_SEND,users);
                req.setAttribute(ALL_MESS,messages);
                req.getRequestDispatcher("/messages/").forward(req,resp);
                break;
            case "4":
                req.setAttribute(WelcomeController.USER_INF,t_user.get(0));
                req.setAttribute(WelcomeController.ALL_MENU_KEY,menud.getAll());
                req.getRequestDispatcher("/menu/").forward(req,resp);
                break;
            case "5":
                req.setAttribute(WelcomeController.USER_INF,t_user.get(0));
                req.setAttribute(WelcomeController.ALL_MENU_KEY,menud.getAll());
                req.getRequestDispatcher("/order/").forward(req,resp);
                break;
            default:
                req.setAttribute(WelcomeController.USER_INF,t_user.get(0));
                req.getRequestDispatcher("/mypage/").forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
