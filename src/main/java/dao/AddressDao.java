package dao;


import model.Address;


import java.util.Collection;


public interface AddressDao {

    int save(Address address);

    Address get(int id);

   <T> Collection<Address> get_cond(String field,T value);

   <T> Address update(String field,T value, int id);

   <T> void remove(String field,T value);

    Collection<Address> getAll();
}
