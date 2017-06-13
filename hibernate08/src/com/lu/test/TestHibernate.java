package com.lu.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.lu.pojo.Product;

/*
使用Criteria 查询数据
1. 通过session的createCriteria创建一个Criteria 对象
2. Criteria.add 增加约束。 在本例中增加一个对name的模糊查询(like)
3. 调用list()方法返回查询结果的集合
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