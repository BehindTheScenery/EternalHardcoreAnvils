package ru.quantum_emperor.eternal_hardcore_anvils.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import ru.quantum_emperor.eternal_hardcore_anvils.config.EHAConfig;
import ru.quantum_emperor.eternal_hardcore_anvils.nbt.EHAEnchantment;

import java.util.Iterator;
import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    @Shadow
    @Final
    private Property levelCost;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/AnvilScreenHandler;sendContentUpdates()V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void multiplyResult(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2) {
        if (!(k == i && k > 0 && this.levelCost.get() >= 40))
            this.levelCost.set((this.levelCost.get() - k) * (int) EHAConfig.get().multiplier + k);
    }

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    private void setEnchantmentUsage(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map map, boolean bl, Map map2, boolean bl2, boolean bl3, Iterator var12, Enchantment enchantment, int q, int r, boolean bl4, int s) {
        int enchantmentUsage = Math.max(EHAEnchantment.getEnchantmentUsage(itemStack), EHAEnchantment.getEnchantmentUsage(itemStack3));
        EHAEnchantment.changeEnchantmentUsage(itemStack2, enchantmentUsage + 1);
    }

    @Inject(method = "updateResult", locals = LocalCapture.CAPTURE_FAILSOFT, at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;"), cancellable = true)
    private void cancelAnvil(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map map, boolean bl, Map map2, boolean bl2, boolean bl3) {
        int enchantmentUsage = Math.max(EHAEnchantment.getEnchantmentUsage(itemStack), EHAEnchantment.getEnchantmentUsage(itemStack3));
        if (itemStack.isOf(Items.ENCHANTED_BOOK) && itemStack3.isOf(Items.ENCHANTED_BOOK) && enchantmentUsage >= EHAConfig.get().enchantmentConstraint) {
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            ci.cancel();
        }
        if (itemStack.isDamageable() && itemStack3.isOf(Items.ENCHANTED_BOOK)
                && EHAEnchantment.getEnchantmentUsage(itemStack) >= EHAConfig.get().enchantmentConstraintForItem) {
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            ci.cancel();
        }
    }
}
