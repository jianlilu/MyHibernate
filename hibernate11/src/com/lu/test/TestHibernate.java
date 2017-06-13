package com.lu.test;

import java.util.Set;

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
         
        Category c = (Category) s.get(Category.class, 1);
        
        Set<Product> ps = c.getProducts();
        
        for (Product p : ps) {
            System.out.println(p.getName());
        }
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}