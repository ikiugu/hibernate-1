package com.ikiugu.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ikiugu.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			
			int studentId = 1;
			
			// get a session then start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on the transaction 
			Student retrievedStudent = session.get(Student.class, studentId);
			
			System.out.println("The retrieved student is " + retrievedStudent);
			
			
			/*
			 * //delete a single record using the normal delete
			 * session.delete(retrievedStudent);
			 */
			
			// delete using a where clause
			session.createQuery("delete from Student where id=3").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
		
		} finally {
			factory.close();
		}
	}

}
