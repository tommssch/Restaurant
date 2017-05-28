package dao.h2;

import dao.MenuDao;
import lombok.SneakyThrows;
import model.Menu;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class H2MenuDao implements MenuDao {

    public static final String SELECT_ALL_MENU = "SELECT id,price,cooktime,description FROM Menu";
    public static final String DELETE_FROM_MENU_SQL = "DELETE FROM Menu WHERE %s=?";
    public static final String UPDATE_MENU_SQL = "UPDATE Menu SET %s=? WHERE id=?";
    public static final String INSERT_MENU_SQL = "INSERT INTO Menu(id,price,cooktime,description) VALUES(?,?,?,?)";
    public static final String SELECT_MENU_ONE_SQL = "SELECT id,price,cooktime,description FROM Menu WHERE id=?";


    private DataSource dataSource;

    public H2MenuDao(DataSource dataSource){
        this.dataSource=dataSource;
    }


    @Override
    @SneakyThrows
    public Menu get(String id) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(SELECT_MENU_ONE_SQL)) {
            pst.setObject(1,new String(id.getBytes(),"UTF-8"));
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                return new Menu(
                        rs.getString("id"),
                        rs.getFloat("price"),
                        rs.getTime("cooktime").toLocalTime(),
                        rs.getString("description"));
            }
            else return null;
        }
    }


    @Override
    @SneakyThrows
    public String save(Menu menu) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                     INSERT_MENU_SQL))
        {

            pst.setObject(1,new String(menu.getId().getBytes(),"UTF-8"));
            pst.setObject(2, menu.getPrice());
            pst.setObject(3, menu.getCooktime());
            pst.setObject(4,new String(menu.getDescription().getBytes(),"UTF-8"));
            pst.executeUpdate();
        }
        return menu.getId();
    }
    @SuppressWarnings("Duplicates")
    @Override
    @SneakyThrows
    public Menu update(String field, String value, String id) {
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
    public void remove(String field, String value) {

        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(DELETE_FROM_MENU_SQL,field))){
            pst.setObject(1,new String(value.getBytes(),Charset.defaultCharset()));
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
                        rs.getString("id"),
                        rs.getFloat("price"),
                        rs.getTime("cooktime").toLocalTime(),
                        rs.getString("description")));
            }
        }
        return menu;
    }
}
