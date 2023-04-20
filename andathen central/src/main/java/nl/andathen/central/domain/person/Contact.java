package nl.andathen.central.domain.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="contact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Contact implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -8051175764695399655L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
	
	/**
	* It creates table "Contact"
	* @param  id the contact_id of the user
	* @param  player the player_id of the user
	*/
	
	public Contact(Long id, Player player) {
		super();
		this.id = id;
		this.player = player;
	}
	
	public Contact(Player player) {
		this.player=player;
	}
	
	public Contact() {}

}
