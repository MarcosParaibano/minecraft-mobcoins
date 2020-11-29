package me.mobcoins.managers;

import org.bukkit.entity.EntityType;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.entity.Player;

import static me.mobcoins.MobCoins.getInstance;

public class NPCManager {

	public static void setNPC(Player p) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npc = registry.createNPC(EntityType.PLAYER, getInstance().getConfigurationLoader().npcName);
		npc.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, getInstance().getConfigurationLoader().npcSkin);
		npc.data().setPersistent(NPC.PLAYER_SKIN_USE_LATEST, false);
		if(npc.isSpawned()){
			npc.despawn();
			npc.spawn(p.getLocation());
		}
		npc.spawn(p.getLocation());
	}
}
