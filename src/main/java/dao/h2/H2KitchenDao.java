package dao.h2;


import dao.KitchenDao;
import lombok.SneakyThrows;
import model.Kitchen;
import model.Menu;
import model.Order_Bill;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class H2KitchenDao implements KitchenDao {

    private static final String INSERT_INTO_KITCHEN_SQL = "INSERT INTO Kitchen(order_bill_id, menu_id, num_of_dishes)VALUES(?,?,?)";
    private static final String SELECT_ALL_KITCHEN_SQL = "SELECT id,order_bill_id,menu_id,num_of_dishes FROM Kitchen ";
    private static final String DELETE_FROM_KITCHEN_SQL = "DELETE FROM Kitchen WHERE %s=?";
    private static final String UPDATE_KITCHEN_SQL = "UPDATE Kitchen SET %s=? WHERE id=?";
    private static final String SELECT_ONE_KITCHEN_SQL="SELECT id,order_bill_id,menu_id,num_of_dishes FROM Kitchen WHERE id=?";
    private static final String SELECT_ALL_KITCHEN_SQL_CONDITION = "SELECT id,order_bill_id,menu_id,num_of_dishes FROM Kitchen WHERE %s=?";


    private DataSource dataSource;
    private Function<Integer, Menu> getMenuId;
    private Function<Integer, Order_Bill> getOrderId;



    public H2KitchenDao(DataSource dataSource,Function<Integer,Menu> getMenuId,
                        Function<Integer,Order_Bill> getOrderId)
    {
        this.getMenuId=getMenuId;
        this.getOrderId=getOrderId;
        this.dataSource=dataSource;
    }


    @Override
    @SneakyThrows
    public Kitchen get(int id){
        try(Connection conn= dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(SELECT_ONE_KITCHEN_SQL)){
            pst.setObject(1,id);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                return new Kitchen(
                        rs.getInt("id"),
                        getOrderId.apply(rs.getInt("order_bill_id")),
                        getMenuId.apply(rs.getInt("menu_id")),
                        rs.getInt("num_of_dishes"));
            }
            else return null;
        }
    }




    @Override
    @SneakyThrows
    public int save(Kitchen kitchen) {
            try(Connection conn=dataSource.getConnection();
                PreparedStatement pst=conn.prepareStatement(INSERT_INTO_KITCHEN_SQL)){
                pst.setObject(1,kitchen.getOrder_bill());
                pst.setObject(2,kitchen.getMenu());
                pst.setObject(3,kitchen.getNum_of_dishes());

                pst.executeUpdate();
                try(ResultSet rst=pst.getGeneratedKeys()){
                    if(rst.next()){
                        kitchen.setId(rst.getInt(1));
                    }
                }
            }
            return kitchen.getId();
    }

    @Override
    @SneakyThrows
    public <T> Collection<Kitchen> get_cond(String field, T value) {
        Collection<Kitchen> kitchens= new ArrayList<>();
        try(Connection con=dataSource.getConnection();
            PreparedStatement pst=con.prepareStatement(String.format(SELECT_ALL_KITCHEN_SQL_CONDITION,field))){
            pst.setObject(1,value);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                kitchens.add(new Kitchen(
                        rs.getInt("id"),
                        getOrderId.apply(rs.getInt("order_bill_id")),
                        getMenuId.apply(rs.getInt("menu_id")),
                        rs.getInt("num_of_dishes")));
            }

            return kitchens;
        }
    }

    @Override
    @SneakyThrows
    public <T> Kitchen update(String field,T value, int id) {

        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(UPDATE_KITCHEN_SQL,field)))
        {
            pst.setObject(1,value);
            pst.setObject(2,id);
            pst.executeUpdate();
        }
        return get(id);

    }

    @Override
    @SneakyThrows
    public <T> void remove(String field,T value) {

        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(DELETE_FROM_KITCHEN_SQL,field))){

            pst.setObject(1,value);
            pst.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Collection<Kitchen> getAll() {
        List<Kitchen> kitchen= new ArrayList<>();
        try(Connection conn=dataSource.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(SELECT_ALL_KITCHEN_SQL)) {
                while (rs.next()){
                    kitchen.add(new Kitchen(
                            rs.getInt("id"),
                            getOrderId.apply(rs.getInt("order_bill_id")),
                            getMenuId.apply(rs.getInt("menu_id")),
                            rs.getInt("num_of_dishes")));
                }

        }
        return kitchen;
    }
}
