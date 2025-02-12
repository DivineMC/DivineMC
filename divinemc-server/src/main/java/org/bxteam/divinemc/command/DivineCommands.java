package org.bxteam.divinemc.command;

import net.minecraft.server.MinecraftServer;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.util.permissions.CraftDefaultPermissions;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.HashMap;
import java.util.Map;

@DefaultQualifier(NonNull.class)
public final class DivineCommands {
    public static final String COMMAND_BASE_PERM = CraftDefaultPermissions.DIVINEMC_ROOT + ".command";

    private DivineCommands() {}

    private static final Map<String, Command> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put(DivineCommand.COMMAND_LABEL, new DivineCommand());
    }

    public static void registerCommands(final MinecraftServer server) {
        COMMANDS.forEach((s, command) -> {
            server.server.getCommandMap().register(s, "DivineMC", command);
        });
    }
}
