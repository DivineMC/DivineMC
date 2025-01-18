package space.bxteam.divinemc.command;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.Nullable;

public abstract class DivineSubCommandPermission implements DivineSubCommand {
    public final Permission permission;

    protected DivineSubCommandPermission(Permission permission) {
        this.permission = permission;
    }

    protected DivineSubCommandPermission(String permission, PermissionDefault permissionDefault) {
        this(new Permission(permission, permissionDefault));
    }

    @Override
    public boolean testPermission(CommandSender sender) {
        return sender.hasPermission(this.permission);
    }

    @Override
    public @Nullable Permission getPermission() {
        return this.permission;
    }
}
