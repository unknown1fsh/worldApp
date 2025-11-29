# 🌍 WorldApp - RESTful API

WorldApp, MySQL veritabanından şehirler, ülkeler ve diller hakkında bilgi yönetimi yapan modern bir Java RESTful API uygulamasıdır. Swagger UI ile tam dokümante edilmiş, güvenli ve profesyonel bir API sunar.

## ✨ Özellikler

- 🚀 **RESTful API** - Modern REST standartlarına uygun API tasarımı
- 📚 **Swagger UI** - İnteraktif API dokümantasyonu ve test arayüzü
- 🔒 **Güvenlik** - SQL Injection koruması, PreparedStatement kullanımı
- 🏗️ **Mimari** - Katmanlı mimari (Model-Repository-Service-Servlet)
- 🌐 **CORS Desteği** - Frontend entegrasyonu için CORS header'ları
- 📦 **JSON API** - Tüm endpoint'ler JSON formatında request/response
- ✅ **Hata Yönetimi** - Kapsamlı hata yönetimi ve HTTP status kodları

## 🛠️ Teknolojiler

- **Java 8+** - Programlama dili
- **Maven** - Build ve dependency yönetimi
- **Jetty** - Embedded web server
- **MySQL** - Veritabanı
- **Swagger UI** - API dokümantasyonu
- **OpenAPI 3.0** - API spesifikasyonu

## 📋 Gereksinimler

- Java Development Kit (JDK) 8 veya üzeri
- Maven 3.6+
- MySQL 8.0+ (world veritabanı ile)

## 🚀 Kurulum

### 1. Projeyi Klonlayın

```bash
git clone https://github.com/your-username/worldApp.git
cd worldApp
```

### 2. Veritabanını Yapılandırın

MySQL'in default `world` veritabanını kullanın veya yeni bir veritabanı oluşturun:

```sql
CREATE DATABASE IF NOT EXISTS world;
```

Veritabanı bağlantı bilgileri `DatabaseConnection.java` dosyasında yapılandırılmıştır:
- **URL**: `jdbc:mysql://localhost:3306/world`
- **Kullanıcı**: `root`
- **Şifre**: `12345`

> ⚠️ **Not**: Üretim ortamında şifreyi değiştirmeyi unutmayın!

### 3. Projeyi Derleyin

```bash
mvn clean install
```

### 4. Uygulamayı Çalıştırın

```bash
mvn exec:java
```

Sunucu `http://localhost:8085` adresinde başlatılacaktır.

## 📖 Kullanım

### API Dokümantasyonu

Swagger UI ile API'yi keşfedin ve test edin:

- **Swagger UI**: http://localhost:8085/swagger
- **OpenAPI Spec**: http://localhost:8085/openapi.json
- **Ana Sayfa**: http://localhost:8085/

### API Endpoint'leri

#### 🏙️ Şehirler (`/cities`)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/cities` | Tüm şehirleri listele veya filtrele |
| POST | `/cities` | Yeni şehir ekle |
| PUT | `/cities` | Şehir güncelle |
| DELETE | `/cities?ID={id}` | Şehir sil |

**Örnek Request (POST):**
```json
{
  "ID": 5000,
  "Name": "İstanbul",
  "CountryCode": "TUR",
  "District": "İstanbul",
  "Population": 15000000
}
```

#### 🌎 Ülkeler (`/countries`)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/countries` | Tüm ülkeleri listele veya filtrele |
| POST | `/countries` | Yeni ülke ekle |
| PUT | `/countries` | Ülke güncelle |
| DELETE | `/countries?Code={code}` | Ülke sil |

**Örnek Request (POST):**
```json
{
  "Code": "TUR",
  "Name": "Turkey",
  "Continent": "Asia",
  "Region": "Middle East",
  "SurfaceArea": 783356,
  "IndepYear": 1923,
  "Population": 82000000,
  "LifeExpectancy": 75.8,
  "GNP": 851300.0,
  "GNPOld": 800000.0,
  "LocalName": "Türkiye",
  "GovernmentForm": "Republic",
  "HeadOfState": "Recep Tayyip Erdoğan",
  "Capital": 1,
  "Code2": "TR"
}
```

