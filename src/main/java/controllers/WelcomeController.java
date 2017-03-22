package controllers;

import dao.MenuDao;
import dao.UserDao;
import model.User;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static model.User.FIRST_NAME_KEY;


@WebServlet("/")
public class WelcomeController extends HttpServlet {

    public static final String ALL_MENU_KEY = "All menu";
    public static final String WELCOME_KEY = "WELCOME";
    public static final String MENU_ID = "MenuId";
    public static final  String GET_ALL_USERS="USERS";
    private MenuDao md;
    private UserDao usr;
    @Override
    public void init(ServletConfig config) throws ServletException {

        md = (MenuDao) config.getServletContext().getAttribute("MenuDao");
        usr=(UserDao) config.getServletContext().getAttribute("UserDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = Optional.ofNullable(req.getSession().getAttribute(FIRST_NAME_KEY))
                .map(o -> String.format("Hello,%S", o))
                .orElse("Hello!");
        req.setAttribute(WELCOME_KEY, s);
        req.setAttribute(GET_ALL_USERS,usr.getAll());
        req.setAttribute(ALL_MENU_KEY, md.getAll());
        req.setAttribute(MENU_ID,md.get("Котлетка по Киевски"));

        req.getRequestDispatcher("/WEB-INF/index.jsp")
                .forward(req, resp);
    }

   }
