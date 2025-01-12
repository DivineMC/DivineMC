package space.bxteam.divinemc.configuration;

import com.mojang.logging.LogUtils;
import io.papermc.paper.configuration.Configuration;
import io.papermc.paper.configuration.ConfigurationPart;
import io.papermc.paper.configuration.PaperConfigurations;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.spigotmc.SpigotWorldConfig;
import org.spongepowered.configurate.objectmapping.meta.Comment;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal", "NotNullFieldNotInitialized", "InnerClassMayBeStatic"})
public class DivineWorldConfiguration extends ConfigurationPart {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final int CURRENT_VERSION = 4;

    private transient final SpigotWorldConfig spigotConfig;
    private transient final ResourceLocation worldKey;

    public DivineWorldConfiguration(SpigotWorldConfig spigotConfig, ResourceLocation worldKey) {
        this.spigotConfig = spigotConfig;
        this.worldKey = worldKey;
    }

    public boolean isDefault() {
        return this.worldKey.equals(PaperConfigurations.WORLD_DEFAULTS_KEY);
    }

    @Setting(Configuration.VERSION_FIELD)
    public int version = CURRENT_VERSION;

    public Optimizations optimizations;

    public class Optimizations extends ConfigurationPart {
        public boolean suppressErrorsFromDirtyAttributes = true;
    }

    public GameplayMechanics gameplayMechanics;

    public class GameplayMechanics extends ConfigurationPart {
        public Mob mob;

        public class Mob extends ConfigurationPart {
            public Shulker shulker;

            public class Shulker extends ConfigurationPart {
                @Comment("If true, shulker bullets will despawn when their owner dies.")
                public boolean despawnShulkerBulletsOnOwnerDeath = true;
            }
        }

        public Projectiles projectiles;

        public class Projectiles extends ConfigurationPart {
            public boolean snowballCanKnockback = true;
            public boolean eggCanKnockback = true;
            public boolean saveFireworks = false;
        }
    }
}
