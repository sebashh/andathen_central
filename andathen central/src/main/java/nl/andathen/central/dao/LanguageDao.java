package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.andathen.central.domain.Language;

@Stateless
public class LanguageDao extends AbstractDao<Language> {
	public Language get(String iso) {
        TypedQuery<Language> query = entityManager.createQuery("FROM Language WHERE :iso = iso", Language.class);
        query.setParameter("iso", iso);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public Language getFuzzy(String name) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Language> q = cb.createQuery(Language.class);
		Root<Language> c = q.from(Language.class);
		q.select(c);
		Predicate predicate=cb.like(c.<String>get("name"), name + "%");
		q.where(predicate);
		TypedQuery<Language> query = entityManager.createQuery(q);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	
	public Language get(Long id) {
        TypedQuery<Language> query = entityManager.createQuery("FROM Language WHERE :id = id", Language.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<Language> get() {
        TypedQuery<Language> query = entityManager.createQuery("FROM Language", Language.class);
        return new TreeSet<>(query.getResultList());
    }
    
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Language", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Language> get(int start, int number) {
        TypedQuery<Language> query = entityManager.createQuery("FROM Language", Language.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
