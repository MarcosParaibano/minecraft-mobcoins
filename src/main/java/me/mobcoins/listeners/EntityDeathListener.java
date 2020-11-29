package me.mobcoins.listeners;

import me.mobcoins.objects.Mobs;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.mobcoins.objects.User;

import static me.mobcoins.MobCoins.*;

public class EntityDeathListener implements Listener {

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player)
			return;
		LivingEntity entity = e.getEntity();
		Player p = e.getEntity().getKiller();
		if (entity == null)
			return;
		for(Mobs mobs : getInstance().getMobsLoader().getMobsArrayList()){
			if(mobs.getType().equals(entity.getType())){
				int value = mobs.getValue();
				p.sendMessage("" + getInstance().getConfigurationLoader()
						.mobDeathMessage.replaceAll("%mob%",
								"" + mobs.getType()));
				User user = getInstance().getUserManager().getUser(p.getUniqueId());
				user.setCoins(user.getCoins() + value);
				getInstance().getUserManager().saveUser(user);
			}
		}
	}
}
