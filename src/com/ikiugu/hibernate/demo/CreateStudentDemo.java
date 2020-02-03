package com.ikiugu.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ikiugu.hibernate.demo.entity.Student;

public class CreateStudentDemo {

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
			
			String dateOfBirth = "02/02/2020";
			
			Date formattedDate = null;
			try {
				formattedDate = DateUtils.parseDate(dateOfBirth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Student someStudent = new Student("Alfy", "Me", "me@email.com", formattedDate);
			
			session.beginTransaction();
			
			session.save(someStudent);
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
