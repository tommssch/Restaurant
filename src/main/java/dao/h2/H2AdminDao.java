package dao.h2;


import dao.AdministrationDao;
import lombok.SneakyThrows;
import model.Administration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class H2AdminDao implements AdministrationDao {

    public static final String SELECT_ALL_SQL  = "SELECT id,name,lastname,fathername,dob,experience";
    public static final String INSERT_INTO_SQL = "INSERT INTO Administration(name,lastname,fathername,dob,experience)VALUES (?,?,?,?,?)";
    public static final String DELETE_FROM_ADMIN_SQL = "DELETE FROM Administration WHERE ?=?";
    public static final String UPDATE_ADMINISTRATION_SQL = "UPDATE Administration SET ?=? WHERE id=?";
    public static final String SELECT_ONE_ADMINISTRATION_SQL = "SELECT id,name,lastname,fathername,dob,experience FROM Administration WHERE id=?";

    private DataSource dataSource;





    public H2AdminDao(DataSource dataSource){

        this.dataSource=dataSource;
    }


    @Override
    @SneakyThrows
    public Administration get(int id) {
        try(Connection conn= dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(SELECT_ONE_ADMINISTRATION_SQL)){
            pst.setObject(1,id);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                return new Administration(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("fathername"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getInt("experiance"));
            }
            else return null;
        }
    }

    @Override
    @SneakyThrows
    public int save(Administration admin) {
        try(Connection con=dataSource.getConnection();
            PreparedStatement pst=con.prepareStatement(INSERT_INTO_SQL)){
            pst.setObject(1,admin.getName());
            pst.setObject(2,admin.getLastname());
            pst.setObject(3,admin.getFathername());
            pst.setObject(4,admin.getDob());
            pst.setObject(5,admin.getExperiance());


            pst.executeBatch();

            try(ResultSet generatedKeys=pst.getGeneratedKeys()){
                admin.setId(generatedKeys.getInt(1));
            }
        }

        return admin.getId();
    }



    @SuppressWarnings("Duplicates")
    @Override
    @SneakyThrows
    public Administration update(String field,String value,int id) {
        try(Connection con=dataSource.getConnection();
            PreparedStatement pst=con.prepareStatement(UPDATE_ADMINISTRATION_SQL)){

            pst.setObject(1,field);
            pst.setObject(2,value);
            pst.setObject(3,id);

            pst.executeBatch();
            }
        return get(id);

    }

    @Override
    @SneakyThrows
    public void remove(String field ,String value) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(DELETE_FROM_ADMIN_SQL)){
            pst.setObject(1,field);
            pst.setObject(2,value);
            pst.executeBatch();
        }
    }

    @Override
    @SneakyThrows
    public Collection<Administration> getAll() {
        List<Administration> admins= new ArrayList<>();
        try(Connection con=dataSource.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(SELECT_ALL_SQL)){
            while(rs.next()){
                admins.add(new Administration(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("fathername"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getInt("experience")
                ));
            }
            return admins;
        }
    }
}
