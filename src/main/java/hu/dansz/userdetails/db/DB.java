package hu.dansz.userdetails.db;

import hu.dansz.userdetails.config.LoadProperties;

import java.sql.*;
import java.util.Properties;

public class DB {

    private Properties properties;
    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;

    public DB() {
        LoadProperties loadProperties = new LoadProperties();
        this.properties = loadProperties.getProperties();
        this.driverClassName = this.properties.getProperty("driverClassName");
        this.jdbcUrl = this.properties.getProperty("url");
        this.username = this.properties.getProperty("username");
        this.password = this.properties.getProperty("password");
    }

    public void execute(String sql) throws SQLException {
        Connection db = this.getConnection();
        Statement statement = db.createStatement();
        statement.execute(sql);
        db.close();
    }

    public ResultSet executeQuerySql(String sql) throws SQLException {
        Connection db = this.getConnection();
        Statement statement = db.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection db = this.getConnection();
        return db.prepareStatement(sql);
    }

    private Connection getConnection() {
        try {
            Class.forName(this.driverClassName);
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }

        Connection _db;

        try {
            return _db = DriverManager.getConnection(this.jdbcUrl, this.username, this.password);
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
