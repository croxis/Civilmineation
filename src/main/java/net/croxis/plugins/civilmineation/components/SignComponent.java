package net.croxis.plugins.civilmineation.components;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.croxis.plugins.civilmineation.CityPlotType;
import net.croxis.plugins.civilmineation.SignType;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "civ_signs")
public class SignComponent {
	@Id
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
	private Ent entityID;
	private String name = "";
	//@ManyToOne
	//@JoinColumn(name="WORLD_ID")
	@NotNull
	private String world;
	@Enumerated(EnumType.STRING)
    private SignType type;
	private int x, y, z;
	private byte rotation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ent getEntityID() {
		return entityID;
	}
	public void setEntityID(Ent entityID) {
		this.entityID = entityID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorld() {
		return world;
	}
	public void setWorld(String world) {
		this.world = world;
	}
	public SignType getType() {
		return type;
	}
	public void setType(SignType type) {
		this.type = type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public byte getRotation() {
		return rotation;
	}
	public void setRotation(byte rotation) {
		this.rotation = rotation;
	}
	
	
}
