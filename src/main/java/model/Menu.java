package model;

import lombok.*;
import lombok.experimental.Accessors;


import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Menu {

//    id          INT PRIMARY KEY,
    private int id;
//    name  VARCHAR(100),
    private String name;
//    price       FLOAT NOT NULL,
    private float price;
//    cooktime    TIME  NOT NULL,
    private LocalTime cooktime;
//    description VARCHAR(300)
    private String description;

}
