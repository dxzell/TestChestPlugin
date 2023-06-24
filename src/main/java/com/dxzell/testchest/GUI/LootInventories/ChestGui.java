package com.dxzell.testchest.GUI.LootInventories;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.Database.DatabaseConnection;
import com.dxzell.testchest.Database.Queries;
import com.dxzell.testchest.GUI.GuiBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ChestGui {

    public static void openChestGUI(Player player) {
        if (DatabaseConnection.isConnected()) {
            player.openInventory(createChestGUI(player));
        } else {
            player.sendMessage(ChatColor.RED + "Keine Datenbankverbindung!");
        }
    }

    public static Inventory createChestGUI(Player player) { //the first chest gui
        Inventory chestGUI = Bukkit.createInventory(player, 36, ChatColor.translateAlternateColorCodes('&', Config.getChestGUITitle()));
        chestGUI.setItem(12, GuiBuilder.buildItemStack(Material.CHEST, ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()),
                ChatColor.GRAY + ">> Du hast " + ChatColor.AQUA + Queries.getApolloAmount(player.getUniqueId().toString()) + "x " + ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle() + (Queries.getApolloAmount(player.getUniqueId().toString()) != 1 ? "n" : "")) + "°°" +
                        ChatColor.AQUA + "Linksclick" + ChatColor.GRAY + ", um deine Kisten zu sehen°" + ChatColor.RED + "Rechtsclick" + ChatColor.GRAY + ", um den Kisteninhalt zu betrachten", false));
        chestGUI.setItem(13, GuiBuilder.buildItemStack(Material.ENDER_CHEST, ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()),
                ChatColor.GRAY + ">> Du hast " + ChatColor.AQUA + Queries.getPoseidonAmount(player.getUniqueId().toString()) + "x " + ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle() + (Queries.getPoseidonAmount(player.getUniqueId().toString()) != 1 ? "n" : "")) + "°°" +
                        ChatColor.AQUA + "Linksclick" + ChatColor.GRAY + ", um deine Kisten zu sehen°" + ChatColor.RED + "Rechtsclick" + ChatColor.GRAY + ", um den Kisteninhalt zu betrachten", false));
        chestGUI.setItem(14, GuiBuilder.buildItemStack(Material.YELLOW_SHULKER_BOX, ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()),
                ChatColor.GRAY + ">> Du hast " + ChatColor.AQUA + Queries.getHeroAmount(player.getUniqueId().toString()) + "x " + ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle() + (Queries.getHeroAmount(player.getUniqueId().toString()) != 1 ? "n" : "")) + "°°" +
                        ChatColor.AQUA + "Linksclick" + ChatColor.GRAY + ", um deine Kisten zu sehen°" + ChatColor.RED + "Rechtsclick" + ChatColor.GRAY + ", um den Kisteninhalt zu betrachten", false));
        chestGUI.setItem(30, GuiBuilder.buildItemStack(Material.CHEST, ChatColor.RED + "Alle Kisten", ChatColor.GRAY + "Klicke hier, um all deine Kisten anzuzeigen", false));
        chestGUI.setItem(31, GuiBuilder.buildItemStack(Material.GOLD_INGOT, ChatColor.YELLOW + "Du hast " + ChatColor.GREEN + Queries.getCredits(player.getUniqueId().toString()) + " Credit" + (Queries.getCredits(player.getUniqueId().toString()) != 1 ? "s" : ""),
                ChatColor.GRAY + "Auf der Seite " + ChatColor.YELLOW + "shop.iostein.net " + ChatColor.GRAY + "kannst du Credits kaufen", false));
        chestGUI.setItem(32, GuiBuilder.buildItemStack(Material.NETHER_STAR, ChatColor.BOLD + "" + ChatColor.GOLD + "Kisten kaufen", ChatColor.GRAY + "Klicke hier, um Kisten zu kaufen", false));
        chestGUI.setItem(27, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        chestGUI.setItem(28, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        chestGUI.setItem(29, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        chestGUI.setItem(33, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        chestGUI.setItem(34, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        chestGUI.setItem(35, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        return chestGUI;
    }
}
