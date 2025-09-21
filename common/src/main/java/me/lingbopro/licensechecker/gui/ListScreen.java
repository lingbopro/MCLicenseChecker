package me.lingbopro.licensechecker.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class ListScreen extends Screen {
    protected final Screen parent;

    protected ListScreen(Screen parent, Component component) {
        super(component);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        Button backButton = Button.builder(Component.translatable("gui.back"), button -> this.onClose()).bounds(this.width - 100, 15, 80, 20).build();

        this.addRenderableWidget(backButton);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.drawString(this.font, this.title, 20, 20, 0xFFFFFFFF);
    }
    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(this.parent);
    }

    protected void addListItem(Button.Builder builder, int index) {
        Button btn = builder.bounds(15, 50 + index * 20, this.width - 30, 20).build();
        this.addRenderableWidget(btn);
    }
}
