package com.example.databasesexample;

import com.example.databasesexample.enity.Product;
import com.example.databasesexample.enity.ProductCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Validate {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Product> products = null;

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Product product = new Product();
        product.setId(4L);
        product.setTitle("iiiiiiigor");
//        product.setCost(new BigDecimal(50));
        Set<ConstraintViolation<Product>> constrs = validator.validate(product);

        for (ConstraintViolation<Product> constr : constrs) {
            System.out.println(constr.getInvalidValue() + " " + constr.getMessage() + " " + constr.getPropertyPath());
        }


//        try {
//            session.beginTransaction();
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//            sessionFactory.close();
//        }

//        System.out.println(products.size());
//        System.out.println(products);

    }
}
