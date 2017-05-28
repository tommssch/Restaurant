package dao.h2;

import dao.UserDao;
import lombok.Data;
import lombok.SneakyThrows;
import model.Address;
import model.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class H2UserDao implements UserDao {


    public static final String SELECTALL = "SELECT u.id, u.name,u.lastname,u.fathername,u.dob,u.email," +
            "u.nickname,u.password,u.address_id,a.city,a.street,a.house,a.flat,telephone" +
            " FROM User u, Address a " +
            "WHERE u.address_id=a.id";
    public static final String DELETE_FROM_USER_SQL = "DELETE FROM User WHERE %s=?";
    public static final String UPDATE_USER_SQL = "UPDATE User SET %s=? WHERE id=?";
    public static final String INSERT_USER_SQL ="INSERT INTO User(name,lastname,fathername,dob," +
            "email,nickname,password,address_id,telephone)" +
            "VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_ONE_USER_SQL = "SELECT id,name,lastname,fathername,dob,email,nickname,password,address_id,telephone FROM User WHERE id=?";
    public static final String SELECT_USER_COND_SQL = "SELECT id,name,lastname,fathername,dob,email,nickname,password,address_id,telephone FROM User WHERE %s=?";

    private DataSource dataSource;
    private Function<Integer,Address> getAddressId;

    public H2UserDao(DataSource dataSource,Function<Integer,Address> getAddressId){
        this.getAddressId=getAddressId;
        this.dataSource=dataSource;
    }



    @Override
    @SneakyThrows
    public User get(int id) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(SELECT_ONE_USER_SQL)){
            pst.setObject(1,id);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("fathername"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        getAddressId.apply(rs.getInt("address_id")),
                        rs.getString("telephone"));
            }
            else return null;
        }
    }

    @Override
    @SneakyThrows
    public User get_cond(String field, String value) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(SELECT_USER_COND_SQL, field))){
            pst.setObject(1,value);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("fathername"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        getAddressId.apply(rs.getInt("address_id")),
                        rs.getString("telephone"));
            }
            else return null;
        }
    }

    @Override
    @SneakyThrows
    public int create(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                     INSERT_USER_SQL)) {
            pst.setObject(1, user.getName());
            pst.setObject(2, user.getLastname());
            pst.setObject(3, user.getFathername());
            pst.setObject(4, user.getDob());
            pst.setObject(5, user.getEmail());
            pst.setObject(6, user.getNickname());
            pst.setObject(7, user.getPassword_hash());
            pst.setObject(8, user.getAddress().getId());
            pst.setObject(9, user.getTelephone());

            pst.executeUpdate();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next())
                    user.setId(generatedKeys.getInt(1));
            }
        }

        return user.getId();
    }



    @Override
    @SneakyThrows
    public User update(String field, String value, int id) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(UPDATE_USER_SQL,field)))
        {
            pst.setObject(1,value);
            pst.setObject(2,id);

        }
        return get(id);
    }

    @Override
    @SneakyThrows
    public void remove(String field,String value) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(DELETE_FROM_USER_SQL,field))){
            pst.setObject(1,value);
            pst.executeUpdate();
        }

    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECTALL)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("fathername"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("nickname"),
                        rs.getString("password"), // TODO: 15.03.2017 need?
                        getAddressId.apply(rs.getInt("address_id")),
                        rs.getString("telephone")));
            }
        }
        return users;
    }
}
