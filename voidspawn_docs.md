# 🌌 VoidSpawn - Optimized Void Teleport Plugin

> High-performance teleportation system for Minecraft Paper/Spigot servers

---

## 📖 Table of Contents

- [English Documentation](#english-documentation)
- [Türkçe Dokümantasyon](#türkçe-dokümantasyon)

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
- Minecraft Server: Paper/Spigot 1.20 or newer
- Java: 17 or higher

### Setup Steps

1. Download `VoidSpawn-1.0.0.jar`
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

---

---

# Türkçe Dokümantasyon

## 📝 Genel Bakış

VoidSpawn, Minecraft Paper/Spigot sunucularında oyuncuların boşluğa (void) düştüklerinde otomatik olarak güvenli bir konuma ışınlanmasını sağlayan yüksek performanslı bir eklentidir. Eklenti, her oyuncunun Y koordinatını izler ve belirlenen eşik değerin altına düştüklerinde anında önceden ayarlanmış spawn noktasına ışınlar.

### Nasıl Çalışır?

1. Oyuncular, dünyaları için yapılandırılmış Y seviyesi eşiğinin altına düşer
2. Eklenti boşluğa düşüşü anında algılar
3. Oyuncular özel spawn noktasına ışınlanır (`/vs setspawn` ile ayarlanmış)
4. Özel spawn yoksa, oyuncular dünyanın varsayılan spawn'ına gönderilir
5. Yumuşak geçiş animasyonu ve ses efekti deneyimi zenginleştirir

---

## ✨ Özellikler

### 🎯 Temel İşlevler
- **Çoklu Dünya Desteği** - Her dünya için ayrı Y seviyesi yapılandırması
- **Özel Spawn Noktaları** - Tam konum ve yönlendirme ile özel ışınlanma noktaları
- **Varsayılan Spawn Yedekleme** - Özel spawn ayarlanmamışsa otomatik dünya spawn'ı
- **Optimize Edilmiş Algılama** - Çok oyunculu ortamlarda bile lag-free performans

### 🎨 Oyuncu Deneyimi
- **Yumuşak Geçiş Efekti** - 2 saniyelik ekran karartma/açma animasyonu
- **Özelleştirilebilir Mesajlar** - `&` ile tam renk kodu desteği
- **Ses Efektleri** - Yapılandırılabilir ışınlanma sesleri
- **Bekleme Sistemi** - Spam ve kötüye kullanımı önler

### 🛡️ Yönetim
- **İzin Tabanlı Erişim** - Komutlar ve bypass için detaylı kontrol
- **Anında Yeniden Yükleme** - Sunucu kapatmadan yapılandırma güncellemesi
- **Dünya Bazlı Ayarlar** - Her dünya için void korumasını aç/kapat
- **Minecraft 1.20+** - En son sürümlerle tam uyumluluk

---

## 🚀 Kurulum

### Gereksinimler
- Minecraft Sunucusu: Paper/Spigot 1.20 veya üzeri
- Java: 17 veya üzeri

### Kurulum Adımları

1. `VoidSpawn-1.0.0.jar` dosyasını indirin
2. Dosyayı sunucunuzun `plugins/` klasörüne yerleştirin
3. Sunucunuzu yeniden başlatın
4. Eklentiyi yapılandırın (dosyalar otomatik oluşturulur):
   - `plugins/VoidSpawn/config.yml`
   - `plugins/VoidSpawn/spawns.yml`

---

## 🎮 Komutlar

### Yönetici Komutları

| Komut | Açıklama | Gerekli İzin |
|-------|----------|--------------|
| `/vs setspawn` | Bulunduğunuz konumu ve baktığınız yönü mevcut dünya için void spawn noktası olarak ayarlar | `voidspawn.admin` |
| `/vs reload` | Yapılandırmayı diskten yeniden yükler | `voidspawn.admin` |

**Alternatif:** `/voidspawn` komutu `/vs` ile aynı şekilde çalışır

---

## 🔐 İzinler

| İzin Adı | Açıklama | Varsayılan |
|----------|----------|------------|
| `voidspawn.admin` | Tüm `/vs` komutlarına erişim | Sadece OP |
| `voidspawn.bypass` | Oyuncuyu void algılamasından muaf tutar (test için kullanışlı) | Sadece OP |

---

## ⚙️ Yapılandırma

### config.yml Yapısı

```yaml
# Geçiş Efekti Ayarları
fade-effect-enabled: true  # Ekran karartma animasyonunu aç/kapat

# Ses Ayarları
teleport-sound: ENTITY_ENDERMAN_TELEPORT  # Işınlanmada çalacak ses ('none' ile devre dışı)

# Bekleme Ayarları
teleport-cooldown: 3  # Oyuncunun tekrar ışınlanabilmesi için geçmesi gereken saniye

# Mesajlar (& renk kodlarını destekler)
teleport-message: "&aGüvenli bir konuma ışınlandınız!"
spawn-not-set-message: "&eÖzel spawn ayarlanmamış. Dünya spawn'ına ışınlanıyorsunuz..."
cooldown-message: "&cTekrar ışınlanmadan önce lütfen bekleyin."

# Dünyaya Özel Ayarlar
worlds:
  world:
    y-level: -64      # Bu Y seviyesinin altındaki oyuncular ışınlanır
    enabled: true     # Bu dünya için void korumasını aç/kapat
  world_nether:
    y-level: -64
    enabled: true
  world_the_end:
    y-level: -64
    enabled: true
```

### Yapılandırma Seçenekleri

#### Görsel Efektler
- **fade-effect-enabled**: `true/false`
  - Işınlanma sırasında yumuşak ekran geçişini aktifleştirir
  - Süre: Toplam 2 saniye (1s karart, 1s aç)

#### Ses Ayarları
- **teleport-sound**: Ses adı veya `none`
  - Varsayılan: `ENTITY_ENDERMAN_TELEPORT`
  - Geçerli Bukkit Sound enum adı olmalı
  - Ses kapatmak için `none` yazın

#### Spam Önleme
- **teleport-cooldown**: Tam sayı (saniye)
  - Tekrarlanan hızlı ışınlanmaları önler
  - Varsayılan: 3 saniye
  - Kötüye kullanımı önlemek için yararlı

#### Dünya Yapılandırması
Her dünya için ayarlayın:
- **y-level**: Y koordinat eşiği (bu değerin altındaki oyuncular ışınlanır)
- **enabled**: Void korumasını açmak/kapatmak için `true/false`

---

## 🗺️ Spawn Noktası Ayarlama

### İlk Kurulum (Gerekli)

1. OP yetkisine veya `voidspawn.admin` iznine sahip olduğunuzdan emin olun
2. Yapılandırmak istediğiniz dünyaya girin
3. Oyuncuların spawn olması gereken tam konuma gidin
4. Oyuncuların bakması gereken yöne bakın
5. Komutu çalıştırın: `/vs setspawn`
6. Onay mesajı görünecektir

### Neler Kaydedilir?
- Dünya adı
- X, Y, Z koordinatları
- Yaw (yatay dönüş)
- Pitch (dikey dönüş)

### Özel Spawn Olmadan
Eğer `/vs setspawn` ile özel spawn ayarlanmamışsa, oyuncular:
1. `spawn-not-set-message` mesajını alır
2. Dünyanın varsayılan spawn noktasına ışınlanır
3. Yine de void korumasından faydalanır

---

## 🔧 Gelişmiş Kullanım

### Çoklu Dünyalar
Her dünya tipi için farklı Y seviyeleri yapılandırın:

```yaml
worlds:
  hayatta_kalma_dunyasi:
    y-level: -64
    enabled: true
  yaratici_dunya:
    y-level: -128
    enabled: false  # Yaratıcıda devre dışı
  madencilik_dunyasi:
    y-level: -64
    enabled: true
```

### Özel Mesajlar
Minecraft renk kodlarını `&` ile kullanın:

```yaml
teleport-message: "&b&lVOID KURTARMA &r&7» &aKurtarıldınız!"
spawn-not-set-message: "&e&l⚠ &7Varsayılan spawn noktası kullanılıyor..."
cooldown-message: "&c&l✖ &7Sonraki ışınlanma için {cooldown}s bekleyin"
```

### Performans Ayarları
Eklenti varsayılan olarak optimize edilmiştir, ancak:
- Minimum yük için geçiş efektlerini devre dışı bırakın
- Olay sıklığını azaltmak için bekleme sürelerini ayarlayın
- Yaratıcı/izleyici dünyalarında devre dışı bırakın

---

## 📊 Performans Bilgileri

VoidSpawn performans öncelikli olarak geliştirilmiştir:

- **Optimize Edilmiş Olay İşleme**: Yalnızca ilgili oyuncu hareket olaylarını işler
- **Bellek Verimliliği**: Oyuncu ayrıldığında otomatik temizlik
- **Tick Gecikmesi Yok**: Mümkün olan yerlerde asenkron işleme
- **Minimal Yük**: 100+ oyuncuyla bile tipik olarak <%1 CPU kullanımı

### En İyi Uygulamalar
- Uygun Y seviyeleri ayarlayın (çok yüksek ayarlamayın)
- Makul bekleme süreleri kullanın (3-5 saniye önerilir)
- Void korumasının gerekli olmadığı dünyalarda devre dışı bırakın

---

## ❓ Sorun Giderme

### Oyuncular ışınlanmıyor
- config.yml'de o dünya için void korumasının etkin olduğunu kontrol edin
- Y seviyesinin doğru ayarlandığını doğrulayın
- Oyuncuların `voidspawn.bypass` iznine sahip olmadığından emin olun
- Sunucu konsolunda hata olup olmadığını kontrol edin

### Spawn noktası çalışmıyor
- Spawn'ın `/vs setspawn` ile ayarlandığını onaylayın
- `spawns.yml` dosyasında dünya girişini kontrol edin
- Spawn'ı tekrar ayarlamayı deneyin
- Dünya adının tam olarak eşleştiğini doğrulayın

### Performans sorunları
- Lag yaşıyorsanız geçiş efektlerini devre dışı bırakın
- Bekleme süresini artırın
- Çakışan eklentileri kontrol edin
- `/tps` komutuyla sunucu TPS'ini inceleyin

---

## 📞 Destek

Hata raporları, özellik talepleri veya genel destek için lütfen geliştirme ekibiyle iletişime geçin.

---

## 📄 License

This plugin is provided as-is for use on Minecraft servers.

## 🙏 Credits

Developed with performance and user experience in mind for the Minecraft community.