package com.lu.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;
import com.lu.pojo.User;


//�ڴ�ӡlog1֮ǰ���ǲ����ӡ��sql���ģ�ֻ���ڷ��ʷ������ԡ�getName()"��ʱ�򣬲Ż�������ݿ�

public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
     
        Product p = (Product)s.load(Product.class, 1);
        System.out.println("log1");
        System.out.println(p.getName());
        System.out.println("log2");
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}