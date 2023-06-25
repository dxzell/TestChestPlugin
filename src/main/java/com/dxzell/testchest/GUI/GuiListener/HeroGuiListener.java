package com.dxzell.testchest.GUI.GuiListener;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.GUI.ChestInventories.HeroChests;
import com.dxzell.testchest.GUI.GuiBuilder;
import com.dxzell.testchest.GUI.LootInventories.ChestGui;
import com.dxzell.testchest.GUI.LootInventories.HeroLoot;
import com.dxzell.testchest.GUI.OpenChest.OpenChestGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class HeroGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) { //event for HeroChestGui
        Player player = (Player) e.getWhoClicked();
        String name = (e.getCurrentItem() == null ? "" : e.getCurrentItem().getItemMeta().getDisplayName());

        //LOOT
        if (GuiBuilder.equalsOneOfThePages(e.getClickedInventory(), HeroLoot.getHeroPages(player))) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(HeroLoot.getHeroPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1));
                    GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(HeroLoot.getHeroPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1));
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1);
            }
        }

        //CHEST
        else if (GuiBuilder.equalsOneOfThePages(e.getClickedInventory(), HeroChests.getHeroPages(player)) && !GuiBuilder.getInAllChests().get(player.getUniqueId())) {

            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(HeroChests.getHeroPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1));
                    GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(HeroChests.getHeroPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1));
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "hero")); //open hero chest
            }
        }
    }
}
