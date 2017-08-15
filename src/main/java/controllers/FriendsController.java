package controllers;


import dao.UserDao;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Friends")
public class FriendsController extends HttpServlet {
    private UserDao ud;
    public static String FRIEND_INF="FRIEND_INF";
    @Override
    public void init(ServletConfig config) throws ServletException {
        ud= (UserDao) config.getServletContext().getAttribute("UserDao");

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<User> friend= (ArrayList<User>)ud.get_cond("nickname",req.getParameter("fr_nick"));
        ArrayList<User> t_user= (ArrayList<User>)ud.get_cond("nickname",req.getParameter("us_nick"));

        req.setAttribute(FRIEND_INF,friend.get(0));
        req.setAttribute(WelcomeController.USER_INF,t_user.get(0));
        req.getRequestDispatcher("/friend_page/").forward(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
