package net.croxis.plugins.civilmineation;

import net.croxis.plugins.civilmineation.components.CityComponent;
import net.croxis.plugins.civilmineation.components.PermissionComponent;
import net.croxis.plugins.civilmineation.components.PlotComponent;
import net.croxis.plugins.civilmineation.components.ResidentComponent;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ActionPermissionListener implements Listener{
	private long time;

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if (event.isCancelled())
			return;
		time = System.currentTimeMillis();
		if (event.getPlayer().hasPermission("civilmineation.admin"))
			return;
		//Block block = event.getBlock();
		
		PlotComponent plot = CivAPI.getPlot(event.getBlock().getChunk());
		if (plot == null)
			return;
		if (plot.getCity() == null)
			return;
		
		// Determine player's relationship to chunk
		 CityComponent city = plot.getCity();
		 //CivilizationComponent civ = city.getCivilization();
		 
		 PermissionComponent cityPerm = CivAPI.getPermissions(city.getEntityID());		 
		 ResidentComponent resident = CivAPI.getResident(event.getPlayer());
		 
		 // TODO: apply plot specific permissions
		 if (plot.getResident() == null){
			 if (resident.getCity() == null){
				 Civilmineation.logDebug("City is null");
				 if (!cityPerm.isOutsiderBuild()){
					 event.setCancelled(true);
					 Civilmineation.logDebug("No outsider build");
					 return;
				 }
			 } else if (resident.getCity().getName().equalsIgnoreCase(city.getName())){
				 if (!cityPerm.isResidentBuild()){
					 event.setCancelled(true);
					 Civilmineation.logDebug("No res build");
					 return;
				 }
			 } else {
				 if (!cityPerm.isOutsiderBuild()){
					 event.setCancelled(true);
					 Civilmineation.logDebug("No outsider build 2");
					 return;
				 }
			 }
		 } else {
			 if (resident.getName().equalsIgnoreCase(plot.getResident().getName()))
					return;
			 PermissionComponent perm = CivAPI.getPermissions(plot.getResident().getEntityID());
			 if (resident.getCity() == null){
				 Civilmineation.logDebug("City is null");
				 if (!perm.isOutsiderBuild()){
					 event.setCancelled(true);
					 Civilmineation.logDebug("No outsider build");
					 return;
				 }
			 } else if (resident.getCity().getName().equalsIgnoreCase(city.getName())){
				//TODO: Friends list here
				 if (!perm.isResidentBuild()){
					 event.setCancelled(true);
					 Civilmineation.logDebug("No res build");
					 return;
				 }
			 } else {
				 if (!perm.isOutsiderBuild()){
					 event.setCancelled(true);
					 Civilmineation.logDebug("No outsider build 2");
					 return;
				 }
			 }
		 }
		 if (event.getPlayer().getName().equalsIgnoreCase("croxis"))
			 Civilmineation.logDebug("Block place event: " + Long.toString(System.currentTimeMillis() - time) + " ms");
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if (event.isCancelled())
			return;
		if (event.getPlayer().hasPermission("civilmineation.admin"))
			return;
		time = System.currentTimeMillis();
		//Block block = event.getBlock();
		
		PlotComponent plot = CivAPI.getPlot(event.getBlock().getChunk());
		if (plot == null)
			return;
		if (plot.getCity() == null)
			return;
		// Determine player's relationship to chunk
		CityComponent city = plot.getCity();
		//CivilizationComponent civ = city.getCivilization();
		 
		PermissionComponent cityPerm = CivAPI.getPermissions(city.getEntityID());
		 
		ResidentComponent resident = CivAPI.getResident(event.getPlayer());
		 
		// Prevent destruction of charter info blocks and blocks behind charter
		if (event.getBlock().getX() == city.getCharter_x() && 
				event.getBlock().getY() + 1 == city.getCharter_y() &&
				event.getBlock().getZ() == city.getCharter_z()){
			event.setCancelled(true);
			return;
		}
		if (city.getCharterRotation() == 2){
			// Block behind charter
			if (event.getBlock().getX() == city.getCharter_x() && 
					event.getBlock().getY() == city.getCharter_y() &&
					event.getBlock().getZ() - 1 == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
			// Signs left and right
			if ((event.getBlock().getX() + 1== city.getCharter_x() || event.getBlock().getX() - 1== city.getCharter_x()) && 
					event.getBlock().getY() == city.getCharter_y() &&
					event.getBlock().getZ() == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
			// Blocks bedhind left and right
			if ((event.getBlock().getX() + 1== city.getCharter_x() || event.getBlock().getX() - 1== city.getCharter_x()) && 
					event.getBlock().getY() == city.getCharter_y() &&
					event.getBlock().getZ() - 1 == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
			//Block behind demographics
			if (event.getBlock().getX() == city.getCharter_x() && 
					event.getBlock().getY() + 1 == city.getCharter_y() &&
					event.getBlock().getZ() - 1 == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
		} else if (city.getCharterRotation() == 3) {
			// Block behind charter
			if (event.getBlock().getX() == city.getCharter_x() && 
					event.getBlock().getY() == city.getCharter_y() &&
					event.getBlock().getZ() + 1 == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
			// Signs left and right
			if ((event.getBlock().getX() + 1== city.getCharter_x() || event.getBlock().getX() - 1== city.getCharter_x()) && 
					event.getBlock().getY() == city.getCharter_y() &&
					event.getBlock().getZ() == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
			// Blocks bedhind left and right
			if ((event.getBlock().getX() + 1== city.getCharter_x() || event.getBlock().getX() - 1== city.getCharter_x()) && 
					event.getBlock().getY() == city.getCharter_y() &&
					event.getBlock().getZ() + 1 == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
			//Block behind demographics
			if (event.getBlock().getX() == city.getCharter_x() && 
					event.getBlock().getY() + 1 == city.getCharter_y() &&
					event.getBlock().getZ() + 1 == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
		} else if (city.getCharterRotation() == 4) {
			// Block behind charter
			if (event.getBlock().getX() + 1== city.getCharter_x() && 
					event.getBlock().getY() == city.getCharter_y() &&
					event.getBlock().getZ() == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
			// Signs left and right
			if (event.getBlock().getX() == city.getCharter_x() && 
					event.getBlock().getY() == city.getCharter_y() &&
					(event.getBlock().getZ() - 1 == city.getCharter_z() || event.getBlock().getZ() + 1 == city.getCharter_z())){
				event.setCancelled(true);
				return;
			}
			// Blocks bedhind left and right
			if (event.getBlock().getX() + 1 == city.getCharter_x() && 
					event.getBlock().getY() == city.getCharter_y() &&
					(event.getBlock().getZ() - 1 == city.getCharter_z() || event.getBlock().getZ() + 1 == city.getCharter_z())){
				event.setCancelled(true);
				return;
			}
			//Block behind demographics
			if (event.getBlock().getX() + 1 == city.getCharter_x() && 
					event.getBlock().getY() + 1 == city.getCharter_y() &&
					event.getBlock().getZ() == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
		} else if (city.getCharterRotation() == 5) {
			// Block behind charter
			if (event.getBlock().getX() - 1== city.getCharter_x() && 
					event.getBlock().getY() == city.getCharter_y() &&
					event.getBlock().getZ() == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
			// Signs left and right
			if (event.getBlock().getX() == city.getCharter_x() && 
					event.getBlock().getY() == city.getCharter_y() &&
					(event.getBlock().getZ() - 1 == city.getCharter_z() || event.getBlock().getZ() + 1 == city.getCharter_z())){
				event.setCancelled(true);
				return;
			}
			// Blocks bedhind left and right
			if (event.getBlock().getX() - 1 == city.getCharter_x() && 
					event.getBlock().getY() == city.getCharter_y() &&
					(event.getBlock().getZ() - 1 == city.getCharter_z() || event.getBlock().getZ() + 1 == city.getCharter_z())){
				event.setCancelled(true);
				return;
			}
			//Block behind demographics
			if (event.getBlock().getX() - 1 == city.getCharter_x() && 
					event.getBlock().getY() + 1 == city.getCharter_y() &&
					event.getBlock().getZ() == city.getCharter_z()){
				event.setCancelled(true);
				return;
			}
		}
			
		// Prevent destruction of charter except for mayor
		if (event.getBlock().getX() == city.getCharter_x() && 
				event.getBlock().getY() == city.getCharter_y() &&
				event.getBlock().getZ() == city.getCharter_z() && !resident.isMayor()){
			event.setCancelled(true);
			return;
		} else if (event.getBlock().getX() == city.getCharter_x() && 
				event.getBlock().getY() == city.getCharter_y() &&
				event.getBlock().getZ() == city.getCharter_z() && resident.isMayor()){
			Civilmineation.logDebug("Disbanding city");
			CivAPI.disbandCity(resident.getCity());
		}
		
		if (plot.getResident() == null){
			if (resident.getCity() == null){
				 if (!cityPerm.isOutsiderDestroy()){
					 event.setCancelled(true);
					 return;
				 }
			 } else if (resident.getCity().getName().equalsIgnoreCase(city.getName())){
				 if (!cityPerm.isResidentDestroy()){
					 event.setCancelled(true);
					 return;
				 }
			 } else {
				 if (!cityPerm.isOutsiderDestroy()){
					 event.setCancelled(true);
					 return;
				 }
			 }
		} else {
			PermissionComponent perm = CivAPI.getPermissions(plot.getResident().getEntityID());
			if (resident.getName().equalsIgnoreCase(plot.getResident().getName()))
				return;
			if (resident.getCity() == null){
				if (!perm.isOutsiderDestroy()){
					event.setCancelled(true);
					return;
				}
			} else if (resident.getCity().getName().equalsIgnoreCase(city.getName())){
				//TODO: Friends list here
				 if (!perm.isResidentDestroy()){
					 event.setCancelled(true);
					 return;
				 }
			} else {
				 if (!perm.isOutsiderDestroy()){
					 event.setCancelled(true);
					 return;
				 }
			}
		}
		 if (event.getPlayer().getName().equalsIgnoreCase("croxis"))
			 Civilmineation.logDebug("Block break event: " + Long.toString(System.currentTimeMillis() - time) + " ms");
	}

}
