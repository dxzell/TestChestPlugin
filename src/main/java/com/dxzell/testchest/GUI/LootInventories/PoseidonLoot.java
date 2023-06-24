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

public class PoseidonLoot {

    private static List<Inventory> pages = new ArrayList<>();

    public static void createPoseidonInv(Player player) throws SQLException { //inv with the possible poseidon wins

        pages.clear();
        Inventory poseidonLoot = Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()));
        pages.add(poseidonLoot);
        int slots = 0; //possible slots for one inventory -> max 44
        int pageAmount = 0; //current page

        ResultSet poseidonSet = Queries.getPoseidonLoot();

        for (int i = 45; i <= 53; i++) //set lower row
        {
            poseidonLoot.setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        }
        poseidonLoot.setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));


        while (poseidonSet.next()) //sets the prices
        {
            pages.get(pageAmount).setItem(slots, GuiBuilder.buildItemStack(
                    Material.getMaterial(poseidonSet.getString("material")),
                    ChatColor.translateAlternateColorCodes('&', poseidonSet.getString("lootName")),
                    ChatColor.translateAlternateColorCodes('&', poseidonSet.getString("lootLore")),
                    false)
            );

            slots++;
            if (slots == 45) {

                pages.get(pageAmount).setItem(53, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Nächste Seite", " ", false));
                pageAmount++;
                pages.add(Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle() )));
                for (int i = 45; i <= 53; i++) //set lower row
                {
                    pages.get(pageAmount).setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
                }
                pages.get(pageAmount).setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));

                slots = 0;
            }
        }

    }

    public static List<Inventory> getPoseidonPages(Player player)
    {
        try {
            createPoseidonInv(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pages;
    }


}
