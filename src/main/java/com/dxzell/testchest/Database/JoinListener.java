package com.dxzell.testchest.Database;

import com.dxzell.testchest.Database.DatabaseConnection;
import com.dxzell.testchest.Database.Queries;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) { //add to playerData if not already added
        if (DatabaseConnection.isConnected()) {
            Queries.addPlayerToData(e.getPlayer().getUniqueId().toString());
        }
    }

}
