---
outline: deep
---

# Configuration

This page details the various configuration settings exposed by Bukkit, Spigot and Paper.

If you want information on settings in **paper.yml**, **spigot.yml**, **bukkit.yml** and **server.properties** you should see their respective documentation pages.

- [Server Configuration (server.properties)](https://minecraft.wiki/w/Server.properties)

- [Bukkit Configuration (bukkit.yml)](https://bukkit.fandom.com/wiki/Bukkit.yml)

- [Spigot Configuration (spigot.yml)](https://www.spigotmc.org/wiki/spigot-configuration)

- [Paper Configuration (paper.yml)](https://docs.papermc.io/paper/reference/paper-global-configuration)

- [Pufferfish Configuration (pufferfish.yml)](https://docs.pufferfish.host/setup/pufferfish-fork-configuration)

- [Purpur Configuration (purpur.yml)](https://purpurmc.org/docs/Configuration)

::: warning

Configuration values change frequently at times. It is possible for the information here to be incomplete. If you cannot find what you’re looking for or think something may be wrong, Contact us through our [Discord](https://discord.gg/p7cxhw7E2M) server.

:::

## Global Settings

Global settings affect all worlds on the server as well as the core server functionality itself.

### verbose

- **default**: false
- **description**: Sets whether the server should dump all configuration values to the server log on startup (this maybe not working)

### settings
- #### disable-chat-reports
  - **default**: false
  - **description**: Disables chat signing, which was introduced in version 1.19.1 (similar to No Chat Reports mod)
- #### async-pathfinding
  - ##### enable
    - **default**: true
    - **description**: Enables or disables async pathfinding feature
  - ##### max-threads
    - **default**: 0
    - **description**: Specifies how many threads to use for async pathfinding. If you specify 0, then the maximum amount will be calculated by itself using the formula: `availableProcessors / 4`
  - ##### keepalive
    - **default**: 60
    - **description**: The time during which inactive threads will remain in the pool until they are completed after downtime, specified in seconds.
- #### region-format
    ##### linear
    - ###### flush-frequency
      - **default**: 10
      - **description**: Time in seconds after which chunks will be flushed
    - ###### flush-max-threads
      - **default**: 1
      - **description**: Maximum number of threads for flushing chunks
- #### do-not-process-chat-commands
  - **default**: true
  - **description**: Commands will not be proceeded while the player is joining the server
- #### optimizations
  - ##### recipe-manager-optimization
    - **default**: true
    - **description**: Enables or disables optimization of recipe manager from the CarpetFixes mod. Optimized by taking out streams & doing extra early checks to quickly remove unrelated recipes
  - ##### biome-manager-optimization
    - **default**: true
    - **description**: Enables or disables optimization of biomes from the CarpetFixes mod. Makes getBiome() method faster by 25% - 75%
  - ##### sheep-optimization
    - **default**: true
    - **description**: Enables or disables optimization of sheep color choosing from the CarpetFixes mod. The game determines the child sheep's color by getting a wool block from the parents, putting them in a crafting
recipe, getting the output wool and getting the color from that.

## world-settings

World settings are on a per-world basis. The child-node `default` is used for all worlds that do not have their own specific settings.

For a more clear explanation of the world settings section of the config, feel free to read through Paper's explanation here: https://docs.papermc.io/paper/per-world-configuration

### gameplay-mechanics
- #### boat
    - ##### dont-eject-players-from-boat-underwater
        - **default**: true
        - **description**: Players can't be ejected from boat underwater

    - ##### always-allow-to-enter-the-boat
        - **default**: true
        - **description**: Player can enter the boat anywhere
- #### mob
  - ##### shulker
    - ##### despawn-bullets-on-player-death
      - **default**: true
      - **description**: If player is dead from shulker - bullets disappering for optimization

- #### suppress-errors-from-dirty-attributes
  - **default**: true
  - **description**:

- #### region-format
  - ##### format
    - **default**: ANVIL
    - **description**: 2 types available: [ANVIL](https://minecraft.wiki/w/Anvil_file_format) and [LINEAR](https://github.com/xymb-endcrystalme/LinearRegionFileFormatTools?tab=readme-ov-file#linear-region-file-format-for-minecraft)
  - ##### linear
    - ###### compression-level
      - **default**: 1
      - **description**: Compression ratio of the region files
    - ###### crash-on-broken-symlink
      - **default**: true
      - **description**: Server crashes if the symlink is broken/damaged
