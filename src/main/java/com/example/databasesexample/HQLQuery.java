package com.example.databasesexample;

import com.example.databasesexample.enity.Product;
import com.example.databasesexample.enity.ProductCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class HQLQuery {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Product> products = null;
        List<Product> productsWithCategory = null;
        List<Object[]> productsWith = null;
        List<Product> productsSelect = null;

        try {
            session.beginTransaction();

            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(3L);
//            productCategory.setTitle("Drink2");
//            session.save(productCategory);

            Product product = new Product("Vodka2", new BigDecimal(40), productCategory);
            session.save(product);

            Query query = session.createQuery("from Product");
            products = query.list();

            productsWithCategory = session.createQuery("from Product p inner join fetch p.category as pc").list();

            Query query1 = session.createQuery("from Product  p inner join p.category as pc with pc.id = :id");
            query1.setParameter("id", 1L);
            productsWith = query1.list();

            Query query2 = session.createQuery("select p from Product  p inner join p.category as pc with pc.id = :id");
            query2.setParameter("id", 1L);
            productsSelect = query2.list();

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

        for (Object[] obj : productsWith) {
            System.out.println(obj[0].toString());
        }

        System.out.println(productsSelect);

    }
}
