insert into customer (first_name, last_name) values
('Carmen', 'Atkinson'),
('Imogen', 'Atkinson');

insert into product (name, price) values
('Apple', 1.00),
('Pear', 0.75),
('Gum', 1.25);

insert into "order" (ordered_by, ordered_on) values
(1, '2013-01-01 00:00:00'),
(2, '2013-06-05 00:00:00'),
(2, '2014-01-01 00:00:00'),
(1, '2014-03-04 00:00:00');

insert into order_product (order_id, product_id) values
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(4, 1),
(4, 3);
