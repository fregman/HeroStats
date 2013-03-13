package de.herobrine.fregman;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	
	public void onDisable() {
		
		
	}
	
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(new HeroListener(this), this);
		
		try {
			SQLite sq = new SQLite();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	

}
