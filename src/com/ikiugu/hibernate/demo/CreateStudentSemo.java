package com.ikiugu.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ikiugu.hibernate.demo.entity.Student;

public class CreateStudentSemo {

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
			
			Student someStudent = new Student("Alfy", "Me", "me@email.com");
			
			session.beginTransaction();
			
			session.save(someStudent);
			
			session.getTransaction().commit();
			
			session.getTransaction().rollback();
			
		} finally {
			factory.close();
		}
	}

}
