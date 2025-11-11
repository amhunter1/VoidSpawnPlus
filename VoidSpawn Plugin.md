# VoidSpawn Plugin

**Minecraft Paper 1.20+ için performans optimize edilmiş void spawn teleport plugini**

## 📋 Özellikler

- ✅ **Paper API 1.20+ desteği** - Tüm 1.20 ve üstü sürümlerle uyumlu
- ✅ **Dünya bazlı Y seviyesi kontrolü** - Her dünya için ayrı Y koordinatı ayarlayabilme
- ✅ **Anında teleport + Fade efekti** - 2 saniyelik karartma/açma animasyonu
- ✅ **Ses efekti desteği** - Config'den ayarlanabilir teleport sesi
- ✅ **Renkli mesajlar** - Minecraft renk kodları desteği (&a, &c vb.)
- ✅ **Permission sistemi** - Admin ve bypass izinleri
- ✅ **Cooldown sistemi** - Spam önleme mekanizması
- ✅ **Bellek optimizasyonu** - HashMap cache, player cleanup, minimal event checking
- ✅ **Performans odaklı** - Sunucu lag'i yaratmayan optimize kod yapısı

## 🚀 Kurulum

1. **VoidSpawn-1.0.0.jar** dosyasını sunucunuzun `plugins/` klasörüne kopyalayın
2. Sunucuyu başlatın veya yeniden başlatın
3. Plugin otomatik olarak `plugins/VoidSpawn/` klasöründe config dosyalarını oluşturacaktır

## ⚙️ Konfigürasyon

### config.yml

```yaml
# Fade efekti (ekran karartma/açma)
fade-effect-enabled: true

# Teleport sesi (none yazarak kapatabilirsiniz)
teleport-sound: ENTITY_ENDERMAN_TELEPORT
sound-volume: 1.0
sound-pitch: 1.0

# Cooldown süresi (saniye)
teleport-cooldown: 3

# Mesajlar (& ile renk kodları kullanabilirsiniz)
teleport-message: '&6[VoidSpawn] &eYou have been teleported to safety!'
spawn-not-set-message: '&6[VoidSpawn] &cWarning: Custom spawn not set for this world!'
spawn-set-message: '&6[VoidSpawn] &aSpawn location set successfully for world &e{world}&a!'
cooldown-message: '&6[VoidSpawn] &cPlease wait before teleporting again!'
reload-message: '&6[VoidSpawn] &aConfiguration reloaded successfully!'

# Dünya ayarları
worlds:
  world:
    y-level: -64
    enabled: true
  world_nether:
    y-level: 0
    enabled: true
  world_the_end:
    y-level: -64
    enabled: true
```

### Dünya Ekleme

Yeni bir dünya eklemek için config.yml'e şu formatı ekleyin:

```yaml
worlds:
  dunya_adi:
    y-level: -64  # Bu Y seviyesinin altına düşen oyuncular ışınlanır
    enabled: true  # Bu dünyada plugin aktif mi?
```

## 🎮 Komutlar

| Komut | Açıklama | İzin |
|-------|----------|------|
| `/vs setspawn` | Bulunduğunuz konumu o dünya için spawn noktası olarak ayarlar | `voidspawn.admin` |
| `/vs reload` | Config dosyasını yeniden yükler | `voidspawn.admin` |

**Alias:** `/voidspawn` komutu da `/vs` ile aynı şekilde çalışır

## 🔐 İzinler

| İzin | Açıklama | Varsayılan |
|------|----------|------------|
| `voidspawn.admin` | Admin komutlarına erişim | OP |
| `voidspawn.bypass` | Void teleportasyonundan muaf olma | Yok |

## 📝 Kullanım

1. Sunucuya OP veya `voidspawn.admin` izni olan bir hesapla giriş yapın
2. Oyuncuların ışınlanmasını istediğiniz konuma gidin
3. `/vs setspawn` komutunu kullanın
4. Artık o dünyada belirlediğiniz Y seviyesinin altına düşen oyuncular bu konuma ışınlanacaktır

## 🛠️ Performans Optimizasyonları

Plugin aşağıdaki optimizasyonları içerir:

- **HashMap Cache:** Spawn konumları O(1) erişim için HashMap'te saklanır
- **Y Koordinat Kontrolü:** Sadece Y değiştiğinde kontrol yapılır
- **Player Cleanup:** Oyuncu çıkışında bellek temizliği
- **Cooldown Sistemi:** Spam önleme ile gereksiz işlemler engellenir
- **Lazy Loading:** Config değerleri cache'lenir, her seferinde okunmaz
- **Minimal Event Checking:** Sadece gerekli event'ler dinlenir

## 📦 Kaynak Kod

Kaynak kodlar **VoidSpawn-Source.zip** dosyasında bulunmaktadır.

### Derleme

```bash
cd VoidSpawn
mvn clean package
```

Derlenmiş JAR dosyası `target/VoidSpawn-1.0.0.jar` konumunda oluşturulacaktır.

### Gereksinimler

- Java 17+
- Maven 3.6+
- Paper API 1.20.1+

## 📄 Lisans

Bu plugin özgürce kullanılabilir ve değiştirilebilir.

## 🐛 Hata Bildirimi

Herhangi bir hata veya öneri için lütfen iletişime geçin.

## 📌 Notlar

- Plugin Paper API kullanır, Spigot'ta da çalışır ancak Paper önerilir
- Spawn ayarları `plugins/VoidSpawn/spawns.yml` dosyasında saklanır
- Config değişikliklerinden sonra `/vs reload` komutunu kullanmayı unutmayın
