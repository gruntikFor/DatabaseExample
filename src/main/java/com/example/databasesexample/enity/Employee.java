package com.example.databasesexample.enity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

    long id;
    String nickname;
    int age;

    Role role;

    public Employee(String nickname, int age) {
        this.nickname = nickname;
        this.age = age;
    }
}
