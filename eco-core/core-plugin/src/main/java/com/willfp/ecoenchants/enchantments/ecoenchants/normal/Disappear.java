package com.willfp.ecoenchants.enchantments.ecoenchants.normal;

import com.willfp.ecoenchants.enchantments.EcoEnchant;
import com.willfp.ecoenchants.enchantments.EcoEnchants;
import com.willfp.ecoenchants.enchantments.meta.EnchantmentType;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
public class Disappear extends EcoEnchant {
    public Disappear() {
        super(
                "disappear", EnchantmentType.NORMAL
        );
    }

    // START OF LISTENERS

    @Override
    public void onDamageWearingArmor(LivingEntity victim, int level, EntityDamageEvent event) {
        this.plugin.getScheduler().runLater(() -> {
            if(victim.getHealth() > EcoEnchants.DISAPPEAR.getConfig().getInt(EcoEnchants.CONFIG_LOCATION + "threshold"))
                return;

            int ticksPerLevel = this.getConfig().getInt(EcoEnchants.CONFIG_LOCATION + "ticks-per-level");
            final int ticks = ticksPerLevel * level;
            victim.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, ticks, 1, false, false, true));
        }, 1);
    }
}
