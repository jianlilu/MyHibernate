package com.lu.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;

/*
���ȿ���ʹ���ֹ��������

���ⴴ��һ�����������������ݡ�

1. ͨ��session1�õ�id=1�Ķ��� product1
2. ��product1ԭ���۸�Ļ���������1000
3. ����product1֮ǰ��ͨ��session2�õ�id=1�Ķ���product2
4. ��product2ԭ���۸�Ļ���������1000
5. ����product1
6. ����product2

�������product�ļ۸�ֻ������1000��������2000
*/

public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s1 = sf.openSession();
        Session s2 = sf.openSession();
 
        s1.beginTransaction();
        s2.beginTransaction();
 
        Product p1 = (Product) s1.get(Product.class, 1);
        System.out.println("��Ʒԭ���۸���: " + p1.getPrice());
 
        p1.setPrice(p1.getPrice() + 1000);
 
        Product p2 = (Product) s2.get(Product.class, 1);
        p2.setPrice(p2.getPrice() + 1000);
 
        s1.update(p1);
        s2.update(p2);
 
        s1.getTransaction().commit();
        s2.getTransaction().commit();
 
        Product p = (Product) s1.get(Product.class, 1);
 
        System.out.println("�������μ۸����Ӻ󣬼۸��Ϊ: " + p.getPrice());
 
        s1.close();
        s2.close();
 
        sf.close();
    }
 
}
