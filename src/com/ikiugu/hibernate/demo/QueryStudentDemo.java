package com.ikiugu.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ikiugu.hibernate.demo.entity.Student;

public class QueryStudentDemo {

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
			
			// start a transaction
			session.beginTransaction();
			
			// query and cast the list
			List<Student> students = session.createQuery("from Student").getResultList(); // the last method here can either be .list() or as is. Hibernate versions 
			
			System.out.println("We have " + students.size() + " students saved: ");
			
			// loop through to display the students
			studentLister(students);
			
			// query students where first name = "tom"
			students = session.createQuery("from Student s where s.firstName='tom'").getResultList();
			
			studentLister(students);
			
			// query students where lastName ="It" Or firstName = "C"
			students = session.createQuery("from Student s where "
					+ "s.lastName='It'"
					+ "OR s.firstName='C'").getResultList();
			
			studentLister(students);
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

	private static void studentLister(List<Student> students) {
		for (Student theStudent : students) {
			System.out.println("\n"+theStudent);
		}
	}

}
