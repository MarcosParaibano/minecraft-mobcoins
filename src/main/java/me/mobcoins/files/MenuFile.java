package me.mobcoins.files;

import me.mobcoins.MobCoins;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class MenuFile {

    private final File file;
    private final FileConfiguration fileconfig;


    public MenuFile() {
        file = new File(MobCoins.getInstance().getDataFolder(), "menu.yml");
        fileconfig = YamlConfiguration.loadConfiguration(file);
        if (!(file.exists())) {
            try {
                file.createNewFile();
                Bukkit.getConsoleSender().sendMessage("§aArquivo menu.yml nao existia, criando...");
                getConfig().options().header("Siga todos os exemplos abaixo, não tente inventar nada além.");
                loadConfig();
                MobCoins.getInstance().saveResource("menu.yml", true);
            } catch (Exception e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("§cErro ao criar o arquivo menu.yml");
            }
        }
    }

    public FileConfiguration getConfig() {
        return fileconfig;
    }

    public void saveConfig() {
        try {
            fileconfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cErro ao salvar o arquivo menu.yml");
        }
    }

    public void loadConfig() {
        getConfig().set("Menu.Nome", "&bMenu Nome Legal");
        getConfig().set("Itens.Pao.ID", "282;0");
        getConfig().set("Itens.Pao.Nome", "&ePao legal");
        getConfig().set("Itens.Pao.Lore", Arrays.asList("&3Pao do nagato", "&eCusto: 100 mobcoins"));
        getConfig().set("Itens.Pao.Valor", "100");
        getConfig().set("Itens.Pao.Comandos", Arrays.asList("give %player% bread 64", "katon no jutsu command"));
        saveConfig();
    }
}
