package model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Menu {

//    id          VARCHAR(100) PRIMARY KEY,
    private String id;
//    price       FLOAT NOT NULL,
    private float price;
//    cooktime    TIME  NOT NULL,
    private LocalTime cooktime;
//    description VARCHAR(300)
    private String description;
}
