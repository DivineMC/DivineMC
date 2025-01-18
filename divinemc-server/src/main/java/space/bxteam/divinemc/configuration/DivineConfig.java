package space.bxteam.divinemc.configuration;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class DivineConfig {
    private static final String HEADER = "This is the main configuration file for DivineMC.\n"
            + "If you need help with the configuration or have any questions related to DivineMC,\n"
            + "join us in our Discord server.\n"
            + "\n"
            + "Discord: https://discord.gg/p7cxhw7E2M \n"
            + "Docs: https://docs.bx-team.space/documentation/divinemc/about \n"
            + "New builds: https://github.com/DivineMC/DivineMC/releases/latest";
    private static File CONFIG_FILE;
    public static YamlConfiguration config;

    private static Map<String, Command> commands;

    public static int version;
    static boolean verbose;

    public static void init(File configFile) {
        CONFIG_FILE = configFile;
        config = new YamlConfiguration();
        try {
            config.load(CONFIG_FILE);
        } catch (IOException ignore) {
        } catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not load divinemc.yml, please correct your syntax errors", ex);
            throw Throwables.propagate(ex);
        }
        config.options().header(HEADER);
        config.options().copyDefaults(true);
        verbose = getBoolean("verbose", false);

        version = getInt("config-version", 4);
        set("config-version", 4);

        readConfig(DivineConfig.class, null);

        Block.BLOCK_STATE_REGISTRY.forEach(BlockBehaviour.BlockStateBase::initCache);
    }

    protected static void log(String s) {
        if (verbose) {
            log(Level.INFO, s);
        }
    }

    protected static void log(Level level, String s) {
        Bukkit.getLogger().log(level, s);
    }

    static void readConfig(Class<?> clazz, Object instance) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isPrivate(method.getModifiers())) {
                if (method.getParameterTypes().length == 0 && method.getReturnType() == Void.TYPE) {
                    try {
                        method.setAccessible(true);
                        method.invoke(instance);
                    } catch (InvocationTargetException ex) {
                        throw Throwables.propagate(ex.getCause());
                    } catch (Exception ex) {
                        Bukkit.getLogger().log(Level.SEVERE, "Error invoking " + method, ex);
                    }
                }
            }
        }

        try {
            config.save(CONFIG_FILE);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save " + CONFIG_FILE, ex);
        }
    }

    private static void set(String path, Object val) {
        config.addDefault(path, val);
        config.set(path, val);
    }

    private static String getString(String path, String def) {
        config.addDefault(path, def);
        return config.getString(path, config.getString(path));
    }

    private static boolean getBoolean(String path, boolean def) {
        config.addDefault(path, def);
        return config.getBoolean(path, config.getBoolean(path));
    }

    private static double getDouble(String path, double def) {
        config.addDefault(path, def);
        return config.getDouble(path, config.getDouble(path));
    }

    private static int getInt(String path, int def) {
        config.addDefault(path, def);
        return config.getInt(path, config.getInt(path));
    }

    private static <T> List getList(String path, T def) {
        config.addDefault(path, def);
        return config.getList(path, config.getList(path));
    }

    static Map<String, Object> getMap(String path, Map<String, Object> def) {
        if (def != null && config.getConfigurationSection(path) == null) {
            config.addDefault(path, def);
            return def;
        }
        return toMap(config.getConfigurationSection(path));
    }

    private static Map<String, Object> toMap(ConfigurationSection section) {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        if (section != null) {
            for (String key : section.getKeys(false)) {
                Object obj = section.get(key);
                if (obj != null) {
                    builder.put(key, obj instanceof ConfigurationSection val ? toMap(val) : obj);
                }
            }
        }
        return builder.build();
    }

    public static boolean noChatSign = true;
    private static void chatMessageSignatures() {
        noChatSign = getBoolean("settings.no-chat-sign", noChatSign);
    }

    public static boolean optimizedDragonRespawn = true;
    private static void optimizations() {
        optimizedDragonRespawn = getBoolean("settings.optimizations.optimized-dragon-respawn", optimizedDragonRespawn);
    }

    public static boolean disableNonEditableSignWarning = true;
    public static boolean removeVanillaUsernameCheck = false;
    public static boolean disableMovedWronglyThreshold = false;
    public static boolean enableSecureSeed = false;
    private static void miscSettings() {
        disableNonEditableSignWarning = getBoolean("settings.misc.disable-non-editable-sign-warning", disableNonEditableSignWarning);
        removeVanillaUsernameCheck = getBoolean("settings.misc.remove-vanilla-username-check", removeVanillaUsernameCheck);
        disableMovedWronglyThreshold = getBoolean("settings.misc.disable-moved-wrongly-threshold", disableMovedWronglyThreshold);
        enableSecureSeed = getBoolean("settings.misc.enable-secure-seed", enableSecureSeed);
    }

    public static int linearFlushFrequency = 5;
    public static boolean throwOnUnknownExtension = false;
    private static void linearSettings() {
        linearFlushFrequency = getInt("settings.region-format.linear.flush-frequency", linearFlushFrequency);
        throwOnUnknownExtension = getBoolean("settings.region-format.linear.throw-on-unknown-extension", throwOnUnknownExtension);
    }

    public static boolean asyncPathfinding = true;
    public static int asyncPathfindingMaxThreads = 0;
    public static int asyncPathfindingKeepalive = 60;
    private static void asyncPathfinding() {
        asyncPathfinding = getBoolean("settings.async-pathfinding.enable", asyncPathfinding);
        asyncPathfindingMaxThreads = getInt("settings.async-pathfinding.max-threads", asyncPathfindingMaxThreads);
        asyncPathfindingKeepalive = getInt("settings.async-pathfinding.keepalive", asyncPathfindingKeepalive);
        if (asyncPathfindingMaxThreads < 0)
            asyncPathfindingMaxThreads = Math.max(Runtime.getRuntime().availableProcessors() + asyncPathfindingMaxThreads, 1);
        else if (asyncPathfindingMaxThreads == 0)
            asyncPathfindingMaxThreads = Math.max(Runtime.getRuntime().availableProcessors() / 4, 1);
        if (!asyncPathfinding)
            asyncPathfindingMaxThreads = 0;
        else
            Bukkit.getLogger().log(Level.INFO, "Using " + asyncPathfindingMaxThreads + " threads for Async Pathfinding");
    }

    public static boolean multithreadedEnabled = false;
    public static boolean multithreadedCompatModeEnabled = false;
    public static int asyncEntityTrackerMaxThreads = 0;
    public static int asyncEntityTrackerKeepalive = 60;
    private static void multithreadedTracker() {
        multithreadedEnabled = getBoolean("settings.multithreaded-tracker.enable", multithreadedEnabled);
        multithreadedCompatModeEnabled = getBoolean("settings.multithreaded-tracker.compat-mode", multithreadedCompatModeEnabled);
        asyncEntityTrackerMaxThreads = getInt("settings.multithreaded-tracker.max-threads", asyncEntityTrackerMaxThreads);
        asyncEntityTrackerKeepalive = getInt("settings.multithreaded-tracker.keepalive", asyncEntityTrackerKeepalive);

        if (asyncEntityTrackerMaxThreads < 0)
            asyncEntityTrackerMaxThreads = Math.max(Runtime.getRuntime().availableProcessors() + asyncEntityTrackerMaxThreads, 1);
        else if (asyncEntityTrackerMaxThreads == 0)
            asyncEntityTrackerMaxThreads = Math.max(Runtime.getRuntime().availableProcessors() / 4, 1);

        if (!multithreadedEnabled)
            asyncEntityTrackerMaxThreads = 0;
        else
            Bukkit.getLogger().log(Level.INFO, "Using " + asyncEntityTrackerMaxThreads + " threads for Async Entity Tracker");
    }
}
