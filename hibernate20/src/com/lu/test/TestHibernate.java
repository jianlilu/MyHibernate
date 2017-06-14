package com.lu.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.lu.pojo.Product;

/*
对于id不存在的对象的处理
都通过id=500去获取对象 
1. get方式会返回null 
2. load方式会抛出异常
*/
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
        
        System.out.println("log1");
        
        Product p = (Product)s.get(Product.class, 5);
        System.out.println("log2");
        
        Product p2 = (Product)s.load(Product.class, 5);
        System.out.println("log3");
         
        Product p3 = (Product)s.get(Product.class, 500);
        System.out.println("p3="+p3);
         
        Product p4 = (Product)s.load(Product.class, 500);
        System.out.println("p3="+p4);
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}




//load方式是延迟加载，只有属性被访问的时候才会调用sql语句
//get方式是非延迟加载，无论后面的代码是否会访问到属性，马上执行sql语句
/*
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
        
        System.out.println("log1");
        Product p = (Product) s.get(Product.class, 1);
        System.out.println("log2");
        
        Product p2 = (Product) s.load(Product.class, 2);
        System.out.println("log3");
        System.out.println(p2.getName());
        System.out.println("log4");
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
*/

