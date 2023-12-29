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

### region-format
 - #### linear
   - ##### flush-frequency
     - **default**: 10
     - **description**: Time in seconds after which chunks will be flushed
   - ##### flush-max-threads
     - **default**: 1
     - **description**: Maximum number of threads for flushing chunks

## world-settings

World settings are on a per-world basis. The child-node `default` is used for all worlds that do not have their own specific settings.

For a more clear explanation of the world settings section of the config, feel free to read through Paper's explanation here: https://docs.papermc.io/paper/per-world-configuration

### region-format
- #### format
  - **default**: ANVIL
  - **description**: 2 types available: [ANVIL](https://minecraft.wiki/w/Anvil_file_format) and [LINEAR](https://github.com/xymb-endcrystalme/LinearRegionFileFormatTools?tab=readme-ov-file#linear-region-file-format-for-minecraft)
- #### linear
  - ##### compression-level
    - **default**: 1
    - **description**: Compression ratio of the region files
  - ##### crash-on-broken-symlink
    - **default**: true
    - **description**: Server crashes if the symlink is broken/damaged

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
