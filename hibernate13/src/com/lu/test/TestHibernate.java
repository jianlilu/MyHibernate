package com.lu.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;
import com.lu.pojo.User;

/*
hibernate中的事务由s.beginTransaction();开始
由s.getTransaction().commit();结束
本例子，执行了两个操作
第一个，删除id=1的产品，这个是会成功的
第二个，修改id=2的产品，使得其产品名称超过了数据库中设置的长度30，这个是会失败的。
因为这两个操作都是在一个事务中，而且第二个操作失败了，所以最后的结果是两个操作都没有生效
*/

public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
 
        Product p = (Product) s.get(Product.class, 1);
        s.delete(p);
 
        Product p2 = (Product) s.get(Product.class, 2);
        p2.setName("长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称");
        s.update(p2);
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}

/*
在Mysql中，只有当表的类型是INNODB的时候，才支持事务，所以需要把表的类型设置为INNODB,否则无法观察到事务.

修改表的类型为INNODB的SQL：
 
alter table product_ ENGINE  = innodb;
 
查看表的类型的SQL
 
show table status from test; 
 
不过有个前提，就是当前的MYSQL服务器本身要支持INNODB
*/