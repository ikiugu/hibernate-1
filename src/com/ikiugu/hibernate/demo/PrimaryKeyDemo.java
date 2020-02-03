package com.ikiugu.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ikiugu.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = (SessionFactory) new Configuration()
				.configure() //this looks for the default file => hibernate.cfg.xml if you dont add it in ()
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		//do db stuff
		try {
			
			Student student1 = new Student("A", "Me", "a@email.com");
			Student student2 = new Student("B", "Me", "b@email.com");
			Student student3 = new Student("C", "Me", "c@email.com");
			
			session.beginTransaction();
			
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}
