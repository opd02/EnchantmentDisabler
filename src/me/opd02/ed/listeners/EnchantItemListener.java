package me.opd02.ed.listeners;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import me.opd02.ed.EnchantmentDisablerPlugin;

public class EnchantItemListener implements Listener {

	@EventHandler
	public void onItemEnchant(EnchantItemEvent e){
		
		ArrayList<Enchantment> allowed = new ArrayList<Enchantment>();
		
		for(Enchantment enc : EnchantmentDisablerPlugin.blockedEnchants.keySet()){
			if(EnchantmentDisablerPlugin.blockedEnchants.get(enc) == false && enc.canEnchantItem(e.getItem())){
				allowed.add(enc);
				//p.sendMessage(enc.toString());
			}
		}
		
		
		
//**********************************************************************		
		//Sometimes cant click item to enchant because blocked enchant is ONLY enchant it was going to get
		//Player p = e.getEnchanter();
//		p.sendMessage(ChatColor.GOLD + "EVENT CALLED");
//		p.sendMessage(ChatColor.LIGHT_PURPLE + e.getEnchantsToAdd().keySet().toString());
		
		ArrayList<Enchantment> toChange = new ArrayList<Enchantment>();
		
		for(Map.Entry<Enchantment, Integer> entry : e.getEnchantsToAdd().entrySet()){
			
			if(entry.getKey() == null){
				continue;
			}
			
			if(EnchantmentDisablerPlugin.blockedEnchants.get(entry.getKey())==false){
				continue;
			}
			
			
			
			//p.sendMessage(ChatColor.GREEN + "Some enchantment was supposed to be replaced");
			toChange.add(entry.getKey());
			//e.getEnchantsToAdd().remove(entry.getKey(), e.getEnchantsToAdd().get(entry.getKey()));
			//e.getEnchantsToAdd().put(allowed.get(chosen), rand.nextInt(allowed.get(chosen).getMaxLevel() + 1));
		}
		
		for(Enchantment en : toChange){
			//Random rand = new Random();
			//int chosen = rand.nextInt(allowed.size());
			e.getEnchantsToAdd().remove(en, e.getEnchantsToAdd().get(en));
			//e.getEnchantsToAdd().put(allowed.get(chosen), (rand.nextInt(allowed.get(chosen).getMaxLevel() + 1)+1));
			//e.getEnchanter().sendMessage(ChatColor.BLUE + "" + allowed.get(chosen) + " at level " + allowed.get(chosen).getMaxLevel());
		}
	}
}
