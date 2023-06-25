package com.dxzell.testchest.GUI.GuiListener;

import com.dxzell.testchest.Database.Queries;
import com.dxzell.testchest.GUI.LootInventories.ChestGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BuyChestsGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) { //event for BuyChestsGui
        Player player = (Player) e.getWhoClicked();
        String name = (e.getCurrentItem() == null ? "" : e.getCurrentItem().getItemMeta().getDisplayName());

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Kisten kaufen")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
                return;
            }
            if (name.contains("1x") || name.contains("3x") || name.contains("6x") || name.contains("12x")) {
                Queries.buyChests((name.contains("1x") ? 1 : (name.contains("3x") ? 3 : (name.contains("6x") ? 6 : (name.contains("12x") ? 12 : null)))), player, (e.getCurrentItem().getType().equals(Material.CHEST)
                        ? "apollo" : (e.getCurrentItem().getType().equals(Material.ENDER_CHEST)
                        ? "poseidon" : (e.getCurrentItem().getType().equals(Material.YELLOW_SHULKER_BOX) ? "hero" : null))));
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                ChestGui.openChestGUI(player);
            }
        }
    }
}
