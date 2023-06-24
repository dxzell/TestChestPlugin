package com.dxzell.testchest.GUI.LootInventories;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.Database.Queries;
import com.dxzell.testchest.GUI.GuiBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeroLoot {

    private static List<Inventory> pages = new ArrayList<>();

    public static void createHeroInv(Player player) throws SQLException { //inv with the possible hero wins

        pages.clear();
        Inventory heroLoot = Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()));
        pages.add(heroLoot);
        int slots = 0; //possible slots for one inventory -> max 44
        int pageAmount = 0; //current page

        ResultSet heroSet = Queries.getHeroLoot();

        for (int i = 45; i <= 53; i++) //set lower row
        {
            heroLoot.setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        }
        heroLoot.setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));


        while (heroSet.next()) //sets the prices
        {
            pages.get(pageAmount).setItem(slots, GuiBuilder.buildItemStack(
                    Material.getMaterial(heroSet.getString("material")),
                    ChatColor.translateAlternateColorCodes('&', heroSet.getString("lootName")),
                    ChatColor.translateAlternateColorCodes('&', heroSet.getString("lootLore")),
                    false)
            );

            slots++;
            if (slots == 45) {
                pages.get(pageAmount).setItem(53, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Nächste Seite", " ", false));
                pageAmount++;
                pages.add(Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle())));
                for (int i = 45; i <= 53; i++) //set lower row
                {
                    pages.get(pageAmount).setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
                }
                pages.get(pageAmount).setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));

                slots = 0;
            }
        }

    }

    public static List<Inventory> getHeroPages(Player player)
    {
        try {
            createHeroInv(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pages;
    }

}
