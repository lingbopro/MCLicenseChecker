package me.lingbopro.licensechecker.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ListScreen extends Screen {
    protected final Screen parent;
    protected Collection<Button.Builder> items = new ArrayList<>();

    protected int currentPage = 0;
    protected int itemsPerPage = 0;

    protected ListScreen(Screen parent, Component component) {
        super(component);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        items.clear();
    }

    protected void draw() {
        Button backButton = Button.builder(Component.translatable("gui.back"), button -> this.onClose()).bounds(this.width - 95, 15, 80, 20).build();
        this.addRenderableWidget(backButton);

        itemsPerPage = (this.height - 100) / 20;

        Button prevButton = Button.builder(Component.literal("<"), button -> {
            if (currentPage > 0) {
                currentPage--;
            }
            this.clearWidgets();
            this.draw();
        }).bounds(15, 40, 20, 20).build();
        this.addRenderableWidget(prevButton);
        Button nextButton = Button.builder(Component.literal(">"), button -> {
            if (currentPage < items.size() / itemsPerPage) {
                currentPage++;
            }
            this.clearWidgets();
            this.draw();
        }).bounds(this.width - 35, 40, 20, 20).build();
        this.addRenderableWidget(nextButton);

        {
            int index = 0;
            for (Button.Builder item : items) {
                if (index < (currentPage + 1) * itemsPerPage && index >= currentPage * itemsPerPage) {
                    Button btn = item.bounds(15, 70 + (index - currentPage * itemsPerPage) * 20, this.width - 30, 20).build();
                    this.addRenderableWidget(btn);
                }
                index++;
            }
        }
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.drawString(this.font, this.title, 20, 20, 0xFFFFFFFF);

        // draw page indicator
        guiGraphics.drawCenteredString(this.font, Component.literal((currentPage + 1) + "/" + (items.size() / itemsPerPage + 1)), this.width / 2, 45,
                0xFFFFFFFF);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(this.parent);
    }

    protected void addListItem(Button.Builder builder, int index) {
        this.items.add(builder);
    }
}
