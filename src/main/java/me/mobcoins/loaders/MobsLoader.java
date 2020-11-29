package me.mobcoins.loaders;

import lombok.Getter;
import me.mobcoins.MobCoins;
import me.mobcoins.objects.Mobs;
import org.bukkit.entity.EntityType;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MobsLoader {
    ArrayList<Mobs> mobsArrayList = new ArrayList<>();

    public MobsLoader(){
        List<String> mobsList = MobCoins.getInstance().getConfig().getStringList("Mobs");
        for (String mobString : mobsList) {

            String[] split = mobString.split(";");
            String type = split[0];
            String value = split[1];

            Mobs mob = new Mobs(Integer.parseInt(value), EntityType.valueOf(type));
            mobsArrayList.add(mob);
        }
    }
}
