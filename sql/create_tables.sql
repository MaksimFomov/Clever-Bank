-- Создание таблицы банков
CREATE TABLE bank (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      location VARCHAR(100) NOT NULL
);

-- Создание таблицы пользователей
CREATE TABLE user (
                      id SERIAL PRIMARY KEY,
                      first_name VARCHAR(50) NOT NULL,
                      last_name VARCHAR(50) NOT NULL,
                      birth_date DATE,
                      email VARCHAR(100)
);

-- Создание таблицы счетов
CREATE TABLE account (
                         id SERIAL PRIMARY KEY,
                         account_number VARCHAR(20) NOT NULL,
                         balance DECIMAL(15, 2) NOT NULL,
                         user_id INT REFERENCES user(id),
                         bank_id INT REFERENCES bank(id),
                         creation_date DATE,
                         is_blocked BOOLEAN
);

-- Создание таблицы транзакций
CREATE TABLE transaction (
                             id SERIAL PRIMARY KEY,
                             source_account_id INT REFERENCES account(id),
                             target_account_id INT REFERENCES account(id),
                             amount DECIMAL(15, 2) NOT NULL,
                             timestamp TIMESTAMP
);
