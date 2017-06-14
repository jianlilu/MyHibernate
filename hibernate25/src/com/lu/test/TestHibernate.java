package com.lu.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;

public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
   
        Session s = sf.openSession();
        s.beginTransaction();
   
        s.createQuery("from Category").list();
           
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}