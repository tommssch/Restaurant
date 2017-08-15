package dao;


import model.Administration;

import java.util.Collection;

public interface AdministrationDao {

    int save(Administration admin);

    Administration get(int id);

   <T> Collection<Administration> get_cond(String field,T value);

   <T> Administration update(String field,T value,int id);

   <T> void remove(String field ,T value);

    Collection<Administration> getAll();

}
