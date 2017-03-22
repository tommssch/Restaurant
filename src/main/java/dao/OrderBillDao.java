package dao;

import model.Order_Bill;

import java.util.Collection;
import java.util.Optional;

public interface OrderBillDao {

    int save(Order_Bill order_bill);

    Order_Bill get(int id);

   Order_Bill update(String field,String value,int id);

   void remove(String field,String value);

    Collection<Order_Bill> getAll();
}
