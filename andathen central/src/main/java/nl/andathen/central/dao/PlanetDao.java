package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.galaxy.planet.Planet;

@Stateless
public class PlanetDao extends AbstractDao<Planet> {
    @Override
	public Planet get(String designation) {
        TypedQuery<Planet> query = entityManager.createQuery("FROM Planet WHERE :designation = designation", Planet.class);
        query.setParameter("designation", designation);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException | IndexOutOfBoundsException e) {
        	return null;
        }
	}
	
    @Override
	public Planet get(Long id) {
        return null;
	}

    @Override
    public SortedSet<Planet> get() {
        TypedQuery<Planet> query = entityManager.createQuery("FROM Planet ORDER BY planet_index ASC", Planet.class);
        return new TreeSet<>(query.getResultList());
    }
    
    @Override
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM Planet", Long.class);
    	return cnt.getSingleResult();
    }
    
    public Long getCount(AccessLevel accessLevel) {
    	System.out.println(accessLevel);
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM Planet WHERE :access_level = access_level", Long.class);
        cnt.setParameter("access_level", accessLevel.name());
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Planet> get(int planet, int number) {
        TypedQuery<Planet> query = entityManager.createQuery("FROM Planet ORDER BY planet_index ASC", Planet.class);
        query.setFirstResult(planet);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
    
    public SortedSet<Planet> get(int planet, int number, AccessLevel accessLevel) {
        TypedQuery<Planet> query = entityManager.createQuery("FROM Planet WHERE :access_level = access_level ORDER BY planet_index ASC", Planet.class);
        query.setParameter("access_level", accessLevel.name());
        query.setFirstResult(planet);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
