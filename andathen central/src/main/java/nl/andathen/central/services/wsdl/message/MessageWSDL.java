package nl.andathen.central.services.wsdl.message;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import nl.andathen.central.dao.MessageDao;
import nl.andathen.central.domain.ChatGroup;
import nl.andathen.central.domain.Message;
/**
 * @author Can Karabey
 *
 */
@WebService(endpointInterface="nl.andathen.central.services.wsdl.message.MessageService")
public class MessageWSDL implements MessageService {
	
	@EJB
	private MessageDao dao;
	
	@Override 
	public List<Message> getMessages(String FromCharacter, String ToCharacter) {
		List<Message> result = new LinkedList<>();
		Collection<Message> messages = dao.get(FromCharacter,ToCharacter);
		result.addAll(messages);
		result.sort(null);
		return result; 
	}

	@Override
	public boolean postMessage(String FromCharacter,String ToCharacter,String Content) {
		try {
			Message msg = new Message(FromCharacter,ToCharacter,Content,System.currentTimeMillis() / 1000L);
			dao.create(msg);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public int setReadToTrue(Long umid) {
		try {
			return dao.setReadToTrue(umid);	
		}
		catch(Exception e) {
			return 0;
		}
	}

	@Override
	public List<Message> getAllMessages() {
		List<Message> result = new LinkedList<>();
		Collection<Message> messages = dao.get();
		result.addAll(messages);
		result.sort(null);
		return result;
	}

	@Override
	public List<String> getGroupChatsForCharacter(String characterName) {
		List<String> result = new LinkedList<>();
		Collection<ChatGroup> chatGroups = dao.getChatGroupsForCharacter(characterName);
		for(ChatGroup gc:chatGroups) {
			result.add(gc.getGroupname());
		}
		result.sort(null);
		return result;
	}

	@Override
	public void addCharacterToGroup(String characterName, String groupName) {
		dao.createGroup(new ChatGroup(characterName,groupName));
		
	}

	@Override
	public List<Message> getGroupMessages(String groupName) {
		List<Message> result = new LinkedList<>();
		Collection<Message> messages = dao.getGroupMessages(groupName);
		result.addAll(messages);
		result.sort(null);
		return result;
	}

	@Override
	public int setVisibleToFalse(Long umid) {
		try {
			return dao.setVisibleToFalse(umid);	
		}
		catch(Exception e) {
			return 0;
		}
	}

}
