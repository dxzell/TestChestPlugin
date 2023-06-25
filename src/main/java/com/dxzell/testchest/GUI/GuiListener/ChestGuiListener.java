package com.dxzell.testchest.GUI.GuiListener;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.GUI.ChestInventories.*;
import com.dxzell.testchest.GUI.GuiBuilder;
import com.dxzell.testchest.GUI.LootInventories.ApolloLoot;
import com.dxzell.testchest.GUI.LootInventories.HeroLoot;
import com.dxzell.testchest.GUI.LootInventories.PoseidonLoot;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChestGuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) { //event for ChestGui
        Player player = (Player) e.getWhoClicked();
        String name = (e.getCurrentItem() == null ? "" : e.getCurrentItem().getItemMeta().getDisplayName());
        if (GuiBuilder.equalsInventory(e.getClickedInventory(), com.dxzell.testchest.GUI.LootInventories.ChestGui.createChestGUI((Player) e.getWhoClicked()))) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
                return;
            }
            if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()))) //APOLLO CHEST
            {
                if (e.getClick().equals(ClickType.RIGHT)) {
                    player.openInventory(ApolloLoot.getApolloPages(player).get(0)); //opens loot page
                } else {
                    player.openInventory(ApolloChests.getApolloPages(player).get(0)); //opens chest vault
                }
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), 0);
                GuiBuilder.getInAllChests().put(player.getUniqueId(), false);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle())))//POSEIDON CHEST
            {
                if (e.getClick().equals(ClickType.RIGHT)) {
                    player.openInventory(PoseidonLoot.getPoseidonPages(player).get(0)); //opens loot page
                } else {
                    player.openInventory(PoseidonChests.getPoseidonPages(player).get(0)); //opens chest vault
                }
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), 0);
                GuiBuilder.getInAllChests().put(player.getUniqueId(), false);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle())))//HERO CHEST
            {
                if (e.getClick().equals(ClickType.RIGHT)) {
                    player.openInventory(HeroLoot.getHeroPages(player).get(0)); //opens loot page
                } else {
                    player.openInventory(HeroChests.getHeroPages(player).get(0)); //opens chest vault
                }
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), 0);
                GuiBuilder.getInAllChests().put(player.getUniqueId(), false);
            } else if (name.equalsIgnoreCase(ChatColor.RED + "Alle Kisten")) {
                player.openInventory(AllChests.getAllPages(player).get(0)); //first page
                GuiBuilder.getCurrentIndexMap().put(player.getUniqueId(), 0);
                GuiBuilder.getInAllChests().put(player.getUniqueId(), true);
            } else if (name.equalsIgnoreCase(ChatColor.GOLD + "Kisten kaufen")) {
                player.openInventory(BuyChests.createBuyChests(player));
            }
        }
    }
}
