package net.croxis.plugins.civilminenation;

import java.awt.Color;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignInteractListener implements Listener{
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent event){
		//Left click is click, Right click is cycle
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			PlotComponent plot = CivAPI.getPlot(event.getClickedBlock().getChunk());
			if (plot == null)
				return;
			if (plot.getCity() == null)
				return;
			CityComponent city = CivAPI.plugin.getDatabase().find(CityComponent.class).where()
					.eq("charter_x", event.getClickedBlock().getX())
					.eq("charter_y", event.getClickedBlock().getY()+1)
					.eq("charter_z", event.getClickedBlock().getZ()).findUnique();
			if (plot.getCity() == city){
				Sign sign = (Sign) event.getClickedBlock().getState();
				if (sign.getLine(3).contains("Open") && sign.getLine(0).contains("=Demographics=")){
					ResidentComponent resident = CivAPI.getResident(event.getPlayer());
					if (CivAPI.addResident(resident, city))
						CivAPI.broadcastToCity("Welcome " + resident.getName() + " to our city!", city);
				}
			}
			
			
		} else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if (event.getClickedBlock().getType().equals(Material.WALL_SIGN)){
				PlotComponent plot = CivAPI.getPlot(event.getClickedBlock().getChunk());
				ResidentComponent resident = CivAPI.getResident(event.getPlayer());
				if (plot == null)
					return;
				if (plot.getCity() == null)
					return;
				if (plot.getCity() != resident.getCity() && resident.getCity().getMayor() != resident)
					return;
				Sign sign = (Sign) event.getClickedBlock().getState();
				if (sign.getLine(3).contains("Open") && sign.getLine(0).contains("=Demographics=")){
					sign.setLine(3, Color.red + "Closed");
				} else {
					sign.setLine(3, Color.green + "Open");
				}
			}
		}
		
	}

}
