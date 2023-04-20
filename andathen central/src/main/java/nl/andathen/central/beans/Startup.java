package nl.andathen.central.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.servlet.ServletContext;

import org.hibernate.type.descriptor.java.JavaTypeDescriptorRegistry;

import nl.andathen.central.util.BufferedImageTypeDescriptor;

@ApplicationScoped
public class Startup {
	public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) ServletContext context) {
		System.out.println("Context initialized");
	}
	
    public void initOnStartup(@Observes @Initialized(ApplicationScoped.class) Object obj) {
		JavaTypeDescriptorRegistry.INSTANCE.addDescriptor(new BufferedImageTypeDescriptor());
		System.out.println("Application Scoped Bean initialized");
    }
}

