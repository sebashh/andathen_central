package nl.andathen.central.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-06-05T09:40:37.750+0200")
@StaticMetamodel(Message.class)
public class Message_ {
	public static volatile SingularAttribute<Message, Long> id;
	public static volatile SingularAttribute<Message, String> sender;
	public static volatile SingularAttribute<Message, String> adressee;
	public static volatile SingularAttribute<Message, String> content;
	public static volatile SingularAttribute<Message, Long> timestamp;
	public static volatile SingularAttribute<Message, Boolean> read;
	public static volatile SingularAttribute<Message, Boolean> visible;
}
