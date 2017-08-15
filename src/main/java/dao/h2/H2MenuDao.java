package dao.h2;

import dao.MenuDao;
import lombok.SneakyThrows;
import model.Menu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class H2MenuDao implements MenuDao {

    private static final String SELECT_ALL_MENU = "SELECT id,name,price,cooktime,description FROM Menu";
    private static final String DELETE_FROM_MENU_SQL = "DELETE FROM Menu WHERE %s=?";
    private static final String UPDATE_MENU_SQL = "UPDATE Menu SET %s=? WHERE id=?";
    private static final String INSERT_MENU_SQL = "INSERT INTO Menu(id,price,cooktime,description) VALUES(?,?,?,?)";
    private static final String SELECT_MENU_ONE_SQL = "SELECT id,name,price,cooktime,description FROM Menu WHERE id=?";
    private static final String SELECT_ALL_MENU_SQL_CONDITION = "SELECT id,name,price,cooktime,description FROM Menu WHERE %s=?";


    private DataSource dataSource;

    public H2MenuDao(DataSource dataSource){
        this.dataSource=dataSource;
    }


    @Override
    @SneakyThrows
    public Menu get(int id) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(SELECT_MENU_ONE_SQL)) {
            pst.setObject(1,id);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                return new Menu(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getTime("cooktime").toLocalTime(),
                        rs.getString("description"));
            }
            else return null;
        }
    }


    @Override
    @SneakyThrows
    public int save(Menu menu) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                     INSERT_MENU_SQL))
        {
            pst.setObject(1,new String(menu.getName().getBytes(),"UTF-8"));
            pst.setObject(2, menu.getPrice());
            pst.setObject(3, menu.getCooktime());
            pst.setObject(4,new String(menu.getDescription().getBytes(),"UTF-8"));
            pst.executeUpdate();
        }
        return menu.getId();
    }

    @Override
    @SneakyThrows
    public <T> Collection<Menu> get_cond(String field, T value) {
        Collection<Menu> menu= new ArrayList<>();
        try(Connection con=dataSource.getConnection();
            PreparedStatement pst=con.prepareStatement(String.format(SELECT_ALL_MENU_SQL_CONDITION,field))){
            pst.setObject(1,value);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                menu.add(new Menu(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getTime("cooktime").toLocalTime(),
                        rs.getString("description")));
            }

            return menu;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    @SneakyThrows
    public <T> Menu update(String field,T value, int id) {
        try(Connection con=dataSource.getConnection();
            PreparedStatement pst=con.prepareStatement(String.format(UPDATE_MENU_SQL,field))){
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
            PreparedStatement pst=conn.prepareStatement(String.format(DELETE_FROM_MENU_SQL,field))){
            pst.setObject(1,value);
            pst.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public Collection<Menu> getAll() {
        List<Menu> menu = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_MENU)) {
            while (rs.next()) {
                menu.add(new Menu(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getTime("cooktime").toLocalTime(),
                        rs.getString("description")));
            }
        }
        return menu;
    }
}
