package com.dxzell.testchest.Database;

import com.dxzell.testchest.AddLoot.LootInformation;
import com.dxzell.testchest.Config;
import com.dxzell.testchest.GUI.GuiBuilder;
import com.dxzell.testchest.GUI.OpenChest.RollItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Queries { //all queries for database

    //APOLLO
    public static void createApolloLootTable() { //creates apolloLoot table if does not exist
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("CREATE TABLE IF NOT EXISTS apolloLoot (id INT AUTO_INCREMENT, material VARCHAR(50), lootName VARCHAR(50), lootLore VARCHAR(100), dropChance INT, command VARCHAR(50), PRIMARY KEY (id));");
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet getApolloLoot() { //gets the whole apollo loot (items)
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM apolloLoot");
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //POSEIDON
    public static void createPoseidonLootTable() { //creates poseidonLoot table if does not exist
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("CREATE TABLE IF NOT EXISTS poseidonLoot (id INT AUTO_INCREMENT, material VARCHAR(50), lootName VARCHAR(50), lootLore VARCHAR(100), dropChance INT, command VARCHAR(50), PRIMARY KEY (id));");
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getPoseidonLoot() { //gets the whole poseidon loot (items)
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM poseidonLoot");
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //HERO
    public static void createHeroLootTable() { //creates heroLoot table if does not exist
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("CREATE TABLE IF NOT EXISTS heroLoot (id INT AUTO_INCREMENT, material VARCHAR(50), lootName VARCHAR(50), lootLore VARCHAR(100), dropChance INT, command VARCHAR(50), PRIMARY KEY (id));");
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getHeroLoot() { //gets the whole hero loot (items)
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM heroLoot");
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //PLAYER DATA
    public static void createPlayerDataTable() { //creates playerData table that saves every players information
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("CREATE TABLE IF NOT EXISTS playerData (uuid VARCHAR(100), credits INT, apolloAmount INT, poseidonAmount INT, heroAmount INT, PRIMARY KEY(uuid));");
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createWinnings() { //creates winnings table that saves every winning from the chest with the winner's id, item id and the chest type the item was from
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("CREATE TABLE IF NOT EXISTS winnings (winnerUUID VARCHAR(100), itemId INT, chestType VARCHAR(50));");
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean playerExists(String uuid) { //returns true if player already exists in playerData table
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("SELECT * FROM playerData WHERE uuid = ?");
            ps.setString(1, uuid);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addPlayerToData(String uuid) { //add player to table with basic values
        try {
            if (!playerExists(uuid)) {
                PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                        ("INSERT INTO playerData (uuid, credits, apolloAmount, poseidonAmount, heroAmount) VALUES (?,?,?,?,?)");
                ps.setString(1, uuid);
                ps.setInt(2, 0);
                ps.setInt(3, 0);
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getApolloAmount(String uuid) { //gets the players apollo chest amount
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("SELECT apolloAmount FROM playerData WHERE uuid = ?");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("apolloAmount");
            } else {
                throw new RuntimeException("Kein Spieler mit der angegebenen UUID gefunden");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getPoseidonAmount(String uuid) { //gets the players poseidon chest amount
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("SELECT poseidonAmount FROM playerData WHERE uuid = ?");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("poseidonAmount");
            } else {
                throw new RuntimeException("Kein Spieler mit der angegebenen UUID gefunden");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getHeroAmount(String uuid) { //gets the players hero chest amount
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("SELECT heroAmount FROM playerData WHERE uuid = ?;");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("heroAmount");
            } else {
                throw new RuntimeException("Kein Spieler mit der angegebenen UUID gefunden");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCredits(String uuid) { //gets the players credit amount
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("SELECT credits FROM playerData WHERE uuid = ?;");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("credits");
            } else {
                throw new RuntimeException("Kein Spieler mit der angegebenen UUID gefunden");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getLeftPercentage(String chestType) { //returns the left percentage that can be put on new loot
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT dropChance FROM " + chestType);
            ResultSet set = ps.executeQuery();
            int dropChance = 0;
            while (set.next()) {
                dropChance += set.getInt("dropChance");
            }
            return (100 - dropChance <= 0 ? 0 : 100 - dropChance);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addItem(LootInformation lootInformation) { //adds new loot to either apollo, poseidon or hero chest
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement
                    ("INSERT INTO " + (lootInformation.getChestType().equalsIgnoreCase("apollo")
                            ? "apolloLoot" : (lootInformation.getChestType().equalsIgnoreCase("poseidon")
                            ? "poseidonLoot" : "heroLoot")) + " (material, lootName, lootLore, dropChance, command)" + " VALUES (?,?,?,?,?);");
            ps.setString(1, lootInformation.getMaterial());
            ps.setString(2, lootInformation.getLootName());
            ps.setString(3, lootInformation.getLore());
            ps.setInt(4, lootInformation.getDropChance());
            ps.setString(5, lootInformation.getCommand());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<RollItem> getRollItems(String chestType) //gets the whole loot from either apollo, poseidon or hero so a random item can be rolled
    {
        List<RollItem> rollItems = new ArrayList<>();

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM " + (chestType.equalsIgnoreCase("apollo") ? "apolloLoot"
                    : (chestType.equalsIgnoreCase("poseidon") ? "poseidonLoot" : "heroLoot")));
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                rollItems.add(new RollItem(set.getInt("id"), set.getInt("dropChance")));
            }
            return rollItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runCommand(int itemId, String chestType, Player player) { //runs the command that the won loot is connected to
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT command FROM " + (chestType.equalsIgnoreCase("apollo") ? "apolloLoot"
                    : (chestType.equalsIgnoreCase("poseidon") ? "poseidonLoot" : "heroLoot")) + " WHERE id = ?");
            ps.setInt(1, itemId);
            ResultSet set = ps.executeQuery();
            set.next();
            player.performCommand(set.getString("command"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ItemStack getRollItemById(int id, String chestType) //gets the item from either apollo, poseidon or hero chest by id
    {
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM " + (chestType.equalsIgnoreCase("apollo")
                    ? "apolloLoot" : (chestType.equalsIgnoreCase("poseidon") ? "poseidonLoot" : "heroLoot")) + " WHERE id = ?");
            ps.setInt(1, id);
            ResultSet set = ps.executeQuery();
            set.next();
            return GuiBuilder.buildItemStack(Material.getMaterial(set.getString("material")),
                    ChatColor.translateAlternateColorCodes('&', set.getString("lootName")),
                    ChatColor.translateAlternateColorCodes('&', set.getString("lootLore")),
                    false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addWin(String chestType, int wonId, UUID winner) { //adds win to winnings table
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO winnings (winnerUUID, itemId, chestType) VALUES (?,?,?)");
            ps.setString(1, winner.toString());
            ps.setInt(2, wonId);
            ps.setString(3, chestType);
            ps.executeUpdate();

            lowerCaseAmount(chestType, winner.toString());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void lowerCaseAmount(String chestType, String uuid) { //lowers chest amount from player for that specific chest by 1

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE playerData SET " + (chestType.equalsIgnoreCase("apollo")
                    ? "apolloAmount" : (chestType.equalsIgnoreCase("poseidon") ? "poseidonAmount" : "heroAmount")) + " = " + (chestType.equalsIgnoreCase("apollo")
                    ? getApolloAmount(uuid) - 1 : (chestType.equalsIgnoreCase("poseidon") ? getPoseidonAmount(uuid) - 1 : getHeroAmount(uuid) - 1)) + " WHERE uuid = ?");
            ps.setString(1, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void higherCaseAmount(String chestType, String uuid, int amount) { //highers chest amount from player by the given amount
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE playerData SET " + (chestType.equalsIgnoreCase("apollo")
                    ? "apolloAmount" : (chestType.equalsIgnoreCase("poseidon") ? "poseidonAmount" : "heroAmount")) + " = " + (chestType.equalsIgnoreCase("apollo")
                    ? getApolloAmount(uuid) + amount : (chestType.equalsIgnoreCase("poseidon") ? getPoseidonAmount(uuid) + amount : getHeroAmount(uuid) + amount)) + " WHERE uuid = ?");
            ps.setString(1, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void buyChests(int amount, Player player, String chestType) //buys a chest
    {
        if (getCredits(player.getUniqueId().toString()) >= amount * (chestType.equalsIgnoreCase("apollo") ? Config.getApolloPrice() : (chestType.equalsIgnoreCase("poseidon")
                ? Config.getPoseidonPrice() : Config.getHeroPrice()))) //has enough credits
        {
            try {
                PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE playerData SET credits = ? WHERE uuid = ?");
                ps.setInt(1, getCredits(player.getUniqueId().toString()) - (amount * (chestType.equalsIgnoreCase("apollo") ? Config.getApolloPrice() : (chestType.equalsIgnoreCase("poseidon")
                        ? Config.getPoseidonPrice() : Config.getHeroPrice()))));
                ps.setString(2, player.getUniqueId().toString());
                ps.executeUpdate();
                higherCaseAmount(chestType, player.getUniqueId().toString(), amount);
                player.sendMessage(ChatColor.GREEN + "Angebot erfolgreich gekauft!");
                player.sendMessage(ChatColor.RED + "-" + amount * (chestType.equalsIgnoreCase("apollo") ? Config.getApolloPrice() : (chestType.equalsIgnoreCase("poseidon")
                        ? Config.getPoseidonPrice() : Config.getHeroPrice())) + " Credits");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else { //does'nt have enough credits
            player.closeInventory();
            player.sendMessage(ChatColor.RED + "Dir fehlen " + ChatColor.DARK_RED + (amount * (chestType.equalsIgnoreCase("apollo") ? Config.getApolloPrice() : (chestType.equalsIgnoreCase("poseidon")
                    ? Config.getPoseidonPrice() : Config.getHeroPrice())) - getCredits(player.getUniqueId().toString())) + " Credits" + ChatColor.RED + ", um dieses Angebot zu kaufen!");
        }

    }


}
