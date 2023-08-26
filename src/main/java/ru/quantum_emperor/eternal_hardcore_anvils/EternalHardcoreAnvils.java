package ru.quantum_emperor.eternal_hardcore_anvils;

import com.google.gson.Gson;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import ru.quantum_emperor.eternal_hardcore_anvils.config.EHAConfig;

public class EternalHardcoreAnvils implements ModInitializer {
    public static final String MOD_ID = "eternal_hardcore_anvils";

    @Override
    public void onInitialize() {
        AutoConfig.register(EHAConfig.class, GsonConfigSerializer::new);
    }
}
