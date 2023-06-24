package com.dxzell.testchest.GUI.ChestInventories;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.Database.Queries;
import com.dxzell.testchest.GUI.GuiBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoseidonChests {

    private static List<Inventory> pages = new ArrayList<>();

    public static void createPoseidonInv(Player player) throws SQLException { //inv with the players poseidon chests

        pages.clear();
        Inventory poseidonChests = Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()));
        pages.add(poseidonChests);
        int pageAmount = 0; //current page

        int chestAmount = Queries.getPoseidonAmount(player.getUniqueId().toString());

        for (int i = 45; i <= 53; i++) //set lower row
        {
            poseidonChests.setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        }
        poseidonChests.setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));

        int var = chestAmount/45 + 1;

        for(int i = 0; i < var; i++)
        {
            int opened = 0;
            for(int k = 0; k < ((i+1 == var) ? chestAmount : 45); k++)
            {
                pages.get(i).setItem(k, GuiBuilder.buildItemStack(Material.PRISMARINE_CRYSTALS, ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()),
                        ChatColor.AQUA + "Linksclick" + ChatColor.GRAY + ", um diese Kiste zu öffnen°" + ChatColor.RED + "Rechtsclick" + ChatColor.GRAY + ", um den Kisteninhalt zu betrachten", false));
                opened++;
            }
            chestAmount-=opened;

            if (i+1 < var) {

                pages.get(pageAmount).setItem(53, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Nächste Seite", " ", false));
                pageAmount++;
                pages.add(Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle())));
                for (int j = 45; j <= 53; j++) //set lower row
                {
                    pages.get(pageAmount).setItem(j, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
                }
                pages.get(pageAmount).setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));

            }
        }

    }

    public static List<Inventory> getPoseidonPages(Player player){
        try {
            createPoseidonInv(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pages;
    }

}
