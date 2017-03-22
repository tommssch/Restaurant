package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
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
