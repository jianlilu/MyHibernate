package com.lu.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.lu.pojo.Product;

/*
��ҳ���ӵ�2����ʼ��һ����ѯ5��Product  
Hibernateʹ��Criteria �����з�ҳ��ѯ
c.setFirstResult(2); ��ʾ�ӵ�2�����ݿ�ʼ
c.setMaxResults(5); ��ʾһ����ѯ5������
*/
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
  
        String name = "iphone";
          
        Criteria c= s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        c.setFirstResult(2);
        c.setMaxResults(5);
        
        List<Product> ps = c.list();
        for (Product p : ps) {
            System.out.println(p.getName());
             
        }
          
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}