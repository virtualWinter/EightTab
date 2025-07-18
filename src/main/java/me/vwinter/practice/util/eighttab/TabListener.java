package me.winter.practice.util.eighttab;

import lombok.AllArgsConstructor;
import me.winter.practice.Practice;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class TabListener implements Listener {

	private EightTab instance;

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		TabLayout layout = (new TabLayout(instance, player));
		boolean validate = TabLayout.getLayoutMapping().containsKey(player.getUniqueId());

        if(TabLayout.getLayoutMapping().get(player.getUniqueId()) != null) {
			validate = true;
		}
		
		if(!validate) {
			Bukkit.getScheduler().runTaskAsynchronously(Practice.getInstance(), layout::create);
		}

		TabLayout.getLayoutMapping().put(player.getUniqueId(), layout);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		instance.removePlayer(player);
	}

	@EventHandler
	public void onKick(PlayerKickEvent event) {
		Player player = event.getPlayer();
		instance.removePlayer(player);
	}
}
