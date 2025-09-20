package me.lingbopro.licensechecker.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class LicenseListScreen extends Screen {
    public LicenseListScreen(Screen parent) {
        super(Component.translatable("config.license_checker.title"));
    }

    public LicenseListScreen() {
        this(null);
    }
}
