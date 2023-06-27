package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;

import nl.andathen.central.dao.SpaceshipClassDao;
import nl.andathen.central.domain.council.SpaceshipClass;

@Named
@SessionScoped
public class SpaceshipClassBean implements Serializable, IImageUploader {
	private static final long serialVersionUID = -6765480720031146029L;
	@EJB
	private SpaceshipClassDao spaceshipClassDao;
	private SortedSet<SpaceshipClass> spaceshipClasses;
	private HashMap<Long, SpaceshipClass> spaceshipClassMap;
	private SpaceshipClass spaceshipClass;
	private Part uploadedFile;
	
	@PostConstruct
	public void init() {
		spaceshipClasses = new TreeSet<>();
		spaceshipClassMap = new HashMap<>();
		spaceshipClasses.addAll(spaceshipClassDao.get());
		for (SpaceshipClass r: spaceshipClasses) {
			spaceshipClassMap.put(r.getId(), r);
		}
		this.spaceshipClass = new SpaceshipClass();
		for (SpaceshipClass c: spaceshipClasses) {
			System.out.println(c);
		}
	}
	
	public SortedSet<SpaceshipClass> getSpaceshipClasses() {
		return spaceshipClasses;
	}
	
	@Transactional
	public String submit() {
		SpaceshipClass del = spaceshipClassMap.get(spaceshipClass.getId());
		spaceshipClasses.remove(del);
		spaceshipClassMap.remove(del.getId());
		SpaceshipClass sk = spaceshipClassDao.merge(spaceshipClass);
		spaceshipClasses.add(sk);
		spaceshipClassMap.put(sk.getId(), sk);
		this.spaceshipClass = new SpaceshipClass();
		return "manage-spaceship-classes?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		spaceshipClassDao.create(spaceshipClass);
		spaceshipClass = spaceshipClassDao.get(spaceshipClass.getId());
		spaceshipClasses.add(spaceshipClass);
		spaceshipClassMap.put(spaceshipClass.getId(), spaceshipClass);
		this.spaceshipClass = new SpaceshipClass();
		return "manage-spaceship-classes?faces-redirect=true";
	}
	
	public String create() {
	    return "add-spaceship-class";
	}
	
	public String cancel() {
		return "manage-spaceship-classes?faces-redirect=true";
	}
	
	public String edit(SpaceshipClass r) throws IOException {
		this.spaceshipClass.setId(r.getId());
		this.spaceshipClass.setName(r.getName());
		this.spaceshipClass.setBasePrice(r.getBasePrice());
		this.spaceshipClass.setDescription(r.getDescription());
		this.spaceshipClass.setAccessLevel(r.getAccessLevel());
		this.spaceshipClass.setImage(r.getImage());
		return "spaceship-class-details";
	}
	
	@Transactional
	public void delete(SpaceshipClass r) {
		SpaceshipClass res = spaceshipClassDao.get(r.getId());
		if (res != null) {
			spaceshipClassDao.remove(res);
			init();
		}
	}
	
	public SpaceshipClass getSpaceshipClass() {
		return spaceshipClass;
	}

	public void setSpaceshipClass(SpaceshipClass spaceshipClass) {
		this.spaceshipClass = spaceshipClass;
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
		spaceshipClass.setImage(img);
	}
}
