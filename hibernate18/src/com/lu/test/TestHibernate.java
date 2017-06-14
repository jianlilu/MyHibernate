package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Category;
import com.lu.pojo.Product;

/*
ʹ�ò�ͬ��session,��ȥ��ȡid=1��category,ֻ�����һ�����ݿ⡣��Ϊ�ڶ��λ�ȡ��Ȼû��
�ӵڶ���session���õ����棬���Ǵ�sessionfactory���õ���Category�������
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

û�п�����������������
����������Session
�ڵ�һ��Session��
��һ�λ�ȡid=1��Category ��ִ��SQL���
�ڶ��λ�ȡid=1��Category������ִ��SQL��䣬��Ϊ��һ������
�ڵڶ���Session��
��ȡid=1��Category����ִ��SQL��䣬��Ϊ�ڵڶ���Session��û�л���ö��� 
�����ܹ��ῴ������SQL��䡣

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