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
    public final boolean enable;
    public final String host;
    public final String port;
    public final String user;
    public final String password;
    public final String database;

}
