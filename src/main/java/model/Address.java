package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address{

//    id     INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
//    city   VARCHAR(100) NOT NULL,
    private String city;
//    street VARCHAR(250) NOT NULL,
    private String street;
//    house  VARCHAR(20)  NOT NULL,
    private String house;
//    flat   INT          NOT NULL
    private int flat;


}
