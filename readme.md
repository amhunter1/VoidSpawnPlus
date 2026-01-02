# 🌌 VoidSpawnPlus - Optimized Void Teleport Plugin

> High-performance teleportation system for Minecraft servers

---

# English Documentation

## 📝 Overview

VoidSpawn is a high-performance Paper/Spigot plugin designed to automatically teleport players to safety when they fall into the void. The plugin monitors each player's Y-coordinate and instantly teleports them to a designated spawn point when they fall below a configurable threshold.

### How It Works

1. Players fall below the configured Y-level threshold for their world
2. The plugin instantly detects the void fall
3. Players are teleported to a custom spawn point (set via `/vs setspawn`)
4. If no custom spawn exists, players are sent to the world's default spawn
5. A smooth fade animation and sound effect enhance the experience

---

## ✨ Key Features

### 🎯 Core Functionality
- **Multi-World Support** - Configure unique Y-levels for each world
- **Custom Spawn Points** - Set specific teleport locations with exact orientation
- **Default Spawn Fallback** - Automatic fallback to world spawn if custom spawn not set
- **Optimized Detection** - Lag-free performance even with many players
- **Folia Support** - Fully compatible with Folia's region threading
- **bStats Integration** - Includes anonymous usage statistics

### 🎨 Player Experience
- **Smooth Fade Effect** - 2-second fade-in/fade-out screen transition
- **Customizable Messages** - Full color code support with `&` formatting
- **Sound Effects** - Configurable teleport sounds
- **Cooldown System** - Prevents teleport spam and exploitation

### 🛡️ Administration
- **Permission-Based Access** - Granular control over commands and bypass
- **Hot Reload** - Update configuration without server restart
- **Per-World Configuration** - Enable/disable void protection per world
- **Minecraft 1.20+** - Full compatibility with latest versions

---

## 🚀 Installation

### Requirements
- Minecraft Server: Paper/Spigot/Folia 1.20 or newer
- Java: 17 or higher

### Setup Steps

1. Download `VoidSpawn-1.2.0.jar`
2. Place the file in your server's `plugins/` folder
3. Restart your server
4. Configure the plugin (files created automatically):
   - `plugins/VoidSpawn/config.yml`
   - `plugins/VoidSpawn/spawns.yml`

---

## 🎮 Commands

### Administrator Commands

| Command | Description | Permission Required |
|---------|-------------|---------------------|
| `/vs setspawn` | Sets the void spawn point for current world at your location and facing direction | `voidspawn.admin` |
| `/vs reload` | Reloads the configuration from disk | `voidspawn.admin` |

**Alias:** `/voidspawn` works identically to `/vs`

---

## 🔐 Permissions

| Permission Node | Description | Default |
|----------------|-------------|---------|
| `voidspawn.admin` | Access to all `/vs` commands | OP only |
| `voidspawn.bypass` | Exempts player from void detection (useful for testing) | OP only |

---

## ⚙️ Configuration

### config.yml Structure

```yaml
# Fade Effect Settings
fade-effect-enabled: true  # Enable/disable screen fade animation

# Sound Settings
teleport-sound: ENTITY_ENDERMAN_TELEPORT  # Sound played on teleport (use 'none' to disable)

# Cooldown Settings
teleport-cooldown: 3  # Seconds before player can be teleported again

# Messages (supports & color codes)
teleport-message: "&aYou have been teleported to safety!"
spawn-not-set-message: "&eNo custom spawn set. Teleporting to world spawn..."
cooldown-message: "&cPlease wait before being teleported again."

# World-Specific Settings
worlds:
  world:
    y-level: -64      # Players below this Y-level are teleported
    enabled: true     # Enable/disable void protection for this world
  world_nether:
    y-level: -64
    enabled: true
  world_the_end:
    y-level: -64
    enabled: true
```

### Configuration Options

#### Visual Effects
- **fade-effect-enabled**: `true/false`
  - Enables smooth screen fade during teleportation
  - Duration: 2 seconds total (1s fade out, 1s fade in)

#### Audio Settings
- **teleport-sound**: Sound name or `none`
  - Default: `ENTITY_ENDERMAN_TELEPORT`
  - Must be valid Bukkit Sound enum name
  - Set to `none` to disable sound

#### Anti-Spam Protection
- **teleport-cooldown**: Integer (seconds)
  - Prevents repeated rapid teleportations
  - Default: 3 seconds
  - Helpful for preventing abuse

#### World Configuration
For each world, configure:
- **y-level**: Y-coordinate threshold (players below this are teleported)
- **enabled**: `true/false` to toggle void protection

---

## 🗺️ Setting Up Spawn Points

### Initial Setup (Required)

1. Ensure you have OP status or `voidspawn.admin` permission
2. Enter the world you want to configure
3. Stand at the exact location where players should spawn
4. Face the direction players should be facing
5. Execute: `/vs setspawn`
6. Confirmation message will appear

### What Gets Saved?
- World name
- X, Y, Z coordinates
- Yaw (horizontal rotation)
- Pitch (vertical rotation)

### Without Custom Spawn
If no custom spawn is set via `/vs setspawn`, players will:
1. Receive the `spawn-not-set-message`
2. Be teleported to the world's default spawn point
3. Still benefit from void protection

---

## 🔧 Advanced Usage

### Multiple Worlds
Configure different Y-levels for each world type:

```yaml
worlds:
  survival_world:
    y-level: -64
    enabled: true
  creative_world:
    y-level: -128
    enabled: false  # Disable in creative
  mining_world:
    y-level: -64
    enabled: true
```

### Custom Messages
Use Minecraft color codes with `&`:

```yaml
teleport-message: "&b&lVOID RESCUE &r&7» &aYou've been saved!"
spawn-not-set-message: "&e&l⚠ &7Using default spawn point..."
cooldown-message: "&c&l✖ &7Wait {cooldown}s before next teleport"
```

### Performance Tuning
The plugin is optimized by default, but you can:
- Disable fade effects for minimal overhead
- Adjust cooldowns to reduce event frequency
- Disable in creative/spectator worlds

---

## 📊 Performance Information

VoidSpawn is built with performance as a priority:

- **Optimized Event Handling**: Only processes relevant player movement events
- **Memory Efficient**: Automatic cleanup on player disconnect
- **No Tick Lag**: Asynchronous processing where possible
- **Folia Native**: Uses region-specific scheduling and async teleportation
- **Minimal Overhead**: Typically <1% CPU usage even with 100+ players

### Best Practices
- Set appropriate Y-levels (don't set too high)
- Use reasonable cooldowns (3-5 seconds recommended)
- Disable in worlds where void protection isn't needed

---

## ❓ Troubleshooting

### Players aren't being teleported
- Check if void protection is enabled for that world in config.yml
- Verify the Y-level is set correctly
- Ensure players don't have `voidspawn.bypass` permission
- Check server console for errors

### Spawn point not working
- Confirm spawn was set with `/vs setspawn`
- Check `spawns.yml` for the world entry
- Try setting the spawn again
- Verify world name matches exactly

### Performance issues
- Disable fade effects if experiencing lag
- Increase cooldown duration
- Check for conflicting plugins
- Review server TPS with `/tps` command

---

## 📞 Support

For bug reports, feature requests, or general support, please contact the development team.