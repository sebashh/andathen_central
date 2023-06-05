package nl.andathen.central.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class MainAdminBean implements Serializable {
	private static final long serialVersionUID = 8394556065580380821L;

	@PostConstruct
	public void init() {

	}
	
	public String manageCultures() { 
		return "manage-cultures?faces-redirect=true";
	}
 	
	public String manageResources() {
		return "manage-resources?faces-redirect=true";
	}
	
	public String manageSkills() {
		return "manage-skills?faces-redirect=true";
	}
	
	public String manageLanguages() {
		return "manage-languages?faces-redirect=true";
	}
	
	public String manageSpecies() {
		return "manage-species?faces-redirect=true";
	}
	
	public String manageOrganizations() {
		return "manage-organizations?faces-redirect=true";
	}
	
	public String manageSources() {
		return "manage-sources?faces-redirect=true";
	}
	
	public String managePersonalNames() {
		return "manage-personal-names?faces-redirect=true";
	}
	
	public String manageZipcodes() {
		return "manage-zipcodes?faces-redirect=true";
	}
	
	public String manageStars() {
		return "manage-stars?faces-redirect=true";
	}
	
	public String manageSpaceships() {
		return "manage-spaceships?faces-redirect=true";
	}
	
	public String manageSpaceshipClasses() {
		return "manage-spaceship-classes?faces-redirect=true";
	}
}
