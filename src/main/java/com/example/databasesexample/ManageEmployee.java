package com.example.databasesexample;

import com.example.databasesexample.enity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManageEmployee {
    static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = HibernateUtil.getSessionFactory();

        ManageEmployee me = new ManageEmployee();
        System.out.println(me.listEmployee());

//        Long userId = me.addEmployee("ken", 43);
//        System.out.println("saved user id: " + userId);
    }

    public Long addEmployee(String nickname, int age) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Long userId = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(nickname, age);
            userId = (Long) session.save(employee);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userId;
    }

    public List<Employee> listEmployee() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                Employee employee = (Employee) iterator.next();
                employeeList.add(employee);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeList;
    }
}
