package controllers;

import dao.MenuDao;
import dao.UserDao;
import handlers.pass_valid.PBKDFTValidPass;
import handlers.pass_valid.PBKDFToPass;
import model.Menu;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;


@WebServlet("/Welcome")
public class WelcomeController extends HttpServlet {

    public static final String ALL_MENU_KEY = "All menu";
    public static final String WELCOME_KEY = "WELCOME";
    private MenuDao md;
    private UserDao ud;
    @Override
    public void init(ServletConfig config) throws ServletException {

        md = (MenuDao) config.getServletContext().getAttribute("MenuDao");
        ud=(UserDao) config.getServletContext().getAttribute("UserDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String s = Optional.ofNullable(req.getSession().getAttribute(FIRST_NAME_KEY))
//                .map(o -> String.format("Hello,%S", o))
//                .orElse("Hello!");
//        req.setAttribute(WELCOME_KEY, s);

        req.setAttribute(ALL_MENU_KEY, md.get("Пельмень"));
        req.getRequestDispatcher("/index.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        if(req.getParameter("bn").equals("add")) {
            System.out.println(req.getParameter("name_id"));
            Menu mn = new Menu(req.getParameter("name_id"),
                    Float.parseFloat(req.getParameter("price")),
                    LocalTime.of(0, 5),
                    req.getParameter("description"));
            md.save(mn);
        }
        else if(req.getParameter("bn").equals("remove"))
        {
            System.out.println(req.getParameter("name id"));
            md.remove("id",req.getParameter("name id"));
        }

        User user=ud.get_cond("nickname",req.getParameter("nick"));
        if((user!=null)&&(PBKDFTValidPass.ValidPass(req.getParameter("pass")
                ,user.getPassword_hash()))) {
            req.getRequestDispatcher("/main/")
                    .forward(req, resp);
        }
        else {
            PrintWriter pw = resp.getWriter();
            pw.write(" IF U SEE THIS, THAN SOMETHING WRONG");
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }

}
