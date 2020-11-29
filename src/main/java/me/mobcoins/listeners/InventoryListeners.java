package me.mobcoins.listeners;

import static me.mobcoins.MobCoins.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.mobcoins.objects.Itens;
import me.mobcoins.objects.User;

public class InventoryListeners implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equals(getInstance().getMenuFile().getConfig().getString("Menu.Nome")
				.replace("&", "§"))){
			if(!e.getCurrentItem().hasItemMeta())return;
			if(!e.getCurrentItem().getItemMeta().hasDisplayName())return;
			User user = getInstance().getUserManager().getUser(p.getUniqueId());
			for(Itens item : getInstance().getItensLoader().getItensArrayList()){
				if(!e.getCurrentItem().getItemMeta().getDisplayName().equals(
						item.getItem().getItemMeta().getDisplayName().replace("&", "§"))) return;

				int value = item.getValue();
				if (user.getCoins() >= value) {
					for (String cmd : item.getCommands()) {
						Bukkit.dispatchCommand(
								Bukkit.getConsoleSender(), cmd.replaceAll("%player%", p.getName()));
					}
					p.sendMessage(getInstance().getConfigurationLoader().sucessMessage
									.replaceAll("%valor%", "" + value)
									.replaceAll("%produto%", item.getItem().getItemMeta().getDisplayName()
									.replaceAll("&", "§")));
					user.setCoins(user.getCoins() - value);
					p.closeInventory();
				} else
					p.sendMessage(getInstance().getConfigurationLoader().failedMessage);
			}
			e.setCancelled(true);
		}
	}
}
