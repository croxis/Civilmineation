package net.croxis.plugins.civilmineation.components;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "civ_entity")
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
