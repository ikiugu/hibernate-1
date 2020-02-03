package com.ikiugu.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ikiugu.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			
			// create a student object
			Student someStudent = new Student("Max", "Lee", "mlee@email.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Student is " +someStudent);
			session.save(someStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// get the primary key from hibernate
			System.out.println("Student id is " + someStudent.getId());
			
			// get a session then start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on the transaction 
			Student retrievedStudent = session.get(Student.class, someStudent.getId());
			
			System.out.println("The retrieved student is " + retrievedStudent);
					
			// commit the transaction
			session.getTransaction().commit();
		
		} finally {
			factory.close();
		}
	}

}
