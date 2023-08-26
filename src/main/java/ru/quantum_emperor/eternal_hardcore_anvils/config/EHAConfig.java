package ru.quantum_emperor.eternal_hardcore_anvils.config;


import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import static ru.quantum_emperor.eternal_hardcore_anvils.EternalHardcoreAnvils.MOD_ID;

@Config(name = MOD_ID)
public class EHAConfig implements ConfigData {
    public float multiplier = 3.0f;

    @Comment("Enchantment constraints for enchanted books")
    public int enchantmentConstraint = 2;

    public int enchantmentConstraintForItem = 1;

    public static EHAConfig get() {
        return AutoConfig.getConfigHolder(EHAConfig.class).getConfig();
    }
}
