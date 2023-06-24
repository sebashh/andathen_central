package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;

import nl.andathen.central.dao.SpaceshipClassDao;
import nl.andathen.central.dao.SpaceshipDao;
import nl.andathen.central.domain.council.Spaceship;
import nl.andathen.central.domain.council.SpaceshipClass;

@Named
@SessionScoped
public class SpaceshipBean implements Serializable, IImageUploader {
	private static final long serialVersionUID = -6765480720031146029L;
	@EJB
	private SpaceshipDao spaceshipDao;
	@EJB
	private SpaceshipClassDao spaceshipClassDao;
	private SortedSet<Spaceship> spaceships;
	private HashMap<Long, Spaceship> spaceshipMap;
	private Spaceship spaceship;
	private List<SpaceshipClass> classOptions;
	private Part uploadedFile;
	
	@PostConstruct
	public void init() {
		spaceships = new TreeSet<>();
		spaceshipMap = new HashMap<>();
		spaceships.addAll(spaceshipDao.get());
		for (Spaceship r: spaceships) {
			spaceshipMap.put(r.getId(), r);
		}
		this.spaceship = new Spaceship();
		classOptions = new ArrayList<>();
		classOptions.addAll(spaceshipClassDao.get());
	}
	
	public SortedSet<Spaceship> getSpaceships() {
		return spaceships;
	}
	
	@Transactional
	public String submit() {
		Spaceship del = spaceshipMap.get(spaceship.getId());
		spaceships.remove(del);
		spaceshipMap.remove(del.getId());
		Spaceship sk = spaceshipDao.merge(spaceship);
		spaceships.add(sk);
		spaceshipMap.put(sk.getId(), sk);
		this.spaceship = new Spaceship();
		return "manage-spaceships?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		SpaceshipClass ssc = spaceshipClassDao.get(spaceship.getSpaceshipClass().getId());
		spaceship.setSpaceshipClass(ssc);
		spaceshipDao.create(spaceship);
		spaceship = spaceshipDao.get(spaceship.getId());
		spaceships.add(spaceship);
		spaceshipMap.put(spaceship.getId(), spaceship);
		this.spaceship = new Spaceship();
		return "manage-spaceships?faces-redirect=true";
	}
	
	public String create() {
	    return "add-spaceship";
	}
	
	public String cancel() {
		return "manage-spaceships?faces-redirect=true";
	}
	
	public String edit(Spaceship r) throws IOException {
		this.spaceship.setId(r.getId());
		this.spaceship.setName(r.getName());
		this.spaceship.setPrice(r.getPrice());
		this.spaceship.setDescription(r.getDescription());
		this.spaceship.setDesignation(r.getDesignation());
		this.spaceship.setAccessLevel(r.getAccessLevel());
		this.spaceship.setImage(r.getImage());
		this.spaceship.setSpaceshipClass(r.getSpaceshipClass());
		return "spaceship-details";
	}
	
	@Transactional
	public void delete(Spaceship r) {
		Spaceship res = spaceshipDao.get(r.getId());
		if (res != null) {
			spaceshipDao.remove(res);
			init();
		}
	}
	
	public Spaceship getSpaceship() {
		return spaceship;
	}

	public void setSpaceship(Spaceship spaceship) {
		this.spaceship = spaceship;
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

	@Override
	public void setImage(BufferedImage img) {
		spaceship.setImage(img);
	}

	public List<SpaceshipClass> getClassOptions() {
		return classOptions;
	}
}
