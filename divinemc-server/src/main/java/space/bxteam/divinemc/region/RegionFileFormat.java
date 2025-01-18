package space.bxteam.divinemc.region;

public enum RegionFileFormat {
    ANVIL,
    LINEAR,
    INVALID;

    public static RegionFileFormat fromString(String format) {
        for (RegionFileFormat rff : values()) {
            if (rff.name().equalsIgnoreCase(format)) {
                return rff;
            }
        }
        return RegionFileFormat.INVALID;
    }
}
