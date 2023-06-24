package nl.andathen.central.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
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
			SearchSession searchSession = Search.session(entityManager);
			try {
				searchSession.massIndexer().startAndWait();
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
