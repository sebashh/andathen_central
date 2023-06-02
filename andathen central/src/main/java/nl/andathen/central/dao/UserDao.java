package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.person.User;

@Stateless
public class UserDao extends AbstractDao<User> {
	public User get(String username) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE :username = username", User.class);
        query.setParameter("username", username);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public User get(Long id) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE :id = id", User.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<User> get() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return new TreeSet<>(query.getResultList());
    }
    
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM User", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<User> get(int start, int number) {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
