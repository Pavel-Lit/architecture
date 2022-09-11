

create table products (
                          id          bigserial primary key,
                          title       varchar(255),
                          price       numeric(8, 2),
                          created_at  timestamp default current_timestamp,
                          updated_at  timestamp default current_timestamp
);


insert into products (title, price) values
                                                     ('Сок', 150.00),
                                                     ('Торт', 650.00),
                                                     ('Кекс', 35.00),
                                                     ('Курица', 350.00),
                                                     ('Кефир', 90.00);

