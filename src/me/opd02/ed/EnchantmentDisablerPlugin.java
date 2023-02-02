package me.opd02.ed;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import me.opd02.ed.commands.MenuCommand;
import me.opd02.ed.listeners.EnchantItemListener;
import me.opd02.ed.listeners.EntityPickupItemListener;
import me.opd02.ed.listeners.InventoryOpenListener;
import me.opd02.ed.listeners.ItemClickListener;
import me.opd02.ed.listeners.PlayerJoinListener;
import me.opd02.ed.listeners.PrepareItemEnchantListener;
import me.opd02.ed.utils.ConfigUtilsEN;

public class EnchantmentDisablerPlugin extends JavaPlugin {
	
	public static HashMap<Enchantment, Boolean> blockedEnchants;
	public static ArrayList<Enchantment> allowedEnchant;
	
	public void onEnable(){
		
		Bukkit.getServer().getPluginManager().registerEvents(new PrepareItemEnchantListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ItemClickListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantItemListener(), this);
		
		if(this.getConfig().getBoolean("PurgeExistingDisabledEnchantedItemsToo") == true) {
			Bukkit.getServer().getPluginManager().registerEvents(new InventoryOpenListener(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);	
		}
		
		
		EnchantmentDisablerPlugin.blockedEnchants = new HashMap<Enchantment, Boolean>();
		
		Bukkit.getServer().getPluginCommand("ed").setExecutor(new MenuCommand(this));
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		ConfigUtilsEN.loadHashMapFromConfig(this);
		allowedEnchant = new ArrayList<Enchantment>();
		
		for(Enchantment en : EnchantmentDisablerPlugin.blockedEnchants.keySet()){
			if(EnchantmentDisablerPlugin.blockedEnchants.get(en)==false){
				EnchantmentDisablerPlugin.allowedEnchant.add(en);
			}
		}
	}
	
	public void onDisable(){
		ConfigUtilsEN.syncHashMapWithConfig(this);
	}
	
}
