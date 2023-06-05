package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import nl.andathen.central.dao.BodyImageDao;
import nl.andathen.central.dao.PlanetDao;
import nl.andathen.central.domain.galaxy.BodyImage;
import nl.andathen.central.domain.galaxy.planet.Planet;


@Named
@SessionScoped
public class PlanetBean extends AbstractBackingBean implements IImageUploader {
	private static final long serialVersionUID = -5236525622645781211L; 
	@EJB
	private PlanetDao planetDao;
	@EJB
	private BodyImageDao imageDao;
 	private Planet planet;
	private Part uploadedFile;
	
	public PlanetBean() {

	}
 	
	@PostConstruct
	public void init()
	{
		super.init();
		this.planet = new Planet();
		load();		
	}
	
	@Override
	protected void load() {

	}
	
	public String cancel() {
		init();
		return "manage-stars?faces-redirect=true";
	}
	
	@Transactional
	public void upload() {
		Paths.get(uploadedFile.getSubmittedFileName()).getFileName().toString();
	    try (InputStream input = uploadedFile.getInputStream()) {
	        byte[] fileContents = input.readAllBytes();
	        if (fileContents.length <= 10485760) {
	        	BufferedImage img = ImageIO.read(new ByteArrayInputStream(fileContents));
	        	BodyImage bi = new BodyImage(img);
			    planet.setBodyImage(bi);
	        	imageDao.create(bi);
	        	planetDao.merge(planet);
	        }
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
    	// Weird, creating two records. For now just remove the wrong one.
    	BodyImage wrong = imageDao.get(planet.getBodyImage().getId()-1);
    	if (wrong != null) {
    		imageDao.remove(wrong);
    	}
	}
	
	public Planet getPlanet() {
		return this.planet;
	}
	
	public String goToMainPage() {
	    return "index?faces-redirect=true";
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String planetDetails(Planet p) throws IOException {
		this.planet = planetDao.get(p.getDesignation());
		return "planet-details";
	}
	
	@Transactional
	public String submit() {
		planetDao.merge(planet);
		this.planet = new Planet();
		return "manage-stars?faces-redirect=true";
	}

	@Override
	public void setImage(BufferedImage img) {
		planet.setImage(img);
	}
}
