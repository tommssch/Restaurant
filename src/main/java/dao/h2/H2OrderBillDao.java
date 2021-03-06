package dao.h2;

import dao.OrderBillDao;

import lombok.SneakyThrows;
import model.Administration;
import model.Order_Bill;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class H2OrderBillDao implements OrderBillDao{

    private static final String SELECT_ALL_ORDERS_SQL = "SELECT id,user_id,administration_id FROM Order_Bill";
    private static final String INSERT_INTO_ORDER_BILL_SQL = "INSERT INTO Order_Bill(user_id, administration_id)VALUES(?,?)";
    private static final String DELETE_FROM_ORDER_SQL = "DELETE FROM Order_Bill WHERE %s=?";
    private static final String UPDATE_ORDER_SQL = "UPDATE Order_Bill SET %s=? WHERE id=?";
    private static final String SELECT_ONE_ORDER_SQL="SELECT id,user_id,administration_id FROM Order_Bill WHERE id=?";
    private  static final String SELECT_ALL_ORDERS_CONDITION = "SELECT id,user_id,administration_id FROM Order_Bill WHERE %s=?";

    private DataSource dataSource;
    private Function<Integer, User> getUserId;
    private Function<Integer, Administration> getAdminId;


    public H2OrderBillDao(DataSource dataSource, Function<Integer,
            User> getUserId, Function<Integer, Administration> getAdminId){
        this.dataSource = dataSource;
        this.getUserId=getUserId;
        this.getAdminId=getAdminId;
    }


    @Override
    @SneakyThrows
    public Order_Bill get(int id) {
        try(Connection conn= dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(SELECT_ONE_ORDER_SQL)){
            pst.setObject(1,id);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                return new Order_Bill(
                        rs.getInt("id"),
                        getUserId.apply(rs.getInt("user_id")),
                        getAdminId.apply(rs.getInt("administration_id")));
            }
            else return null;
        }
    }

    @Override
    @SneakyThrows
    public <T> Collection<Order_Bill> get_cond(String field, T value) {
        Collection<Order_Bill> order_bills=new ArrayList<>();
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(SELECT_ALL_ORDERS_CONDITION,field)))
        {
            pst.setObject(1,value);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                order_bills.add(new Order_Bill(
                        rs.getInt("id"),
                        getUserId.apply(rs.getInt("user_id")),
                        getAdminId.apply(rs.getInt("administration_id"))));
            }

        }
        return order_bills;
    }

    @Override
    @SneakyThrows
    public int save(Order_Bill order_bill) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(INSERT_INTO_ORDER_BILL_SQL)){
            pst.setObject(1,order_bill.getUser());
            pst.setObject(2,order_bill.getAdministration());

            pst.executeUpdate();

            try(ResultSet rs=pst.getGeneratedKeys()){
                if(rs.next()){
                    order_bill.setId(rs.getInt(1));
                }
            }
        }
        return order_bill.getId();
    }



    @Override
    @SneakyThrows
    public <T> Order_Bill update(String field, T value, int id) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(UPDATE_ORDER_SQL,field)))
        {
            pst.setObject(1,value);
            pst.setObject(2,id);
            pst.executeUpdate();
        }
        return get(id);

    }

    @Override
    @SneakyThrows
    public <T> void remove(String field, T value) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(DELETE_FROM_ORDER_SQL,field))){
            pst.setObject(1,value);
            pst.executeUpdate();
        }

    }

    @Override
    @SneakyThrows
    public Collection<Order_Bill> getAll() {

        ArrayList<Order_Bill> orders= new ArrayList<>();
        try(Connection conn= dataSource.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(SELECT_ALL_ORDERS_SQL)){
            while(rs.next()){
                orders.add(new Order_Bill(
                        rs.getInt("id"),
                        getUserId.apply(rs.getInt("user_id")),
                        getAdminId.apply(rs.getInt("administration_id"))));
            }
        }
        return orders;
    }
}