#### 🗣️ Ülke Dilleri (`/countrylanguages`)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/countrylanguages` | Tüm dilleri listele veya filtrele |
| POST | `/countrylanguages` | Yeni dil ekle |
| PUT | `/countrylanguages` | Dil güncelle |
| DELETE | `/countrylanguages?CountryCode={code}&Language={lang}` | Dil sil |

**Örnek Request (POST):**
```json
{
  "CountryCode": "TUR",
  "Language": "Turkish",
  "IsOfficial": "T",
  "Percentage": 90.0
}
```

## 🏗️ Proje Yapısı

```
worldApp/
├── src/main/java/com/example/world/
│   ├── config/
│   │   └── DatabaseConnection.java      # Merkezi veritabanı bağlantı yönetimi
│   ├── model/
│   │   ├── City.java                     # Şehir modeli
│   │   ├── Country.java                  # Ülke modeli
│   │   └── CountryLanguage.java          # Ülke dili modeli
│   ├── repository/
│   │   ├── CityRepository.java           # Şehir veritabanı işlemleri
│   │   ├── CountryRepository.java        # Ülke veritabanı işlemleri
│   │   └── CountryLanguageRepository.java # Dil veritabanı işlemleri
│   ├── service/
│   │   ├── CityService.java              # Şehir iş mantığı
│   │   ├── CountryService.java           # Ülke iş mantığı
│   │   └── CountryLanguageService.java   # Dil iş mantığı
│   ├── servlet/
│   │   ├── CityServlet.java              # Şehir REST endpoint'leri
│   │   ├── CountryServlet.java           # Ülke REST endpoint'leri
│   │   ├── CountryLanguageServlet.java   # Dil REST endpoint'leri
│   │   ├── WelcomeServlet.java           # Ana sayfa
│   │   ├── SwaggerUIServlet.java         # Swagger UI
│   │   └── OpenApiServlet.java           # OpenAPI spec
│   └── JettyServer.java                  # Sunucu başlatma
├── pom.xml                               # Maven yapılandırması
└── README.md                             # Bu dosya
```

## 🔧 Yapılandırma

### Veritabanı Bağlantısı

Veritabanı bağlantı ayarlarını değiştirmek için `src/main/java/com/example/world/config/DatabaseConnection.java` dosyasını düzenleyin:

```java
private static final String URL = "jdbc:mysql://localhost:3306/world";
private static final String USER = "root";
private static final String PASSWORD = "12345";
```

### Port Değiştirme

Sunucu portunu değiştirmek için `JettyServer.java` dosyasında:

```java
Server server = new Server(8085); // Port numarasını değiştirin
```

## 🧪 Test Etme

### Swagger UI ile Test

1. Sunucuyu başlatın: `mvn exec:java`
2. Tarayıcıda http://localhost:8085/swagger adresine gidin
3. Endpoint'leri keşfedin ve "Try it out" butonunu kullanarak test edin

### cURL ile Test

```bash
# Şehirleri listele
curl http://localhost:8085/cities

# Yeni şehir ekle
curl -X POST http://localhost:8085/cities \
  -H "Content-Type: application/json" \
  -d '{"ID":5000,"Name":"İstanbul","CountryCode":"TUR","District":"İstanbul","Population":15000000}'
```

## 🔒 Güvenlik

- ✅ SQL Injection koruması (PreparedStatement kullanımı)
- ✅ Input validation
- ✅ CORS header'ları
- ✅ Hata mesajlarında hassas bilgi sızıntısı yok

## 📝 Lisans

Bu proje MIT lisansı altında lisanslanmıştır.

## 👤 Yazar

- GitHub: [@unknown1fsh](https://github.com/unknown1fsh)

## 🤝 Katkıda Bulunma

Katkılarınızı memnuniyetle karşılıyoruz! Lütfen:

1. Fork edin
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Commit edin (`git commit -m 'Add some amazing feature'`)
4. Push edin (`git push origin feature/amazing-feature`)
5. Pull Request açın

## 📞 Destek

Sorularınız veya önerileriniz için issue açabilirsiniz.

---

⭐ Bu projeyi beğendiyseniz yıldız vermeyi unutmayın!
