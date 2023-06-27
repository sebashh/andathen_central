package nl.andathen.central.services.wsdl;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;

import nl.andathen.central.domain.Message;
import nl.andathen.central.services.wsdl.message.MessageService;

/**
 * 
 * @author Can Karabey
 *
 */
public class MessageTest {
	public static void main(String[] args) throws Exception {		   
		URL url = new URL("http://localhost:8080/ciadan-central/services/wsdl/message/MessageWSDL?wsdl");
	    QName qname = new QName("http://message.wsdl.services.central.andathen.nl/", "MessageWSDLService");
	
	    Service service = Service.create(url, qname);
	    MessageService ms = service.getPort(MessageService.class);
	    List<Message> messages = ms.getMessages("Ch2","Ch1");
	    for (Message m: messages) {
	    	System.out.println(m.isRead());
	    	System.out.println(m.isVisible());
		    System.out.println(m);
	    }
	    
	    System.out.println(ms.getMessages("Ch1","Ch2"));
	    System.out.println(ms.setReadToTrue(1L));  
	    System.out.println(ms.getAllMessages()); 
	    System.out.println(ms.getGroupChatsForCharacter("Ch1"));
	    System.out.println(ms.getGroupMessages("AMOeL"));
	    System.out.println(ms.setVisibleToFalse(1L));
	    
	}
}
