package me.mobcoins.commands;

import me.mobcoins.objects.User;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

import static me.mobcoins.MobCoins.getInstance;

public class MobCoinsCommand {

    @Command(name = "mobcoins", target = CommandTarget.PLAYER)
    public void handleCommand(Context<Player> context){
       User user = getInstance().getUserManager().getUser(context.getSender().getUniqueId());
       context.getSender().sendMessage(
               getInstance().getConfigurationLoader().mobCoinsMessage
                            .replace("%mobcoins%" , NumberFormat.getInstance().format(user.getCoins()))
       );
    }
}
