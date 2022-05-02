package com.example.databasesexample.jdbctemplate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
class JDBCTestsOracle {

    static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/localtest";
    static final String DB_USER = "igor";
    static final String DB_PASSWORD = "gruntik";

    @Test
    void createTable() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
        ) {
            String sql = "CREATE TABLE users " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " surname VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Table Created");
        }
    }

    @Test
    void insertRow() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();) {

            String sql = "insert into users values('vanya','portynko',23,'discription')";
            stmt.executeUpdate(sql);
            System.out.println("Row added");
        }
    }

    @Test
    void selectRow() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();) {
            String sql = "select id, name, surname, age from users ";
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id"));
                System.out.println("name: " + resultSet.getString("name"));
                System.out.println("surname: " + resultSet.getString("surname"));
                System.out.println("age: " + resultSet.getInt("age"));
                System.out.println("\n-------next row");
            }
        }
    }

    @Test
    void updateRow() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();) {

            String sql = "update users set age = 55 where id in (1,2)";
            stmt.executeUpdate(sql);
        }
    }

    @Test
    void addColumn() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();) {
            String sql = "alter table users add description varchar(128)";
            System.out.println(stmt.executeUpdate(sql));
        }

    }
    @Test
    void deleteRow() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();) {
            String sql = "delete from users where id = 2";
            System.out.println(stmt.executeUpdate(sql));
        }
    }

}
