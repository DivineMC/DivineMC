package space.bxteam.divinemc.configuration;

import io.papermc.paper.configuration.Configuration;
import io.papermc.paper.configuration.ConfigurationPart;
import org.bukkit.Bukkit;
import org.spongepowered.configurate.objectmapping.meta.PostProcess;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.logging.Level;

@SuppressWarnings({"CanBeFinal", "FieldCanBeLocal", "FieldMayBeFinal", "NotNullFieldNotInitialized", "InnerClassMayBeStatic"})
public class DivineGlobalConfiguration extends ConfigurationPart {
    static final int CURRENT_VERSION = 4;

    private static DivineGlobalConfiguration instance;

    public static DivineGlobalConfiguration get() {
        return instance;
    }

    static void set(DivineGlobalConfiguration instance) {
        DivineGlobalConfiguration.instance = instance;
    }

    @Setting(Configuration.VERSION_FIELD)
    public int version = CURRENT_VERSION;

    public AsyncPathfinding asyncPathfinding;

    public class AsyncPathfinding extends ConfigurationPart {
        public boolean asyncPathfinding = true;
        public int asyncPathfindingMaxThreads = 0;
        public int asyncPathfindingKeepalive = 60;

        @PostProcess
        public void init() {
            if (asyncPathfindingMaxThreads < 0) {
                asyncPathfindingMaxThreads = Math.max(Runtime.getRuntime().availableProcessors() + asyncPathfindingMaxThreads, 1);
            } else if (asyncPathfindingMaxThreads == 0) {
                asyncPathfindingMaxThreads = Math.max(Runtime.getRuntime().availableProcessors() / 4, 1);
            }

            if (!asyncPathfinding) {
                asyncPathfindingMaxThreads = 0;
            } else {
                Bukkit.getLogger().log(Level.INFO, "Using " + asyncPathfindingMaxThreads + " threads for Async Pathfinding");
            }
        }
    }

    public MultithreadTracker multithreadTracker;

    public class MultithreadTracker extends ConfigurationPart {
        public boolean multithreadedEnabled = false;
        public boolean multithreadedCompatModeEnabled = false;
        public int asyncEntityTrackerMaxThreads = 0;
        public int asyncEntityTrackerKeepalive = 60;

        @PostProcess
        public void init() {
            if (asyncEntityTrackerMaxThreads < 0) {
                asyncEntityTrackerMaxThreads = Math.max(Runtime.getRuntime().availableProcessors() + asyncEntityTrackerMaxThreads, 1);
            } else if (asyncEntityTrackerMaxThreads == 0) {
                asyncEntityTrackerMaxThreads = Math.max(Runtime.getRuntime().availableProcessors() / 4, 1);
            }

            if (!multithreadedEnabled) {
                asyncEntityTrackerMaxThreads = 0;
            } else {
                Bukkit.getLogger().log(Level.INFO, "Using " + asyncEntityTrackerMaxThreads + " threads for Async Entity Tracker");
            }
        }
    }

    public Optimizations optimizations;

    public class Optimizations extends ConfigurationPart {
        public boolean optimizedDragonRespawn = true;
    }

    public Chat chat;

    public class Chat extends ConfigurationPart {
        public boolean noChatSign = true;
    }

    public Misc misc;

    public class Misc extends ConfigurationPart {
        public boolean disableNonEditableSignWarning = true;
        public boolean removeVanillaUsernameCheck = false;
        public boolean disableMovedWronglyThreshold = false;
        public boolean enableSecureSeed = false;
    }
}
