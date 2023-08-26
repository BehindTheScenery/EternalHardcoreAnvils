package ru.quantum_emperor.eternal_hardcore_anvils.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import ru.quantum_emperor.eternal_hardcore_anvils.config.EHAConfig;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(EHAConfig.class, parent).get();
    }
}
