package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.council.Spaceship;

@Stateless
public class SpaceshipDao extends AbstractDao<Spaceship> {
    @Override
	public Spaceship get(String designation) {
        TypedQuery<Spaceship> query = entityManager.createQuery("FROM Spaceship WHERE :designation = designation", Spaceship.class);
        query.setParameter("designation", designation);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException | IndexOutOfBoundsException e) {
        	return null;
        }
	}
	
    @Override
	public Spaceship get(Long id) {
        TypedQuery<Spaceship> query = entityManager.createQuery("FROM Spaceship WHERE :id = id", Spaceship.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException | IndexOutOfBoundsException e) {
        	return null;
        }
	}

    @Override
    public SortedSet<Spaceship> get() {
        TypedQuery<Spaceship> query = entityManager.createQuery("FROM Spaceship ORDER BY access_level ASC", Spaceship.class);
        return new TreeSet<>(query.getResultList());
    }
    
    @Override
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM Spaceship", Long.class);
    	return cnt.getSingleResult();
    }
    
    public Long getCount(AccessLevel accessLevel) {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM Spaceship WHERE :access_level = access_level", Long.class);
        cnt.setParameter("access_level", accessLevel.name());
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Spaceship> get(int start, int number) {
        TypedQuery<Spaceship> query = entityManager.createQuery("FROM Spaceship ORDER BY access_level ASC", Spaceship.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
