package dao.h2;


import dao.MessageDao;
import lombok.SneakyThrows;
import model.Message;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class H2MessageDao implements MessageDao {

    private static final String SELECTALL = "SELECT id,user_from_id,user_to_id,message,send_datetime FROM Messages";
    private  static  final String SELECT_ONE = "SELECT id,user_from_id,user_to_id,message,send_datetime FROM Messages WHERE id=?";
    private  static final String SELECT_ALL_MESSAGES_CONDITION = "SELECT id,user_from_id,user_to_id,message,send_datetime FROM Messages WHERE %s=?";
    private  static final String DELETE_MESSAGE = "DELETE id,user_from_id,user_to_id,message,send_datetime FROM Messages WHERE %s=?";
    private static final String UPDATE_MESSAGE = "UPDATE Messages SET %s=? WHERE id=?";
    private static final String INSERT_INTO_MESSAGES = "INSERT INTO Messages(user_from_id, user_to_id, message, send_datetime)VALUES(?,?,?,?)";

    private  DataSource dataSource;
    private Function<Integer,User> getUserid;
    public H2MessageDao(DataSource dataSource,Function<Integer,User>func){
        this.dataSource=dataSource;
        this.getUserid=func;
    }
    @Override
    @SneakyThrows
    public Message get(int id) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(SELECT_ONE))
        {
            pst.setObject(1,id);
            ResultSet rst=pst.executeQuery();
            if(rst.next())
                return new Message(rst.getInt("id"),
                        getUserid.apply(rst.getInt("user_from_id")),
                        getUserid.apply(rst.getInt("user_to_id")),
                        rst.getString("message"),
                        rst.getTimestamp("send_date_time").toLocalDateTime());
            else
                return null;
        }
    }

    @Override
    @SneakyThrows
    public Collection<Message> getAll() {
        Collection<Message> msgs=new ArrayList<>();
        try(Connection conn=dataSource.getConnection();
            Statement st= conn.createStatement();
            ResultSet rst=st.executeQuery(SELECTALL))
        {
            while(rst.next()) {
                msgs.add(new Message(rst.getInt("id"),
                        getUserid.apply(rst.getInt("user_from_id")),
                        getUserid.apply(rst.getInt("user_to_id")),
                        rst.getString("message"),
                        rst.getTimestamp("send_datetime").toLocalDateTime()));
            }
        }
        return msgs;
    }

    @Override
    @SneakyThrows
    public <T> Collection<Message> getAll_cond(String field,T value) {
        Collection<Message> msgs=new ArrayList<>();
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(String.format(SELECT_ALL_MESSAGES_CONDITION,field)))
        {
            pst.setObject(1,value);
            ResultSet rst=pst.executeQuery();
            while (rst.next()){
                msgs.add(new Message(rst.getInt("id"),
                        getUserid.apply(rst.getInt("user_from_id")),
                        getUserid.apply(rst.getInt("user_to_id")),
                        rst.getString("message"),
                        rst.getTimestamp("send_datetime").toLocalDateTime()));
            }

        }
        return msgs;
    }

    @Override
    @SneakyThrows
    public <T> void remove(String field,T value) {
       try(Connection conn=dataSource.getConnection();
       PreparedStatement pst=conn.prepareStatement(String.format(DELETE_MESSAGE,field))){
        pst.setObject(1,value);
        pst.executeUpdate();
       }
    }

    @Override
    @SneakyThrows
    public <T> Message update(String field, T value, int id) {
        try(Connection conn=dataSource.getConnection();
        PreparedStatement pst=conn.prepareStatement(String.format(UPDATE_MESSAGE,field))) {
            pst.setObject(1,value);
            pst.setObject(2,id);
            pst.executeUpdate();
        }
        return get(id);
    }

    @SneakyThrows
    @Override
    public int save(Message msg) {
        try(Connection conn=dataSource.getConnection();
        PreparedStatement pst=conn.prepareStatement(INSERT_INTO_MESSAGES)){
            pst.setObject(1,msg.getUser_from().getId());
            pst.setObject(2,msg.getUser_to().getId());
            pst.setObject(3,msg.getMessage());
            pst.setObject(4,msg.getSend_datetime());
            pst.executeUpdate();
            try(ResultSet generatedKeys=pst.getGeneratedKeys()){
                if(generatedKeys.next())
                    msg.setId(generatedKeys.getInt(1));
            }
            return msg.getId();
        }
    }
}
