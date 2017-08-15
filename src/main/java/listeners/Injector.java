package listeners;


import dao.h2.*;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class Injector implements ServletContextListener {

    @Resource(name="jdbc/TestDB")
    private   DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        H2MenuDao menuDao=new H2MenuDao(dataSource);
        H2AddressDao addressDao=new H2AddressDao(dataSource);
        H2AdminDao adminDao=new H2AdminDao(dataSource);
        H2UserDao userDao= new H2UserDao(dataSource, addressDao::get);
        H2OrderBillDao orderBillDao=new H2OrderBillDao(dataSource,userDao::get,adminDao::get);
        H2KitchenDao kitchenDao=new H2KitchenDao(dataSource,menuDao::get,orderBillDao::get);
        H2MessageDao messageDao=new H2MessageDao(dataSource,userDao::get);

        sce.getServletContext().setAttribute("AddressDao",addressDao);
        sce.getServletContext().setAttribute("AdministrationDao",adminDao);
        sce.getServletContext().setAttribute("UserDao",userDao);
        sce.getServletContext().setAttribute("MenuDao",menuDao);
        sce.getServletContext().setAttribute("OrderBillDao",orderBillDao);
        sce.getServletContext().setAttribute("KitchenDao",kitchenDao);
        sce.getServletContext().setAttribute("MessageDao",messageDao);

    }


}
