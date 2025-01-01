USE spring_boot_practice;
SELECT DATABASE() AS current_db;

CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    done BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    finished_at TIMESTAMP
);
DROP TABLE tasks;
SELECT * FROM tasks;

INSERT INTO tasks
(title, description, done)
VALUES
('Coding', 'I love coding', FALSE);

INSERT INTO tasks
(title, description, done)
VALUES
('Buy Rose Plant', 'Today I will buy a beautiful rose plant', FALSE),
('Picnic', 'I will enjoy new year (2025) with a joyful picnic with my freinds', FALSE);