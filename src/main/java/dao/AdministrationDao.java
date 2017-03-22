package dao;


import model.Administration;

import java.util.Collection;
import java.util.Optional;

public interface AdministrationDao {

    int save(Administration admin);

    Administration get(int id);

    Administration update(String field,String value,int id);

    void remove(String field ,String value);

    Collection<Administration> getAll();

}
