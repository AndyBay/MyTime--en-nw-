package com.rrodgaming.mytime;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    Logger mine;
    Boolean nw = false; 
    
    @Override
    public void onDisable() {
        mine.info("[MyTime] v0.3 Disabled");
    }

    @Override
    public void onEnable() {
        mine = Logger.getLogger("Minecraft"); 
        mine.info("[MyTime] v0.3 Enabled");
        mine.info("[MyTime] Type /norwegian or /english to select language! ");
        mine.info("[MyTime] Defaults to English. ");
    }
    
    public void onReload() {
        mine = null; 
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (((cmd.getName().equalsIgnoreCase("MyTime")) || (cmd.getName().equalsIgnoreCase("MinTid")))
                && ((sender.hasPermission("mytime.change"))
                        || (sender.isOp()))) {
            
            Player player = (Player)sender; 
            long curTime = player.getPlayerTime(); 
            long newTime = curTime - curTime % 24000L; 
            
            if ((args[0].equalsIgnoreCase("day")) || (args[0].equalsIgnoreCase("dag"))) {
                newTime += 0L;
                player.setPlayerTime(newTime, false); 
                if (nw == true) player.sendMessage(ChatColor.YELLOW + "Din tid er endret til: " + ChatColor.WHITE + "dag. ");
                if (nw == false) player.sendMessage(ChatColor.YELLOW + "Your time has been changed to: " + ChatColor.WHITE + "day. ");
            } else if ((args[0].equalsIgnoreCase("night")) || (args[0].equalsIgnoreCase("natt"))) {
                newTime += 14000L;
                player.setPlayerTime(newTime, false); 
                if (nw == true) player.sendMessage(ChatColor.YELLOW + "Din tid er endret til: " + ChatColor.WHITE + "natt. ");
                if (nw == false) player.sendMessage(ChatColor.YELLOW + "Your time has been changed to: " + ChatColor.WHITE + "night. ");
            } else if ((args[0].equalsIgnoreCase("dusk")) || (args[0].equalsIgnoreCase("skumring"))) {
                newTime += 12500L;
                player.setPlayerTime(newTime, false); 
                if (nw == true) player.sendMessage(ChatColor.YELLOW + "Din tid er endret til: " + ChatColor.WHITE + "skumring. ");
                if (nw == false) player.sendMessage(ChatColor.YELLOW + "Your time has been changed to: " + ChatColor.WHITE + "dusk. ");
            } else if (args[0].equalsIgnoreCase("dawn")) {
                newTime += 23000L;
                player.setPlayerTime(newTime, false); 
                if (nw == true) player.sendMessage(ChatColor.YELLOW + "Din tid er endret til: " + ChatColor.WHITE + "dawn. ");
                if (nw == false) player.sendMessage(ChatColor.YELLOW + "Your time has been changed to: " + ChatColor.WHITE + "dawn. ");
            } else if ((args[0].equalsIgnoreCase("reset")) || (args[0].equalsIgnoreCase("resett"))) {
                long curTimeWorld = player.getWorld().getTime();
                player.setPlayerTime(curTimeWorld, false); 
                if (nw == true) player.sendMessage(ChatColor.YELLOW + "Din tid er nå tilbakestilt!");
                if (nw == false) player.sendMessage(ChatColor.YELLOW + "Your time has been reset! ");
            }
        } else if ((cmd.getName().equalsIgnoreCase("Norwegian")) && (sender.isOp())) {
            sender.sendMessage("[MyTime] Språk oversatt til Norsk"); 
            nw = true; 
        }  else if ((cmd.getName().equalsIgnoreCase("English")) && (sender.isOp())) {
            sender.sendMessage("[MyTime] Set language to English!"); 
            nw = false; 
        }
        else {
            if (nw == true) sender.sendMessage(ChatColor.RED + "[MinTid] Du har ikke tillatelse til � gj�re det!te");
            if (nw == false) sender.sendMessage("[MyTime] You don't have permission to do that!"); 
        }
        return false;
    }

}
