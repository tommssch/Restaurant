CREATE TABLE Address (
  id     INT AUTO_INCREMENT PRIMARY KEY,
  city   VARCHAR(100) NOT NULL,
  street VARCHAR(250) NOT NULL,
  house  VARCHAR(20)  NOT NULL,
  flat   INT          NOT NULL
);

CREATE TABLE Administration (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(50)  NOT NULL,
  lastname   VARCHAR(100) NOT NULL,
  fathername VARCHAR(100) NOT NULL,
  dob        DATE,
  experience INT
);

CREATE TABLE Menu (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(100),
  price       FLOAT NOT NULL,
  cooktime    TIME  NOT NULL,
  description VARCHAR(300),
  UNIQUE (name)
);

CREATE TABLE User (
  id         INT AUTO_INCREMENT  PRIMARY KEY,
  name       VARCHAR(50)  NOT NULL,
  lastname   VARCHAR(50)  NOT NULL,
  fathername VARCHAR(50)  NOT NULL,
  dob         DATE    NOT NULL,
  email      VARCHAR(100) NOT NULL,
  nickname   VARCHAR(50)  NOT NULL,
  password   VARCHAR(1001) NOT NULL,
  address_id INT NOT NULL ,
  telephone VARCHAR(100) NOT NULL ,

  FOREIGN KEY (address_id) REFERENCES Address(id),
  UNIQUE (nickname),
  UNIQUE (email)
);

CREATE TABLE Order_Bill (
  id                INT AUTO_INCREMENT PRIMARY KEY,
  user_id           INT NOT NULL,
  administration_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES User (id),
  FOREIGN KEY (administration_id) REFERENCES Administration (id)
);

CREATE TABLE Kitchen (
  id            INT AUTO_INCREMENT PRIMARY KEY,
  order_bill_id INT          NOT NULL,
  menu_id       INT          NOT NULL,
  num_of_dishes INT          NOT NULL,

  FOREIGN KEY (order_bill_id) REFERENCES Order_Bill (id),
  FOREIGN KEY (menu_id) REFERENCES Menu (id)
);
CREATE TABLE  Messages (
  id INT AUTO_INCREMENT PRIMARY KEY ,
  user_from_id INT NOT NULL ,
  user_to_id INT NOT NULL ,
  message VARCHAR(1000) NOT NULL ,
  send_datetime DATETIME NOT NULL ,
  FOREIGN KEY (user_from_id) REFERENCES User(id),
  FOREIGN KEY (user_to_id) REFERENCES User(id)
);