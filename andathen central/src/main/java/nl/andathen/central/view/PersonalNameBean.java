package nl.andathen.central.view;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;

import nl.andathen.central.dao.LanguageDao;
import nl.andathen.central.dao.PersonNameDao;
import nl.andathen.central.domain.Gender;
import nl.andathen.central.domain.Language;
import nl.andathen.central.domain.PersonalName;

@Named
@SessionScoped
public class PersonalNameBean implements Serializable{
	private static final long serialVersionUID = -7105958290740880553L;
	@EJB
	private PersonNameDao personalNameDao;
	@EJB
	private LanguageDao languageDao;
	private List<PersonalName> personalNames;
 	private PersonalName personalName;
 	private String bulkData;
 	
	@PostConstruct
	public void init()
	{
		personalNames = new ArrayList<>();
		personalNames.addAll(personalNameDao.get());
		personalNames.sort(null);
		this.personalName = new PersonalName();
		this.bulkData = new String();
	}
	
	public PersonalNameBean() {

	}

	@Transactional
	public void delete(PersonalName pn) {
		System.out.println(pn);
		PersonalName res = personalNameDao.get(pn.getId());
		System.out.println(res);
		if (res != null) {
			personalNameDao.remove(res);
			init();
		}
	}
	
	public String cancel() {
		init();
		return "manage-personal-names?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		StringBuilder remnant = new StringBuilder(); 
		String[] lines = bulkData.split(System.getProperty("line.separator"));
		for (String line: lines) {
			PersonalName pn = null;
			String[] record = line.split(",");
			try {
				if ((record[2] != null) && (record[1] != null) && (record[0] != null)) {
					Language language = languageDao.get(record[0]);
					if (language == null) {
						language = languageDao.getFuzzy(record[0]);
					}
					Gender gender = Gender.parse(record[1]);
					if ((language != null) && (gender != null)) {
						record[2].trim();
						if (record[2].length() > 0) {
							if ((record.length < 4) || (record[3] == null) || (record[3].length() == 0)) {
								record[3] = null;
							}
							// Create a name to be added to the database and do so.
							if (Charset.forName("US-ASCII").newEncoder().canEncode(record[2])) {
								if ((record[3] == null) || (Charset.forName("US-ASCII").newEncoder().canEncode(record[3]))) {
									pn = new PersonalName(language, record[2], record[3],gender);
									try {
										personalNameDao.create(pn);
									}
									catch (EntityExistsException e2) {
										
									}
								}
							}
						}
					}
				}
				if (pn == null) {
					// Record was wrong; leave it in the list on the screen
					remnant.append(line);
					remnant.append(System.getProperty("line.separator"));
				}
			}
			catch (IndexOutOfBoundsException e) {
				// Record was wrong; leave it in the list on the screen
				remnant.append(line);
				remnant.append(System.getProperty("line.separator"));
			}
		}
		// Check the remaining lines. If all that is left is line separators return, otherwise stay.
		String left = new String(remnant);
		if ((left.length() < 1) || (!left.contains(","))) {
			init();
			return "manage-personal-names?faces-redirect=true";
		}
		else {
			bulkData = left;
			return "";
		}
	}
	
	public PersonalName getPersonalName() {
		return this.personalName;
	}
	
	public List<PersonalName> getPersonalNames(){
		return this.personalNames;
	}
	
	public String goToMainPage() {
	    return "index?faces-redirect=true";
	}
	
	public String getBulkData() {
		return bulkData;
	}

	public void setBulkData(String bulkData) {
		this.bulkData = bulkData;
	}

	public String addBulk() {
	    return "add-personal-names?faces-redirect=true";
	}
}
