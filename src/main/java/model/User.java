package model;


import java.time.LocalDate;

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
