package com.dxzell.testchest;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Config { //returns config saved information

    private static TestChest main;

    public Config(TestChest main) {
        this.main = main;
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();
    }

    //CHEST
    public static Location getChestLocation() {

        return new Location(
                Bukkit.getWorld(main.getConfig().getString("chestLocation.world")),
                main.getConfig().getInt("chestLocation.x"),
                main.getConfig().getInt("chestLocation.y"),
                main.getConfig().getInt("chestLocation.z")
        );
    }

    //DATABASE
    public static String getHost() {
        return main.getConfig().getString("database.host");
    }

    public static int getPort() {
        return main.getConfig().getInt("database.port");
    }

    public static String getDatabase() {
        return main.getConfig().getString("database.database");
    }

    public static String getUsername() {
        return main.getConfig().getString("database.username");
    }

    public static String getPassword() {
        return main.getConfig().getString("database.password");
    }

    //MESSAGES
    public static String getChestGUITitle()
    {
        return main.getConfig().getString("messages.chestGUITitle");
    }

    public static String getApolloLootTitle()
    {
        return main.getConfig().getString("messages.apolloLootTitle");
    }

    public static String getPoseidonLootTitle()
    {
        return main.getConfig().getString("messages.poseidonLootTitle");
    }

    public static String getHeroLootTitle()
    {
        return main.getConfig().getString("messages.heroLootTitle");
    }

    //PRICe

    public static int getApolloPrice()
    {
        return main.getConfig().getInt("price.apollo");
    }
    public static int getPoseidonPrice()
    {
        return main.getConfig().getInt("price.poseidon");
    }
    public static int getHeroPrice()
    {
        return main.getConfig().getInt("price.hero");
    }





}
