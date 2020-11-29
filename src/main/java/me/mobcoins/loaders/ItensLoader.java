package me.mobcoins.loaders;

import lombok.Getter;
import me.mobcoins.items.ItemBuilder;
import me.mobcoins.objects.Itens;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static me.mobcoins.MobCoins.getInstance;

@Getter
public class ItensLoader {
    ArrayList<Itens> itensArrayList = new ArrayList<>();

    public ItensLoader() {
        ConfigurationSection cs = getInstance().getMenuFile().getConfig().getConfigurationSection("Itens");
        for (String item : cs.getKeys(false)) {
            String material = getInstance().getMenuFile().getConfig().getString("Itens." + item + ".ID");
            int valor = getInstance().getMenuFile().getConfig().getInt("Itens." + item + ".Valor");
            String nome = getInstance().getMenuFile().getConfig().getString("Itens." + item + ".Nome");
            List<String> lore = getInstance().getMenuFile().getConfig().getStringList("Itens." + item + ".Lore");
            List<String> comandos = getInstance().getMenuFile().getConfig().getStringList("Itens." + item + ".Comandos");

            String[] split = material.split(";");
            int id = Integer.parseInt(split[0]);
            int data = Integer.parseInt(split[1]);

            ItemStack itemStack = new ItemBuilder(Material.getMaterial(id), 1, (short) data)
                    .setDisplayName(nome)
                    .setLore(lore.stream().map(l -> l.replaceAll("&", "§")).collect(Collectors.toList()))
                    .build();

            itensArrayList.add(new Itens(itemStack, comandos, valor));
        }
    }
}
