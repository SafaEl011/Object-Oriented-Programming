# Archaeological Artifact Database – PGR112v24

Dette er en Java-basert konsollapplikasjon for å håndtere arkeologiske funn (artefakter).  
Applikasjonen lagrer informasjon om personer, museer og ulike artefakttyper (mynter, smykker, våpen) i en PostgreSQL-database.

Prosjektet ble opprinnelig utviklet for **MySQL**, men er nå **fullt migrert til PostgreSQL** for å fungere i Replit-miljøet.

---

## 🚀 Oversikt

- Konsollbasert Java-program
- JDBC-basert databasehåndtering
- Automatisk databaseopprettelse ved oppstart
- Menysystem for å vise og filtrere artefakter
- Full støtte for PostgreSQL i Replit

---

## 📁 Prosjektstruktur

src/main/java/
├── Main.java ← Programstart
├── database/
│   ├── DatabaseConnection.java
│   ├── MenuHandler.java
│   ├── InsertDataIntoDatabase.java
│   └── sql/ ← PostgreSQL-skjemaer
└── model/
    ├── Artifact.java
    ├── Coin.java
    ├── Jewelry.java
    ├── Weapon.java
    ├── FoundItem.java
    ├── Person.java
    └── Museum.java

src/main/resources/
├── funn.txt
└── database.properties (brukes lokalt)

pom.xml  
start.sh

---

## 🔄 Endringer etter Replit-migrering

### 🗄️ Database: MySQL → PostgreSQL
- Replit bruker PostgreSQL, derfor ble all databasekode oppdatert.
- `DatabaseConnection.java` bruker nå Replit sine miljøvariabler:
  - `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`
- Alle SQL-skript konvertert:
  - `DATETIME → TIMESTAMP`
  - Fjernet `USE database;`
  - Oppdatert til ren PostgreSQL-syntaks

### 🧠 Kodeendringer
- **DatabaseConnection.java** fullstendig omskrevet for PostgreSQL
- **Main.java** utvidet med manglende klassedeklarasjon
- Meny- og spørringslogikk oppdatert for kompatibilitet

### 📦 Maven-endringer
- Fjernet MySQL-connector  
- Lagt til PostgreSQL JDBC driver:  
  `org.postgresql:postgresql:42.7.1`
- Java-versjon justert til **19** (Replit støtter ikke 21)
- Lagt til `exec-maven-plugin`

---

## 🏁 Nye hjelpeskript

- **start.sh** – kjører kompilering, avhengigheter og oppstart  
- **init_db.sh** – sjekker om databasen eksisterer og oppretter skjema  

---

## ▶️ Hvordan kjøre i Replit

Startscriptet:

1. Leser databasevariabler fra Replit-miljøet  
2. Initialiserer og oppretter database-skjema ved behov  
3. Kompilerer Java-koden med Maven  
4. Starter applikasjonen  

Manuell kjøring:

```
./start.sh
```

---

## 🗄️ Databaseoppsett

Tabeller som opprettes automatisk:

- Person  
- Museum  
- Mynt  
- Smykke  
- Våpen  

Testdata lastes inn automatisk.

---

## ✨ Funksjoner i menyen

1. View all artifacts  
2. View artifacts older than a specific year  
3. View total number of artifacts  
4. Exit  

---

## 🛠️ Teknologier

- Java 19 (GraalVM – Replit standard)  
- PostgreSQL (Replit)  
- Maven  
- JDBC  
- Shell scripts (start.sh, init_db.sh)

---

## 🧱 Arkitektur

- Konsollbasert brukergrensesnitt  
- JDBC-drevet databasekommunikasjon  
- All konfigurasjon basert på miljøvariabler  
- Enkel oppstartsarkitektur for undervisning  

---

## ✔️ Status

🎉 Prosjektet kjører stabilt i Replit  
💾 PostgreSQL fungerer med automatisert skjema  
🔧 Alle migrasjoner og kompatibilitetserrorer er løst  
