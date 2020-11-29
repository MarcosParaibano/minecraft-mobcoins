package me.mobcoins.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.mobcoins.objects.Itens;
import me.mobcoins.utils.ScrollerInventory;

import static me.mobcoins.MobCoins.getInstance;

import java.util.ArrayList;

public class NPCListeners implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity entity = e.getRightClicked();
		if (entity.getName().contains(getInstance().getConfigurationLoader().npcName)) {
			ArrayList<ItemStack> itensArray = new ArrayList<>();
			for (Itens itens : getInstance().getItensLoader().getItensArrayList())
				itensArray.add(itens.getItem());
			new ScrollerInventory(itensArray, getInstance().getMenuFile().getConfig().getString("Menu.Nome")
					.replaceAll("&", "§"), p);
		}
	}
}
