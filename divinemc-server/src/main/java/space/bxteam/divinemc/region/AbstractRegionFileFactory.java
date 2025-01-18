package space.bxteam.divinemc.region;

import java.io.IOException;
import java.nio.file.Path;
import net.minecraft.world.level.chunk.storage.RegionFile;
import net.minecraft.world.level.chunk.storage.RegionFileVersion;
import net.minecraft.world.level.chunk.storage.RegionStorageInfo;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AbstractRegionFileFactory {
    @Contract("_, _, _, _ -> new")
    public static @NotNull AbstractRegionFile getAbstractRegionFile(RegionStorageInfo storageKey, Path directory, Path path, boolean dsync) throws IOException {
        return getAbstractRegionFile(storageKey, directory, path, RegionFileVersion.getCompressionFormat(), dsync);
    }

    @Contract("_, _, _, _, _ -> new")
    public static @NotNull AbstractRegionFile getAbstractRegionFile(RegionStorageInfo storageKey, Path path, Path directory, RegionFileVersion compressionFormat, boolean dsync) throws IOException {
        if (path.toString().endsWith(".linear")) {
            return new LinearRegionFile(path, storageKey.linearCompressionLevel());
        } else {
            return new RegionFile(storageKey, path, directory, compressionFormat, dsync);
        }
    }
}
