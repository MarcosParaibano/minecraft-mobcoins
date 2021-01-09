package me.mobcoins.loaders;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class ConfigurationLoader {
    //TOP
    public final String format;
    public final String title;
    public final String delay;

    //Messages
    public final String mobCoinsMessage;
    public final Boolean mobDeathBoolean;
    public final String mobDeathMessage;
    public final String sucessMessage;
    public final String failedMessage;
    public final String invalidPlayer;
    public final String mobCoinsGive;
    public final String noMobCoins;

    //NPC
    public final String npcName;
    public final String npcSkin;

    //SQL
    public final String sqlHost;
    public final int sqlPort;
    public final String sqlUser;
    public final String sqlPassword;
    public final String sqlDatabase;

}
