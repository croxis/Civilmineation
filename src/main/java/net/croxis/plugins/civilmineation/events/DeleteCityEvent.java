package net.croxis.plugins.civilmineation.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeleteCityEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private int entityID;
	private String name;
	
	public DeleteCityEvent(String name, int entID){
		entityID = entID;
		this.name = name;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
