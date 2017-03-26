package com.rozetka.selenium.utils.db;

import com.rozetka.selenium.utils.SeleniumProperties;

import java.sql.*;

/**
 * Created by artem on 3/24/17.
 */
public class MySqlDb {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public MySqlDb() {
        connection = getConnection();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public void executeQueryUpdate(String query) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public String executeCustomQueryOneResult(String query) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getString(1);
    }

    public void closeDbConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }

    private Connection getConnection() {
        loadDataBaseDriver();
        String host = SeleniumProperties.getProperty("mysql.host");
        String username = SeleniumProperties.getProperty("mysql.user");
        String password = SeleniumProperties.getProperty("mysql.password");
        String database = SeleniumProperties.getProperty("mysql.database");
        String port = SeleniumProperties.getProperty("mysql.port");
        String defaultDbEncoding = "UTF-8";
        String url = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=%s", host, port, database, defaultDbEncoding);
        System.out.println("Connecting database...");
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private void loadDataBaseDriver() {
        System.out.println("Loading driver...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }
}