package me.mobcoins;

import lombok.Getter;
import me.mobcoins.commands.MobCoinsCommand;
import me.mobcoins.commands.subcommands.*;
import me.mobcoins.files.MenuFile;
import me.mobcoins.loaders.ItensLoader;
import me.mobcoins.loaders.MobsLoader;
import me.mobcoins.managers.NPCManager;
import me.mobcoins.managers.TopManager;
import me.mobcoins.objects.User;
import me.mobcoins.placeholder.PlaceholderAPI;
import me.mobcoins.loaders.ConfigurationLoader;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.mobcoins.listeners.EntityDeathListener;
import me.mobcoins.listeners.InventoryListeners;
import me.mobcoins.listeners.NPCListeners;
import me.mobcoins.listeners.PlayersListeners;
import me.mobcoins.listeners.ScrollerListener;
import me.mobcoins.managers.UserManager;
import me.mobcoins.placeholder.MVdWPlaceholderAPI;
import me.mobcoins.database.DatabaseConnector;

@Getter
public class MobCoins extends JavaPlugin{
	private static MobCoins instance;
	private UserManager userManager;
	private DatabaseConnector databaseConnector;
	private ConfigurationLoader configurationLoader;
	private MobsLoader mobsLoader;
	private ItensLoader itensLoader;
	private MenuFile menuFile;
	private TopManager topManager;
	private NPCManager npcManager;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		setupConfig();
		initialize();
	}

	private void initialize(){
		userManager = new UserManager(databaseConnector);
		databaseConnector = new DatabaseConnector(DatabaseConnector.Credentials.builder()
				.ip(configurationLoader.sqlHost)
				.port(configurationLoader.sqlPort)
				.database(configurationLoader.sqlDatabase)
				.password(configurationLoader.sqlPassword)
				.user(configurationLoader.sqlUser)
				.build());
		mobsLoader = new MobsLoader();
		menuFile = new MenuFile();
		itensLoader = new ItensLoader();
		topManager = new TopManager();
		npcManager = new NPCManager();
		loadCommands();
		registerListeners();
		topScheduler();
		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)new PlaceholderAPI().register();
		if(Bukkit.getPluginManager().getPlugin("MVdWPlaceholderAPI") != null)MVdWPlaceholderAPI.run();
		if(Bukkit.getPluginManager().getPlugin("Citizens") == null)
			System.out.println("�cPlugin Citizens N�o encontrado!!!");
		for(Player p : Bukkit.getOnlinePlayers())
			getUserManager().loadUser(p.getUniqueId());
	}

	public void onDisable() {
		for(Player p : Bukkit.getOnlinePlayers()){
			User user = getUserManager().getUserByUUID(p.getUniqueId());
			getUserManager().saveUser(user);
		}
		getDatabaseConnector().disconnect();
	}

	private void loadCommands(){
		BukkitFrame bukkitFrame = new BukkitFrame(this);
		bukkitFrame.registerCommands(
				new MobCoinsCommand(),
				new AddSubCommand(),
				new HelpSubCommand(),
				new RemoveSubCommand(),
				new SetSubCommand(),
				new ShopSubCommand(),
				new TopSubCommand(),
                new NPCSubCommand()
		);
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new PlayersListeners(), this);
		Bukkit.getPluginManager().registerEvents(new NPCListeners(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryListeners(), this);
		Bukkit.getPluginManager().registerEvents(new ScrollerListener(), this);
	}

	private void setupConfig() {
		configurationLoader = ConfigurationLoader.builder()
				.format(getString("Top.Format"))
				.title(getString("Top.Title"))
				.delay(getString("Top.Delay"))
				.failedMessage(getString("Mensagens.FailedMessage"))
				.invalidPlayer(getString("Mensagens.InvalidPlayer"))
				.mobCoinsGive(getString("Mensagens.MobCoinsGive"))
				.mobCoinsMessage(getString("Mensagens.MobCoinsMessage"))
				.mobDeathBoolean(Boolean.getBoolean(getString("Mensagens.MobDeathBoolean")))
				.mobDeathMessage(getString("Mensagens.MobDeathMessage"))
				.noMobCoins(getString("Mensagens.NoMobCoins"))
				.sucessMessage(getString("Mensagens.SucessMessage"))
                .npcName(getString("NPC.Nome"))
                .npcSkin(getString("NPC.Skin"))
				.sqlHost(getString("SQL.Host"))
				.sqlPort(getConfig().getInt(""))
				.sqlUser(getString("SQL.User"))
				.sqlPassword(getString("SQL.Password"))
				.sqlDatabase(getString("SQL.Database"))
				.build();
	}

	private String getString(String path){
		return getConfig().getString(path).replaceAll("&", "�");
	}

	private void topScheduler(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> getTopManager().updateTop(),
				0L, 20L * 60 * Integer.parseInt(getConfigurationLoader().delay));
	}

	public static MobCoins getInstance() {
		return instance;
	}
}