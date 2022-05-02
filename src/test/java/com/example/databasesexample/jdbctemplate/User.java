package com.example.databasesexample.jdbctemplate;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Integer id;
    String name;
    int age;
    String surname;
    String description;

}
