package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kitchen {

//    id            INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
//    order_bill_id INT          NOT NULL,
    private Order_Bill order_bill;
//    menu_id       VARCHAR(100) NOT NULL,
    private Menu menu;
//    num_of_dishes INT          NOT NULL,
    private int num_of_dishes;

}
