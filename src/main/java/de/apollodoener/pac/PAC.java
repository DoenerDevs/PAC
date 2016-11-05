package de.apollodoener.pac;

import org.bukkit.plugin.java.JavaPlugin;

public class PAC extends JavaPlugin {

	private static PAC instance;

	@Override
	public void onEnable() {
		instance = null;
	}

	@Override
	public void onDisable() {

	}

	public static PAC getInstance() {
		return instance;
	}
}
