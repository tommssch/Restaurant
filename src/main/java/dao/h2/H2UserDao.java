package dao.h2;

import dao.UserDao;
import lombok.SneakyThrows;
import model.Address;
import model.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2UserDao implements UserDao {


    public static final String SELECTALL = "SELECT id, name,lastname,fathername,dob,email," +
            "nickname,password,address_id,a.city,a.street,a.house,a.flat,telephone" +
            " FROM User u, Address a " +
            "WHERE u.address_id=a.id";
    @Resource(name="jdbc/TestDB")
    private DataSource dataSource;

    @Override
    @SneakyThrows
    public int create(User user) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(
                    "INSERT INTO User(name,lastname,fathername,dob," +
                            "email,nickname,password,address_id,telephone)" +
                            "VALUES(?,?,?,?,?,?,?,?,?)")) {
            pst.setObject(1,user.getName());
            pst.setObject(2,user.getLastname());
            pst.setObject(3,user.getFathername());
            pst.setObject(4,user.getDob());
            pst.setObject(5,user.getEmail());
            pst.setObject(6,user.getNickname());
            pst.setObject(7,user.getPassword_hash());
            pst.setObject(8,user.getAddress().getId());
            pst.setObject(9,user.getTelephone());

            pst.executeUpdate();

            try(ResultSet generatedKeys=pst.getGeneratedKeys()){
                if(generatedKeys.next())
                    user.setId(generatedKeys.getInt(1));
            }
        }

        return user.getId();
    }

    @Override
    public void remove(User user) {

    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        List<User> users=new ArrayList<>();
        try(Connection conn=dataSource.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(SELECTALL)){
           while (rs.next()){
               users.add(new User(
                       rs.getInt("id"),
                       rs.getString("name"),
                       rs.getString("lastname"),
                       rs.getString("fathername"),
                       rs.getDate("dob").toLocalDate(),
                       rs.getString("email"),
                       rs.getString("nickname"),
                       rs.getString("password"),
                       new Address(
                               rs.getInt("address_id"),
                               rs.getString("a.city"),
                               rs.getString("a.street"),
                               rs.getString("a.house"),
                               rs.getInt("a.flat")),
                       rs.getString("telephone")
                       ));
           }
        }
        return users;
    }
}
