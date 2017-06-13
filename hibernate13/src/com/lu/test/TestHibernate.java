package com.lu.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lu.pojo.Product;
import com.lu.pojo.User;

/*
hibernate�е�������s.beginTransaction();��ʼ
��s.getTransaction().commit();����
�����ӣ�ִ������������
��һ����ɾ��id=1�Ĳ�Ʒ������ǻ�ɹ���
�ڶ������޸�id=2�Ĳ�Ʒ��ʹ�����Ʒ���Ƴ��������ݿ������õĳ���30������ǻ�ʧ�ܵġ�
��Ϊ����������������һ�������У����ҵڶ�������ʧ���ˣ��������Ľ��������������û����Ч
*/

public class TestHibernate {
	
    public static void main(String[] args) {
    	
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
 
        Product p = (Product) s.get(Product.class, 1);
        s.delete(p);
 
        Product p2 = (Product) s.get(Product.class, 2);
        p2.setName("���ȳ���30���ַ�����Ϊ��Ʒ���Ƴ��ȳ���30���ַ�����Ϊ��Ʒ���Ƴ��ȳ���30���ַ�����Ϊ��Ʒ���Ƴ��ȳ���30���ַ�����Ϊ��Ʒ����");
        s.update(p2);
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}

/*
��Mysql�У�ֻ�е����������INNODB��ʱ�򣬲�֧������������Ҫ�ѱ����������ΪINNODB,�����޷��۲쵽����.

�޸ı������ΪINNODB��SQL��
 
alter table product_ ENGINE  = innodb;
 
�鿴������͵�SQL
 
show table status from test; 
 
�����и�ǰ�ᣬ���ǵ�ǰ��MYSQL����������Ҫ֧��INNODB
*/