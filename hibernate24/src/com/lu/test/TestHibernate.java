package com.lu.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;

/*
首先看不使用乐观锁的情况

故意创造一个场景来制造脏数据。

1. 通过session1得到id=1的对象 product1
2. 在product1原来价格的基础上增加1000
3. 更新product1之前，通过session2得到id=1的对象product2
4. 在product2原来价格的基础上增加1000
5. 更新product1
6. 更新product2

最后结果是product的价格只增加了1000，而不是2000
*/

public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s1 = sf.openSession();
        Session s2 = sf.openSession();
 
        s1.beginTransaction();
        s2.beginTransaction();
 
        Product p1 = (Product) s1.get(Product.class, 1);
        System.out.println("产品原本价格是: " + p1.getPrice());
 
        p1.setPrice(p1.getPrice() + 1000);
 
        Product p2 = (Product) s2.get(Product.class, 1);
        p2.setPrice(p2.getPrice() + 1000);
 
        s1.update(p1);
        s2.update(p2);
 
        s1.getTransaction().commit();
        s2.getTransaction().commit();
 
        Product p = (Product) s1.get(Product.class, 1);
 
        System.out.println("经过两次价格增加后，价格变为: " + p.getPrice());
 
        s1.close();
        s2.close();
 
        sf.close();
    }
 
}
