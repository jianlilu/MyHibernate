package com.lu.test;

import java.util.List;

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
 
        String name = "iphone";
        Query q =s.createQuery("from Product p where p.name like ?");
        q.setString(0, "%"+name+"%");
        List<Product> ps= q.list();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
 
}