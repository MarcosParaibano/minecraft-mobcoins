package me.mobcoins.commands.subcommands;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.entity.Player;

public class HelpSubCommand {
    @Command(name = "mobcoins.help")
    public void handleCommand(Context<Player> context){
        Player p = context.getSender();
        p.sendMessage(" ");
        p.sendMessage("§c> §7/mobcoins top");
        p.sendMessage("§c> §7/mobcoins shop");
        if(p.hasPermission("mobcoins.admin")){
            p.sendMessage("§c> §7/mobcoins set");
            p.sendMessage("§c> §7/mobcoins add");
            p.sendMessage("§c> §7/mobcoins remove");
        }
        p.sendMessage(" ");
    }
}
