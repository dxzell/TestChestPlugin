package com.dxzell.testchest.AddLoot;

import com.dxzell.testchest.Database.DatabaseConnection;
import com.dxzell.testchest.Database.Queries;
import com.dxzell.testchest.GUI.GuiBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class addLootCommand implements CommandExecutor, Listener {

    private static HashMap<UUID, LootInformation> eachLootinformation = new HashMap<>(); //saves LootInformation for each player running the command

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { //allows players to add loot for all cests
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("add")) { //add <ChestType> <Material> <LootName> <DropChance> <Command>
                if (player.hasPermission("chest.admin")) {
                    if (args.length == 5) {
                        //ChestType -> apollo, poseidon, hero
                        if (args[0].equalsIgnoreCase("apollo") || args[0].equalsIgnoreCase("poseidon") || args[0].equalsIgnoreCase("hero")) {
                            if (Material.getMaterial(args[1]) != null) {

                                try {
                                    //dropChance -> 1-100
                                    int rate = Integer.parseInt(args[3]);
                                    if (rate > 0 && rate <= Queries.getLeftPercentage((args[0].equalsIgnoreCase("apollo") ? "apolloLoot" : (args[0].equalsIgnoreCase("poseidon") ? "poseidonLoot" : "heroLoot")))) {

                                        if (DatabaseConnection.isConnected()) {
                                            eachLootinformation.put(player.getUniqueId(), new LootInformation(args[0], args[1], args[2], rate, args[4]));
                                            player.sendMessage(ChatColor.GRAY + "Schreibe nun die gewünschte Lore für dieses Item in den Chat. Nutze ColorCodes mit " + ChatColor.GOLD + "&" + ChatColor.GRAY + ".");
                                        } else {
                                            player.sendMessage(ChatColor.RED + "Keine Datenbankverbindung!");
                                        }

                                    } else {
                                        if (Queries.getLeftPercentage((args[0].equalsIgnoreCase("apollo") ? "apolloLoot" : (args[0].equalsIgnoreCase("poseidon") ? "poseidonLoot" : "heroLoot"))) == 0) {//no more slots due to no more available dropchance
                                            player.sendMessage(ChatColor.RED + "Keine neuen Drops möglich, da alle Wahrscheinlichkeiten aufgebraucht sind.");
                                        } else {
                                            player.sendMessage(ChatColor.RED + "Keine gültige Wahrscheinlichkeit! Möglich sind Eingaben von 1 bis " + Queries.getLeftPercentage((args[0].equalsIgnoreCase("apollo") ? "apolloLoot" : (args[0].equalsIgnoreCase("poseidon") ? "poseidonLoot" : "heroLoot"))) + ". Nutze: " + ChatColor.DARK_RED + "/add <KistenTyp> <Material> <LootName> <DropChance> <Command>");
                                        }
                                    }

                                } catch (Exception e) {
                                    player.sendMessage(ChatColor.RED + "Keine gültige Wahrscheinlichkeit! Möglich sind Eingaben von 1 bis " + Queries.getLeftPercentage((args[0].equalsIgnoreCase("apollo") ? "apolloLoot" : (args[0].equalsIgnoreCase("poseidon") ? "poseidonLoot" : "heroLoot"))) + ". Nutze: " + ChatColor.DARK_RED + "/add <KistenTyp> <Material> <LootName> <DropChance> <Command>");
                                }

                            } else {
                                player.sendMessage(ChatColor.RED + "Kein gültiges Material! Nutze: " + ChatColor.DARK_RED + "/add <KistenTyp> <Material> <LootName> <DropChance> <Command>");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Kein gültiger Kisten-Typ! Wähle entweder Apollo, Poseidon oder Hero. Nutze: " + ChatColor.DARK_RED + "/add <KistenTyp> <Material> <LootName> <DropChance> <Command>");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Falscher Command! Nutze: " + ChatColor.DARK_RED + "/add <KistenTyp> <Material> <LootName> <DropChance> <Command>");
                    }
                }
            }
        }


        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        //sets lore for the loot item
        if (eachLootinformation.containsKey(e.getPlayer().getUniqueId())) {
            eachLootinformation.get(e.getPlayer().getUniqueId()).setLore(GuiBuilder.cutString(e.getMessage()));
            e.getPlayer().sendMessage(ChatColor.GRAY + "Das Item wurde erfolgreich zum ApolloLoot hinzugefügt!");
            Queries.addItem(eachLootinformation.get(e.getPlayer().getUniqueId()));
            eachLootinformation.remove(e.getPlayer().getUniqueId());
            e.setCancelled(true);
        }
    }

}
