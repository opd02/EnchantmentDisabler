package me.opd02.ed.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

import me.opd02.ed.EnchantmentDisablerPlugin;

public class PrepareItemEnchantListener implements Listener {

	@EventHandler
	public void onPlayerPrepEnchant(PrepareItemEnchantEvent e){
		//Bukkit.getServer().broadcastMessage("Prep");
	
	//	Player p = e.getEnchanter();
		ArrayList<Enchantment> allowed = new ArrayList<Enchantment>();
		
		for(Enchantment en : EnchantmentDisablerPlugin.blockedEnchants.keySet()){
			if(EnchantmentDisablerPlugin.blockedEnchants.get(en) == false && en.canEnchantItem(e.getItem())){
				allowed.add(en);
			//	p.sendMessage(ChatColor.GOLD + en.toString());
			}
		}
		//p.sendMessage(ChatColor.DARK_PURPLE + "" + allowed.size() + " possible enchants");
		//p.sendMessage(ChatColor.AQUA + "" + EnchantmentDisablerPlugin.blockedEnchants.size() + " size of grand list");
		
		for(EnchantmentOffer eo : e.getOffers().clone()){
			if(eo == null){
				continue;
			}
			if(EnchantmentDisablerPlugin.blockedEnchants.get(eo.getEnchantment()) == true){
				Random rand = new Random();
				int chosen = rand.nextInt(allowed.size());
				
				//p.sendMessage(ChatColor.RED + allowed.get(chosen).toString());
				
				eo.setEnchantment(allowed.get(chosen));
				eo.setEnchantmentLevel(allowed.get(chosen).getMaxLevel());
				}

				//eo.setEnchantment(Enchantment.ARROW_DAMAGE);
				//p.sendMessage("That enchantment is blocked!");
			}
		}
	}
