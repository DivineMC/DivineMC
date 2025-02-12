package org.bxteam.divinemc.util;

import net.minecraft.Util;
import org.bxteam.divinemc.configuration.DivineConfig;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class AsyncDataSaving {
    private AsyncDataSaving() {
        throw new IllegalStateException("This class cannot be instantiated");
    }

    public static void saveAsync(Runnable runnable) {
        if (!DivineConfig.asyncPlayerDataSaveEnabled) {
            runnable.run();
            return;
        }

        ExecutorService ioExecutor = Util.backgroundExecutor().service();
        CompletableFuture.runAsync(runnable, ioExecutor);
    }
}
