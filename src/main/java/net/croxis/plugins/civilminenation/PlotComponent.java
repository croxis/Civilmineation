package net.croxis.plugins.civilminenation;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "civ_plots")
public class PlotComponent {
	@Id
	private int id;
	private Ent entityID;
	private String name;
	//@ManyToOne
	//@JoinColumn(name="WORLD_ID")
	private String world;
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private CityComponent city;
	@ManyToOne
	@JoinColumn(name="RESIDENT_ID")
	private ResidentComponent resident;
	@Enumerated(EnumType.STRING)
    private CityPlotType type;
	private int x, z;
	private double plotPrice = -1;
	
    private boolean isChanged;
	public String getWorld() {
		return world;
	}
	public void setWorld(String world) {
		this.world = world;
	}
	public CityComponent getCity() {
		return city;
	}
	public void setCity(CityComponent city) {
		this.city = city;
	}
	public ResidentComponent getResident() {
		return resident;
	}
	public void setResident(ResidentComponent resident) {
		this.resident = resident;
	}
	public CityPlotType getType() {
		return type;
	}
	public void setType(CityPlotType type) {
		this.type = type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public double getPlotPrice() {
		return plotPrice;
	}
	public void setPlotPrice(double plotPrice) {
		this.plotPrice = plotPrice;
	}
	public boolean isChanged() {
		return isChanged;
	}
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
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
}
