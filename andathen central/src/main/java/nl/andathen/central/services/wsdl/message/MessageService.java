package nl.andathen.central.services.wsdl.message;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import nl.andathen.central.domain.Message;

@WebService
public interface MessageService {
	
	@WebMethod public List<Message> getMessages(@WebParam(name="FromCharacter") String FromCharacter, 
												@WebParam(name="ToCharacter") String ToCharacter);
	
	@WebMethod public boolean postMessage(@WebParam(name="FromCharacter") String FromCharacter,
										  @WebParam(name="ToCharacter") String ToCharacter,
										  @WebParam(name="Content") String Content);
	
	@WebMethod public int setReadToTrue(@WebParam(name="UMID") Long umid);
	
	@WebMethod public int setVisibleToFalse(@WebParam(name="UMID") Long umid);
	
	@WebMethod public List<String> getGroupChatsForCharacter(@WebParam(name="characterName") String characterName);
	
	@WebMethod public void addCharacterToGroup(@WebParam(name="characterName")String characterName,
											   @WebParam(name="groupName") String groupName);
	
	@WebMethod public List<Message> getAllMessages();
	
	@WebMethod public List<Message> getGroupMessages(@WebParam(name="groupName") String groupName);

}
