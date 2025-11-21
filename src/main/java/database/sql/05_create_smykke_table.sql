USE Funn;

CREATE TABLE Smykke (
    Id INT PRIMARY KEY,
    Funnsted VARCHAR(255),
    Finner_id INT,
    Funntidspunkt DATETIME,
    Antatt_aartstall INT,
    Museum_id INT,
    Type VARCHAR(100),
    Verdiestimat INT,
    filnavn VARCHAR(255),
    FOREIGN KEY (Finner_id) REFERENCES Person(Id),
    FOREIGN KEY (Museum_id) REFERENCES Museum(Id)
);