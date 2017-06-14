package com.lu.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;

/*
Hibernateʹ��Iteratorʵ��N 1

����ͨ��Query��iterator����������������Product��id�����

Ȼ����ͨ��it.next()��ѯÿһ������
�����������ڻ����У���ֱ�Ӵӻ�����ȡ��
����ʹ����ݿ��л�ȡ

N+1�е�1������ָֻ����id��SQL��䣬Nָ��������ڻ������Ҳ�����Ӧ�����ݣ��͵����ݿ���ȥ��
*/
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
  
        String name = "iphone";
         
        Query q =s.createQuery("from Product p where p.name like ?");
         
        q.setString(0, "%"+name+"%");
        
        Iterator<Product> it= q.iterate();
        while(it.hasNext()){
            Product p =it.next();
            System.out.println(p.getName());
        }
        
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
  
}