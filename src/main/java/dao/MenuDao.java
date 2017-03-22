package dao;

import model.Administration;
import model.Menu;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public interface MenuDao {

    String save(Menu menu);


    Menu get(String id);
    Menu update(String field,String value,String id);

    void remove(String field ,String value);


    Collection<Menu> getAll();

}
