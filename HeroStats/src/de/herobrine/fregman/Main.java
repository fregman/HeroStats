package de.herobrine.fregman;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	
	public void onDisable() {
		
		
	}
	
	public void onEnable() {
		
		final MySQL sql = new MySQL();




		

		
		
		getServer().getPluginManager().registerEvents(new HeroListener(this, sql), this);
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                  sql.setTimestamp();
            }
}, 0L, 200L);
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            sql.checkList(getServer().getOnlinePlayers()); 
            }
}, 0L, 600L);

		
	}
	
	

}
