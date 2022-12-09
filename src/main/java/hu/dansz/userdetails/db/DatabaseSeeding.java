package hu.dansz.userdetails.db;

import java.sql.*;

public class DatabaseSeeding {

    public void seedingDb() {
        this.createSchema();
        this.loadUsers();
        this.loadAddress();
    }

    private void loadUsers() {
        DB db = new DB();
        String sql = "INSERT INTO users (firstname, lastname) VALUES('Kristian', 'Taylor'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Keira', 'Solis'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Hajra', 'Chang'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Alexis', 'Leon'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Nikita', 'Krueger'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Cynthia', 'Andersen'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Felix', 'Cisneros'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Cora', 'Powers'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Tamara', 'Mooney'); " +
                "INSERT INTO users (firstname, lastname) VALUES('Roxanne', 'Myers');";
        try {
            db.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAddress() {
        DB db = new DB();
        String sql = "INSERT INTO address (cim) VALUES('Budapest XII. Ker.'); " +
                "INSERT INTO address (cim) VALUES('Debrecen Nagyerdei körút 98'); " +
                "INSERT INTO address (cim) VALUES('Nyíregyháza Bujtos utca 2'); ";
        try {
            db.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createSchema() {
        DB db = new DB();
        String userTableSql = "CREATE TABLE users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "firstname VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "email VARCHAR(50), " +
                "address_id BIGINT, " +
                "FOREIGN KEY (address_id) REFERENCES address(id))";
        String addressTableSql = "CREATE TABLE address (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "cim VARCHAR(50))";
        try {
            db.execute(addressTableSql);
            db.execute(userTableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
