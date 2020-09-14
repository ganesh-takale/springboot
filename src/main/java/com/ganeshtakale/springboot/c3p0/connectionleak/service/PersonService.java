package com.ganeshtakale.springboot.c3p0.connectionleak.service;

import com.ganeshtakale.springboot.c3p0.connectionleak.model.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class PersonService {

	private SessionFactory sessionFactory;

	private DataSource dataSource;

	public PersonService(SessionFactory sessionFactory, DataSource dataSource) {
		this.dataSource = dataSource;
		this.sessionFactory = sessionFactory;
	}

	public List<Person> getAll() {
		List resultList = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Person", Person.class);
			resultList = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			//			uncomment to close the session
			//						if(session != null) {
			//				session.close();
			//			}
		}
		return resultList;
	}

	public List<Person> getAllPerson() {
		List resultList = null;
		Connection session = null;
		try {
			session = dataSource.getConnection();
			session.getSchema();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//			try {
			//				uncomment to close the connection
			//				if (session != null) {
			//					session.close();
			//				}
			//			} catch (SQLException e) {
			//				e.printStackTrace();
			//			}
		}
		return resultList;
	}

}
