package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Category;
import com.lu.pojo.Product;

/*
使用不同的session,都去获取id=1的category,只会访问一次数据库。因为第二次获取虽然没有
从第二个session中拿到缓存，但是从sessionfactory中拿到了Category缓存对象
*/
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
         
        Category c1 = (Category)s.get(Category.class, 1);
        System.out.println("log1");
        
        Category c2= (Category)s.get(Category.class, 1);
        System.out.println("log2");
        
        s.getTransaction().commit();
        s.close();
        
        //////////////////////////////////////////////////////
        
        Session s2 = sf.openSession();
        s2.beginTransaction();
        
        Category c3 = (Category)s2.get(Category.class, 1);
        System.out.println("log3");
  
        s2.getTransaction().commit();
        s2.close();
        sf.close();
    }
}





/*

没有开启二级缓存的情况：
创建了两个Session
在第一个Session里
第一次获取id=1的Category 会执行SQL语句
第二次获取id=1的Category，不会执行SQL语句，因为有一级缓存
在第二个Session里
获取id=1的Category，会执行SQL语句，因为在第二个Session，没有缓存该对象。 
所以总共会看到两条SQL语句。

public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
        
        Category p1 = (Category) s.get(Category.class, 1);
        Category p2 = (Category) s.get(Category.class, 1);
        
        s.getTransaction().commit();
        s.close();
        
        //////////////////////////////////////////////////
        
        Session s2 = sf.openSession();
        s2.beginTransaction();
        
        Category p3 = (Category) s2.get(Category.class, 1);
 
        s2.getTransaction().commit();
        s2.close();
        sf.close();
    }
}
*/