package me.opd02.ed.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import me.opd02.ed.EnchantmentDisablerPlugin;

public class EntityPickupItemListener implements Listener {

	@EventHandler
	public void onItemPickUp(EntityPickupItemEvent e){
		
		
		
		ItemStack i = e.getItem().getItemStack();
		
		for(Enchantment en : i.getEnchantments().keySet()){
			if(!EnchantmentDisablerPlugin.allowedEnchant.contains(en)){
				i.removeEnchantment(en);
			}
		}
	}
}
