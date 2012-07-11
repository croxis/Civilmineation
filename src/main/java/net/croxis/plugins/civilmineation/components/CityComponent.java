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
import javax.persistence.Transient;
import javax.persistence.Version;

import net.croxis.plugins.civilmineation.CityPlotType;

import org.bukkit.ChatColor;
import org.bukkit.Location;

@Entity()
@Table(name = "civ_city")
public class CityComponent{
	@Id
	private int id;
	@Version
	private int version;
	@OneToOne(fetch = FetchType.LAZY)
	private Ent entityID;
	private String name;
	//@OneToMany(mappedBy="city", fetch = FetchType.LAZY)
	//private List<ResidentComponent> residents = new ArrayList<ResidentComponent>();
	//@OneToMany(mappedBy="cityAssistant")
	//private List<ResidentComponent> assistants = new ArrayList<ResidentComponent>();
	//@OneToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="mayor_ID")
	//private ResidentComponent mayor;
	private int bonusBlocks;
	private int purchasedBlocks;
	private double taxes, plotTax, commercialPlotTax, embassyPlotTax,
		plotPrice, commercialPlotPrice, embassyPlotPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CIVILIZATION_ID")
	private CivilizationComponent civilization;
	private boolean hasUpkeep, isPublic, isTaxPercentage;
	private String townBoard = "/city set board [msg]", tag;
	@Transient
	private Location spawn;
	
	private double spawn_x;
	private double spawn_y;
	private double spawn_z;
	//@OneToOne
	private String spawn_world;
	private long registered;
	
	private int culture = 0;
	
	private String texturePackNode;
	private String capeNode;    	
	private String musicNode;
	
	private boolean capital;
	
	//@Enumerated(EnumType.STRING)
    private char chatcolor = ChatColor.AQUA.getChar();
	
	//public List<ResidentComponent> getResidents() {
	//	return residents;
	//}
	//public void setResidents(List<ResidentComponent> residents) {
	//	this.residents = residents;
	//}
	//public List<ResidentComponent> getAssistants() {
	//	return assistants;
	//}
	//public void setAssistants(List<ResidentComponent> assistants) {
	//	this.assistants = assistants;
	//}
	//public ResidentComponent getMayor() {
	//	return mayor;
	//}
	//public void setMayor(ResidentComponent mayor) {
	//	this.mayor = mayor;
	//}
	public static CityComponent copy(CityComponent old){
		CityComponent c = new CityComponent();
		c.setCapital(old.isCapital());
		c.setChatcolor(old.getChatcolor());
		c.setCivilization(old.getCivilization());
		c.setCulture(old.getCulture());
		c.setEntityID(old.getEntityID());
		c.setName(old.getName());
		c.setRegistered(old.getRegistered());
		c.setSpawn_world(old.getSpawn_world());
		c.setSpawn_x(old.getSpawn_x());
		c.setSpawn_y(old.getSpawn_y());
		c.setSpawn_z(old.getSpawn_z());
		c.setTownBoard(old.getTownBoard());
		return c;
	}
	@Override
	public String toString(){
		return this.getName();
	}
	public int getBonusBlocks() {
		return bonusBlocks;
	}
	public void setBonusBlocks(int bonusBlocks) {
		this.bonusBlocks = bonusBlocks;
	}
	public int getPurchasedBlocks() {
		return purchasedBlocks;
	}
	public void setPurchasedBlocks(int purchasedBlocks) {
		this.purchasedBlocks = purchasedBlocks;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getPlotTax() {
		return plotTax;
	}
	public void setPlotTax(double plotTax) {
		this.plotTax = plotTax;
	}
	public double getCommercialPlotTax() {
		return commercialPlotTax;
	}
	public void setCommercialPlotTax(double commercialPlotTax) {
		this.commercialPlotTax = commercialPlotTax;
	}
	public double getEmbassyPlotTax() {
		return embassyPlotTax;
	}
	public void setEmbassyPlotTax(double embassyPlotTax) {
		this.embassyPlotTax = embassyPlotTax;
	}
	public double getPlotPrice() {
		return plotPrice;
	}
	public void setPlotPrice(double plotPrice) {
		this.plotPrice = plotPrice;
	}
	public double getCommercialPlotPrice() {
		return commercialPlotPrice;
	}
	public void setCommercialPlotPrice(double commercialPlotPrice) {
		this.commercialPlotPrice = commercialPlotPrice;
	}
	public double getEmbassyPlotPrice() {
		return embassyPlotPrice;
	}
	public void setEmbassyPlotPrice(double embassyPlotPrice) {
		this.embassyPlotPrice = embassyPlotPrice;
	}
	public CivilizationComponent getCivilization() {
		return civilization;
	}
	public void setCivilization(CivilizationComponent civilization) {
		this.civilization = civilization;
	}
	public boolean isHasUpkeep() {
		return hasUpkeep;
	}
	public void setHasUpkeep(boolean hasUpkeep) {
		this.hasUpkeep = hasUpkeep;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public boolean isTaxPercentage() {
		return isTaxPercentage;
	}
	public void setTaxPercentage(boolean isTaxPercentage) {
		this.isTaxPercentage = isTaxPercentage;
	}
	public String getTownBoard() {
		return townBoard;
	}
	public void setTownBoard(String townBoard) {
		this.townBoard = townBoard;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Location getSpawn() {
		return spawn;
	}
	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}
	public double getSpawn_x() {
		return spawn_x;
	}
	public void setSpawn_x(double spawn_x) {
		this.spawn_x = spawn_x;
	}
	public double getSpawn_y() {
		return spawn_y;
	}
	public void setSpawn_y(double spawn_y) {
		this.spawn_y = spawn_y;
	}
	public double getSpawn_z() {
		return spawn_z;
	}
	public void setSpawn_z(double spawn_z) {
		this.spawn_z = spawn_z;
	}
	public String getSpawn_world() {
		return spawn_world;
	}
	public void setSpawn_world(String spawn_world) {
		this.spawn_world = spawn_world;
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
	public int getCulture() {
		return culture;
	}
	public void setCulture(int culture) {
		this.culture = culture;
	}
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	public char getChatcolor() {
		return chatcolor;
	}
	public void setChatcolor(char chatcolor) {
		this.chatcolor = chatcolor;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

}
