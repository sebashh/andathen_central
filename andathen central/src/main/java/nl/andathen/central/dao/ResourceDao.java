package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.resources.Resource;

@Stateless
public class ResourceDao extends AbstractDao<Resource> {
	public Resource get(String name) {
        TypedQuery<Resource> query = entityManager.createQuery("FROM Resource WHERE :name = name", Resource.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public Resource get(Long id) {
        TypedQuery<Resource> query = entityManager.createQuery("FROM Resource WHERE :id = id", Resource.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<Resource> get() {
        TypedQuery<Resource> query = entityManager.createQuery("FROM Resource", Resource.class);
        return new TreeSet<>(query.getResultList());
    }
    
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Resource", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Resource> get(int start, int number) {
        TypedQuery<Resource> query = entityManager.createQuery("FROM Resource", Resource.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
