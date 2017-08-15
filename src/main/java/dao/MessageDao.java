package dao;


import model.Message;

import java.util.Collection;

public interface MessageDao {

    Message get(int id);

    Collection<Message> getAll();

    <T> Collection<Message> getAll_cond(String field,T value);

    <T> void remove(String field,T value);

    <T> Message update(String field,T value,int id);

    int save(Message msg);
}
