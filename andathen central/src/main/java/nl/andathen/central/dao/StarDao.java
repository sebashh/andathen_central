package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.galaxy.Star;

@Stateless
public class StarDao extends AbstractDao<Star> {
    @Override
	public Star get(String designation) {
        TypedQuery<Star> query = entityManager.createQuery("FROM Star WHERE :designation = designation", Star.class);
        query.setParameter("designation", designation);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException | IndexOutOfBoundsException e) {
        	return null;
        }
	}
	
    @Override
	public Star get(Long id) {
        return null;
	}

    @Override
    public SortedSet<Star> get() {
        TypedQuery<Star> query = entityManager.createQuery("FROM Star ORDER BY access_level ASC, distance_to_ciadan ASC", Star.class);
        return new TreeSet<>(query.getResultList());
    }
    
    @Override
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM Star", Long.class);
    	return cnt.getSingleResult();
    }
    
    public Long getCount(AccessLevel accessLevel) {
    	System.out.println(accessLevel);
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(designation) FROM Star WHERE :access_level = access_level", Long.class);
        cnt.setParameter("access_level", accessLevel.name());
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Star> get(int start, int number) {
        TypedQuery<Star> query = entityManager.createQuery("FROM Star ORDER BY access_level ASC, distance_to_ciadan ASC", Star.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
    
    public SortedSet<Star> get(int start, int number, AccessLevel accessLevel) {
        TypedQuery<Star> query = entityManager.createQuery("FROM Star WHERE :access_level = access_level ORDER BY distance_to_ciadan ASC", Star.class);
        query.setParameter("access_level", accessLevel.name());
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
    
    @SuppressWarnings("unchecked")
	public SortedSet<Star> get(int start, int number, AccessLevel accessLevel, boolean hasHabitablePlanets, boolean hasMarginalPlanets) {
    	if (hasHabitablePlanets && !hasMarginalPlanets) {
    		String sql = "SELECT b.*, s.* from Body b inner join star s on b.designation = s.designation inner join planet p on p.star = s.designation inner join planet_type pt on p.type = pt.type inner join habitability h on pt.habitability = h.level WHERE h.level = :level1 AND b.access_level = :access_level";
    		Query query = entityManager.createNativeQuery(sql, Star.class);
            query.setParameter("level1", "YES");
            query.setParameter("access_level", accessLevel.name());
            query.setFirstResult(start);
            query.setMaxResults(number);
            return new TreeSet<Star>(query.getResultList());
    	}
    	if (!hasHabitablePlanets && hasMarginalPlanets) {
    		String sql = "SELECT b.*, s.* from Body b inner join star s on b.designation = s.designation inner join planet p on p.star = s.designation inner join planet_type pt on p.type = pt.type inner join habitability h on pt.habitability = h.level WHERE h.level = :level2 AND b.access_level = :access_level";
    		Query query = entityManager.createNativeQuery(sql, Star.class);
            query.setParameter("level2", "MARGINALLY");
            query.setParameter("access_level", accessLevel.name());
            query.setFirstResult(start);
            query.setMaxResults(number);
            return new TreeSet<Star>(query.getResultList());
    	}
    	if (hasHabitablePlanets && hasMarginalPlanets) {
    		String sql = "SELECT b.*, s.* from Body b inner join star s on b.designation = s.designation inner join planet p on p.star = s.designation inner join planet_type pt on p.type = pt.type inner join habitability h on pt.habitability = h.level WHERE h.level = :level1 AND b.access_level = :access_level UNION SELECT b.*, s.* from Body b inner join star s on b.designation = s.designation inner join planet p on p.star = s.designation inner join planet_type pt on p.type = pt.type inner join habitability h on pt.habitability = h.level WHERE h.level = :level2 AND b.access_level = :access_level";
    		//String sql = "SELECT b.*, s.* from Body b inner join star s on b.designation = s.designation inner join planet p on p.star = s.designation inner join planet_type pt on p.type = pt.type inner join habitability h on pt.habitability = h.level WHERE (h.level = :level3 OR h.level = :level4) AND b.access_level = :access_level";
    		Query query = entityManager.createNativeQuery(sql, Star.class);
            query.setParameter("level1", "YES");
            query.setParameter("level2", "MARGINALLY");
            query.setParameter("access_level", accessLevel.name());
            query.setFirstResult(start);
            query.setMaxResults(number);
            return new TreeSet<Star>(query.getResultList());
    	}
   		TypedQuery<Star> query = entityManager.createQuery("FROM Star WHERE :access_level = access_level ORDER BY distance_to_ciadan ASC",Star.class);
           query.setParameter("access_level", accessLevel.name());
           query.setFirstResult(start);
           query.setMaxResults(number);
           return new TreeSet<>(query.getResultList());
    }
}
