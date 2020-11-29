package me.mobcoins.commands.subcommands;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.entity.Player;

import static me.mobcoins.MobCoins.getInstance;

public class TopSubCommand {
    @Command(name = "mobcoins.top")
    public void handleCommand(Context<Player> context){
        getInstance().getTopManager().sendTop(context.getSender());
    }
}
