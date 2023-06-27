package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.council.SpaceshipClass;

@Stateless
public class SpaceshipClassDao extends AbstractDao<SpaceshipClass> {
    @Override
	public SpaceshipClass get(String name) {
        TypedQuery<SpaceshipClass> query = entityManager.createQuery("FROM SpaceshipClass WHERE :name = name", SpaceshipClass.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException | IndexOutOfBoundsException e) {
        	return null;
        }
	}
	
    @Override
	public SpaceshipClass get(Long id) {
        TypedQuery<SpaceshipClass> query = entityManager.createQuery("FROM SpaceshipClass WHERE :id = id", SpaceshipClass.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException | IndexOutOfBoundsException e) {
        	return null;
        }
	}

    @Override
    public SortedSet<SpaceshipClass> get() {
        TypedQuery<SpaceshipClass> query = entityManager.createQuery("FROM SpaceshipClass ORDER BY access_level ASC", SpaceshipClass.class);
        return new TreeSet<>(query.getResultList());
    }
    
    @Override
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(name) FROM SpaceshipClass", Long.class);
    	return cnt.getSingleResult();
    }
    
    public Long getCount(AccessLevel accessLevel) {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM SpaceshipClass WHERE :access_level = access_level", Long.class);
        cnt.setParameter("access_level", accessLevel.name());
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<SpaceshipClass> get(int start, int number) {
        TypedQuery<SpaceshipClass> query = entityManager.createQuery("FROM SpaceshipClass ORDER BY access_level ASC", SpaceshipClass.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
