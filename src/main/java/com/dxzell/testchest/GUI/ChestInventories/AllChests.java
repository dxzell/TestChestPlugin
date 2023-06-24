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

public class AllChests {

    private static List<Inventory> pages = new ArrayList<>();

    public static void createAllInv(Player player) throws SQLException { //inv with all chests from every type combined

        pages.clear();
        Inventory allChests = Bukkit.createInventory(player, 54, ChatColor.RED + "Alle Kisten");
        pages.add(allChests);
        int pageAmount = 0; //current page

        int apolloAmount = Queries.getApolloAmount(player.getUniqueId().toString());
        int poseidonAmount = Queries.getPoseidonAmount(player.getUniqueId().toString());
        int heroAmount = Queries.getHeroAmount(player.getUniqueId().toString());
        int chestAmount = apolloAmount + poseidonAmount + heroAmount;

        for (int i = 45; i <= 53; i++) //set lower row
        {
            allChests.setItem(i, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
        }
        allChests.setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));

        int var = chestAmount/45 + 1; //amount of pages

        for(int i = 0; i < var; i++)
        {
            int opened = 0;
            for(int k = 0; k < ((i+1 == var) ? chestAmount : 45); k++)
            {
                pages.get(i).setItem(k, GuiBuilder.buildItemStack(Material.PRISMARINE_CRYSTALS, ChatColor.translateAlternateColorCodes('&', (apolloAmount > 0 ? Config.getApolloLootTitle() : (poseidonAmount > 0 ? Config.getPoseidonLootTitle() : Config.getHeroLootTitle()))),
                        ChatColor.AQUA + "Linksclick" + ChatColor.GRAY + ", um diese Kiste zu öffnen°" + ChatColor.RED + "Rechtsclick" + ChatColor.GRAY + ", um den Kisteninhalt zu betrachten", false));
                opened++;
                if(apolloAmount > 0)
                {
                    apolloAmount--;
                }else if(poseidonAmount > 0)
                {
                    poseidonAmount--;
                }else{
                    heroAmount--;
                }
            }
            chestAmount-=opened;
            if (i+1 < var) {

                pages.get(pageAmount).setItem(53, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Nächste Seite", " ", false));
                pageAmount++;
                pages.add(Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', ChatColor.RED + "Alle Kisten")));
                for (int j = 45; j <= 53; j++) //set lower row
                {
                    pages.get(pageAmount).setItem(j, GuiBuilder.buildItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", " ", false));
                }
                pages.get(pageAmount).setItem(45, GuiBuilder.buildItemStack(Material.GREEN_WOOL, ChatColor.GREEN + "Zurück", " ", false));

            }
        }

    }

    public static List<Inventory> getAllPages(Player player){
        try {
            createAllInv(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pages;
    }

}
