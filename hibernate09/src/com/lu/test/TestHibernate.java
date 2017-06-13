package com.lu.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
使用Session的createSQLQuery方法执行标准SQL语句

因为标准SQL语句有可能返回各种各样的结果，比如多表查询，分组统计结果等等。 不能保证其查询结果能够装进
一个Product对象中，所以返回的集合里的每一个元素是一个对象数组。 然后再通过下标把这个对象数组中的数据取出来。
*/

public class TestHibernate {
	
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
 
        String name = "iphone";
         
        String sql = "select * from product_ p where p.name like '%"+name+"%'";
         
        Query q= s.createSQLQuery(sql);
        
        List<Object[]> list= q.list();
        
        for (Object[] os : list) {
            for (Object filed: os) {
                System.out.print(filed+"\t");
            }
            System.out.println();
        }
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}