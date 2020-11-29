package me.mobcoins.commands.subcommands;

import me.mobcoins.objects.User;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.mobcoins.MobCoins.getInstance;

public class RemoveSubCommand {
    @Command(name = "mobcoins.remove")
    public void handleCommand(Context<Player> context){
        Player p = context.getSender();
        if(context.getArgs().length != 2){
            context.getSender().sendMessage("utilize /mobcoins remove <player> <quantia>");
            return;
        }
        Player target = Bukkit.getPlayer(context.getArg(0));
        int i = Integer.parseInt(context.getArg(1));
        if(target.isOnline()){
            User user = getInstance().getUserManager().getUser(target.getUniqueId());
            if(user == null)
                p.sendMessage("§cEsse usuario não existe em nosso banco de dados");
            if(i <= user.getCoins()){
                user.setCoins(user.getCoins() - i);
                getInstance().getUserManager().saveUser(user);
                p.sendMessage(getInstance().getConfigurationLoader().mobCoinsGive
                        .replaceAll("%player%", target.getName())
                        .replaceAll("%mobcoins%", "" + user.getCoins())
                );
            }else p.sendMessage(getInstance().getConfigurationLoader().noMobCoins);
        }else  p.sendMessage(getInstance().getConfigurationLoader().invalidPlayer);

    }
}
