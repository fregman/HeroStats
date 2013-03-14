package de.herobrine.fregman;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class HeroListener implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;
	MySQL sql;

	
	
	HeroListener(Main plugin, MySQL sql) {
		
		this.plugin = plugin;
		this.sql = sql;
		
		
	}


	@EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {

		Player player = event.getPlayer();
		String playerName = player.getName();
		
		System.out.println("Spieler " + playerName + " hat den Server betreten");
		boolean bla = sql.checkName(playerName);
		if (bla){
			
			sql.updatePlayer(playerName, 1);
			
		} else {
			
			sql.insertPlayer(playerName, 1);
			
		}
		
		
    }
	
	
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

		Player player = event.getPlayer();
		String playerName = player.getName();
		
		System.out.println("Spieler " + playerName + " hat den Server verlassen");
		boolean bla = sql.checkName(playerName);
		if (bla){
			
			sql.updatePlayer(playerName, 0);
			
		} else {
			
			sql.insertPlayer(playerName, 0);
			
		}
		
		
    }

	

}
