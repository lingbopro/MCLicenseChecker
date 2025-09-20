package me.lingbopro.licensechecker.neoforge;

import me.lingbopro.licensechecker.LicenseChecker;
import me.lingbopro.licensechecker.gui.LicenseListScreen;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(LicenseChecker.MOD_ID)
public final class LicenseCheckerNeoForge {
    public LicenseCheckerNeoForge(ModContainer container) {
        // Run our common setup.
        LicenseChecker.init();

        container.registerExtensionPoint(IConfigScreenFactory.class, (cont, screen) -> new LicenseListScreen(screen));
    }
}
