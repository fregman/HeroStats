package de.herobrine.fregman;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;


public class HeroListener implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;

	
	
	HeroListener(Main plugin) {
		
		this.plugin = plugin;
		
		
	}
	
	
	
	@EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {

		Player player = event.getPlayer();
		
		System.out.println("Spieler " + player.getName() + " hat den Server betreten");
		
		
    }
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		player.sendMessage("Block zerstört: " + event.getBlock().getTypeId());
		System.out.println("Block zerstört:" + event.getBlock().getTypeId() + " von " + player.getName());

	}
	

}
