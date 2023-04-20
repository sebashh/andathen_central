package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import nl.andathen.central.dao.OrganizationDao;
import nl.andathen.central.domain.Organization;

@Named
@SessionScoped
public class OrganizationBean implements Serializable {
	private static final long serialVersionUID = 8387235314477039936L;
	@EJB
	private OrganizationDao organizationDao;
	private SortedSet<Organization> organizations;
	private HashMap<Long, Organization> organizationMap;
	private Organization organization;
	private Part uploadedFile;
	
	@PostConstruct
	public void init() {
		organizations = new TreeSet<>();
		organizationMap = new HashMap<>();
		organizations.addAll(organizationDao.get());
		for (Organization r: organizations) {
			organizationMap.put(r.getId(), r);
		}
		this.organization = new Organization();
	}
	
	public SortedSet<Organization> getOrganizations() {
		return organizations;
	}
	
	public void upload() {
		Paths.get(uploadedFile.getSubmittedFileName()).getFileName().toString();
	    try (InputStream input = uploadedFile.getInputStream()) {
	        byte[] fileContents = input.readAllBytes();
	        if (fileContents.length <= 10485760) {
	        	BufferedImage img = ImageIO.read(new ByteArrayInputStream(fileContents));
			    organization.setImage(img);
	        }
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@Transactional
	public String submit() {
		Organization del = organizationMap.get(organization.getId());
		organizations.remove(del);
		organizationMap.remove(del.getId());
		Organization sk = organizationDao.merge(organization);
		organizations.add(sk);
		organizationMap.put(sk.getId(), sk);
		this.organization = new Organization();
		return "manage-organizations?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		organization.setRegistrationNumber(Math.abs((long) organization.getName().hashCode() * (long) organization.getDescription().hashCode()) / 2l); // Change to a more meaningful number later
		organizationDao.create(organization);
		organization = organizationDao.get(organization.getId());
		organizations.add(organization);
		organizationMap.put(organization.getId(), organization);
		this.organization = new Organization();
		return "manage-organizations?faces-redirect=true";
	}
	
	public String create() {
	    return "add-organizaton";
	}
	
	public String cancel() {
		return "manage-organizations?faces-redirect=true";
	}
	
	public String edit(Organization r) throws IOException {
		this.organization.setId(r.getId());
		this.organization.setName(r.getName());
		this.organization.setRegistrationNumber(r.getRegistrationNumber());
		this.organization.setDescription(r.getDescription());
		this.organization.setAccessLevel(r.getAccessLevel());
		this.organization.setImage(r.getImage());
		return "organization-details";
	}
	
	@Transactional
	public void delete(Organization r) {
		Organization res = organizationDao.get(r.getId());
		if (res != null) {
			organizationDao.remove(res);
			init();
		}
	}
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String goToMainPage() {
	    return "index?faces-redirect=true";
	}
}
