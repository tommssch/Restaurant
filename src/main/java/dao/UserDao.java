package dao;

import model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserDao {


    int create(User user);

    User get(int id);
    User update(String field,String value,int id);

    void remove(String field ,String value);

    Collection<User> getAll();
}
