package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Species;

@Stateless
public class SpeciesDao extends AbstractDao<Species> {
    @Override
	public Species get(String name) {
        TypedQuery<Species> query = entityManager.createQuery("FROM Species WHERE :name = name", Species.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
    @Override
	public Species get(Long id) {
        TypedQuery<Species> query = entityManager.createQuery("FROM Species WHERE :id = id", Species.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Species", Long.class);
    	return cnt.getSingleResult();
    }
	
	/**
	 * Overload version to get all the Species of a specific {@link AccessLevel}.
	 * @param lvl The specified {@link AccessLevel}
	 * @return Sortedset containing Species of specified AccessLevel
	 * @author Dani Bakker
	 */
	public SortedSet<Species> getOnAccessLevel(AccessLevel lvl) {
        TypedQuery<Species> query = entityManager.createQuery("FROM Species where :access_level = access_level", Species.class);
        query.setParameter("access_level", lvl.toString());
        return new TreeSet<>(query.getResultList());
    }
	
	/**
	 * Overload version to get all the Species till and including a specified {@link AccessLevel}.
	 * @param lvl The specified {@link AccessLevel}
	 * @return Sortedset containing Species of specified AccessLevel or lower.
	 * @author Dani Bakker
	 */
	public SortedSet<Species> getTillAccessLevel(AccessLevel lvl) {
        TypedQuery<Species> query = entityManager.createQuery("FROM Species where :access_level <= access_level", Species.class);
        query.setParameter("access_level", lvl.toString());
        return new TreeSet<>(query.getResultList());
    }

    @Override
    public SortedSet<Species> get() {
        TypedQuery<Species> query = entityManager.createQuery("FROM Species ORDER BY access_level ASC, technical_level ASC, name ASC", Species.class);
        return new TreeSet<>(query.getResultList());
    }
    
    @Override
    public SortedSet<Species> get(int start, int number) {
        TypedQuery<Species> query = entityManager.createQuery("FROM Species ORDER BY access_level ASC, technical_level ASC, name ASC", Species.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
