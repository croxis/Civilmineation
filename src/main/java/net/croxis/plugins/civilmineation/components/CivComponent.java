package net.croxis.plugins.civilmineation.components;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bukkit.ChatColor;

@Entity()
@Table(name = "civ_civilization")
public class CivComponent{
	@Id
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
	private Ent entityID;
	private String name;
	//@OneToMany(mappedBy="civAssistant")
    //private List<ResidentComponent> assistants = new ArrayList<ResidentComponent>();
	@OneToMany(mappedBy="civilization", fetch = FetchType.LAZY)
    private List<CityComponent> cities = new ArrayList<CityComponent>();
	/*@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="CIV_ALLIES",
			joinColumns = @JoinColumn(name="CIV_ID", referencedColumnName="ID"),
			inverseJoinColumns = @JoinColumn(name="ALLY_ID", referencedColumnName="ID") 
			)*/
	@ManyToMany(targetEntity=CivComponent.class)
	@JoinTable(
			name="civ_civ_ally_map",
			joinColumns=@JoinColumn(name="civ_id", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="ally_id", referencedColumnName="ID")
			)
	    private List<CivComponent> allies = new ArrayList<CivComponent>();
	/*@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="CIV_ENEMIES",
			joinColumns = @JoinColumn(name="CIV_ID", referencedColumnName="ID"),
			inverseJoinColumns = @JoinColumn(name="ENEMY_ID", referencedColumnName="ID") 
			)*/
	@ManyToMany(targetEntity=CivComponent.class)
	@JoinTable(
			name="civ_civ_enemy_map",
			joinColumns=@JoinColumn(name="civ_id", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="enemy_id", referencedColumnName="ID")
			)
    private List<CivComponent> enemies = new ArrayList<CivComponent>();
	//@OneToOne
	//@JoinColumn(name="CITY_ID")
    //private CityComponent capital;
    private double taxes;
    private boolean neutral = false;
    private String tag;
    
    private long registered;
    
    private String texturePackNode;
	private String capeNode;    	
	private String musicNode;
	
	//@Enumerated(EnumType.STRING)
    private char chatcolor = ChatColor.YELLOW.getChar();
	//public List<ResidentComponent> getAssistants() {
	//	return assistants;
	//}
	//public void setAssistants(List<ResidentComponent> assistants) {
	//	this.assistants = assistants;
	//}
	public List<CityComponent> getCities() {
		return cities;
	}
	public void setCities(List<CityComponent> cities) {
		this.cities = cities;
	}
	public List<CivComponent> getAllies() {
		return allies;
	}
	public void setAllies(List<CivComponent> allies) {
		this.allies = allies;
	}
	public List<CivComponent> getEnemies() {
		return enemies;
	}
	public void setEnemies(List<CivComponent> enemies) {
		this.enemies = enemies;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public boolean isNeutral() {
		return neutral;
	}
	public void setNeutral(boolean neutral) {
		this.neutral = neutral;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public long getRegistered() {
		return registered;
	}
	public void setRegistered(long registered) {
		this.registered = registered;
	}
	public String getTexturePackNode() {
		return texturePackNode;
	}
	public void setTexturePackNode(String texturePackNode) {
		this.texturePackNode = texturePackNode;
	}
	public String getCapeNode() {
		return capeNode;
	}
	public void setCapeNode(String capeNode) {
		this.capeNode = capeNode;
	}
	public String getMusicNode() {
		return musicNode;
	}
	public void setMusicNode(String musicNode) {
		this.musicNode = musicNode;
	}
	
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
	public char getChatcolor() {
		return chatcolor;
	}
	public void setChatcolor(char chatcolor) {
		this.chatcolor = chatcolor;
	}

}
