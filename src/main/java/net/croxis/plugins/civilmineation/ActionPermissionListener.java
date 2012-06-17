package net.croxis.plugins.civilmineation;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ActionPermissionListener implements Listener{
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if (event.isCancelled())
			return;
		
		Player player = event.getPlayer();
		//Block block = event.getBlock();
		Chunk chunk = event.getBlock().getChunk();
		
		PlotComponent plot = CivAPI.getPlot(chunk);
		if (plot == null)
			return;
		if (plot.getCity() == null)
			return;
		// Determine player's relationship to chunk
		 CityComponent city = plot.getCity();
		 //CivilizationComponent civ = city.getCivilization();
		 
		 PermissionComponent cityPerm = CivAPI.getPermissions(city.getEntityID());
		 
		 ResidentComponent resident = CivAPI.getResident(player);		 
		 if (resident.getCity() == null){
			 Civilmineation.log("City is null");
			 if (!cityPerm.isOutsiderBuild()){
				 event.setCancelled(true);
				 Civilmineation.log("No outsider build");
				 return;
			 }
		 } else if (resident.getCity().getName().equalsIgnoreCase(city.getName())){
			 if (!cityPerm.isResidentBuild()){
				 event.setCancelled(true);
				 Civilmineation.log("No res build");
				 return;
			 }
		 } else {
			 if (!cityPerm.isOutsiderBuild()){
				 event.setCancelled(true);
				 Civilmineation.log("No outsider build 2");
				 return;
			 }
		 }
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if (event.isCancelled())
			return;
		
		Player player = event.getPlayer();
		//Block block = event.getBlock();
		Chunk chunk = event.getBlock().getChunk();
		
		PlotComponent plot = CivAPI.getPlot(chunk);
		if (plot == null)
			return;
		if (plot.getCity() == null)
			return;
		// Determine player's relationship to chunk
		 CityComponent city = plot.getCity();
		 //CivilizationComponent civ = city.getCivilization();
		 
		 PermissionComponent cityPerm = CivAPI.getPermissions(city.getEntityID());
		 
		 ResidentComponent resident = CivAPI.getResident(player);
		 
		 if (resident.getCity() == null){
			 if (!cityPerm.isOutsiderDestroy()){
				 event.setCancelled(true);
				 System.out.println("a");
				 return;
			 }
		 } else if (resident.getCity().getName().equalsIgnoreCase(city.getName())){
			 if (!cityPerm.isResidentDestroy()){
				 event.setCancelled(true);
				 System.out.println("b");
				 return;
			 }
			 if (event.getBlock().getType().equals(Material.WALL_SIGN)){
				 Sign sign = (Sign) event.getBlock();
				 if (sign.getLine(0).contains("Charter") && resident.isMayor())
					 CivAPI.disbandCity(resident.getCity());
			 }
		 } else {
			 if (!cityPerm.isOutsiderDestroy()){
				 event.setCancelled(true);
				 System.out.println("c");
				 return;
			 }
		 }
		 
		 
	}

}
