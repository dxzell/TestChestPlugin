package com.dxzell.testchest.Chest;

import com.dxzell.testchest.GUI.LootInventories.ChestGui;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ChestListener implements Listener {

    private Chest chest;

    public ChestListener(Chest chest) {
        this.chest = chest;
    }

    @EventHandler
    public void onChestClick(PlayerInteractAtEntityEvent e) { //opens chestgui if clicked on armorstand chest
        if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)
                && ((ArmorStand) e.getRightClicked()).getUniqueId().equals(chest.getChestStand().getUniqueId())) {
            e.setCancelled(true);
            ChestGui.openChestGUI(e.getPlayer());
        }
    }


}
