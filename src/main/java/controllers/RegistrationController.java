package controllers;


import dao.AddressDao;
import dao.UserDao;
import dao.h2.H2UserDao;
import handlers.data_valid.validAddress;
import handlers.data_valid.validUser;
import handlers.pass_valid.PBKDFToPass;
import model.Address;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;


@WebServlet("/Registration")
public class RegistrationController extends HttpServlet {

    private UserDao usrDao;
    private AddressDao addrDao;
    @Override
    public void init(ServletConfig config) throws ServletException {
        addrDao=(AddressDao) config.getServletContext().getAttribute("AddressDao");
        usrDao=(UserDao) config.getServletContext().getAttribute("UserDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Address address= new Address(0,
                req.getParameter("city"),
                req.getParameter("street"),
                req.getParameter("house"),
                req.getParameter("flat").equals("") ? 0
                        : Integer.parseInt(req.getParameter("flat")));
        if(!validAddress.validMessage(address).equals("OK")){
            req.getRequestDispatcher("/registration/").forward(req,resp);
            return;
        }

        User user=new User(0,
                req.getParameter("name"),
                req.getParameter("lname"),
                req.getParameter("fname"),
                LocalDate.parse(req.getParameter("dob").equals("")? LocalDate.MIN.toString()
                        : req.getParameter("dob")),
                req.getParameter("email"),
                req.getParameter("nick"),
                PBKDFToPass.generatePassHash(req.getParameter("pass")),
                address,
                req.getParameter("phone"));

        if(!validUser.validMessage(user,usrDao).equals("OK")) {
            req.getRequestDispatcher("/registration/").forward(req,resp);
        }
        else {
            addrDao.save(address);
            usrDao.create(user);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }


}
