package com.dxzell.testchest.GUI.OpenChest;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.Database.DatabaseConnection;
import com.dxzell.testchest.Database.Queries;
import com.dxzell.testchest.GUI.GuiBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.util.Random;

public class OpenChestGui {

    public static Inventory createOpenChestGui(Player player, String chestType) { //open chest gui
        Inventory openChestInv = Bukkit.createInventory(player, 27, ChatColor.GOLD + "Gewinne einen Preis");
        for (int i = 0; i < 27; i++) {
            openChestInv.setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, "", "", false));
        }
        openChestInv.setItem(4, GuiBuilder.buildItemStack(Material.HOPPER, ChatColor.GREEN + "Gewinn", "", false));
        openChestInv.setItem(13, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Öffne " + (chestType.equalsIgnoreCase("apollo")
                ? ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()) : (chestType.equalsIgnoreCase("poseidon")
                ? ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()) : ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()))), "", false));


        return openChestInv;
    }


    public static void rollPrice(Player player, Inventory inv, String chestType) //picks the price
    {
        int num = (new Random()).nextInt(100) + 1; //1 - 100 random
        for (RollItem rollitem : Queries.getRollItems(chestType)) {
            if (rollitem.getDropChance() - num >= 0) //won this item
            {
                for (int i = 0; i < 27; i++) {
                    inv.setItem(i, GuiBuilder.buildItemStack(Material.GREEN_STAINED_GLASS_PANE, "", "", false));
                }
                inv.setItem(4, GuiBuilder.buildItemStack(Material.HOPPER, ChatColor.GREEN + "Gewinn", "", false));
                inv.setItem(13, Queries.getRollItemById(rollitem.getId(), chestType));

                Queries.addWin(chestType, rollitem.getId(), player.getUniqueId());
                Queries.runCommand(rollitem.getId(), chestType, player);

                return; //found
            }
            num -= rollitem.getDropChance();
        }
        //won no item

        for (int i = 0; i < 27; i++) {
            inv.setItem(i, GuiBuilder.buildItemStack(Material.RED_STAINED_GLASS_PANE, "", "", false));
        }
        inv.setItem(4, GuiBuilder.buildItemStack(Material.HOPPER, ChatColor.GREEN + "Gewinn", "", false));
        inv.setItem(13, GuiBuilder.buildItemStack(Material.BARRIER, ChatColor.RED + "Kein Gewinn", " ", false));
    }
}
