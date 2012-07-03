package net.croxis.plugins.civilmineation.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ResidentJoinEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private int entityID;
	private String residentName;
	
	public ResidentJoinEvent(String name, int entID){
		entityID = entID;
		residentName = name;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}

	public int getEntityID() {
		return entityID;
	}

	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}

	public String getResidentName() {
		return residentName;
	}
	
	public String getName() {
		return residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}
}
