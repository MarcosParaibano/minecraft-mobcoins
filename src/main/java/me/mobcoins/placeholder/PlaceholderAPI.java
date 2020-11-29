package me.mobcoins.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import static me.mobcoins.MobCoins.getInstance;
import me.mobcoins.objects.User;
import org.bukkit.OfflinePlayer;

import java.text.NumberFormat;

public class PlaceholderAPI extends PlaceholderExpansion {
    @Override
    public boolean canRegister(){
        return true;
    }
    @Override
    public String getAuthor(){
        return "paraibano";
    }
    @Override
    public String getIdentifier(){
        return "mobcoins";
    }
    @Override
    public String getVersion(){
        return "3.0";
    }
    @Override
    public String onRequest(OfflinePlayer player, String identifier){
        if(identifier.equals("coins")){
            User user =  getInstance().getUserManager().getUser(player.getUniqueId());
            return NumberFormat.getInstance().format(user.getCoins());
        }
        return null;
    }
}
