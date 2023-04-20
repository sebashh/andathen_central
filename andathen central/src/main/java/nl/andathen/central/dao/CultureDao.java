package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Culture;

/**
 * The Culture Dao responsible for getting information from the database. <br>
 * Extends {@link MySQLDao} for the create, remove and merge functionality.
 * @author Dani Bakker
 */
@Stateless
public class CultureDao extends AbstractDao<Culture> {
	/**
	 * Overload version to get a {@link Culture} using it's name.
	 * @param name The name of the Culture in question.
	 * @return Single {@link Culture} matching on name or NULL.
	 * @author Dani Bakker
	 */
	public Culture get(String name) {
		TypedQuery<Culture> query = entityManager.createQuery("FROM Culture WHERE :name = name", Culture.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	/**
	 * Overload version to get a Culture using it's identifier.
	 * @param id The identifier of the {@link Culture}.
	 * @return Single {@link Culture} matching it's ID or NULL.
	 * @author Dani Bakker
	 */
	public Culture get(Long id) {
		TypedQuery<Culture> query = entityManager.createQuery("FROM Culture WHERE :culture_id = culture_id", Culture.class);
		query.setParameter("culture_id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	/**
	 * Overload version to get all the cultures of a specific {@link AccessLevel}.
	 * @param lvl The specified {@link AccessLevel}
	 * @return {@link SortedSet} containing {@link Culture}s of specified AccessLevel
	 * @author Dani Bakker
	 */
	public SortedSet<Culture> getOnAccessLevel(AccessLevel lvl) {
        TypedQuery<Culture> query = entityManager.createQuery("FROM Culture where :access_level = access_level", Culture.class);
        query.setParameter("access_level", lvl.toString());
        return new TreeSet<>(query.getResultList());
    }
	
	/**
	 * Overload version to get all the cultures till and including a specified {@link AccessLevel}.
	 * @param lvl The specified {@link AccessLevel}
	 * @return {@link SortedSet} containing {@link Culture}s of specified AccessLevel or lower.
	 * @author Dani Bakker
	 */
	public SortedSet<Culture> getTillAccessLevel(AccessLevel lvl) {
        TypedQuery<Culture> query = entityManager.createQuery("FROM Culture where :access_level <= access_level", Culture.class);
        query.setParameter("access_level", lvl.toString());
        return new TreeSet<>(query.getResultList());
    }
	
	/**
	 * Elastic Search get function for retrieving cultures where the searchTerm contain parts of a {@link Culture}s name or description.
	 * @param searchTerm The {@link String} to search with.
	 * @return {@link SortedSet} containing {@link Culture}s matching (partly) the searchTerm.
	 * @author Dani Bakker
	 */ /*
	public SortedSet<Culture> getTextSearch(String searchTerm){
		SearchResult<Culture> result = Search.session(entityManager)
				.search(Culture.class)
				.where(x -> x.match()
						.fields("name","description")
						.matching(searchTerm))
				.fetchAll();
		return new TreeSet<>(result.hits());
	}
	*/
	
	/**
	 * Overload version to get all Cultures
	 * @return {@link SortedSet} containing all {@link Culture}s.
	 * @author Dani Bakker
	 */
	public SortedSet<Culture> get() {
		TypedQuery<Culture> query = entityManager.createQuery("FROM Culture", Culture.class);
		return new TreeSet<>(query.getResultList());
	}
	
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Culture", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Culture> get(int start, int number) {
        TypedQuery<Culture> query = entityManager.createQuery("FROM Culture", Culture.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
