package ru.quantum_emperor.eternal_hardcore_anvils.nbt;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class EHAEnchantment {
    public static final String ENCHANTMENT_KEY = "EHAEnchantmentsUsage";

    public static void changeEnchantmentUsage(ItemStack stack, int delta) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(ENCHANTMENT_KEY, getEnchantmentUsage(stack) + delta);

    }
    public static void setEnchantmentUsage(ItemStack stack, int amount) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(ENCHANTMENT_KEY, amount);
    }

    public static int getEnchantmentUsage(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains(ENCHANTMENT_KEY)) {
            return nbt.getInt(ENCHANTMENT_KEY);
        }
        return 0;
    }
}
