package me.mobcoins.database;

import static me.mobcoins.MobCoins.getInstance;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class DatabaseConnector {

	public static Connection con = null;
	public static boolean enable = getInstance().getConfigurationLoader().enable;

	public DatabaseConnector(){
		openConnection();
	}

	public void openConnection() {
		if (enable) {
			String host = getInstance().getConfigurationLoader().host;
			int port = Integer.parseInt(getInstance().getConfigurationLoader().port);
			String user = getInstance().getConfigurationLoader().user;
			String database = getInstance().getConfigurationLoader().database;
			String password = getInstance().getConfigurationLoader().password;

			String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
			try {
				if (con != null)
					return;
				con = DriverManager.getConnection(url, user, password);
				createTable("mobcoins", "uuid TEXT, coins TEXT");
				Bukkit.getConsoleSender().sendMessage("§aConexao com MySQL aberta com sucesso!");
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage("§cConexao com MySQL nao foi possivel, desabilitando plugin");
				getInstance().getPluginLoader().disablePlugin(getInstance());
			}

		} else {
			try {
				File file = new File(getInstance().getDataFolder(), "storage.db");
				if (con != null)
					return;
				Class.forName("org.sqlite.JDBC");
				String url = "jdbc:sqlite:" + file;
				con = DriverManager.getConnection(url);
				createTable("mobcoins", "uuid TEXT, coins TEXT");
				Bukkit.getConsoleSender().sendMessage("§aConexao com SQLite foi aberta com sucesso!");
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage("§cConexao com SQLite nao foi possivel, desabilitando plugin");
				getInstance().getPluginLoader().disablePlugin(getInstance());
			} catch (ClassNotFoundException e) {		
			}
		}
	}

	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("§cErro ao fechar conexão");
			}
		}
	}

	public Connection getConnection() {
		if (con != null) {
			return con;
		}
		return null;
	}

	public void createTable(String tableName, String columns) {
		PreparedStatement st = null;
		try {
			st = con.prepareStatement("CREATE TABLE IF NOT EXISTS `" + tableName + "` (" + columns + ")");
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao criar a tabela " + tableName);
		}
	}

}
