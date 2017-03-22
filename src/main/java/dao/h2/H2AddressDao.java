package dao.h2;

import dao.AddressDao;
import lombok.Data;
import lombok.SneakyThrows;
import model.Address;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class H2AddressDao implements AddressDao {

    public static final String INSERT_INTO_ADDRESS_SQL = "INSERT INTO Address(city,street,house,flat)VALUES(?,?,?,?)";
    public static final String SELECT_ALL_ADDRESSES_SQL = "SELECT id,city,street,house,flat FROM Address";
    public static final String UPDATE_ADDRESS_SQL = "UPDATE Address SET ?=? WHERE id=?";
    public static final String DELETE_FROM_ADDRESS_SQL="DELETE FROM Address WHERE ?=?";
    public static final String SELECT_ONE_ADDRESS_SQL = "SELECT id,city,street,house,flat FROM Address WHERE id=?";
    private DataSource dataSource;


    public H2AddressDao(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    @SneakyThrows
    public Address get(int id) {
        Address address;
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(SELECT_ONE_ADDRESS_SQL)){
            pst.setObject(1,id);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                address=new Address(
                        rs.getInt("id"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getString("house"),
                        rs.getInt("flat"));
                return address;
            }
            else
                return null;
        }

    }

    @Override
    @SneakyThrows
    public int save(Address address) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(INSERT_INTO_ADDRESS_SQL)) {
            pst.setObject(1,address.getCity());
            pst.setObject(2,address.getStreet());
            pst.setObject(3,address.getHouse());
            pst.setObject(4,address.getFlat());

            pst.executeBatch();

            try(ResultSet rs= pst.getGeneratedKeys()){
                if(rs.next()){
                    address.setId(rs.getInt(1));
                }
            }

        }
        return address.getId();
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("Duplicates")
    public Address update(String field, String value, int id) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(UPDATE_ADDRESS_SQL)){
            pst.setObject(1,field);
            pst.setObject(2,value);
            pst.setObject(3,id);

            pst.executeBatch();
        }
        return get(id);
    }

    @Override
    @SneakyThrows
    public void remove(String field,String value) {
        try(Connection conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(DELETE_FROM_ADDRESS_SQL)){
            pst.setObject(1,field);
            pst.setObject(2,value);
            pst.executeBatch();
        }
    }

    @Override
    @SneakyThrows
    public Collection<Address> getAll() {
        List<Address> addresses= new ArrayList<>();

        try(Connection conn=dataSource.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(SELECT_ALL_ADDRESSES_SQL)){
            while(rs.next()){
                addresses.add(new Address(
                        rs.getInt("id"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getString("house"),
                        rs.getInt("flat")));
            }

            return addresses;
        }
    }
}
