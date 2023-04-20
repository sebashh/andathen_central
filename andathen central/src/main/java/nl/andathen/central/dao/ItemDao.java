package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import nl.andathen.central.domain.resources.Item;

@Stateless
public class ItemDao extends AbstractDao<Item> {
	public Item get(String name) {
        TypedQuery<Item> query = entityManager.createQuery("FROM Item WHERE :name = name", Item.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public Item get(Long id) {
        TypedQuery<Item> query = entityManager.createQuery("FROM Item WHERE :id = id", Item.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<Item> get() {
        TypedQuery<Item> query = entityManager.createQuery("FROM Item", Item.class);
        return new TreeSet<>(query.getResultList());
    }
    
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Item", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Item> get(int start, int number) {
        TypedQuery<Item> query = entityManager.createQuery("FROM Item", Item.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
