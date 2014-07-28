package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ExtrasListener implements Listener {

	public ExtrasListener(JavaPlugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (TeamCyan.hasArenaPlayer(e.getPlayer()) || TeamLime.hasArenaPlayer(e.getPlayer())) {
				Utilities.reloadSnowballs(e.getPlayer());
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (TeamCyan.hasArenaPlayer(e.getPlayer()) || TeamLime.hasArenaPlayer(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (TeamCyan.hasArenaPlayer(e.getPlayer()) || TeamLime.hasArenaPlayer(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSnowballThrow(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			if (e.getEntityType() == EntityType.SNOWBALL && (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p))) {
				if (!p.getInventory().containsAtLeast(new ItemStack(Material.SNOW_BALL), 1)) {
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
		if (TeamCyan.hasPlayer(e.getWhoClicked().getName()) || TeamLime.hasPlayer(e.getWhoClicked().getName())) {
			if (e.getSlotType() == SlotType.ARMOR) {
				e.setCancelled(true);
			}
			if (e.getCursor().getType() == Material.SNOW_BALL) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void playerDropItem(PlayerDropItemEvent e) {
		if (TeamCyan.hasPlayer(e.getPlayer()) || TeamLime.hasPlayer(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onNewSign(SignChangeEvent e) {
		if (e.getPlayer().isOp()) {
			if (e.getLine(0).equalsIgnoreCase("[sb]")) {
				e.setLine(0, ChatColor.GOLD + "[" + ChatColor.AQUA + "SB" + ChatColor.GOLD + "]");
				e.setLine(1, ChatColor.DARK_RED + "(click here)");
				e.setLine(2, ChatColor.BLUE + "(RANK)");
				e.setLine(3, ChatColor.GOLD + "(H/L)");
			}
		}
	}

	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		if (TeamCyan.hasPlayer(e.getPlayer()) || TeamLime.hasPlayer(e.getPlayer())) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
					Sign s = (org.bukkit.block.Sign) e.getClickedBlock().getState();
					if (s.getLine(0).equalsIgnoreCase(ChatColor.GOLD + "[" + ChatColor.AQUA + "SB" + ChatColor.GOLD + "]")) {
						s.setLine(1, ChatColor.DARK_RED + e.getPlayer().getName());
						s.setLine(2, ChatColor.BLUE + Rank.getRankName(e.getPlayer().getName()));
						s.setLine(3, ChatColor.GOLD + "H/L: " + ChatColor.GREEN + String.valueOf(new Stats(e.getPlayer()).getKDRatio()));
						s.update();
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerScrollEvent(PlayerItemHeldEvent e) {
		if(Arenas.getPlayerScrollList().containsKey(e.getPlayer().getName())) {
			if(e.getPreviousSlot() > Arenas.getPlayerScrollList().get(e.getPlayer().getName())) {
				e.getPlayer().teleport(Arena.getInstanceFromConfig(Arenas.getNameList().get(Arenas.getPlayerScrollList().get(e.getPlayer().getName()))).getCyanSide());
				Arenas.setPlayerScrollList(e.getPlayer(), Arenas.getPlayerScrollList().get(e.getPlayer().getName()) + 1);
			} else if(e.getPreviousSlot() < Arenas.getPlayerScrollList().get(e.getPlayer().getName())) {
				e.getPlayer().teleport(Arena.getInstanceFromConfig(Arenas.getNameList().get(Arenas.getPlayerScrollList().get(e.getPlayer().getName()))).getCyanSide());
				Arenas.setPlayerScrollList(e.getPlayer(), Arenas.getPlayerScrollList().get(e.getPlayer().getName()) - 1);
			}
		}
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (!e.getPlayer().isOp() && (TeamCyan.hasPlayer(e.getPlayer()) || TeamLime.hasPlayer(e.getPlayer()))) {
			if (!e.getMessage().split(" ")[0].replace("/", "").equalsIgnoreCase("sb") && !e.getMessage().split(" ")[0].replace("/", "").equalsIgnoreCase("snowbrawl")) {
				Chat.sendPPM("You may not execute external commands while in game!", e.getPlayer());
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		ChatColor cc = null;
		if (TeamCyan.hasPlayer(e.getPlayer().getName())) {
			cc = ChatColor.AQUA;
			e.setFormat(ChatColor.GOLD + "[" + ChatColor.RESET + ChatColor.BOLD + ChatColor.BLUE + Rank.getRankName(e.getPlayer().getName()) + ChatColor.RESET + ChatColor.GOLD + "]" + ChatColor.RESET + "<" + cc + e.getPlayer().getName() + ChatColor.RESET + "> " + e.getMessage());
//			for(Player p : e.getRecipients()) {
//				if(p.isOp() && p.getName() != e.getPlayer().getName() && (!TeamCyan.hasPlayer(p) || !TeamLime.hasPlayer(p))) {
//					Chat.sendPM(ChatColor.GOLD + "[" + ChatColor.RESET + ChatColor.BOLD + ChatColor.BLUE + Rank.getRankName(e.getPlayer().getName()) + ChatColor.RESET + ChatColor.GOLD + "]" + ChatColor.RESET + "<" + cc + e.getPlayer().getName() + ChatColor.RESET + "> " + e.getMessage(), p);
//				}
//			}
			e.setCancelled(true);
		}
		if (TeamLime.hasPlayer(e.getPlayer().getName())) {
			cc = ChatColor.GREEN;
			e.setFormat(ChatColor.GOLD + "[" + ChatColor.RESET + ChatColor.BOLD + ChatColor.BLUE + Rank.getRankName(e.getPlayer().getName()) + ChatColor.RESET + ChatColor.GOLD + "]" + ChatColor.RESET + "<" + cc + e.getPlayer().getName() + ChatColor.RESET + "> " + e.getMessage());
//			for(Player p : e.getRecipients()) {
//				if(p.isOp() && p.getName() != e.getPlayer().getName() && (!TeamCyan.hasPlayer(p) || !TeamLime.hasPlayer(p))) {
//					Chat.sendPM(ChatColor.GOLD + "[" + ChatColor.RESET + ChatColor.BOLD + ChatColor.BLUE + Rank.getRankName(e.getPlayer().getName()) + ChatColor.RESET + ChatColor.GOLD + "]" + ChatColor.RESET + "<" + cc + e.getPlayer().getName() + ChatColor.RESET + "> " + e.getMessage(), p);
//				}
//			}
		}
	}

}
