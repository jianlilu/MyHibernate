package com.lu.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.lu.pojo.Product;

/*
ʹ��Criteria ��ѯ����
1. ͨ��session��createCriteria����һ��Criteria ����
2. Criteria.add ����Լ���� �ڱ���������һ����name��ģ����ѯ(like)
3. ����list()�������ز�ѯ����ļ���
*/

public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
 
        String name = "iphone";
          
        Criteria c= s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        List<Product> ps = c.list();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}