package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import nl.andathen.central.domain.person.Zipcode;

@Stateless
public class ZipcodeDao extends AbstractDao<Zipcode> {
    @Override
	public Zipcode get(String code) {
        TypedQuery<Zipcode> query = entityManager.createQuery("FROM Zipcode WHERE :code = code", Zipcode.class);
        query.setParameter("code", code);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
    @Override
	public Zipcode get(Long id) {
        TypedQuery<Zipcode> query = entityManager.createQuery("FROM Zipcode WHERE :id = id", Zipcode.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    @Override
    public SortedSet<Zipcode> get() {
        TypedQuery<Zipcode> query = entityManager.createQuery("FROM Zipcode ORDER BY country_id ASC, code ASC", Zipcode.class);
        return new TreeSet<>(query.getResultList());
    }
    
    @Override
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Zipcode", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Zipcode> get(int start, int number) {
        TypedQuery<Zipcode> query = entityManager.createQuery("FROM Zipcode ORDER BY country_id ASC, code ASC", Zipcode.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
