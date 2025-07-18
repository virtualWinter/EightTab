package me.winter.practice.util.eighttab;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class EightTab {

	@Getter
	private static EightTab instance;
	
	private final TabAdapter adapter;
	
	public EightTab(JavaPlugin plugin, TabAdapter adapter) {
		instance = this;
		this.adapter = adapter;
		
		new TabPacket(plugin);
				
		Bukkit.getServer().getPluginManager().registerEvents(new TabListener(this), plugin);
		Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new TabRunnable(adapter), 20L, 20L);
	}
	
	public void onDisable() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			removePlayer(player);
		}
	}
	
	public void removePlayer(Player player) {
		boolean continueAt = TabLayout.getLayoutMapping().containsKey(player.getUniqueId());

        if (continueAt) {
			TabLayout.getLayoutMapping().remove(player.getUniqueId());
		}
	}
}