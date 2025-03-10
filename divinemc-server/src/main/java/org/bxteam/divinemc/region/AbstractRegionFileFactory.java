package org.bxteam.divinemc.region;

import java.io.IOException;
import java.nio.file.Path;
import net.minecraft.world.level.chunk.storage.RegionFile;
import net.minecraft.world.level.chunk.storage.RegionFileVersion;
import net.minecraft.world.level.chunk.storage.RegionStorageInfo;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.bxteam.divinemc.configuration.DivineConfig;

public class AbstractRegionFileFactory {
    @Contract("_, _, _, _ -> new")
    public static @NotNull AbstractRegionFile getAbstractRegionFile(RegionStorageInfo storageKey, Path directory, Path path, boolean dsync) throws IOException {
        return getAbstractRegionFile(storageKey, directory, path, RegionFileVersion.getCompressionFormat(), dsync);
    }

    @Contract("_, _, _, _, _ -> new")
    public static @NotNull AbstractRegionFile getAbstractRegionFile(RegionStorageInfo storageKey, Path directory, Path path, boolean dsync, boolean canRecalcHeader) throws IOException {
        return getAbstractRegionFile(storageKey, directory, path, RegionFileVersion.getCompressionFormat(), dsync, canRecalcHeader);
    }

    @Contract("_, _, _, _, _ -> new")
    public static @NotNull AbstractRegionFile getAbstractRegionFile(RegionStorageInfo storageKey, Path path, Path directory, RegionFileVersion compressionFormat, boolean dsync) throws IOException {
        return getAbstractRegionFile(storageKey, path, directory, compressionFormat, dsync, true);
    }

    @Contract("_, _, _, _, _, _ -> new")
    public static @NotNull AbstractRegionFile getAbstractRegionFile(RegionStorageInfo storageKey, @NotNull Path path, Path directory, RegionFileVersion compressionFormat, boolean dsync, boolean canRecalcHeader) throws IOException {
        final String fullFileName = path.getFileName().toString();
        final String[] fullNameSplit = fullFileName.split("\\.");
        final String extensionName = fullNameSplit[fullNameSplit.length - 1];
        switch (RegionFileFormat.fromExtension(extensionName)) {
            case UNKNOWN -> {
                if (DivineConfig.throwOnUnknownExtension) {
                    throw new IllegalArgumentException("Unknown region file extension for file: " + fullFileName + "!");
                }

                return new RegionFile(storageKey, path, directory, compressionFormat, dsync);
            }

            case LINEAR -> {
                return new LinearRegionFile(path, storageKey.linearCompressionLevel());
            }

            default -> {
                return new RegionFile(storageKey, path, directory, compressionFormat, dsync);
            }
        }
    }
}
