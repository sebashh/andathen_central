package nl.andathen.central.dao;

import java.util.Collection;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import nl.andathen.central.domain.ChatGroup;
import nl.andathen.central.domain.Message;

/**
 * 
 * @author Can Karabey
 *
 */
@Stateless
public class MessageDao {
	
	@PersistenceContext(unitName="andathen")
    private EntityManager entityManager;

	public Message get(String umid) {
        TypedQuery<Message> query = entityManager.createQuery("FROM Message WHERE :umid = umid", Message.class);
        query.setParameter("umid", umid);
        try {
        	return query.getSingleResult();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public Collection<Message> get(String FromCharacter, String ToCharacter) {
        TypedQuery<Message> query = entityManager.createQuery("FROM Message WHERE :FromCharacter = sender and :ToCharacter = addressee", Message.class);
        query.setParameter("FromCharacter", FromCharacter);
        query.setParameter("ToCharacter", ToCharacter);
        try {
        	return query.getResultList();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public int setReadToTrue(Long umid) {
        Query query = entityManager.createQuery("UPDATE Message SET ReadByReciever = True WHERE :UMID = umid");
        query.setParameter("UMID", umid);
        try {
        	return query.executeUpdate();
        }
        catch(NoResultException e) {
        	System.out.println(e);
        	return 0;
        }
	}
	
	public int setVisibleToFalse(Long umid) {
        Query query = entityManager.createQuery("UPDATE Message SET Visible = False WHERE :UMID = umid");
        query.setParameter("UMID", umid);
        try {
        	return query.executeUpdate();
        }
        catch(NoResultException e) {
        	System.out.println(e);
        	return 0;
        }
	}
	

    public Collection<Message> get() {
        TypedQuery<Message> query = entityManager.createQuery("FROM Message", Message.class);
        return query.getResultList();
    }
    
	public Collection<ChatGroup> getChatGroupsForCharacter(String characterName) {
        TypedQuery<ChatGroup> query = entityManager.createQuery("FROM ChatGroup WHERE :characterName = characterName", ChatGroup.class);
        query.setParameter("characterName", characterName);
        try {
        	return query.getResultList();
        }
        catch (NoResultException e) {
        	return null;
        }
	}
	
	public Collection<Message> getGroupMessages(String ToCharacter){
		TypedQuery<Message> query = entityManager.createQuery("FROM Message WHERE :ToCharacter = addressee", Message.class);
        query.setParameter("ToCharacter", ToCharacter);
        try {
        	return query.getResultList();
        }
        catch (NoResultException e) {
        	return null;
        }
	}

    public void create(Message r) {
    	entityManager.persist(r);
    }

	public void remove(Message r) {
		entityManager.remove(r);
	}
	
	public void createGroup(ChatGroup g) {
		entityManager.persist(g);
	}
	
	public void removeGroup(ChatGroup g) {
		entityManager.persist(g);
	}
	
	public Message merge(Message r) {
		return entityManager.merge(r);
	}
}