package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administration {

//    id         INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
//    name       VARCHAR(50)  NOT NULL,
    private String name;
//    lastname   VARCHAR(100) NOT NULL,
    private String lastname;
//    fathername VARCHAR(100) NOT NULL,
    private String fathername;
//    dob        DATE,
    private LocalDate dob;
//    experiance INT
    private int experiance;
}
