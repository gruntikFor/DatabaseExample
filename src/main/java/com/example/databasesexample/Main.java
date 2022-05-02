package com.example.databasesexample;

import org.hibernate.SessionFactory;

public class Main {

    static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
}
