package me.opd02.ed.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.opd02.ed.EnchantmentDisablerPlugin;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		Inventory inv = p.getInventory();
		
		for(ItemStack i : inv.getContents()){
			if(i==null || i.getType().equals(Material.AIR)){
				continue;
			}
			for(Enchantment en : i.getEnchantments().keySet()){
				if(!EnchantmentDisablerPlugin.allowedEnchant.contains(en)){
					i.removeEnchantment(en);
				}
			}
		}
	}
}
