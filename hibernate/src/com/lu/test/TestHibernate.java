package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Category;

public class TestHibernate {
	 static Session s1;
	    static Session s2;
	 
	    public static void main(String[] args) {
	    	SessionFactory sf = new Configuration().configure().buildSessionFactory();

			Session s = sf.openSession();
			s.beginTransaction();

			for (int i = 0; i < 10; i++) {
				Category p = new Category();
				p.setName("iphone" + i);
				s.save(p);
			}

			s.getTransaction().commit();
			s.close();
			sf.close();
	    	
	    }
}