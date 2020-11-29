package me.mobcoins.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.mobcoins.objects.User;

import static me.mobcoins.MobCoins.*;

public class PlayersListeners implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		getInstance().getUserManager().loadUser(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		User user = getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
		getInstance().getUserManager().saveUser(user);
	}
}
