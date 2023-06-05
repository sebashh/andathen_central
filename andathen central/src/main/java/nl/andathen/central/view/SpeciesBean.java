package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import nl.andathen.central.dao.SpeciesDao;
import nl.andathen.central.domain.Species;

@Named
@SessionScoped
public class SpeciesBean extends AbstractBackingBean implements IImageUploader {
	private static final long serialVersionUID = 8479845193888586075L;
	@EJB
	private SpeciesDao speciesDao;
	private SortedSet<Species> species;
	private HashMap<Long, Species> speciesMap;
	private Species specie;
	private Part uploadedFile;
	
	@PostConstruct
	public void init()
	{
		super.init();
		this.specie = new Species();
		species = new TreeSet<>();
		speciesMap = new HashMap<>();
		
		// Get the count of the table
		totalRows = speciesDao.getCount().intValue();
		load();		
	}
	
	@Override
	protected void load() {
		// Load the set of zipcodes from the database
		species.clear();
		species.addAll(speciesDao.get((int) getFirstRow(),(int) getRowsPerPage()));
		for (Species s: species) {
			speciesMap.put(s.getId(), s);
		}
		calculatePageSettings();
	}
	
	public SortedSet<Species> getSpecies() {
		return species;
	}
	
	@Transactional
	public String submit() {
		Species del = speciesMap.get(specie.getId());
		species.remove(del);
		speciesMap.remove(del.getId());
		Species sk = speciesDao.merge(specie);
		species.add(sk);
		speciesMap.put(sk.getId(), sk);
		this.specie = new Species();
		return "manage-species?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		speciesDao.create(specie);
		specie = speciesDao.get(specie.getId());
		species.add(specie);
		speciesMap.put(specie.getId(), specie);
		this.specie = new Species();
		return "manage-species?faces-redirect=true";
	}
	
	public String cancel() {
		return "manage-species?faces-redirect=true";
	}
	
	public String edit(Species r) throws IOException {
		this.specie.setId(r.getId());
		this.specie.setName(r.getName());
		this.specie.setDescription(r.getDescription());
		this.specie.setPlayerNotes(r.getPlayerNotes());
		this.specie.setIntelligence(r.getIntelligence()); 
		this.specie.setAccessLevelTechlevel(r.getAccessLevelTechlevel());
		this.specie.setTechnicalLevel(r.getTechnicalLevel());
		this.specie.setPreferredPlanetType(r.getPreferredPlanetType());
		this.specie.setPreferredClimate(r.getPreferredClimate());
		this.specie.setPlanetOfOrigin(r.getPlanetOfOrigin());
		this.specie.setAccessLevel(r.getAccessLevelOrigins());
		this.specie.setPlayable(r.isPlayable());
		this.specie.setInCouncilSpace(r.isInCouncilSpace());
		this.specie.setCouncilRelations(r.getCouncilRelations());
		this.specie.setSkillModifiers(r.getSkillModifiers());
		this.specie.setAccessLevelModifiers(r.getAccessLevelModifiers());
		this.specie.setAccessLevel(r.getAccessLevel());
		this.specie.setImage(r.getImage());
		this.specie.setAccessLevelImage(r.getAccessLevelImage());
		this.specie.setSource(r.getSource());
		return "species-details";
	}
	
	@Transactional
	public void delete(Species r) {
		Species res = speciesDao.get(r.getName());
		if (res != null) {
			speciesDao.remove(res);
			init();
		}
	}

	public Species getSpecie() {
		return specie;
	}

	public void setSpecie(Species specie) {
		this.specie = specie;
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
		specie.setImage(img);
	}
}
