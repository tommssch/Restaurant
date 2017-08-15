package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    //id INT AUTO_INCREMENT PRIMARY KEY
    private int id;
    //user_from_id INT NOT NULL
    private User user_from;
    //user_to_id INT NOT NULL
    private User user_to;
    //message VARCHAR(300) NOT NULL
    private String message;
    //send_datetime DATETIME NOT NULL
    private LocalDateTime send_datetime;

}
