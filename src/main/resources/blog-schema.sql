DROP TABLE IF EXISTS Post;

CREATE TABLE Post (
    id varchar(255) NOT NULL,
    title varchar(255) NOT NULL,
    text varchar(255) NOT NULL,
    date date NOT NULL,
    time_to_read int NOT NULL,
    tags varchar(255),
    PRIMARY KEY (id)
);

INSERT INTO Post
(id, title, text, date, time_to_read, tags)
VALUES ('1', 'TODO', 'Coding', CURRENT_DATE, 5, 'Spring Boot, Java');