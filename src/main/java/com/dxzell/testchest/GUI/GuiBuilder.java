package com.dxzell.testchest.GUI;


import com.dxzell.testchest.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class GuiBuilder { //helps building guis

    private static HashMap<UUID, Integer> currentIndex = new HashMap<>(); //saves current page index
    private static HashMap<UUID, Boolean> inAllChests = new HashMap<>(); //saves whether player is in allChestGui or not

    public static ItemStack buildItemStack(Material mat, String displayName, String lore, boolean enchantment) {
        ItemStack stack = new ItemStack(mat);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);

        //Splitting the lore

        String[] splitLore = lore.split("°");
        List<String> loreList = new ArrayList<>();
        for (String split : splitLore) {
            loreList.add(split);
        }
        meta.setLore(loreList);

        //End

        if (enchantment) meta.addEnchant(Enchantment.KNOCKBACK, 0, false);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);

        return stack;

    }

    public static String cutString(String text) //cuts the string so it fits into the item lor
    {

        ChatColor color = ChatColor.WHITE;
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (String string : text.split(" ")) {
            counter += string.length();
            if (!string.contains("&")) {
                if (counter > 20) {
                    builder.append(color + string + "°");
                    counter = 0;
                } else {
                    builder.append(color + string + " ");
                }
            } else {
                if (counter > 20) {
                    builder.append(string + "°");
                    counter = 0;
                } else {
                    builder.append(string + " ");
                }
            }

            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '&') {
                    if (i + 1 <= string.length()) {
                        color = ChatColor.getByChar(string.charAt(i + 1));
                    }
                }
            }
        }
        return builder.toString();
    }

    public static boolean equalsInventory(Inventory inv1, Inventory inv2) { //checks whether both inventories are equal
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

    public static boolean equalsOneOfThePages(Inventory inv, List<Inventory> pages) { //checks if given inventory equals one of the pages
        for (Inventory eachInv : pages) {
            if (equalsInventory(inv, eachInv)) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<UUID, Integer> getCurrentIndexMap()
    {
        return currentIndex;
    }

    public static HashMap<UUID, Boolean> getInAllChests()
    {
        return inAllChests;
    }


}
