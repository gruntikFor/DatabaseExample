package com.example.databasesexample;

import com.example.databasesexample.enity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HQLQuery {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Product> products = null;
        List<Product> productsWithCategory = null;

        try {
            session.beginTransaction();

            Query query = session.createQuery("from Product");
            products = query.list();

            productsWithCategory = session.createQuery("from Product p inner join fetch p.category as pc").list();


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

        System.out.println(products);

        System.out.println(productsWithCategory);

    }
}
