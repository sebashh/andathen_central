package nl.andathen.central.dao;

import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import nl.andathen.central.domain.character.Skill;

@Stateless
public class SkillDao extends AbstractDao<Skill> {
	public Skill get(String name) {
        TypedQuery<Skill> query = entityManager.createQuery("FROM Skill WHERE :name = name", Skill.class);
        query.setParameter("name", name);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public Skill get(Long id) {
        TypedQuery<Skill> query = entityManager.createQuery("FROM Skill WHERE :id = id", Skill.class);
        query.setParameter("id", id);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public SortedSet<Skill> get() {
        TypedQuery<Skill> query = entityManager.createQuery("FROM Skill", Skill.class);
        return new TreeSet<>(query.getResultList());
    }
    
    public Long getCount() {
    	TypedQuery<Long> cnt = entityManager.createQuery("SELECT COUNT(id) FROM Skill", Long.class);
    	return cnt.getSingleResult();
    }
    
    @Override
    public SortedSet<Skill> get(int start, int number) {
        TypedQuery<Skill> query = entityManager.createQuery("FROM Skill", Skill.class);
        query.setFirstResult(start);
        query.setMaxResults(number);
        return new TreeSet<>(query.getResultList());
    }
}
