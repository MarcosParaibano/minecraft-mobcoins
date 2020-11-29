package me.mobcoins.managers;

import lombok.Getter;
import me.mobcoins.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.mobcoins.MobCoins.getInstance;

@Getter
public class TopManager{
	List<User> topUsers = new ArrayList<>();

	public void updateTop(){
        try {
            getInstance().getDatabaseConnector().openConnection();
            Statement statement = getInstance().getDatabaseConnector().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mobcoins ORDER BY coins DESC LIMIT 10");
            topUsers.clear();
            while (resultSet.next()) {
                User user = getInstance().getUserManager().getUser(
                        UUID.fromString(resultSet.getString("uuid")));
                topUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void sendTop(Player p){
	    p.sendMessage(getInstance().getConfigurationLoader().title);
		for (int i = 0; i < topUsers.size(); i++) {
			User user = topUsers.get(i);
			String coins = NumberFormat.getInstance().format(user.getCoins());
			OfflinePlayer po  = Bukkit.getOfflinePlayer(user.getUuid());
            p.sendMessage(getInstance().getConfigurationLoader().format
                    .replaceAll("%rank%", "" + i + 1)
                    .replaceAll("%player%", po.getName())
                    .replaceAll("%coins%", coins)
            );
		}
	}
}
