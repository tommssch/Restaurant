package dao;


import model.Address;


import java.util.Collection;
import java.util.Optional;

public interface AddressDao {

    int save(Address address);

    Address get(int id);

    Address update(String field, String value, int id);

    void remove(String field,String value);

    Collection<Address> getAll();
}
