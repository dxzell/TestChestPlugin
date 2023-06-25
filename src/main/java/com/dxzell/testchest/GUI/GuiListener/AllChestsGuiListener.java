package com.dxzell.testchest.GUI.GuiListener;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.GUI.ChestInventories.AllChests;
import com.dxzell.testchest.GUI.GuiBuilder;
import com.dxzell.testchest.GUI.LootInventories.ChestGui;
import com.dxzell.testchest.GUI.OpenChest.OpenChestGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AllChestsGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) { //event for AllChestsGui
        Player player = (Player) e.getWhoClicked();
        String name = (e.getCurrentItem() == null ? "" : e.getCurrentItem().getItemMeta().getDisplayName());

        if (GuiBuilder.equalsOneOfThePages(e.getClickedInventory(), AllChests.getAllPages(player)) && GuiBuilder.getInAllChests().get(player.getUniqueId())) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(AllChests.getAllPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1));
                    GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1);
                    GuiBuilder.getCurrentIndexMap().get(player.getUniqueId());
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(AllChests.getAllPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1));
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1);
            }
            if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "apollo")); //open apollo chest
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "poseidon")); //open poseidon chest
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "hero")); //open hero chest
            }
        }
    }
}
