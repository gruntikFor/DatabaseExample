package com.example.databasesexample;

import com.example.databasesexample.enity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class SQLQuery {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Object[]> products = null;
        try {
            session.beginTransaction();

            NativeQuery queryUpdate = session.createSQLQuery("update product set title = ? where id = ?");
            queryUpdate.setParameter(1, "fff");
            queryUpdate.setParameter(2, "1");
            queryUpdate.executeUpdate();

            NativeQuery sqlQuery = session.createSQLQuery("select * from product p inner join product_category pc on pc.id = p.category_id ");
            sqlQuery.addEntity("p", Product.class);
            sqlQuery.addJoin("pc", "p.category");
            products = sqlQuery.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

        for (Object[] obj : products) {
            System.out.println(obj[0].toString());
        }
    }
}
