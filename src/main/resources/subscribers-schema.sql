DROP TABLE IF EXISTS Subscribers;

CREATE TABLE Subscribers (
                      id varchar(255) NOT NULL,
                      name varchar(255) NOT NULL,
                      email varchar(255) NOT NULL,
                      PRIMARY KEY (id)
);

INSERT INTO Subscribers
(id, name, email)
VALUES ('1', 'Kalle', 'kalle@email.com');