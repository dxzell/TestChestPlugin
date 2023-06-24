package com.dxzell.testchest.GUI.ChestInventories;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.Database.Queries;
import com.dxzell.testchest.GUI.GuiBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BuyChests {

    public static Inventory createBuyChests(Player player) { //inv for the buy menu
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Kisten kaufen");
        for (int i = 45; i <= 53; i++) {
            inv.setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        }
        inv.setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));

        inv.setItem(1, GuiBuilder.buildItemStack(Material.CHEST, ChatColor.GOLD + "1x " + ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "1 " + ChatColor.GRAY + "Kiste für " + ChatColor.GOLD + Config.getApolloPrice() +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getApolloPrice() ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));
        inv.setItem(10, GuiBuilder.buildItemStack(Material.ENDER_CHEST,ChatColor.GOLD + "1x " +  ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "1 " + ChatColor.GRAY + "Kiste für " + ChatColor.GOLD + Config.getPoseidonPrice() +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getPoseidonPrice() ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));
        inv.setItem(19, GuiBuilder.buildItemStack(Material.YELLOW_SHULKER_BOX,ChatColor.GOLD + "1x " +  ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "1 " + ChatColor.GRAY + "Kiste für " + ChatColor.GOLD + Config.getHeroPrice() +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getHeroPrice() ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));

        inv.setItem(3, GuiBuilder.buildItemStack(Material.CHEST,ChatColor.GOLD + "3x " +  ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "3 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getApolloPrice()*3 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getApolloPrice()*3 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));
        inv.setItem(12, GuiBuilder.buildItemStack(Material.ENDER_CHEST,ChatColor.GOLD + "3x " +  ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "3 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getPoseidonPrice()*3 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getPoseidonPrice()*3 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));
        inv.setItem(21, GuiBuilder.buildItemStack(Material.YELLOW_SHULKER_BOX,ChatColor.GOLD + "3x " +  ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "3 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getHeroPrice()*3 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getHeroPrice()*3 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));

        inv.setItem(5, GuiBuilder.buildItemStack(Material.CHEST,ChatColor.GOLD + "6x " +  ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "6 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getApolloPrice()*6 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getApolloPrice()*6 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));
        inv.setItem(14, GuiBuilder.buildItemStack(Material.ENDER_CHEST,ChatColor.GOLD + "6x " +  ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "6 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getPoseidonPrice()*6 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getPoseidonPrice()*6 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));
        inv.setItem(23, GuiBuilder.buildItemStack(Material.YELLOW_SHULKER_BOX,ChatColor.GOLD + "6x " +  ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "6 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getHeroPrice()*6 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getHeroPrice()*6 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));

        inv.setItem(7, GuiBuilder.buildItemStack(Material.CHEST,ChatColor.GOLD + "12x " +  ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "12 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getApolloPrice()*12 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getApolloPrice()*12 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));
        inv.setItem(16, GuiBuilder.buildItemStack(Material.ENDER_CHEST,ChatColor.GOLD + "12x " +  ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "12 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getPoseidonPrice()*12 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getPoseidonPrice()*12 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));
        inv.setItem(25, GuiBuilder.buildItemStack(Material.YELLOW_SHULKER_BOX,ChatColor.GOLD + "12x " +  ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()+ "n"),
                ChatColor.GRAY + "Klicke, um " + ChatColor.YELLOW + "12 " + ChatColor.GRAY + "Kisten für " + ChatColor.GOLD + Config.getHeroPrice()*12 +
                        " Credits°" + ChatColor.GRAY + "zu kaufen°°" + (Queries.getCredits(player.getUniqueId().toString()) >= Config.getHeroPrice()*12 ?
                        ChatColor.GREEN + "Du hast genug " + ChatColor.GOLD + "Credits" : ChatColor.RED + "Du hast nicht genug " + ChatColor.GOLD + "Credits") , false ));


        return inv;
    }

}
