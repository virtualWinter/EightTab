package me.winter.practice.util.eighttab;

import org.bukkit.entity.Player;

import java.util.List;

public interface TabAdapter {
    /**
     * Get the tab lines for a player.
     *
     * @param player the player
     * @return map
     */
    List<TabEntry> getLines(Player player);
}
