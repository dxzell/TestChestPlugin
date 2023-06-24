package com.dxzell.testchest.AddLoot;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class addLootCompleter implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) { //tabCompleter for the add loot command
        List<String> liste = new ArrayList<>();

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList(new String[]{"Apollo", "Poseidon", "Hero"}), new ArrayList<>());
        } else if (args.length == 2) {
            List<String> list = new ArrayList<>();
            for (Material mat : Material.values()) {
                list.add(mat.toString());
            }
            return StringUtil.copyPartialMatches(args[1], list, new ArrayList<>());
        }
        return liste;
    }
}
