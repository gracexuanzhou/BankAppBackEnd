
INSERT INTO transactions(date, incoming_amount, outgoing_amount, bank_account_id, category_id,description)
VALUES ('2022-04-01 12:30:00',0,12,1,1,'car');

INSERT INTO Customers (first_name, lastname, email, username,password) VALUES ('John', 'Doe', 'johndoe@email.com', 'joe','123456789');

INSERT INTO categories(name) VALUES ('Groceries');
INSERT INTO categories(name) VALUES ('Dining');
INSERT INTO categories(name) VALUES ('Transport');
INSERT INTO categories(name) VALUES ('Shopping');
INSERT INTO categories(name) VALUES ('Housing expenses');
INSERT INTO categories(name) VALUES ('Entertainment');
INSERT INTO categories(name) VALUES ('Salary');
INSERT INTO categories(name) VALUES ('Others');

INSERT INTO bankaccounts(BALANCE, IBAN, CUSTOMER_ID) VALUES (0,'XXX123',1);

INSERT INTO bankaccounts(BALANCE, IBAN, CUSTOMER_ID) VALUES (0,'XXX1223',1);