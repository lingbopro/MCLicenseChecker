package me.lingbopro.licensechecker.gui;

import dev.architectury.platform.Mod;
import me.lingbopro.licensechecker.util.LicenseUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Collection;
import java.util.Map;

import static me.lingbopro.licensechecker.LicenseChecker.LOGGER;

public class LicenseListScreen extends ListScreen {

    public LicenseListScreen(Screen parent) {
        super(parent, Component.translatable("config.license_checker.title"));

    }

    @Override
    protected void init() {
        super.init();

        // 列出许可证
        LOGGER.info("Start listing licenses");
        final Map<String, Collection<Mod>> licenses = LicenseUtils.getLicenses();
        {
            int index = 0;
            for (Map.Entry<String, Collection<Mod>> entry : licenses.entrySet()) {
                String licenseName = entry.getKey();

                Button.Builder licenseButton = Button.builder(Component.literal(licenseName), button -> {
                    LOGGER.info("Opening license detail screen for " + licenseName);
                    Minecraft.getInstance().setScreen(new LicenseDetailScreen(this, licenseName));
                });

                this.addListItem(licenseButton, index);
                index++;
            }
        }

        this.draw();
    }
}
