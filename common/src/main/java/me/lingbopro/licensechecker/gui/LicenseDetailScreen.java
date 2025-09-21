package me.lingbopro.licensechecker.gui;

import dev.architectury.platform.Mod;
import me.lingbopro.licensechecker.util.LicenseUtils;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Collection;
import java.util.Map;

import static me.lingbopro.licensechecker.LicenseChecker.LOGGER;

public class LicenseDetailScreen extends ListScreen {
    private final String licenseName;

    protected LicenseDetailScreen(Screen parent, String licenseName) {
        super(parent, Component.literal(licenseName));
        this.licenseName = licenseName;
    }

    @Override
    protected void init() {
        super.init();

        // 列出模组
        LOGGER.info("Start listing mods");
        final Map<String, Collection<Mod>> licenses = LicenseUtils.getLicenses();
        // filter mods with specific licenses
        Collection<Mod> mods = licenses.get(licenseName);
        {
            int index = 0;
            for (Mod mod : mods) {
                Button.Builder modButton = Button.builder(Component.literal(mod.getName()), button -> {
                    // TODO
                });

                this.addListItem(modButton, index);
                index++;
            }
        }
    }
}
