package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class User {

    public static String FIRST_NAME_KEY="first name";

//    id         INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
//    name       VARCHAR(50)  NOT NULL,
    private String name;
//    lastname   VARCHAR(50)  NOT NULL,
    private String lastname;
//    fathername VARCHAR(50)  NOT NULL,
    private String fathername;
//    bd         DATE    NOT NULL,
    private LocalDate dob;
//    email      VARCHAR(100) NOT NULL,
    private String email;
//    nickname   VARCHAR(50)  NOT NULL,
    private String nickname;
//    password   VARCHAR(255) NOT NULL,
    private String password_hash;
//    address_id INT NOT NULL ,
    private Address address;
//    telephone,
    private String telephone;

}
