package com.dxzell.testchest.GUI;

import com.dxzell.testchest.Config;
import com.dxzell.testchest.Database.DatabaseConnection;
import com.dxzell.testchest.Database.Queries;
import com.dxzell.testchest.GUI.ChestInventories.*;
import com.dxzell.testchest.GUI.LootInventories.ApolloLoot;
import com.dxzell.testchest.GUI.LootInventories.ChestGui;
import com.dxzell.testchest.GUI.LootInventories.HeroLoot;
import com.dxzell.testchest.GUI.LootInventories.PoseidonLoot;
import com.dxzell.testchest.GUI.OpenChest.OpenChestGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GuiListener implements Listener {

    private HashMap<UUID, Integer> currentIndex = new HashMap<>(); //saves current page index
    private HashMap<UUID, Boolean> inAllChests = new HashMap<>(); //saves whether player is in allChestGui or not

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        String name = (e.getCurrentItem() == null ? "" : e.getCurrentItem().getItemMeta().getDisplayName());
        //CHEST GUI--------------------------------------------------------------------------------------------------------------------------
        if (equalsInventory(e.getClickedInventory(), ChestGui.createChestGUI((Player) e.getWhoClicked()))) {
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
                currentIndex.put(player.getUniqueId(), 0);
                inAllChests.put(player.getUniqueId(), false);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle())))//POSEIDON CHEST
            {
                if (e.getClick().equals(ClickType.RIGHT)) {
                    player.openInventory(PoseidonLoot.getPoseidonPages(player).get(0)); //opens loot page
                } else {
                    player.openInventory(PoseidonChests.getPoseidonPages(player).get(0)); //opens chest vault
                }
                currentIndex.put(player.getUniqueId(), 0);
                inAllChests.put(player.getUniqueId(), false);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle())))//HERO CHEST
            {
                if (e.getClick().equals(ClickType.RIGHT)) {
                    player.openInventory(HeroLoot.getHeroPages(player).get(0)); //opens loot page
                } else {
                    player.openInventory(HeroChests.getHeroPages(player).get(0)); //opens chest vault
                }
                currentIndex.put(player.getUniqueId(), 0);
                inAllChests.put(player.getUniqueId(), false);
            } else if (name.equalsIgnoreCase(ChatColor.RED + "Alle Kisten")) {
                player.openInventory(AllChests.getAllPages(player).get(0)); //first page
                currentIndex.put(player.getUniqueId(), 0);
                inAllChests.put(player.getUniqueId(), true);
            } else if (name.equalsIgnoreCase(ChatColor.GOLD + "Kisten kaufen")) {
                player.openInventory(BuyChests.createBuyChests(player));
            }

            //APOLLO LOOT GUI------------------------------------------------------------------------------------------
        } else if (equalsOneOfThePages(e.getClickedInventory(), ApolloLoot.getApolloPages(player))) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (currentIndex.get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(ApolloLoot.getApolloPages(player).get(currentIndex.get(player.getUniqueId()) - 1));
                    currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(ApolloLoot.getApolloPages(player).get(currentIndex.get(player.getUniqueId()) + 1));
                currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) + 1);
            }


            //POSEIDON LOOT GUI---------------------------------------------------------------------------------------------
        } else if (equalsOneOfThePages(e.getClickedInventory(), PoseidonLoot.getPoseidonPages(player))) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (currentIndex.get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(PoseidonLoot.getPoseidonPages(player).get(currentIndex.get(player.getUniqueId()) - 1));
                    currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(PoseidonLoot.getPoseidonPages(player).get(currentIndex.get(player.getUniqueId()) + 1));
                currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) + 1);
            }


            //HERO LOOT GUI-------------------------------------------------------------------------------------------------
        } else if (equalsOneOfThePages(e.getClickedInventory(), HeroLoot.getHeroPages(player))) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (currentIndex.get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(HeroLoot.getHeroPages(player).get(currentIndex.get(player.getUniqueId()) - 1));
                    currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(HeroLoot.getHeroPages(player).get(currentIndex.get(player.getUniqueId()) + 1));
                currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) + 1);
            }


            //APOLLO CHEST GUI-------------------------------------------------------------------------------------------------
        } else if (equalsOneOfThePages(e.getClickedInventory(), ApolloChests.getApolloPages(player)) && !inAllChests.get(player.getUniqueId())) {

            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (currentIndex.get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(ApolloChests.getApolloPages(player).get(currentIndex.get(player.getUniqueId()) - 1));
                    currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(ApolloChests.getApolloPages(player).get(currentIndex.get(player.getUniqueId()) + 1));
                currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) + 1);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "apollo")); //open apollo chest
            }

            //POSEIDON CHEST GUI---------------------------------------------------------------------------------------------------------
        } else if (equalsOneOfThePages(e.getClickedInventory(), PoseidonChests.getPoseidonPages(player)) && !inAllChests.get(player.getUniqueId())) {

            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (currentIndex.get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(PoseidonChests.getPoseidonPages(player).get(currentIndex.get(player.getUniqueId()) - 1));
                    currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(PoseidonChests.getPoseidonPages(player).get(currentIndex.get(player.getUniqueId()) + 1));
                currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) + 1);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "poseidon")); //open apollo chest
            }


            //HERO CHEST GUI------------------------------------------------------------------------------------------------------------------
        } else if (equalsOneOfThePages(e.getClickedInventory(), HeroChests.getHeroPages(player)) && !inAllChests.get(player.getUniqueId())) {

            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (currentIndex.get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(HeroChests.getHeroPages(player).get(currentIndex.get(player.getUniqueId()) - 1));
                    currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(HeroChests.getHeroPages(player).get(currentIndex.get(player.getUniqueId()) + 1));
                currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) + 1);
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "hero")); //open apollo chest
            }

            //ALL CHESTS------------------------------------------------------------------------------------------------------------------------
        } else if (equalsOneOfThePages(e.getClickedInventory(), AllChests.getAllPages(player))) {
            e.setCancelled(true);
            if (name.equalsIgnoreCase(ChatColor.GREEN + "Zurück")) {
                if (currentIndex.get(player.getUniqueId()) == 0) //first page -> open chest gui
                {
                    ChestGui.openChestGUI(player);
                } else //open previous page
                {
                    player.openInventory(AllChests.getAllPages(player).get(currentIndex.get(player.getUniqueId()) - 1));
                    currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) - 1);
                }
            } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Nächste Seite")) //open next page
            {
                player.openInventory(AllChests.getAllPages(player).get(currentIndex.get(player.getUniqueId()) + 1));
                currentIndex.put(player.getUniqueId(), currentIndex.get(player.getUniqueId()) + 1);
            }
            if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getApolloLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "apollo")); //open apollo chest
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getPoseidonLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "poseidon")); //open apollo chest
            } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Config.getHeroLootTitle()))) {
                player.openInventory(OpenChestGui.createOpenChestGui(player, "hero")); //open apollo chest
            }

            //OPEN CHESTS------------------------------------------------------------------------------------------------------------------------
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Gewinne einen Preis")) {
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

            //BUY CHESTS---------------------------------------------------------------------------------------------------------------------------
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Kisten kaufen")) {
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

    private boolean equalsInventory(Inventory inv1, Inventory inv2) { //checks whether both inventories are equal
        //check if both inventories are not null
        if (inv1 == null || inv2 == null) {
            return false;
        }

        //check if the sizes are equal
        if (inv1.getSize() != inv2.getSize()) {
            return false;
        }

        //check if the contents are equal
        ItemStack[] contents1 = inv1.getContents();
        ItemStack[] contents2 = inv2.getContents();

        for (int i = 0; i < contents1.length; i++) {
            ItemStack stack1 = contents1[i] != null ? contents1[i] : new ItemStack(Material.AIR);
            ItemStack stack2 = contents2[i] != null ? contents2[i] : new ItemStack(Material.AIR);

            //false if material is'nt equal or the item amount
            if (!stack1.isSimilar(stack2) || stack1.getAmount() != stack2.getAmount()) {
                return false;
            }
        }

        return true;
    }

    private boolean equalsOneOfThePages(Inventory inv, List<Inventory> pages) { //checks if given inventory equals one of the pages
        for (Inventory eachInv : pages) {
            if (equalsInventory(inv, eachInv)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) { //add to playerData if not already added
        if(DatabaseConnection.isConnected()) {
            Queries.addPlayerToData(e.getPlayer().getUniqueId().toString());
        }
    }


}
