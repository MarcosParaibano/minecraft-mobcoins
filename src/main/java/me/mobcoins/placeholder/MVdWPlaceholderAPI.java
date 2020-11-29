package me.mobcoins.placeholder;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import me.mobcoins.objects.User;

import java.text.NumberFormat;

import static me.mobcoins.MobCoins.getInstance;

public class MVdWPlaceholderAPI {

	public static void run() {
        PlaceholderAPI.registerPlaceholder(getInstance(), "mobcoins_coins", placeholderReplaceEvent -> {
            if (placeholderReplaceEvent.isOnline()) {
                User user = getInstance().getUserManager()
                        .getUser(placeholderReplaceEvent.getPlayer().getUniqueId());
                return NumberFormat.getInstance().format(user.getCoins());
            }
            return null;
        });
	}
}
