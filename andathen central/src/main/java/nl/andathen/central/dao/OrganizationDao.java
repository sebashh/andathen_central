package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Organization;

@Stateless
public class OrganizationDao extends AbstractDao<Organization> {
	public Organization get(String name) {
        TypedQuery<Organization> query = entityManager.createQuery("FROM Organization WHERE :name = name", Organization.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public Organization get(Long id) {
        TypedQuery<Organization> query = entityManager.createQuery("FROM Organization WHERE :id = id", Organization.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<Organization> get() {
        TypedQuery<Organization> query = entityManager.createQuery("FROM Organization", Organization.class);
        return new TreeSet<>(query.getResultList());
    }
    
	public SortedSet<Organization> getOnAccessLevel(AccessLevel lvl) {
        TypedQuery<Organization> query = entityManager.createQuery("FROM Organization where :access_level = access_level", Organization.class);
        query.setParameter("access_level", lvl.toString());
        return new TreeSet<>(query.getResultList());
    }
	
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Organization", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Organization> get(int start, int number) {
        TypedQuery<Organization> query = entityManager.createQuery("FROM Organization", Organization.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
