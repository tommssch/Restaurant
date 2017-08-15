package controllers;

import dao.MenuDao;
import dao.UserDao;
import handlers.pass_valid.PBKDFTValidPass;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/")
public class WelcomeController extends HttpServlet {

    public static final String ALL_MENU_KEY = "All menu";
    public static final String WELCOME_KEY = "WELCOME";
    public static final String USER_INF = "USR_INFO";
    public static final String ALL_USERS = "ALL_USERS";


    private MenuDao md;
    private UserDao ud;
    private StringBuilder err;

    @Override
    public void init(ServletConfig config) throws ServletException {

        md = (MenuDao) config.getServletContext().getAttribute("MenuDao");
        ud=(UserDao) config.getServletContext().getAttribute("UserDao");
        err=new StringBuilder("");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(ALL_MENU_KEY, md.getAll());
        req.setAttribute(ALL_USERS,ud.getAll());
        req.setAttribute("err",err.toString());
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ArrayList<User> user=(ArrayList<User>)ud.get_cond("nickname",req.getParameter("nick"));
        StringBuilder str= new StringBuilder();
        boolean nf_nick=user.size()!=0;
        boolean wr_pass= nf_nick && PBKDFTValidPass.ValidPass(req.getParameter("pass")
                , user.get(0).getPassword_hash());

        if(nf_nick && wr_pass) {
            req.setAttribute(USER_INF,user.get(0));
            req.getRequestDispatcher("/mypage/").forward(req,resp);
        }
        else {
            if(!nf_nick)
                str.append("Wrong nickname \n");
            if(!wr_pass)
                str.append("Wrong password");

            err=str;
            req.setAttribute("err",err);
            doGet(req,resp);
        }
    }

}
