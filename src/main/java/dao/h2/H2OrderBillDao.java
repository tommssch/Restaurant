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
import java.util.Optional;
import java.util.function.Function;

public class H2OrderBillDao implements OrderBillDao{

    public static final String SELECT_ALL_ORDERS_SQL = "SELECT id,user_id,administration_id FROM Order_Bill";
    public static final String INSERT_INTO_ORDER_BILL_SQL = "INSERT INTO Order_Bill(user_id, administration_id)VALUES(?,?)";
    public static final String DELETE_FROM_ORDER_SQL = "DELETE FROM Order_Bill WHERE ?=?";
    public static final String UPDATE_ORDER_SQL = "UPDATE Order_Bill SET ?=? WHERE id=?";
    public static final String SELECT_ONE_ORDER_SQL="SELECT id,user_id,address_id FROM Order_Bill WHERE id=?";
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
    public int save(Order_Bill order_bill) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(INSERT_INTO_ORDER_BILL_SQL)){
            pst.setObject(1,order_bill.getUser());
            pst.setObject(2,order_bill.getAdministration());

            pst.executeBatch();

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
    public Order_Bill update(String field, String value, int id) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(UPDATE_ORDER_SQL))
        {
            pst.setObject(1,field);
            pst.setObject(2,value);
            pst.setObject(3,id);

        }
        return get(id);

    }

    @Override
    @SneakyThrows
    public void remove(String field, String value) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(DELETE_FROM_ORDER_SQL)){
            pst.setObject(1,field);
            pst.setObject(2,value);
            pst.executeBatch();
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
