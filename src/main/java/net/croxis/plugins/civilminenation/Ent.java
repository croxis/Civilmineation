package net.croxis.plugins.civilminenation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entity")
public class Ent {
	@Id
	private int id;
	private String debugName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDebugName() {
		return debugName;
	}
	public void setDebugName(String debugName) {
		this.debugName = debugName;
	}
}
