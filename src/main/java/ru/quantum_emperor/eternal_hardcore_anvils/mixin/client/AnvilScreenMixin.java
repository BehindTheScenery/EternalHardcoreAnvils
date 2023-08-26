package ru.quantum_emperor.eternal_hardcore_anvils.mixin.client;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import ru.quantum_emperor.eternal_hardcore_anvils.config.EHAConfig;

@Mixin(value = AnvilScreen.class)
public class AnvilScreenMixin {

    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40, ordinal = 0))
    private int getMax(int constant) {
        return constant * (int) EHAConfig.get().multiplier;
    }
}
