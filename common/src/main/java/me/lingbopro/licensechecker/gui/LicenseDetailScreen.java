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

public class LicenseDetailScreen extends Screen {
    private final Screen parent;
    private final String licenseName;

    protected LicenseDetailScreen(Screen parent, String licenseName) {
        super(Component.literal(licenseName));
        this.parent = parent;
        this.licenseName = licenseName;
    }

    @Override
    protected void init() {
        super.init();

        Button backButton = Button.builder(Component.translatable("gui.back"), button -> this.onClose()).bounds(this.width - 100, 15, 80, 20).build();

        this.addRenderableWidget(backButton);

        // 列出模组
        LOGGER.info("Start listing mods");
        final Map<String, Collection<Mod>> licenses = LicenseUtils.getLicenses();
        // filter mods with specific licenses
        Collection<Mod> mods = licenses.get(licenseName);
        {
            int index = 0;
            for (Mod mod : mods) {
                Button modButton = Button.builder(Component.literal(mod.getName()), button -> {
                    // TODO
                }).bounds(15, 50 + index * 20, this.width - 30, 20).build();

                this.addRenderableWidget(modButton);
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
