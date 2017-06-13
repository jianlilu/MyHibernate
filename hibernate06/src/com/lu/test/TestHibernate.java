package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;

/*
1. ����id��ȡ�ö���
2. �޸ĸö��������
3. ͨ��Session��update�����ѱ仯���µ����ݿ���

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
