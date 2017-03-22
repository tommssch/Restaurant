INSERT INTO Address(city,street,house,flat)
  VALUES('SPb',
         'Маршала Жуковва',
         '20',
         150);

INSERT INTO User(name,lastname,fathername,dob,
                 email,nickname,password,address_id,telephone)
    VALUES ('Сергей',
            'Петров',
            'Иванович',
            '1967-12-22',
            'sergeypetrov@mail.ru',
            'sereega',
            'qwerty',
            1,
            '987-48-98');

INSERT INTO Menu(id,price,cooktime,description)
    VALUES ('Котлетка по Киевски',99.9,
            '00:30:00',
            'Котлетка в панировке с использованием курицы');

INSERT INTO Menu(id,price,cooktime,description)
VALUES ('Кофе Эспрессо',180.0,
        '00:15:00',
        'Бодрящий кофе');

INSERT INTO Menu(id,price,cooktime,description)
VALUES ('Ванильное мороженно',50.0,
        '00:05:00',
        'Нежный пломбир');

INSERT INTO Administration(name,lastname,fathername,dob,experience)
    VALUES ('Иван',
            'Федоров',
            'Акакиевич',
            '1988-03-06',6);

INSERT INTO Order_Bill(user_id, administration_id)
  VALUES(1,1);

INSERT INTO Kitchen(order_bill_id, menu_id, num_of_dishes)
  VALUES(1,'Котлетка по Киевски',5);
