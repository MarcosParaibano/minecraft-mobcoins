package me.mobcoins.objects;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public class Itens {
	private final ItemStack item;
	private final List<String> commands;
	private final int value;
}
