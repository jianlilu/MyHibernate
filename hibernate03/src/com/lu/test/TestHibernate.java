package com.lu.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;

/*
˲ʱ
	new ��һ��Product();�������ݿ��л�û�ж�Ӧ�ļ�¼�����ʱ��Product�����״̬��˲ʱ�ġ�
	 
�־�
	ͨ��Session��save�Ѹö��󱣴��������ݿ��У��ö���Ҳ��Session֮���������ϵ����ʱ״̬�ǳ־õġ�
	
�ѹ�
	����Session�ر��ˣ�������������ݿ�����Ȼ�ж�Ӧ�����ݣ������Ѿ���Sessionʧȥ����ϵ��
	�൱�������˹���״̬�����ѹܵ�
*/

public class TestHibernate {
    
    public static void main(String[] args) {
  
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
        
        Product p = new Product();
        p.setName("p1");
        System.out.println("��ʱp��˲ʱ״̬");
        
        s.save(p);
        System.out.println("��ʱp�ǳ־�״̬");
        
        s.getTransaction().commit();
        s.close();
        System.out.println("��ʱp���ѹ�״̬");
        
        sf.close();
    }
  
}