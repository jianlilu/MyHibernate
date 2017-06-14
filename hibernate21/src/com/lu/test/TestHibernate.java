package com.lu.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.lu.pojo.Product;


//getCurrentSession在提交事务后，session自动关闭
//下面代码在事务关闭后，试图关闭session,就会报session已经关闭的异常
public class TestHibernate {
	 
    public static void main(String[] args) throws InterruptedException {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s1 = sf.getCurrentSession();
 
        s1.beginTransaction();
 
        s1.get(Product.class, 5);
 
        s1.getTransaction().commit();
         
        s1.close();
 
        sf.close();
    }
 
}



//getCurrentSession所有操作都必须放在事务中
//对于getCurrentSession而言所有操作都必须放在事务中，甚至于查询和get都需要事务。
//下面的get没有放在事务中，就会导致异常产生
/*
public class TestHibernate {
	 
    public static void main(String[] args) throws InterruptedException {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s1 = sf.getCurrentSession();
 
        s1.get(Product.class, 5);
 
        s1.close();
 
        sf.close();
    }
 
}
*/


//openSession查询时候不需要事务
//如果是做增加，修改，删除是必须放在事务里进行的。 但是如果是查询或者get，
//那么对于openSession而言就不需要放在事务中进行
/*
public class TestHibernate {
    static Session s1;
    static Session s2;
 
    public static void main(String[] args) throws InterruptedException {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s1 = sf.openSession();
 
        s1.get(Product.class, 5);
 
        s1.close();
 
        sf.close();
    }
}
*/

//不同线程的getCurrentSession
//如果是不同线程，每次获取的都是不同的Session
/*
public class TestHibernate {
    static Session s1;
    static Session s2;
 
    public static void main(String[] args) throws InterruptedException {
 
        final SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Thread t1 = new Thread() {
            public void run() {
                s1 = sf.getCurrentSession();
            }
        };
        t1.start();
 
        Thread t2 = new Thread() {
            public void run() {
                s2 = sf.getCurrentSession();
            }
        };
        t2.start();
        
        t1.join();
        t2.join();
 
        System.out.println(s1 == s2);
    }
 
}
*/


//相同线程的getCurrentSession
//如果是同一个线程(本例是在主线程里)，每次获取的都是相同的Session
/*
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s1 = sf.getCurrentSession();
        Session s2 = sf.getCurrentSession();
 
        System.out.println(s1 == s2);
 
        s1.close();
//      s2.close();
        sf.close();
    }
}
*/

//OpenSession每次都会得到一个新的Session对象，所以System.out.println(s1==s2);会打印false
/*
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s1 = sf.openSession();
        Session s2 = sf.openSession();
        System.out.println(s1 == s2);
         
        s1.close();
        s2.close();
        sf.close();
    }
}
*/