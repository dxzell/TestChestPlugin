package com.dxzell.testchest.GUI.GuiListener;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.GUI.ChestInventories.ApolloChests;
import com.dxzell.testchest.GUI.GuiBuilder;
import com.dxzell.testchest.GUI.LootInventories.ApolloLoot;
import com.dxzell.testchest.GUI.LootInventories.ChestGui;
import com.dxzell.testchest.GUI.OpenChest.OpenChestGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ApolloGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) { //event for ApolloGui
        Player player = (Player) e.getWhoClicked();
        String name = (e.getCurrentItem() == null ? "" : e.getCurrentItem().getItemMeta().getDisplayName());

        //LOOT
        if (GuiBuilder.equalsOneOfThePages(e.getClickedInventory(), ApolloLoot.getApolloPages(player))) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(ApolloLoot.getApolloPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1));
                    GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(ApolloLoot.getApolloPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1));
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1);
            }
        }

        //CHEST
        else if (GuiBuilder.equalsOneOfThePages(e.getClickedInventory(), ApolloChests.getApolloPages(player)) && !GuiBuilder.getInAllChests().get(player.getUniqueId())) {

            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(ApolloChests.getApolloPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1));
                    GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(ApolloChests.getApolloPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1));
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "apollo")); //open apollo chest
            }
        }
    }
}
