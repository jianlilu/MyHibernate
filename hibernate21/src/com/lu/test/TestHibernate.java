package com.lu.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.lu.pojo.Product;


//getCurrentSession���ύ�����session�Զ��ر�
//�������������رպ���ͼ�ر�session,�ͻᱨsession�Ѿ��رյ��쳣
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



//getCurrentSession���в������������������
//����getCurrentSession�������в�����������������У������ڲ�ѯ��get����Ҫ����
//�����getû�з��������У��ͻᵼ���쳣����
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


//openSession��ѯʱ����Ҫ����
//����������ӣ��޸ģ�ɾ���Ǳ��������������еġ� ��������ǲ�ѯ����get��
//��ô����openSession���ԾͲ���Ҫ���������н���
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

//��ͬ�̵߳�getCurrentSession
//����ǲ�ͬ�̣߳�ÿ�λ�ȡ�Ķ��ǲ�ͬ��Session
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


//��ͬ�̵߳�getCurrentSession
//�����ͬһ���߳�(�����������߳���)��ÿ�λ�ȡ�Ķ�����ͬ��Session
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

//OpenSessionÿ�ζ���õ�һ���µ�Session��������System.out.println(s1==s2);���ӡfalse
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