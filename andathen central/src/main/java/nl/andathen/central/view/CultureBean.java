package nl.andathen.central.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import nl.andathen.central.dao.CultureDao;
import nl.andathen.central.dao.SpeciesDao;
import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Culture;
import nl.andathen.central.domain.Species;

// TODO Add Accesslevel filter on Culture Table
// TODO Add Elastic Search (TEXT Search on Name and description.
/**
 * The session bean responsible for managing cultures and their corresponding admin views.
 * @author Dani Bakker
 */
@Named
@SessionScoped
public class CultureBean implements Serializable{
	private static final long serialVersionUID = 8621037251111155216L;
	@EJB
	private CultureDao cultureDao;
	@EJB
	private SpeciesDao speciesDao;
	private List<Culture> cultures;
	private List<Species> specieOptions;
 	private Culture culture;
 	private AccessLevel filterAccessLevelCultures = null;
 	private AccessLevel filterAccessLevelSpecies = AccessLevel.L0;
 	
 	/**
 	 * The function responsible for initializing the bean for use. <br/>
 	 * Including the lists containing the cultures and the species
 	 * @author Dani Bakker
 	 */
	@PostConstruct
	public void init()
	{
		cultures = new ArrayList<>();
		cultures.addAll(cultureDao.get());
		cultures.sort(null);
		
		specieOptions = new ArrayList<>();
		specieOptions.addAll(speciesDao.get());
		specieOptions.sort(null);
		
		this.culture = new Culture();
	}
	
	/**
	 * Default auto-generated constructor. <br/>
	 * Required initialization is done by {@link #init}.
	 * @author (Commented by) Dani Bakker 
	 */
	public CultureBean() {
		//TODO Auto-generated constructor stub
	}
	
	/**
	 * Function responsible for editing the selected {@link Culture} and pushing it's changes to the Database. <br/>
	 * Also adjust the local list {@link #cultures}.
	 * @see nl.andathen.central.dao.CultureDaoMySQL
	 * @return {@link String} Redirect to the manage ingame cultures page.
	 * @author Dani Bakker
	 */
	@Transactional
	public String submit() {
		cultures.remove(culture);
		Culture sk = cultureDao.merge(culture);
		cultures.add(sk);
		cultures.sort(null);
		this.culture = new Culture();
		return "manage-cultures?faces-redirect=true";
	}
	
	/**
	 * Function responsible for adding a new {@link Culture} to the database and to the local list {@link #cultures}
	 * @see nl.andathen.central.dao.CultureDaoMySQL
	 * @return {@link String} Redirect to the manage ingame cultures page.
	 * @author Dani Bakker
	 */
	@Transactional
	public String add() {
		if ((culture.getName() != null) && (culture.getName().length() > 0)) {
			cultureDao.create(culture);
			cultures.add(culture);
			cultures.sort(null);
			this.culture = new Culture();
			return "manage-cultures?faces-redirect=true";
		}
		else {
			return "";
		}
	}
	
	/**
	 * Function responsible for setting up the editing page environment. Set's up the {@link Culture} in question.
	 * @param cult The {@link Culture} from the Table.
	 * @return ({@link String}) Redirect to the ingame cultures details page.
	 * @throws IOException
	 * @author Dani Bakker
	 */
	public String edit(Culture cult) throws IOException {
		this.culture.setId(cult.getId());
		this.culture.setName(cult.getName());
		this.culture.setDescription(cult.getDescription());
		this.culture.setAccessLevel(cult.getAccessLevel());
		this.culture.setSpecies(cult.getSpecies());
		setFilterAccessLevelSpecies(cult.getSpecies().getAccessLevel());
		updateSpecies(null);
		return "culture-details";
	}
	
	/**
	 * Function responsible for deleting the specified {@link Culture} from the Database.
	 * @param c The {@link Culture} from the Table.
	 * @see nl.andathen.central.dao.CultureDaoMySQL
	 * @author Dani Bakker
	 */
	@Transactional
	public void delete(Culture c) {
		Culture cult = cultureDao.get(c.getId());
		if (cult != null) {
			cultureDao.remove(cult);
			cultures.remove(cult);
			cultures.sort(null);
			this.culture = new Culture();
		}
	}
	
	/**
	 * Function responsible for adjusting the {@link #specieOptions} according to the selected filter ({@link #filterAccessLevelSpecies}).
	 * @param event The {@link AjaxBehaviorEvent} surrounding the ajax update.
	 * @author Dani Bakker
	 */
	public void updateSpecies(AjaxBehaviorEvent event) {
		specieOptions.clear();
		if (filterAccessLevelSpecies != null) {
			specieOptions.addAll(speciesDao.getOnAccessLevel(filterAccessLevelSpecies));
		}
		else {
			specieOptions.addAll(speciesDao.get());
		}
		specieOptions.sort(null);
	}

	/**
	 * Function responsible for adjusting the {@link #cultures} according to the selected filter ({@link #filterAccessLevelCultures}).
	 * @param event The {@link AjaxBehaviorEvent} surrounding the ajax update.
	 * @author Dani Bakker
	 */
	public void updateCultures(AjaxBehaviorEvent event) {
		cultures.clear();
		if (filterAccessLevelCultures != null) {
			cultures.addAll(cultureDao.getOnAccessLevel(filterAccessLevelCultures));
		}
		else {
			cultures.addAll(cultureDao.get());
		}
		cultures.sort(null);
	}
	
	public Culture getCulture() {
		return this.culture;
	}
	
	public AccessLevel getFilterAccessLevelCultures() {
		return this.filterAccessLevelCultures;
	}
	
	public void setFilterAccessLevelCultures(AccessLevel lvl) {
		this.filterAccessLevelCultures = lvl;
	}
	
	public AccessLevel getFilterAccessLevelSpecies() {
		return this.filterAccessLevelSpecies;
	}
	
	public void setFilterAccessLevelSpecies(AccessLevel lvl) {
		this.filterAccessLevelSpecies = lvl;
	}
	
	public List<Culture> getCultures(){
		return this.cultures;
	}
	
	public List<Species> getSpecieOptions() {
		return this.specieOptions;
	}
	
	public String create() {
	    return "add-culture";
	}
	
	public String cancel() {
		return "manage-cultures?faces-redirect=true";
	}
	
	public String goToMainPage() {
	    return "index?faces-redirect=true";
	}
}
