package ru.quantum_emperor.eternal_hardcore_anvils.nbt;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class EHAEnchantment {
    public static final String ENCHANTMENT_KEY = "EHAEnchantmentsUsage";

    public static void changeEnchantmentUsage(ItemStack stack, short delta) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putShort(ENCHANTMENT_KEY, (short) (getEnchantmentUsage(stack) + delta));

    }
    public static void setEnchantmentUsage(ItemStack stack, short amount) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putShort(ENCHANTMENT_KEY, amount);
    }

    public static short getEnchantmentUsage(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains(ENCHANTMENT_KEY)) {
            return nbt.getShort(ENCHANTMENT_KEY);
        }
        return 0;
    }
}
