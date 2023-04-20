package nl.andathen.central.services.wsdl.culture;

import java.util.SortedSet;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Culture;

@WebService
public interface CultureService {
	
	/**
	 * Get a specific {@link Culture} using it's identifier, checking if the user can also access it.
	 * @param userAccessLevel The  {@link AccessLevel} of the user.
	 * @param id The identifier of the {@link Culture}.
	 * @return The {@link Culture} requested.
	 * @author Dani Bakker
	 */
	@WebMethod public Culture getCulture(
			@WebParam(name="UserAccessLevel") AccessLevel userAccessLevel,
			@WebParam(name="CultureID") Long id);
	
	/**
	 * Get all the possible cultures to show till the user's accessLevel
	 * @param userAccessLevel The {@link AccessLevel} of the user.
	 * @return {@link SortedSet} containing {@link Culture}s till specified AccessLevel
	 * @author Dani Bakker
	 */
	@WebMethod public SortedSet<Culture> getCultures(
			@WebParam(name="UserAccessLevel") AccessLevel userAccessLevel);
	
	/**
	 * Get all the {@link Culture}s of a specified (filter) {@link AccessLevel} <br/>
	 * Also checks if the user has the {@link AccessLevel} to access the filter AccessLevel.
	 * @param userAccessLevel The {@link AccessLevel} of the user.
	 * @param filterAccessLevel The {@link AccessLevel} requested.
	 * @return {@link SortedSet} containing {@link Culture}s on specified AccessLevel
	 * @author Dani Bakker 
	 */
	@WebMethod public SortedSet<Culture> getCulturesFilterAccessLevel(
			@WebParam(name="UserAccessLevel") AccessLevel userAccessLevel,
			@WebParam(name="FilterAccessLevel") AccessLevel filterAccessLevel);
}
