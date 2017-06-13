package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;

/*
1. 根据id获取该对象
2. 修改该对象的属性
3. 通过Session的update方法把变化更新到数据库中

*/

public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
 
        Product p =(Product) s.get(Product.class, 6);
         
        System.out.println(p.getName());
         
        p.setName("iphone-modified");
         
        s.update(p);
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
 
}
