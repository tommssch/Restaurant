package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {


    int create(User user);

    default Optional<User> get(int id){
        return getAll().stream()
                .filter(user->user.getId()==id)
                .findAny();
     }
    //update(User user);
    void remove(User user);

    List<User> getAll();
}
