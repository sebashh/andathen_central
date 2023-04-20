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

import nl.andathen.central.dao.ResourceDao;
import nl.andathen.central.domain.resources.Resource;

@Named
@SessionScoped
public class ResourceBean implements Serializable {
	private static final long serialVersionUID = 8387235314477039936L;
	@EJB
	private ResourceDao resourceDao;
	private SortedSet<Resource> resources;
	private HashMap<Long, Resource> resourceMap;
	private Resource resource;
	private Part uploadedFile;
	
	@PostConstruct
	public void init() {
		resources = new TreeSet<>();
		resourceMap = new HashMap<>();
		resources.addAll(resourceDao.get());
		for (Resource r: resources) {
			resourceMap.put(r.getId(), r);
		}
		this.resource = new Resource();
	}
	
	public SortedSet<Resource> getResources() {
		return resources;
	}
	
	public void upload() {
		Paths.get(uploadedFile.getSubmittedFileName()).getFileName().toString();
	    try (InputStream input = uploadedFile.getInputStream()) {
	        byte[] fileContents = input.readAllBytes();
	        if (fileContents.length <= 10485760) {
	        	BufferedImage img = ImageIO.read(new ByteArrayInputStream(fileContents));
			    resource.setImage(img);
	        }
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@Transactional
	public String submit() {
		Resource del = resourceMap.get(resource.getId());
		resources.remove(del);
		resourceMap.remove(del.getId());
		Resource sk = resourceDao.merge(resource);
		resources.add(sk);
		resourceMap.put(sk.getId(), sk);
		this.resource = new Resource();
		return "manage-resources?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		resourceDao.create(resource);
		resource = resourceDao.get(resource.getId());
		resources.add(resource);
		resourceMap.put(resource.getId(), resource);
		this.resource = new Resource();
		return "manage-resources?faces-redirect=true";
	}
	
	public String create() {
	    return "add-resource";
	}
	
	public String cancel() {
		return "manage-resources?faces-redirect=true";
	}
	
	public String edit(Resource r) throws IOException {
		this.resource.setId(r.getId());
		this.resource.setName(r.getName());
		this.resource.setDescription(r.getDescription());
		this.resource.setPlayerNotes(r.getPlayerNotes());
		this.resource.setAccessLevel(r.getAccessLevel());
		this.resource.setBasePrice(r.getBasePrice());
		this.resource.setScarcity(r.getScarcity());
		this.resource.setImage(r.getImage());
		return "resource-details";
	}
	
	@Transactional
	public void delete(Resource r) {
		Resource res = resourceDao.get(r.getName());
		if (res != null) {
			resourceDao.remove(res);
			init();
		}
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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
