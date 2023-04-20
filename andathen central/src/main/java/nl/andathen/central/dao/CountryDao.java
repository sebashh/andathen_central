package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.person.Country;

@Stateless
public class CountryDao extends AbstractDao<Country> {
	public Country get(String name) {
        TypedQuery<Country> query = entityManager.createQuery("FROM Country WHERE :name = name", Country.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e1) {
        	query = entityManager.createQuery("FROM Country WHERE :alternate_name = alternate_name", Country.class);
            query.setParameter("alternate_name", name);
            try {
            	return query.getSingleResult();
            }
            catch (NoResultException e2) {
            	return null;
            }
        }
	}
	
	public Country get(Long id) {
        TypedQuery<Country> query = entityManager.createQuery("FROM Country WHERE :id = id", Country.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<Country> get() {
        TypedQuery<Country> query = entityManager.createQuery("FROM Country", Country.class);
        return new TreeSet<>(query.getResultList());
    }
    
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Country", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Country> get(int start, int number) {
        TypedQuery<Country> query = entityManager.createQuery("FROM Country", Country.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
