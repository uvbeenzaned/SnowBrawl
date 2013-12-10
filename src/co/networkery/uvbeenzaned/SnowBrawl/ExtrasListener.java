package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ExtrasListener implements Listener {

	public ExtrasListener(JavaPlugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_AIR
				|| e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (TeamCyan.hasPlayer(e.getPlayer())
					|| TeamLime.hasPlayer(e.getPlayer())) {
				Utilities.reloadSnowballs(e.getPlayer());
			}
		}
	}

	@EventHandler
	public void onSnowballThrow(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			if (e.getEntityType() == EntityType.SNOWBALL
					&& (TeamCyan.hasPlayer(p) || TeamLime.hasPlayer(p))) {
				if (!p.getInventory().containsAtLeast(
						new ItemStack(Material.SNOW_BALL), 1)) {
					Utilities.reloadSnowballs(p);
				}
				Stats s = new Stats(p);
				s.addSnowballsThrown(1);
				if (p.isOp()) {
					e.getEntity().setFireTicks(600);
				}
			}
		}
	}

	@EventHandler
	public void playerInvClick(InventoryClickEvent e) {
		if (TeamCyan.hasPlayer(e.getWhoClicked().getName())
				|| TeamLime.hasPlayer(e.getWhoClicked().getName())) {
			if (e.getSlotType() == SlotType.ARMOR) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		ChatColor cc = null;
		if (TeamCyan.hasPlayer(e.getPlayer().getName())) {
			cc = ChatColor.AQUA;
			e.setFormat(ChatColor.GOLD + "[" + ChatColor.RESET + ChatColor.BOLD
					+ ChatColor.BLUE
					+ Rank.getRankName(e.getPlayer().getName())
					+ ChatColor.RESET + ChatColor.GOLD + "]" + ChatColor.RESET
					+ "<" + cc + e.getPlayer().getName() + ChatColor.RESET
					+ "> " + e.getMessage());
		}
		if (TeamLime.hasPlayer(e.getPlayer().getName())) {
			cc = ChatColor.GREEN;
			e.setFormat(ChatColor.GOLD + "[" + ChatColor.RESET + ChatColor.BOLD
					+ ChatColor.BLUE
					+ Rank.getRankName(e.getPlayer().getName())
					+ ChatColor.RESET + ChatColor.GOLD + "]" + ChatColor.RESET
					+ "<" + cc + e.getPlayer().getName() + ChatColor.RESET
					+ "> " + e.getMessage());
		}
	}

}
