package me.lingbopro.licensechecker.gui;

import dev.architectury.platform.Mod;
import me.lingbopro.licensechecker.util.LicenseUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Collection;
import java.util.Map;

import static me.lingbopro.licensechecker.LicenseChecker.LOGGER;

public class LicenseListScreen extends Screen {
    private final Screen parent;

    public LicenseListScreen(Screen parent) {
        super(Component.translatable("config.license_checker.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        Button backButton = Button.builder(Component.translatable("gui.back"), button -> this.onClose()).bounds(this.width - 100, 15, 80, 20).build();

        this.addRenderableWidget(backButton);

        // 列出许可证
        LOGGER.info("Start listing licenses");
        final Map<String, Collection<Mod>> licenses = LicenseUtils.getLicenses();
        {
            int index = 0;
            for (Map.Entry<String, Collection<Mod>> entry : licenses.entrySet()) {
                String licenseName = entry.getKey();
                Collection<Mod> mods = entry.getValue();

                Button licenseButton = Button.builder(Component.literal(licenseName), button -> {
                    // TODO
                }).bounds(15, 50 + index * 20, this.width - 30, 20).build();

                this.addRenderableWidget(licenseButton);
                index++;
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.drawString(this.font, this.title, 20, 20, 0xFFFFFFFF);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(this.parent);
    }
}
