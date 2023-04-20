package nl.andathen.central.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import nl.andathen.central.util.BufferedImageConverter;

@ApplicationScoped
public class ApplicationScopedBean {
	@PersistenceContext(unitName="andathen")
    protected EntityManager entityManager;
	
	private static final boolean INIT = false;
	
	public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) 
									ServletContext context) {
		if (INIT) {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			try {
				fullTextEntityManager.createIndexer().startAndWait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public BufferedImageConverter getImageConverter() {
		return new BufferedImageConverter(); // Check memory effects of this!
	}
}
