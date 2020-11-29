package me.mobcoins.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class User {
	private final UUID uuid;
	@Setter
	private int coins;
}
