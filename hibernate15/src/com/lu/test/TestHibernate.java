package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Category;


public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
        Category c = (Category) s.get(Category.class, 1);
 
        System.out.println("log1");
        System.out.println(c.getProducts());
        System.out.println("log1");
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}