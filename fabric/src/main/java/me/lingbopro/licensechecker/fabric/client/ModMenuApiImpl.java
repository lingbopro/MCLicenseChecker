package me.lingbopro.licensechecker.fabric.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.lingbopro.licensechecker.gui.LicenseListScreen;

public class ModMenuApiImpl implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return LicenseListScreen::new;
    }
}
