package nl.andathen.central.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.type.descriptor.java.JavaTypeDescriptorRegistry;

import nl.andathen.central.util.image.BufferedImageConverter;
import nl.andathen.central.util.image.BufferedImageTypeDescriptor;

@ApplicationScoped
public class ApplicationScopedBean {
	public static final String DEFAULT_SERVICE_URL = "http://localhost:8080/ciadan-central/services/wsdl/";
	public static final String REVERSED_SERVICE_URL = ".wsdl.services.central.andathen.nl/";
	private static final boolean INIT = false;
	
	@PersistenceContext(unitName="andathen")
    protected EntityManager entityManager;
	
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
	
    public void initOnStartup(@Observes @Initialized(ApplicationScoped.class) Object obj) {
		JavaTypeDescriptorRegistry.INSTANCE.addDescriptor(new BufferedImageTypeDescriptor());
		System.out.println("Application Scoped Bean initialized");
    }
}
