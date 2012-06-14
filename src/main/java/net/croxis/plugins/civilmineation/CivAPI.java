package net.croxis.plugins.civilmineation;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class CivAPI {
	public static Civilmineation plugin;
	public CivAPI(Civilmineation p){
		plugin = p;
	}
	
	public static ResidentComponent getResident(String name){
    	return plugin.getDatabase().find(ResidentComponent.class).where().ieq("name", name).findUnique();
    }
    
    public static ResidentComponent getResident(Player player){
    	return plugin.getDatabase().find(ResidentComponent.class).where().ieq("name", player.getName()).findUnique();
    }
    
    public static PlotComponent getPlot(Chunk chunk){
    	return plugin.getDatabase().find(PlotComponent.class).where().eq("x", chunk.getX()).eq("z", chunk.getZ()).findUnique();
    }
    
    public static PermissionComponent getPermissions(Ent ent){
    	return plugin.getDatabase().find(PermissionComponent.class).where().eq("entityID", ent).findUnique();
    }
    
    public static CivilizationComponent getCiv(String name){
    	return plugin.getDatabase().find(CivilizationComponent.class).where().ieq("name", name).findUnique();
    }
    
    public static CityComponent getCity(Location charterLocation){
    	return plugin.getDatabase().find(CityComponent.class).where().eq("charter_x", charterLocation.getX())
    			.eq("charter_y", charterLocation.getY())
    			.eq("charter_z", charterLocation.getZ()).findUnique();
    }
    
    public static Set<CityComponent> getCities(){
    	return plugin.getDatabase().find(CityComponent.class).findSet();
    }
    
    public static Set<ResidentComponent> getAssistants(CityComponent city){
    	return plugin.getDatabase().find(ResidentComponent.class).where().eq("city", city).eq("cityAssistant", true).findSet();
    }
    
    public static Set<PlotComponent> getPlots(CityComponent city){
    	return plugin.getDatabase().find(PlotComponent.class).where().eq("city", city).findSet();
    }
    
    public static void addCulture(CityComponent city, int culture){
    	city.setCulture(city.getCulture() + culture);
    	plugin.getDatabase().save(city);
    	updateCityCharter(city);
    }
    
    public static void updateCityCharter(CityComponent city){
    	if (city == null){
    		Civilmineation.log("Error. No city at that charter");
    		return;
    	}
    	Block charter = plugin.getServer().getWorld(city.getCharterWorld()).getBlockAt(city.getCharter_x(), city.getCharter_y(), city.getCharter_z());
    	Sign block = (Sign) charter.getRelative(BlockFace.DOWN).getState();
		block.setLine(0, "=Demographics=");
		block.setLine(1, "Population: " + Integer.toString(city.getResidents().size()));
		block.setLine(2, "=Immigration=");
		block.setLine(3, ChatColor.GREEN + "Open");
		block.update();
		if (city.getCharterRotation() == 4 || city.getCharterRotation() == 5){
    		charter.getRelative(BlockFace.EAST).setTypeIdAndData(68, city.getCharterRotation(), true);
			block = (Sign) charter.getRelative(BlockFace.EAST).getState();
			block.setLine(1, "Money: N/A");
			block.setLine(2, "Research: N/A");
			block.update();
			charter.getRelative(BlockFace.WEST).setTypeIdAndData(68, city.getCharterRotation(), true);
			block = (Sign) charter.getRelative(BlockFace.WEST).getState();
			block.setLine(1, "Plots: N/A");
			block.setLine(2, "Culture: " + ChatColor.LIGHT_PURPLE + Integer.toString(city.getCulture()));
			block.update();
		} else if (city.getCharterRotation() == 2 || city.getCharterRotation() == 3) {
			charter.getRelative(BlockFace.NORTH).setTypeIdAndData(68, city.getCharterRotation(), true);
			block = (Sign) charter.getRelative(BlockFace.NORTH).getState();
			block.setLine(1, "Money: N/A");
			block.setLine(2, "Research: N/A");
			block.update();
			charter.getRelative(BlockFace.SOUTH).setTypeIdAndData(68, city.getCharterRotation(), true);
			block = (Sign) charter.getRelative(BlockFace.SOUTH).getState();
			block.setLine(1, "Plots: N/A");
			block.setLine(2, "Culture: " + ChatColor.LIGHT_PURPLE + Integer.toString(city.getCulture()));
			block.update();
		}
    }
    
    public static boolean addResident(ResidentComponent resident, CityComponent city){
    	if (resident.getCity() != null)
    		return false;
    	resident.setCity(city);
    	plugin.getDatabase().save(resident);
    	return true;
    }
    
    public static void broadcastToCity(String message, CityComponent city){
    	List<ResidentComponent> residents = city.getResidents();
    	for (ResidentComponent resident : residents){
    		plugin.getServer().getPlayer(resident.getName()).sendMessage(message);
    	}
    }

	public static void disbandCity(CityComponent city) {
		broadcastToCity("City disbanding", city);
		String name = city.getName();
		for (ResidentComponent resident : city.getResidents()){
			resident.setCity(null);
			resident.setMayor(false);
			resident.setCityAssistant(false);
			resident.setCivAssistant(null);
			plugin.getDatabase().save(resident);
		}
		for (PlotComponent plot : getPlots(city)){
			plot.setCity(null);
			plot.setResident(null);
			plugin.getDatabase().save(plot);
		}
		Ent civEnt = city.getCivilization().getEntityID();
		plugin.getDatabase().delete(city.getCivilization());
		plugin.getDatabase().delete(civEnt);
		Ent cityEnt = city.getEntityID();
		plugin.getDatabase().delete(city);
		plugin.getDatabase().delete(cityEnt);
		plugin.getServer().broadcastMessage(name + " has fallen to dust!"); 
	}
}
