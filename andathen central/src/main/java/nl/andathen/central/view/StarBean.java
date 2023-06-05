package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import nl.andathen.central.dao.BodyImageDao;
import nl.andathen.central.dao.PlanetDao;
import nl.andathen.central.dao.StarDao;
import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.galaxy.BodyImage;
import nl.andathen.central.domain.galaxy.Star;
import nl.andathen.central.domain.galaxy.StarDistanceComparator;


@Named
@SessionScoped
public class StarBean extends AbstractBackingBean implements IImageUploader {
	private static final long serialVersionUID = -5236525622645781211L; 
	@EJB
	private StarDao starDao;
	@EJB
	private PlanetDao planetDao;
	@EJB
	private BodyImageDao imageDao;
	private List<Star> stars;
 	private Star star;
 	private AccessLevel filterAccessLevelStars = AccessLevel.L0;
 	private boolean filterHabitableOnly;
 	private boolean filterMarginalPlanets;
	private Part uploadedFile;
	public StarBean() {

	}
 	
	@PostConstruct
	public void init()
	{
		super.init();
		this.star = new Star();
		stars = new ArrayList<>();
	 	filterHabitableOnly = false;
	 	filterMarginalPlanets = false;
		load();		
	}
	
	@Override
	protected void load() {
		// Load the set of stars from the database
		stars.clear();
		totalRows = starDao.getCount(filterAccessLevelStars).longValue();
		calculatePageSettings();
		stars.addAll(starDao.get((int) getFirstRow(),(int) getRowsPerPage(), filterAccessLevelStars, filterHabitableOnly, filterMarginalPlanets));
		stars.sort(new StarDistanceComparator());
		calculatePageSettings();
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
			    star.setBodyImage(bi);
	        	imageDao.create(bi);
	        	starDao.merge(star);
	        }
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
    	// Weird, creating two records. For now just remove the wrong one.
    	BodyImage wrong = imageDao.get(star.getBodyImage().getId()-1);
    	if (wrong != null) {
    		imageDao.remove(wrong);
    	}
	}
	
	public Star getStar() {
		return this.star;
	}
	
	public List<Star> getStars(){
		return this.stars;
	}
	
	public String goToMainPage() {
	    return "index?faces-redirect=true";
	}
	
	public void setStars(List<Star> stars) {
		this.stars = stars;
	}

	public void setStar(Star star) {
		this.star = star;
	}
	
	public AccessLevel getFilterAccessLevelStars() {
		return filterAccessLevelStars;
	}

	public void setFilterAccessLevelStars(AccessLevel filterAccessLevelStars) {
		this.filterAccessLevelStars = filterAccessLevelStars;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String starDetails(Star s) throws IOException {
		this.star = starDao.get(s.getDesignation());
		return "star-details";
	}

	public boolean isFilterHabitableOnly() {
		return filterHabitableOnly;
	}

	public void setFilterHabitableOnly(boolean filterHabitableOnly) {
		this.filterHabitableOnly = filterHabitableOnly;
	}

	public boolean isFilterMarginalPlanets() {
		return filterMarginalPlanets;
	}

	public void setFilterMarginalPlanets(boolean filterMarginalPlanets) {
		this.filterMarginalPlanets = filterMarginalPlanets;
	}

	public void updateStars(AjaxBehaviorEvent event) {
		stars.clear();
		if (filterAccessLevelStars == null) {
			filterAccessLevelStars = AccessLevel.L0;
		}
		load();
	}
	
	@Transactional
	public String submit() {
		starDao.merge(star);
		this.star = new Star();
		return "manage-stars?faces-redirect=true";
	}

	@Override
	public void setImage(BufferedImage img) {
		star.setImage(img);
	}
}
