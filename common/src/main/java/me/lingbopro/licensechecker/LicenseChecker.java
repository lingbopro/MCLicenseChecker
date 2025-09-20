package me.lingbopro.licensechecker;

import com.mojang.logging.LogUtils;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import org.slf4j.Logger;

public final class LicenseChecker {
    public static final String MOD_ID = "licensechecker";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(MOD_ID, Registries.MENU);

    public static void init() {
        // Write common init code here.
        LOGGER.info("Mod Startup");

        ClientLifecycleEvent.CLIENT_SETUP.register(client -> {});
    }
}
