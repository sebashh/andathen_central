package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Source;

@Stateless
public class SourceDao extends AbstractDao<Source> {
	public Source get(String name) {
        TypedQuery<Source> query = entityManager.createQuery("FROM Source WHERE :name = name", Source.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public Source get(Long id) {
        TypedQuery<Source> query = entityManager.createQuery("FROM Source WHERE :id = id", Source.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Source", Long.class);
    	return cnt.getSingleResult();
    }

    public SortedSet<Source> get() {
        TypedQuery<Source> query = entityManager.createQuery("FROM Source", Source.class);
        return new TreeSet<>(query.getResultList());
    }
    
	public SortedSet<Source> getOnAccessLevel(AccessLevel lvl) {
        TypedQuery<Source> query = entityManager.createQuery("FROM Source where :access_level = access_level", Source.class);
        query.setParameter("access_level", lvl.toString());
        return new TreeSet<>(query.getResultList());
    }
	
    @Override
    public SortedSet<Source> get(int start, int number) {
        TypedQuery<Source> query = entityManager.createQuery("FROM Source", Source.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
