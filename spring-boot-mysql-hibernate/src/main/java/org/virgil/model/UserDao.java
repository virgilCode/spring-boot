package org.virgil.model;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(User u) {
		getSession().save(u);
	}

	public void delete(User u) {
		getSession().delete(u);
	}

	public User getUserByEmail(String email) {
		return (User) getSession().createQuery("from User where email=?")
				.setParameter("email", email).uniqueResult();
	}

	public User getUserById(long id) {
		return (User) getSession().load(User.class, id);
	}

}
