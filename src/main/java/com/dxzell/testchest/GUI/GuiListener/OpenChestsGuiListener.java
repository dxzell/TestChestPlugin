package com.dxzell.testchest.GUI.GuiListener;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.GUI.OpenChest.OpenChestGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OpenChestsGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) { //event for OpenChestsGui
        Player player = (Player) e.getWhoClicked();
        String name = (e.getCurrentItem() == null ? "" : e.getCurrentItem().getItemMeta().getDisplayName());

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Gewinne einen Preis")) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Öffne " + ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()))) {
                //APOLLO
                OpenChestGui.rollPrice(player, e.getInventory(), "apollo");


            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Öffne " + ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()))) {
                //POSEIDON
                OpenChestGui.rollPrice(player, e.getInventory(), "poseidon");


            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Öffne " + ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()))) {
                //HERO
                OpenChestGui.rollPrice(player, e.getInventory(), "hero");

            }
        }
    }
}
