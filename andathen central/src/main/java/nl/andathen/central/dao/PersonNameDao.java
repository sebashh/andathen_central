package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import nl.andathen.central.domain.PersonalName;

@Stateless
public class PersonNameDao extends AbstractDao<PersonalName> {
	public PersonalName get(String name) {
        TypedQuery<PersonalName> query = entityManager.createQuery("FROM PersonalName WHERE :name = lastname", PersonalName.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public PersonalName get(Long id) {
        TypedQuery<PersonalName> query = entityManager.createQuery("FROM PersonalName WHERE :id = id", PersonalName.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<PersonalName> get() {
        TypedQuery<PersonalName> query = entityManager.createQuery("FROM PersonalName", PersonalName.class);
        return new TreeSet<>(query.getResultList());
    }
    
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM PersonalName", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<PersonalName> get(int start, int number) {
        TypedQuery<PersonalName> query = entityManager.createQuery("FROM PersonalName", PersonalName.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
