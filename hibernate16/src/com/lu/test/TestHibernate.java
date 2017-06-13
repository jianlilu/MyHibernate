package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Category;
import com.lu.pojo.Product;



public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        
//        ��3��˲ʱ״̬�Ĳ�Ʒ������Ȼû����ӵ����ݿ�������������ύ��ʱ�򣬻������3���־û��� 
//        ���û��cascade="save-update"���ͻᱨ�� 
        Category c = (Category) s.get(Category.class, 5);
        Product p1 = new Product();
        p1.setName("product_501");
        Product p2 = new Product();
        p2.setName("product_502");
        Product p3 = new Product();
        p3.setName("product_503");
 
        c.getProducts().add(p1);
        c.getProducts().add(p2);
        c.getProducts().add(p3);
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
     
    public static void main4delete(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        
        Category c = (Category) s.get(Category.class, 3);
        s.delete(c);
        
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}


//	all ���� delete+save-update
//	none ����û�У�Ĭ�Ͼ���none



/*
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        
//        delete ����
//        ɾ�������ʱ�򣬻�ѷ����¶�Ӧ�Ĳ�Ʒ��ɾ����������ֻ��Ѳ�Ʒ��Ӧ��cid����Ϊ��
        Category c = (Category) s.get(Category.class, 3);
        s.delete(c);
        
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
*/