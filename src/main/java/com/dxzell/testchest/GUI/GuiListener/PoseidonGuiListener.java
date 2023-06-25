package com.dxzell.testchest.GUI.GuiListener;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.GUI.ChestInventories.PoseidonChests;
import com.dxzell.testchest.GUI.GuiBuilder;
import com.dxzell.testchest.GUI.LootInventories.ChestGui;
import com.dxzell.testchest.GUI.LootInventories.PoseidonLoot;
import com.dxzell.testchest.GUI.OpenChest.OpenChestGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PoseidonGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) { //event for PoseidonChestGui
        Player player = (Player) e.getWhoClicked();
        String name = (e.getCurrentItem() == null ? "" : e.getCurrentItem().getItemMeta().getDisplayName());

        //LOOT
        if (GuiBuilder.equalsOneOfThePages(e.getClickedInventory(), PoseidonLoot.getPoseidonPages(player))) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(PoseidonLoot.getPoseidonPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1));
                    GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(PoseidonLoot.getPoseidonPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1));
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1);
            }
        }

        //CHEST
        else if (GuiBuilder.equalsOneOfThePages(e.getClickedInventory(), PoseidonChests.getPoseidonPages(player)) && !GuiBuilder.getInAllChests().get(player.getUniqueId())) {

            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(PoseidonChests.getPoseidonPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1));
                    GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(PoseidonChests.getPoseidonPages(player).get(GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1));
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), GuiBuilder.getCurrentIndexMap().get(player.getUniqueId()) + 1);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "poseidon")); //open poseidon chest
            }
        }
    }


}
