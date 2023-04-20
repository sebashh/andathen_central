package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.galaxy.planet.KnownPlanet;
import nl.andathen.central.domain.galaxy.planet.Planet;

@Stateless
public class KnownPlanetDao extends AbstractDao<KnownPlanet> {
    @Override
	public KnownPlanet get(String designation) {
        TypedQuery<KnownPlanet> query = entityManager.createQuery("FROM Known-Planet WHERE :designation = designation", KnownPlanet.class);
        query.setParameter("designation", designation);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException | IndexOutOfBoundsException e) {
        	return null;
        }
	}
	
    @Override
	public KnownPlanet get(Long id) {
        return null;
	}

    @Override
    public SortedSet<KnownPlanet> get() {
        TypedQuery<KnownPlanet> query = entityManager.createQuery("FROM Known-Planet ORDER BY planet_index ASC", KnownPlanet.class);
        return new TreeSet<>(query.getResultList());
    }
    
    @Override
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM Known-Planet", Long.class);
    	return cnt.getSingleResult();
    }
    
    public Long getCount(AccessLevel accessLevel) {
    	System.out.println(accessLevel);
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM Known-Planet WHERE :access_level = access_level", Long.class);
        cnt.setParameter("access_level", accessLevel.name());
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<KnownPlanet> get(int planet, int number) {
        TypedQuery<KnownPlanet> query = entityManager.createQuery("Known-Planet k ON p.designation = k.designation ORDER BY p.planet_index ASC", KnownPlanet.class);
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
