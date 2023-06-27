package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import nl.andathen.central.domain.galaxy.BodyImage;

@Stateless
public class BodyImageDao extends AbstractDao<BodyImage> {
	public BodyImage get(Long id) {
        TypedQuery<BodyImage> query = entityManager.createQuery("FROM BodyImage WHERE :id = id", BodyImage.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<BodyImage> get() {
        TypedQuery<BodyImage> query = entityManager.createQuery("FROM BodyImage", BodyImage.class);
        return new TreeSet<>(query.getResultList());
    }
    
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM BodyImage", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<BodyImage> get(int start, int number) {
        TypedQuery<BodyImage> query = entityManager.createQuery("FROM BodyImage", BodyImage.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
