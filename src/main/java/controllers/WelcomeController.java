package controllers;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static model.User.FIRST_NAME_KEY;
@WebServlet("/")
public class WelcomeController extends HttpServlet {

    public static  String WELCOME_KEY="WELCOME";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String s= Optional.ofNullable(req.getSession().getAttribute(FIRST_NAME_KEY))
               .map(o->String.format("Hello,%S",o))
               .orElse("Hello!");
        req.setAttribute(WELCOME_KEY,s);

        req.getRequestDispatcher("/WEB-INF/index.jsp")
                .forward(req,resp);
    }
}
