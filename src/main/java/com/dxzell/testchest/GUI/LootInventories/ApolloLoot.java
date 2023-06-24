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

public class ApolloLoot {

    private static List<Inventory> pages = new ArrayList<>();

    public static void createApolloInv(Player player) throws SQLException { //inv with the possible apollo wins

        pages.clear();
        Inventory apolloLoot = Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()));
        pages.add(apolloLoot);
        int slots = 0; //possible slots for one inventory -> max 44
        int pageAmount = 0; //current page

        ResultSet apolloSet = Queries.getApolloLoot();

        for (int i = 45; i <= 53; i++) //set lower row
        {
            apolloLoot.setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        }
        apolloLoot.setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));


        while (apolloSet.next()) //sets the prices
        {
            pages.get(pageAmount).setItem(slots, GuiBuilder.buildItemStack(
                    Material.getMaterial(apolloSet.getString("material")),
                    ChatColor.translateAlternateColorCodes('&', apolloSet.getString("lootName")),
                    ChatColor.translateAlternateColorCodes('&', apolloSet.getString("lootLore")),
                    false)
            );

            slots++;
            if (slots == 45) {

                pages.get(pageAmount).setItem(53, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Nächste Seite", " ", false));
                pageAmount++;
                pages.add(Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle())));
                for (int i = 45; i <= 53; i++) //set lower row
                {
                    pages.get(pageAmount).setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
                }
                pages.get(pageAmount).setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));
                slots = 0;
            }
        }

    }

    public static List<Inventory> getApolloPages(Player player){
        try {
            createApolloInv(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pages;
    }
}


