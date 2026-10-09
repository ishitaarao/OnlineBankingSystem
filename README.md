# Online Banking System

Customers manage accounts and transactions; administrators manage users, monitor transactions and configure the system.

## Tech stack
Java (JDK 17+), MySQL 8, JDBC (MySQL Connector/J)

## Setup
1. Install JDK and MySQL.
2. Run `database/schema.sql` in MySQL to create the `bankdb` database and tables.
3. Download MySQL Connector/J and put the `.jar` in the `lib/` folder.
4. Copy `db.properties.example` to `db.properties` and enter your MySQL username and password.

## Run the JDBC test
```
javac -cp "lib/*" -d out src/util/*.java src/model/*.java src/dao/*.java src/TestJDBC.java
java -cp "out;lib/*" TestJDBC        (on Mac/Linux use ":" instead of ";")
```

## Structure
`model` entities | `dao` database operations | `util` connection and helpers | `database` SQL schema
