package me.mobcoins.commands.subcommands;

import me.mobcoins.managers.NPCManager;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public class NPCSubCommand {
    @Command(name = "mobcoins.setnpc", target = CommandTarget.PLAYER)
    public void handleCommand(Context<Player> context){
        context.getSender().sendMessage("§aNPC Setado com sucesso!");
        NPCManager.setNPC(context.getSender());
    }
}
