package com.dxzell.testchest.AddLoot;

public class LootInformation {

    private String material;

    private String lootName;

    private int dropChance;

    private String command;

    private String lore;

    private String chestType;

    public LootInformation(String chestType, String material, String lootName, int dropChance, String command) {
        this.chestType = chestType;
        this.material = material;
        this.lootName = lootName;
        this.dropChance = dropChance;
        this.command = command;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getMaterial() {
        return material;
    }

    public String getLootName() {
        return lootName;
    }

    public int getDropChance() {
        return dropChance;
    }

    public String getCommand() {
        return command;
    }

    public String getLore() {
        return lore;
    }

    public String getChestType() {
        return chestType;
    }

}
