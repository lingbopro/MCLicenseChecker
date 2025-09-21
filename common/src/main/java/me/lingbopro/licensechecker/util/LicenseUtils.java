package me.lingbopro.licensechecker.util;

import dev.architectury.platform.Mod;
import dev.architectury.platform.Platform;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class LicenseUtils {
    /**
     * 缓存许可证信息的映射表，键为许可证名称，值为拥有该许可证的Mod集合
     */
    @Nullable
    public static Map<String, Collection<Mod>> licenseCache = null;

    /**
     * 获取所有Mod的许可证信息映射表
     */
    public static Map<String, Collection<Mod>> getLicenses() {
        // 如果缓存已存在，直接返回缓存数据
        if (licenseCache != null)
            return licenseCache;

        // 获取所有Mod并构建许可证映射表
        Collection<Mod> mods = Platform.getMods();
        Map<String, Collection<Mod>> licenseList = new HashMap<>();

        // 遍历所有Mod，按许可证分类
        mods.forEach(mod -> {
            Collection<String> modLicense = mod.getLicense();
            Collection<String> license = modLicense == null ? new ArrayList<>() : new ArrayList<>(modLicense);
            // 如果Mod没有许可证，则标记为"NONE"
            if (license.isEmpty())
                license.add("NONE");
            license.forEach(licenseName -> {
                // 如果该许可证尚未在映射表中，创建新的条目
                if (!licenseList.containsKey(licenseName))
                    licenseList.put(licenseName, new ArrayList<>());
                // 将当前Mod添加到对应许可证的集合中
                licenseList.get(licenseName).add(mod);
            });
        });
        licenseCache = licenseList;
        return licenseList;
    }

}
