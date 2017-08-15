package dao;

import model.User;
import java.util.Collection;

public interface UserDao {

    <T> Collection<User> get_cond(String field, T value);

    int create(User user);

    User get(int id);

    <T> User update(String field,T value,int id);

    <T> void remove(String field,T value);

    Collection<User> getAll();
}
