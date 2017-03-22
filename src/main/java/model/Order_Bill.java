package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_Bill {


//    id                INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
//    user_id           INT NOT NULL,
    private User user;
//    administration_id INT NOT NULL,
    private Administration administration;

}
