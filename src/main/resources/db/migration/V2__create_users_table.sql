CREATE TABLE users (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(45) NOT NULL,
password VARCHAR(64) NOT NULL,
role varchar(45) NOT NULL,
enabled TINYINT DEFAULT NULL
);

INSERT INTO users (name, password,role,enabled)
VALUES ('user', 'jdbcDefault','ROLE_USER',1);
