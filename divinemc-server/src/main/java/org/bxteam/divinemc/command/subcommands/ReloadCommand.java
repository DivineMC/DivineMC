package org.bxteam.divinemc.command.subcommands;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.permissions.PermissionDefault;
import org.bxteam.divinemc.command.DivineCommand;
import org.bxteam.divinemc.command.DivineSubCommandPermission;
import org.bxteam.divinemc.configuration.DivineConfig;

import java.io.File;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

public final class ReloadCommand extends DivineSubCommandPermission {
    public final static String LITERAL_ARGUMENT = "reload";
    public static final String PERM = DivineCommand.BASE_PERM + "." + LITERAL_ARGUMENT;

    public ReloadCommand() {
        super(PERM, PermissionDefault.OP);
    }

    @Override
    public boolean execute(final CommandSender sender, final String subCommand, final String[] args) {
        this.doReload(sender);
        return true;
    }

    private void doReload(final CommandSender sender) {
        Command.broadcastCommandMessage(sender, text("Please note that this command is not supported and may cause issues.", RED));
        Command.broadcastCommandMessage(sender, text("If you encounter any issues please use the /stop command to restart your server.", RED));

        MinecraftServer server = ((CraftServer) sender.getServer()).getServer();
        DivineConfig.init((File) server.options.valueOf("divinemc-settings"));
            for (ServerLevel level : server.getAllLevels()) {
                level.divinemcConfig.init();
                level.resetBreedingCooldowns();
            }
            server.server.reloadCount++;

        Command.broadcastCommandMessage(sender, text("DivineMC config reload complete.", GREEN));
    }
}
