package me.opd02.ed.utils;

import org.bukkit.enchantments.Enchantment;

import me.opd02.ed.EnchantmentDisablerPlugin;

public class ConfigUtilsEN {

	@SuppressWarnings("deprecation")
	public static void syncHashMapWithConfig(EnchantmentDisablerPlugin plugin){
		//EnchantmentDisablerPlugin.blockedEnchants.clear();
//		
//		for(String path : plugin.getConfig().getConfigurationSection("enchants").getKeys(true)){
//			EnchantmentDisablerPlugin.blockedEnchants.put(Enchantment.getByName(path), (Boolean)plugin.getConfig().getBoolean("enchants." + path));
//			//blockedEnchants.put(EnchantmentWrapper.getByKey(NamespacedKey.minecraft(path.toLowerCase())), (Boolean)this.getConfig().getBoolean("enchants." + path));
//		}
		
		for(Enchantment en : EnchantmentDisablerPlugin.blockedEnchants.keySet()){
			plugin.getConfig().set("enchants." + en.getName(), EnchantmentDisablerPlugin.blockedEnchants.get(en));
		}
		plugin.saveConfig();
	}
	
	@SuppressWarnings("deprecation")
	public static void loadHashMapFromConfig(EnchantmentDisablerPlugin plugin){
		for(String path : plugin.getConfig().getConfigurationSection("enchants").getKeys(true)){
			EnchantmentDisablerPlugin.blockedEnchants.put(Enchantment.getByName(path), (Boolean)plugin.getConfig().getBoolean("enchants." + path));
			//blockedEnchants.put(EnchantmentWrapper.getByKey(NamespacedKey.minecraft(path.toLowerCase())), (Boolean)this.getConfig().getBoolean("enchants." + path));
		}
	}
}
