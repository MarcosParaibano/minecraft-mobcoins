package me.mobcoins.commands.subcommands;

import me.mobcoins.objects.Itens;
import me.mobcoins.utils.ScrollerInventory;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.mobcoins.MobCoins.getInstance;

public class ShopSubCommand {
    @Command(name = "mobcoins.shop")
    public void handleCommand(Context<Player> context){
        Player p = context.getSender();
        if(context.getArgs().length == 0) {
            ArrayList<ItemStack> itensArray = new ArrayList<>();
            for (Itens itens : getInstance().getItensLoader().getItensArrayList())
                itensArray.add(itens.getItem());
            new ScrollerInventory(itensArray, getInstance().getMenuFile().getConfig().getString("Menu.Nome")
                    .replaceAll("&", "§"), p);
        }
    }
}
