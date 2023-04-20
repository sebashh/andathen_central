package nl.andathen.central.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class representing a message.
 * @author Can Karabey
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="message")
public class Message implements Comparable<Message>, Serializable {
	private static final long serialVersionUID = -5634291945042478052L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="sender", nullable=false)
	private String sender;
	@Column(name="addressee", nullable=false)
	private String adressee;
	@Column(name="content", columnDefinition="TEXT")
	private String content;
	@Column(name="timestamp", nullable=false)
	private long timestamp;
	@Column(name="was_read", nullable=false)
	private boolean read = false;
	@Column(name="is_visible", nullable=false)
	private boolean visible = true;
	
	public Message(String from, String to, String content, long timestamp) {
		super();
		this.sender = from;
		this.adressee = to;
		this.content = content;
		this.timestamp = timestamp;
		this.read = false;
		this.visible = true;
	}

	public Message() {
	
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public long getId() {
		return id;
	}

	public String getSender() {
		return sender;
	}

	public String getAdressee() {
		return adressee;
	}

	public String getContent() {
		return content;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, sender);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(content, other.content) && Objects.equals(sender, other.sender);
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + ", adressee=" + adressee + ", content=" + content + ", timestamp="
				+ timestamp + ", read=" + read + ", visible=" + visible + "]";
	}

	@Override
	public int compareTo(Message o) {
		if (!this.getContent().toUpperCase().equals(o.getContent().toUpperCase())) {
			return o.getContent().compareTo(this.getContent());
		}
		else {
			return this.getSender().compareTo(o.getSender());
		}
	}
}
