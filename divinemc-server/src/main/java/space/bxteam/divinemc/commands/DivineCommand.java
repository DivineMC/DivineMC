package space.bxteam.divinemc.commands;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import space.bxteam.divinemc.configuration.DivineConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DivineCommand extends Command {
    public DivineCommand(String name) {
        super(name);
        this.description = "DivineMC related commands";
        this.usageMessage = "/divinemc [reload | version]";
        this.setPermission("bukkit.command.divinemc");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
        if (args.length == 1) {
            return Stream.of("reload", "version")
                    .filter(arg -> arg.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            Command.broadcastCommandMessage(sender, ChatColor.RED + "Please note that this command is not supported and may cause issues.");
            Command.broadcastCommandMessage(sender, ChatColor.RED + "If you encounter any issues please use the /stop command to restart your server.");

            MinecraftServer console = MinecraftServer.getServer();
            DivineConfig.init((File) console.options.valueOf("divinemc-settings"));
            for (ServerLevel level : console.getAllLevels()) {
                level.divinemcConfig.init();
                level.resetBreedingCooldowns();
            }
            console.server.reloadCount++;

            Command.broadcastCommandMessage(sender, ChatColor.GREEN + "DivineMC config reload complete.");
        } else if (args[0].equalsIgnoreCase("version")) {
            Command verCmd = org.bukkit.Bukkit.getServer().getCommandMap().getCommand("version");
            if (verCmd != null) {
                return verCmd.execute(sender, commandLabel, new String[0]);
            }
        }

        return true;
    }
}
