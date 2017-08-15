package dao;

import model.Order_Bill;

import java.util.Collection;

public interface OrderBillDao {

    int save(Order_Bill order_bill);

    Order_Bill get(int id);

    <T> Collection<Order_Bill> get_cond(String field,T value);

    <T> Order_Bill update(String field,T value,int id);

    <T> void remove(String field,T value);

    Collection<Order_Bill> getAll();
}
