package com.dxzell.testchest.Chest;

import com.dxzell.testchest.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Chest { //the clickable chest

    private ArmorStand chestStand;

    public Chest() { //chest on armorstand that opens the chest gui
        try {
            chestStand = (ArmorStand) Config.getChestLocation().getWorld().spawnEntity(Config.getChestLocation(), EntityType.ARMOR_STAND);
            chestStand.setInvisible(true);
            chestStand.setGravity(false);
            chestStand.setInvulnerable(true);
            chestStand.setHelmet(new ItemStack(Material.CHEST));
        } catch (Exception e) {
            System.out.println("Truhe wurde nicht gespawnt. Setze Location Werte in der Config.");
        }
    }

    public void despawnChest() {
        chestStand.remove();
    }

    public ArmorStand getChestStand() {
        return chestStand;
    }


}
