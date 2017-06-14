package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Category;
import com.lu.pojo.Product;

/*
��һ��ͨ��id=1��ȡ�����ʱ��session����û�ж�Ӧ�������ģ����Ի���"log1"�����sql��ѯ��䡣
�ڶ���ͨ��id=1��ȡ�����ʱ��session���ж�Ӧ�Ļ������������"log2"�󲻻����sql��ѯ���

�����ܹ��ῴ��һ��SQL������
*/

public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
   
        Session s = sf.openSession();
        s.beginTransaction();
        
        System.out.println("log1");
        Category c1 = (Category)s.get(Category.class, 1);
        
        System.out.println("log2");
        
        Category c2= (Category)s.get(Category.class, 1);
        System.out.println("log3");        
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}

