USE Funn;

CREATE TABLE Vaapen (
    Id INT PRIMARY KEY,
    Funnsted VARCHAR(255),
    Finner_id INT,
    Funntidspunkt DATETIME,
    Antatt_aarstall INT,
    Museum_id INT,
    Type VARCHAR(100),
    Materiale VARCHAR(100),
    Vekt INT,
    FOREIGN KEY (Finner_id) REFERENCES Person(Id),
    FOREIGN KEY (Museum_id) REFERENCES Museum(Id)
);