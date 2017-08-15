package dao;

import model.Kitchen;

import java.util.Collection;

public interface KitchenDao {

    Kitchen get(int id);

    int save(Kitchen kitchen);

    <T> Collection<Kitchen> get_cond(String field,T value);

    <T> Kitchen update(String field,T value,int id);

    <T> void remove(String field,T value);

    Collection<Kitchen> getAll();
}
