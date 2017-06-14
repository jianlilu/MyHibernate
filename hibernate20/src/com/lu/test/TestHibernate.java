package com.lu.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.lu.pojo.Product;

/*
����id�����ڵĶ���Ĵ���
��ͨ��id=500ȥ��ȡ���� 
1. get��ʽ�᷵��null 
2. load��ʽ���׳��쳣
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




//load��ʽ���ӳټ��أ�ֻ�����Ա����ʵ�ʱ��Ż����sql���
//get��ʽ�Ƿ��ӳټ��أ����ۺ���Ĵ����Ƿ����ʵ����ԣ�����ִ��sql���
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

