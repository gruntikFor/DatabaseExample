package com.example.databasesexample;

import com.example.databasesexample.enity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CriteriaQuery {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Product> products = null;

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Product.class, "product");
            criteria.createCriteria("product.category", "category");
            criteria.add(Restrictions.eq("product.title", "Vodka"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

//            criteria.add(Restrictions.eq("title", "Vodka2"));
//            criteria.add(Restrictions.between("cost", new BigDecimal(35), new BigDecimal(50)));
//            criteria.add(Restrictions.like("title", "Vodka%"));
//            criteria.add(Restrictions.or(
//                    Restrictions.eq("title", "Vodka"),
//                    Restrictions.eq("title", "Vodka3")
//            ));

//            criteria.setProjection(Projections.distinct(Projections.property("title")));

//            criteria.setFirstResult(3);
//            criteria.setMaxResults(1);


//            criteria.add(Restrictions.sqlRestriction("cost > 40"));
//            criteria.addOrder(Order.desc("title"));

            products = criteria.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

        System.out.println(products.size());
        System.out.println(products);


    }
}
