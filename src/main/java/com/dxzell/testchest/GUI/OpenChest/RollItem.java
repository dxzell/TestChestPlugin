package com.dxzell.testchest.GUI.OpenChest;

public class RollItem { //saves id of the item + its dropChance

    private int id;
    private int dropChance;

    public RollItem(int id, int dropChance) {
        this.id = id;
        this.dropChance = dropChance;
    }

    public int getId()
    {
        return id;
    }

    public int getDropChance()
    {
        return dropChance;
    }

}
