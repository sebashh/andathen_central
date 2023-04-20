package nl.andathen.central.services.wsdl.culture;

import java.util.SortedSet;

import javax.ejb.EJB;
import javax.jws.WebService;

import nl.andathen.central.dao.CultureDao;
import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Culture;

/**
 * Class responsible for replying to requests incoming from the app requesting {@link Culture}
 * @author Dani Bakker
 */
@WebService(endpointInterface="nl.andathen.central.services.wsdl.culture.CultureService")
public class CultureWSDL implements CultureService {
	@EJB
	private CultureDao cultureDao;
	
	/**
	 * Get a specific {@link Culture} using it's identifier, checking if the user can also access it.
	 * @param userAccessLevel The  {@link AccessLevel} of the user.
	 * @param id The identifier of the {@link Culture}.
	 * @return The {@link Culture} requested.
	 * @author Dani Bakker
	 */
	@Override
	public Culture getCulture(AccessLevel userAccessLevel, Long id) {
		Culture cult = cultureDao.get(id);
		
		return (cult.getAccessLevel().asInt() <= userAccessLevel.asInt()) ? cult : null;
	}
	
	/**
	 * Get all the possible cultures to show till the user's accessLevel
	 * @param userAccessLevel The {@link AccessLevel} of the user.
	 * @return {@link SortedSet} containing {@link Culture}s till specified AccessLevel
	 * @author Dani Bakker
	 */
	@Override
	public SortedSet<Culture> getCultures(AccessLevel userAccessLevel) {
		return cultureDao.getTillAccessLevel(userAccessLevel);
	}

	/**
	 * Get all the {@link Culture}s of a specified (filter) {@link AccessLevel} <br/>
	 * Also checks if the user has the {@link AccessLevel} to access the filter AccessLevel.
	 * @param userAccessLevel The {@link AccessLevel} of the user.
	 * @param filterAccessLevel The {@link AccessLevel} requested.
	 * @return {@link SortedSet} containing {@link Culture}s on specified AccessLevel
	 * @author Dani Bakker 
	 */
	@Override
	public SortedSet<Culture> getCulturesFilterAccessLevel(AccessLevel userAccessLevel, AccessLevel filterAccessLevel) {
		return (filterAccessLevel.asInt() <= userAccessLevel.asInt()) ? cultureDao.getOnAccessLevel(filterAccessLevel) : null;
	}
}
