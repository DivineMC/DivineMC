package org.bxteam.divinemc.region;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Locale;

public enum RegionFileFormat {
    LINEAR(".linear"),
    MCA(".mca"),
    UNKNOWN(null);

    private final String extensionName;

    RegionFileFormat(String extensionName) {
        this.extensionName = extensionName;
    }

    public String getExtensionName() {
        return this.extensionName;
    }

    @Contract(pure = true)
    public static RegionFileFormat fromName(@NotNull String name) {
        switch (name.toUpperCase(Locale.ROOT)) {
            default -> {
                return UNKNOWN;
            }

            case "MCA" -> {
                return MCA;
            }

            case "LINEAR" -> {
                return LINEAR;
            }
        }
    }

    @Contract(pure = true)
    public static RegionFileFormat fromExtension(@NotNull String name) {
        switch (name.toLowerCase()) {
            case "mca" -> {
                return MCA;
            }

            case "linear" -> {
                return LINEAR;
            }

            default -> {
                return UNKNOWN;
            }
        }
    }
}
