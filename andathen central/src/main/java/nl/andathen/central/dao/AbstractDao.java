package nl.andathen.central.dao;

import java.util.SortedSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.andathen.central.util.image.IImageProvider;

@Stateless
public abstract class AbstractDao<T> {
	
	@PersistenceContext(unitName="andathen")
    protected EntityManager entityManager;

	public abstract Long getCount();
	public abstract T get(Long id);
    public abstract SortedSet<T> get();
    public abstract SortedSet<T> get(int start, int number);
	
    public void create(T r) {
    	entityManager.persist(r);
    }

	public void remove(T r) {
		entityManager.remove(r);
	}
	
	public T merge(T updated) {
		System.out.println(updated);
		return entityManager.merge(updated);
	}
	
	public IImageProvider getImageProvider(Long id) {
		return (IImageProvider) this.get(id);
	}
	
	public T get(String name) {
		return null;
	};
}
