package com.dxzell.testchest;

import com.dxzell.testchest.AddLoot.addLootCommand;
import com.dxzell.testchest.AddLoot.addLootCompleter;
import com.dxzell.testchest.Chest.Chest;
import com.dxzell.testchest.Chest.ChestListener;
import com.dxzell.testchest.Database.DatabaseConnection;
import com.dxzell.testchest.GUI.GuiListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class TestChest extends JavaPlugin {

    private Chest chest;
    private DatabaseConnection database;

    private Config config;

    @Override
    public void onEnable() {

        config = new Config(this);
        chest = new Chest();
        Bukkit.getPluginManager().registerEvents(new ChestListener(chest), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        Bukkit.getPluginManager().registerEvents(new addLootCommand(), this);

        getCommand("add").setExecutor(new addLootCommand());
        getCommand("add").setTabCompleter(new addLootCompleter());


        database = new DatabaseConnection();
        try {
            database.connect();
        } catch (SQLException e) {
            System.out.println("Datenbankverbindung konnte nicht aufgebaut werden. Prüfe die Daten in der config.");
        }
        System.out.println(database.isConnected());

    }

    @Override
    public void onDisable() {
        database.disconnect();
        chest.despawnChest();
    }
}
