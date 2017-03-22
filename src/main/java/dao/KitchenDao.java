package dao;

import model.Kitchen;

import java.util.Collection;
import java.util.Optional;

public interface KitchenDao {

    Kitchen get(int id);

    int save(Kitchen kitchen);

    Kitchen update(String field,String value,int id);

    void remove(String field,String value);

    Collection<Kitchen> getAll();
}
