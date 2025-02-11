CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    city VARCHAR(50)
);

-- 대량의 테스트 데이터를 삽입 (반복문이나 스크립트를 사용해도 됨)
INSERT INTO customers (first_name, last_name, email, city)
VALUES 
('Alice', 'Lee', 'alice@example.com', 'Seoul'),
('Bob', 'Kim', 'bob@example.com', 'Busan'),
('Charlie', 'Park', 'charlie@example.com', 'Daegu');
