package space.bxteam.divinemc.configuration;

import io.papermc.paper.configuration.Configuration;
import io.papermc.paper.configuration.ConfigurationPart;
import org.spongepowered.configurate.objectmapping.meta.Setting;

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
}
