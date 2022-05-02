package com.example.databasesexample.jdbctemplate;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class JavaBeans {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url("jdbc:sqlserver://localhost:1433;database=db_example");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("flywaypass");
        return dataSourceBuilder.build();
    }
}
