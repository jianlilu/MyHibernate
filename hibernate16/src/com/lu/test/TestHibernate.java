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
        
//        这3个瞬时状态的产品对象虽然没有添加到数据库里，但是在事务提交的时候，会把他们3个持久化。 
//        如果没有cascade="save-update"，就会报错 
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


//	all 就是 delete+save-update
//	none 就是没有，默认就是none



/*
public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        
//        delete 级联
//        删除分类的时候，会把分类下对应的产品都删除掉，否则只会把产品对应的cid设置为空
        Category c = (Category) s.get(Category.class, 3);
        s.delete(c);
        
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
*/