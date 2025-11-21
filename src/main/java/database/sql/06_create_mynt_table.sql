USE Funn;

CREATE TABLE Mynt (
    Id INT PRIMARY KEY,
    Funnsted VARCHAR(255),
    Finner_id INT,
    Funntidspunkt DATETIME,
    Antatt_aarstall INT,
    Museum_id INT,
    Diameter INT,
    Metall VARCHAR(100),
    FOREIGN KEY (Finner_id) REFERENCES Person(Id),
    FOREIGN KEY (Museum_id) REFERENCES Museum(Id)
);