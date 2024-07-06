# WorldApp Java Projesi

## Açıklama

WorldApp Java Projesi, MySQL veritabanından şehirler, ülkeler ve diller hakkında bilgi yönetimi ve sorgulama işlemleri yapan Java tabanlı bir web uygulamasıdır. Jetty web sunucusu kullanılarak geliştirilmiştir ve şehir, ülke ve ülke dili verilerini yönetmek için CRUD işlemlerini içerir.

## Başlangıç

### Gereksinimler

- Java Development Kit (JDK) 8 veya daha üstü
- Maven
- MySQL

### Kurulum

1. **Depoyu klonlayın:**
   ```sh
   git clone https://github.com/your-username/worldApp.git
   cd worldApp
   ```

2. **MySQL veritabanını yapılandırın:**
   - `world` adında bir veritabanı oluşturun.
   - Gerekli tabloları oluşturmak ve örnek verilerle doldurmak için sağlanan SQL betiklerini kullanın.

3. **Veritabanı bağlantı ayarlarını güncelleyin:**
   - `src/main/resources/application.properties` dosyasını açın.
   - MySQL bağlantı detaylarını (kullanıcı adı, şifre, URL) güncelleyin.

4. **Projeyi derleyin:**
   ```sh
   mvn clean install
   ```

5. **Uygulamayı çalıştırın:**
   ```sh
   mvn exec:java -Dexec.mainClass="com.example.world.JettyServer"
   ```

## Kullanım

Uygulama aşağıdaki uç noktaları sağlar:

### Şehirler

- **GET /cities**: Tüm şehirleri alın veya özelliklere göre filtreleyin.
- **POST /cities**: Yeni bir şehir ekleyin.
- **PUT /cities**: Mevcut bir şehri güncelleyin.
- **DELETE /cities**: Belirli kriterlere göre bir şehri silin.

### Ülkeler

- **GET /countries**: Tüm ülkeleri alın veya özelliklere göre filtreleyin.
- **POST /countries**: Yeni bir ülke ekleyin.
- **PUT /countries**: Mevcut bir ülkeyi güncelleyin.
- **DELETE /countries**: Belirli kriterlere göre bir ülkeyi silin.

### Ülke Dilleri

- **GET /countrylanguages**: Tüm ülke dillerini alın veya özelliklere göre filtreleyin.
- **POST /countrylanguages**: Yeni bir ülke dili ekleyin.
- **PUT /countrylanguages**: Mevcut bir ülke dilini güncelleyin.
- **DELETE /countrylanguages**: Belirli kriterlere göre bir ülke dilini silin.

## Yazar

[SELİM SERCAN ÇINAR](https://github.com/unknown1fsh)
