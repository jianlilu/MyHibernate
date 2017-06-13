package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Category;
import com.lu.pojo.Product;

public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
         
        Category c =new Category();
        c.setName("c1");
        s.save(c);
         
        Product p = (Product) s.get(Product.class, 8);
        p.setCategory(c);
        s.update(p);
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}