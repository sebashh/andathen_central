package nl.andathen.central.domain.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class Email extends Contact {
	private static final long serialVersionUID = 5044808859264541259L;
	@Column(name="email")
	private String email;
	
	public Email(String email, Player id) {
		super(id);
		this.email=email;		
	}
	
	public Email() {
		super();
	}

	
}
