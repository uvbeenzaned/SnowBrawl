package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SBCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length > 0) {
				switch (args[0].toLowerCase()) {
				case "givearmor":
					if(p.isOp()) {
						p.getInventory().addItem(Rank.colorArmor(255, 0, 0));
						p.getInventory().addItem(Rank.colorArmor(255, 74, 0));
						p.getInventory().addItem(Rank.colorArmor(255, 119, 0));
						p.getInventory().addItem(Rank.colorArmor(255, 195, 0));
						p.getInventory().addItem(Rank.colorArmor(255, 255, 0));
						p.getInventory().addItem(Rank.colorArmor(204, 255, 0));
						p.getInventory().addItem(Rank.colorArmor(0, 255, 0));
						p.getInventory().addItem(Rank.colorArmor(0, 255, 255));
						p.getInventory().addItem(Rank.colorArmor(0, 0, 255));
						p.getInventory().addItem(Rank.colorArmor(89, 0, 255));
						p.getInventory().addItem(Rank.colorArmor(255, 0, 255));
						p.getInventory().addItem(Rank.colorArmor(255, 0, 78));
						p.getInventory().addItem(Rank.colorArmor(0, 0, 0));
						p.getInventory().addItem(Rank.colorArmor(96, 96, 96));
						p.getInventory().addItem(Rank.colorArmor(160, 160, 160));
						p.getInventory().addItem(Rank.colorArmor(255, 255, 255));
						return true;
					}
					return false;
				case "config":
					if (p.isOp() || p.hasPermission("SnowBrawl.config")) {
						switch (args[1].toLowerCase()) {
						case "set-lobby-spawn-location":
							Lobby.setLobbyspawnlocation(p.getLocation(), p);
							return true;
						case "set-round-start-delay":
							if (args.length > 2) {
								Settings.setRoundstartdelay(Integer.parseInt(args[2]), p);
								return true;
							}
							return false;
						case "set-team-points":
							if (args.length > 2) {
								Settings.setTeampoints(Integer.parseInt(args[2]), p);
								return true;
							}
							return false;
						case "set-snowball-reload-delay":
							if (args.length > 2) {
								Settings.setSnowballReloadDelay(Integer.parseInt(args[2]), p);
								return true;
							}
							return false;
						}
						Help.printHelp(p);
						Help.printOpCommands(p);
						return true;
					}
					Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
					return true;
				case "stats":
					if (args.length > 1) {
						Stats s = new Stats(args[1], p);
						if (!s.getError()) {
							for (String msg : s.getAllStats()) {
								Chat.sendPPM(msg, p);
							}
							return true;
						}
					}
					for (String msg : Stats.getGlobalStats()) {
						Chat.sendPPM(msg, p);
					}
					return true;
				case "round":
					if (args.length > 1) {
						switch (args[1].toLowerCase()) {
						case "lineup":
							if (Round.getMapLineup().isEmpty()) {
								Round.generateMapLineup();
							}
							Chat.sendPPM("Next arenas coming up: ", p);
							for (int i = 0; i < 5; i++) {
								int read = i;
								read += 1;
								if (Round.getMapLineup().size() >= 5) {
									Chat.sendPM("    " + ChatColor.GOLD + String.valueOf(read) + ChatColor.RESET + ". " + Round.getMapLineup().get(i), p);
								} else {
									for (int j = 0; j < Round.getMapLineup().size(); j++) {
										read = j;
										read += 1;
										Chat.sendPM("    " + ChatColor.GOLD + String.valueOf(read) + ChatColor.RESET + ". " + Round.getMapLineup().get(j), p);
									}
									break;
								}
							}
							Chat.sendPM("    (arena circulation: " + ChatColor.GOLD + String.valueOf(Round.getMapLineup().size()) + ChatColor.RESET + "/" + ChatColor.BLUE + String.valueOf(Configurations.getArenasconfig().getKeys(false).size()) + ChatColor.RESET + ")", p);
							return true;
						}
					}
					return false;
				case "power":
					if (args.length > 1) {
						Stats s = new Stats(p);
						switch (args[1].toLowerCase()) {
						case "list":
							Chat.sendPPM("List of powers:", p);
							String pws = "";
							for (Powers pw : Powers.values()) {
								if (s.ownsPower(pw) || !Store.isEnabled()) {
									pws = pws + ChatColor.GREEN + pw.toString() + ChatColor.RESET + ", ";
								} else {
									pws = pws + ChatColor.DARK_GRAY + pw.toString() + ChatColor.RESET + ", ";
								}
							}
							Chat.sendPM(pws, p);
							return true;
						case "info":
							if (args.length > 2) {
								if (Utilities.getPowersList().contains(Utilities.convertArgsToString(args, 2).toLowerCase())) {
									for (Powers value : Powers.values()) {
										if (value.equalsName(Utilities.convertArgsToString(args, 2))) {
											Power pw = new Power(value, p);
											Chat.sendPPM(pw.getPowerInfo().get(0), p);
											Chat.sendPM(pw.getPowerInfo().get(1), p);
											Chat.sendPM(pw.getPowerInfo().get(2), p);
											Chat.sendPM(pw.getPowerInfo().get(3), p);
											return true;
										}
									}
								}
								return false;
							}
							return false;
						case "set":
							if (args.length > 2) {
								if (Utilities.getPowersList().contains(Utilities.convertArgsToString(args, 2).toLowerCase())) {
									for (Powers pw : Powers.values()) {
										if (pw.equalsName(Utilities.convertArgsToString(args, 2))) {
											if ((s.ownsPower(pw) || pw.equals(Powers.NONE)) || !Store.isEnabled()) {
												s.setPower(pw);
												Chat.sendPPM("Your power has been changed to: " + s.getPower().toString(), p);
												return true;
											}
											Chat.sendPPM("You do not own this power.  Please purchase it to use it! /" + cmd.getName() + " power info " + pw.toString() + ".", p);
											return true;
										}
									}
								}
								Chat.sendPPM("That power does not exist!  Please check /" + cmd.getName() + " power list.", p);
								return true;
							}
							return false;
						}
						return false;
					}
					return false;
				case "arena":
					if (p.isOp() || p.hasPermission("SnowBrawl.arena")) {
						if (args.length > 1) {
							switch (args[1].toLowerCase()) {
							case "list":
								if (args.length == 2) {
									String astring = "";
									int cnt = 0;
									Chat.sendPPM("Arena list:", p);
									for (String name : Arenas.getNameList()) {
										if (!Arena.getInstanceFromConfig(name).getEnabled()) {
											astring = astring + ChatColor.DARK_GRAY + name + ChatColor.RESET + ", ";
											cnt++;
										} else {
											astring = astring + name + ", ";
											cnt++;
										}
									}
									astring = astring + ChatColor.LIGHT_PURPLE + "[" + ChatColor.GOLD + cnt + ChatColor.LIGHT_PURPLE + "]";
									Chat.sendPM(astring, p);
									return true;

								} else if (args.length > 2) {
									Stats s = new Stats(args[2], p);
									if (!s.getError()) {
										if (!s.getArenasList().isEmpty()) {
											String astring = "";
											int cnt = 0;
											Chat.sendPPM("Arenas created/assisted by " + args[2] + ChatColor.RESET + ":", p);
											for (String name : s.getArenasList()) {
												if (!Arena.getInstanceFromConfig(name).getEnabled()) {
													astring = astring + ChatColor.DARK_GRAY + name + ChatColor.RESET + ", ";
													cnt++;
												} else {
													astring = astring + name + ", ";
													cnt++;
												}
											}
											astring = astring + ChatColor.LIGHT_PURPLE + "[" + ChatColor.GOLD + cnt + ChatColor.LIGHT_PURPLE + "]";
											Chat.sendPM(astring, p);
											return true;
										}
										Chat.sendPPM("This player has not created or assisted in any arenas.", p);
										return true;
									}
									return true;
								}
								Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
								return true;
							case "info":
								if (args.length > 2) {
									Arena a = Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2));
									if (a != null && Configurations.getArenasconfig().contains(Utilities.convertArgsToString(args, 2)))
										Chat.sendPPM("Name: " + a.getName(), p);
									Chat.sendPPM("Description: " + a.getDescription(), p);
									Chat.sendPPM("Authors: " + a.getAuthorsString(), p);
									Chat.sendPPM("CYAN" + ChatColor.RESET + " side: " + LocationSerializer.loc2str(a.getCyanSide()), p);
									Chat.sendPPM("LIME" + ChatColor.RESET + " side: " + LocationSerializer.loc2str(a.getLimeSide()), p);
									return true;
								}
								return false;
							case "warp":
								if (args.length > 2) {
									if (Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)) != null && Configurations.getArenasconfig().contains(Utilities.convertArgsToString(args, 2))) {
										p.teleport(Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)).getCyanSide());
										return true;
									}
								}
								return false;
							case "add":
								Arenas.startArenaWizard(p);
								return true;
							case "remove":
								if (args.length > 2) {
									if (Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)) != null && Configurations.getArenasconfig().contains(Utilities.convertArgsToString(args, 2))) {
										if (Round.getMapLineup().contains(Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)).getName())) {
											Round.removeMapFromLineup(Utilities.convertArgsToString(args, 2));
										}
										Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)).delete();
									}
									Chat.sendPPM("Removed arena successfully!", p);
									return true;
								} else {
									Chat.sendPPM("The arena \"" + Utilities.convertArgsToString(args, 2) + "\" does not exist in the arena list!", p);
								}
								return false;
							case "toggle":
								if (args.length > 2) {
									if (Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)) != null && Configurations.getArenasconfig().contains(Utilities.convertArgsToString(args, 2))) {
										Arena atoggle = Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2));
										if (p.isOp() || atoggle.getAuthors().contains(p.getName())) {
											if (atoggle.getEnabled()) {
												atoggle.setEnabled(false);
												atoggle.save();
												if (Round.getMapLineup().contains(atoggle.getName())) {
													Round.removeMapFromLineup(atoggle.getName());
												}
												Chat.sendPPM("Disabled " + Utilities.convertArgsToString(args, 2) + " and removed from current circulation.", p);
												return true;
											} else {
												atoggle.setEnabled(true);
												atoggle.save();
												if (!Round.getMapLineup().contains(atoggle.getName())) {
													Round.addMapToLineup(atoggle.getName());
												}
												Chat.sendPPM("Enabled " + Utilities.convertArgsToString(args, 2) + " for circulation.", p);
												return true;
											}
										}
										Chat.sendPPM("You are not allowed to disable an arena that you did not assist/create!", p);
										return true;
									}
									Chat.sendPPM(Utilities.convertArgsToString(args, 2) + " does not exist in the config!", p);
									return true;
								}
							}
						}
						Help.printHelp(p);
						Help.printOpCommands(p);
						return true;
					}
					Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
					return true;
				case "store":
					if (args.length > 1) {
						switch (args[1].toLowerCase()) {
						case "buy":
							if (args.length > 2) {
								switch (args[2].toLowerCase()) {
								case "power":
									if (args.length >= 3) {
										if (Utilities.getPowersList().contains(Utilities.convertArgsToString(args, 3).toLowerCase())) {
											for (Powers pw : Powers.values()) {
												if (pw.toString().equalsIgnoreCase(Utilities.convertArgsToString(args, 3))) {
													Store.purchasePower(p, p, pw);
													return true;
												}
											}
										}
										Chat.sendPPM("The power " + Utilities.convertArgsToString(args, 3) + " does not exist!", p);
										return true;
									}
									return false;
								}
							}
							return false;
						case "toggle":
							if (p.isOp()) {
								if (Store.isEnabled()) {
									Store.setEnabled(false);
									Settings.setStoreEnabled(false, p);
									return true;
								} else {
									Store.setEnabled(true);
									Settings.setStoreEnabled(true, p);
									return true;
								}
							}
							Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
							return true;
						}
						Help.printHelp(p);
						Help.printOpCommands(p);
						return true;
					}
					Help.printHelp(p);
					Help.printOpCommands(p);
					return true;
				case "help":
					Help.printHelp(p);
					Help.printOpCommands(p);
					return true;
				case "start":
					if (p.isOp() && args.length > 1) {
						Chat.sendPPM("Manually starting map...", p);
						Round.startMap(Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 1)));
					} else {
						Round.startTimerRound();
						Chat.sendPPM("Manually starting timer round...", p);
					}
					return false;
				case "stop":
					if (p.isOp()) {
						Clock.stopTimer();
						Round.setGameActive(false);
						TeamCyan.teleportAllPlayersToLobby();
						TeamLime.teleportAllPlayersToLobby();
						Chat.sendAllTeamsMsg(p.getName() + ChatColor.RESET + " has manually stopped all round progress!");
						return true;
					}
					Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
					return true;
				case "lobby":
					if (!TeamCyan.hasArenaPlayer(p) && !TeamLime.hasArenaPlayer(p))
						p.teleport(Lobby.getLobbyspawnlocation());
					return true;
				case "join":
					if ((args.length > 1)) {
						if (args[1].equalsIgnoreCase("cyan")) {
							TeamCyan.join(p);
							return true;
						}
						if (args[1].equalsIgnoreCase("lime")) {
							TeamLime.join(p);
							return true;
						}
					}
					if (TeamCyan.getPlayers().size() < TeamLime.getPlayers().size()) {
						TeamCyan.join(p);
						return true;
					}
					if (TeamLime.getPlayers().size() < TeamCyan.getPlayers().size()) {
						TeamLime.join(p);
						return true;
					}
					Random r = new Random(System.currentTimeMillis());
					int n = r.nextInt(10);
					if (n <= 5) {
						TeamCyan.join(p);
					}
					if (n >= 5) {
						TeamLime.join(p);
					}
					return true;
				case "leave":
					TeamCyan.leave(p);
					TeamLime.leave(p);
					return true;
				}
			} else {
				Help.printHelp(p);
				Help.printOpCommands(p);
				return true;
			}
		} else {
			sender.sendMessage(Chat.formatString("You have to be a player to run commands here."));
			return true;
		}
		return false;
	}
}
