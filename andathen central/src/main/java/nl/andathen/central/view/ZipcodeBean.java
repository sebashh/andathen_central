package nl.andathen.central.view;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import nl.andathen.central.dao.CountryDao;
import nl.andathen.central.dao.ZipcodeDao;
import nl.andathen.central.domain.person.Country;
import nl.andathen.central.domain.person.Zipcode;

@Named
@SessionScoped
public class ZipcodeBean extends AbstractBackingBean {
	private static final long serialVersionUID = -5236525622645781211L; 
	@EJB
	private ZipcodeDao zipcodeDao;
	@EJB
	private CountryDao countryDao;
	private List<Zipcode> zipcodes;
 	private Zipcode zipcode;
 	private String bulkData;
	
	public ZipcodeBean() {

	}
 	
	@PostConstruct
	public void init()
	{
		super.init();
		this.zipcode = new Zipcode();
		this.bulkData = new String();
		zipcodes = new ArrayList<>();
		
		// Get the count of the table
		totalRows = zipcodeDao.getCount().intValue();
		load();		
	}
	
	@Override
	protected void load() {
		// Load the set of zipcodes from the database
		zipcodes.clear();
		zipcodes.addAll(zipcodeDao.get((int) getFirstRow(),(int) getRowsPerPage()));
		zipcodes.sort(null);
		calculatePageSettings();
	}

	@Transactional
	public void delete(Zipcode pn) {
		Zipcode res = zipcodeDao.get(pn.getId());
		System.out.println(res);
		if (res != null) {
			zipcodeDao.remove(res);
			init();
		}
	}
	
	public String cancel() {
		init();
		return "manage-zipcodes?faces-redirect=true";
	}
	
	// 0. Code
	// 1. Street
	// 2. Municipality
	// 3. Country
	// 4. Town
	// 5. Region (province)
	// 6. Housenumber min (optional)
	// 7. Housenumber max (optional)
	@Transactional
	public String add() {
		StringBuilder remnant = new StringBuilder(); 
		String[] lines = bulkData.split(System.getProperty("line.separator"));
		for (String line: lines) {
			Zipcode pn = null;
			String[] record = line.split(",");
			try {
				if ((record[3] != null) && (record[2] != null) && (record[1] != null) && (record[0] != null) && (record[3].trim().length() > 0) && (record[2].trim().length() > 0) && (record[1].length() > 0) && (record[0].trim().length() > 0)) {
					Country country = countryDao.get(record[3].trim());
					String code = record[0].trim().toUpperCase();
					String street = record[1].trim();
					String municipality = record[2].trim();
					Integer minNumber = null, maxNumber = null;
					String town = null, region = null;
					if ((record.length >= 5) && (record[4] != null) && (record[4].trim().length() > 0)) {
						town = record[4].trim();
					}
					if ((record.length >= 6) && (record[5] != null) && (record[5].trim().length() > 0)) {
						region = record[5].trim();
					}
					if ((record.length >= 7) && (record[6] != null) && (record[6].trim().length() > 0)) {
						try {
							minNumber = Integer.valueOf(record[6].trim());
						}
						catch (NumberFormatException e) {
							minNumber = -1;
						}
					}
					if ((record.length >= 8) && (record[7] != null) && (record[7].trim().length() > 0)) {
						try {
							maxNumber = Integer.valueOf(record[7].trim());
						}
						catch (NumberFormatException e) {
							maxNumber = -1;
						}
					}
					if ((country != null) && (minNumber != -1) && (maxNumber != -1)) {
						// Create a name to be added to the database and do so.
						if (Charset.forName("US-ASCII").newEncoder().canEncode(code) && (Charset.forName("US-ASCII").newEncoder().canEncode(street)  && Charset.forName("US-ASCII").newEncoder().canEncode(municipality))) {
							pn = new Zipcode(code, minNumber, maxNumber, street, municipality, town, region, country);
							try {
								zipcodeDao.create(pn);
							}
							catch (Exception e2) {
							
							}
						}	
					}
					if (pn == null) {
						// Record was wrong; leave it in the list on the screen
						remnant.append(line);
						remnant.append(System.getProperty("line.separator"));
					}
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
			return "manage-zipcodes?faces-redirect=true";
		}
		else {
			bulkData = left;
			return "";
		}
	}
	
	public Zipcode getZipcode() {
		return this.zipcode;
	}
	
	public List<Zipcode> getZipcodes(){
		return this.zipcodes;
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
	
	public void setZipcodes(List<Zipcode> zipcodes) {
		this.zipcodes = zipcodes;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	public String addBulk() {
	    return "add-zipcodes?faces-redirect=true";
	}
}
