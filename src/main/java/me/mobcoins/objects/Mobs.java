package me.mobcoins.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.EntityType;

@Getter
@AllArgsConstructor
public class Mobs {
	private final int value;
	private final EntityType type;
}
