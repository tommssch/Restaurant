package dao;

import model.Menu;

import java.util.Collection;

public interface MenuDao {

    int save(Menu menu);

    <T> Collection<Menu> get_cond(String field,T value);

    Menu get(int id);

    <T> Menu update(String field,T value,int id);

    <T> void remove(String field ,T value);

    Collection<Menu> getAll();

}
