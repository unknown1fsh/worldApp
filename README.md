-------
ENGLISH
-------

# WorldApp Java Project

## Description

WorldApp is a comprehensive Java-based web application designed to manage and retrieve information about cities, countries, and languages from a MySQL database. The application leverages the Jetty web server and includes robust CRUD operations for managing city, country, and country language data.

## Table of Contents

- [Project Structure](#project-structure)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
  - [Cities](#cities)
  - [Countries](#countries)
  - [CountryLanguages](#countrylanguages)
- [Postman Collection](#postman-collection)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## Project Structure

- **Main.java**: The entry point of the application.
- **model package**: Contains data model classes:
  - `City.java`: Represents the City entity.
  - `Country.java`: Represents the Country entity.
  - `CountryLanguage.java`: Represents the CountryLanguage entity.
- **repository package**: Contains repository classes for database interactions:
  - `CityRepository.java`: Handles database operations for the City entity.
  - `CountryRepository.java`: Handles database operations for the Country entity.
  - `CountryLanguageRepository.java`: Handles database operations for the CountryLanguage entity.
- **service package**: Contains service classes to handle business logic:
  - `CityService.java`: Provides business logic for city-related operations.
  - `CountryService.java`: Provides business logic for country-related operations.
  - `CountryLanguageService.java`: Provides business logic for country language-related operations.
- **servlet package**: Contains servlet classes for handling HTTP requests and responses:
  - `CityServlet.java`: Manages HTTP operations for city data.
  - `CountryServlet.java`: Manages HTTP operations for country data.
  - `CountryLanguageServlet.java`: Manages HTTP operations for country language data.
- **JettyServer.java**: Configures and starts the Jetty server.

## Features

- CRUD operations for managing cities, countries, and country languages.
- RESTful API endpoints for seamless integration.
- MySQL database integration for persistent data storage.
- Comprehensive error handling and validation.
- Modular and extensible codebase for easy maintenance and scalability.

## Technologies Used

- **Java**: Core programming language.
- **Jetty**: Web server for handling HTTP requests.
- **MySQL**: Database for data storage.
- **Maven**: Build and dependency management tool.
- **Postman**: API testing tool.

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL

### Steps

1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-username/worldApp.git
   cd worldApp
   ```

2. **Configure the MySQL database:**
   - Create a database named `world`.
   - Use the provided SQL scripts to create the necessary tables and populate them with sample data.

3. **Update database connection settings:**
   - Open `src/main/resources/application.properties`.
   - Update the MySQL connection details (username, password, URL).

4. **Build the project:**
   ```sh
   mvn clean install
   ```

5. **Run the application:**
   ```sh
   mvn exec:java -Dexec.mainClass="com.example.world.JettyServer"
   ```

## Configuration

Update the database connection settings in `src/main/resources/application.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/world
db.username=root
db.password=password
```

## Usage

The application provides the following RESTful API endpoints to interact with the database.

## API Endpoints

### Cities

- **GET /cities**: Retrieve all cities or filter by attributes.
- **POST /cities**: Add a new city.
- **PUT /cities**: Update an existing city.
- **DELETE /cities**: Delete a city based on criteria.

### Countries

- **GET /countries**: Retrieve all countries or filter by attributes.
- **POST /countries**: Add a new country.
- **PUT /countries**: Update an existing country.
- **DELETE /countries**: Delete a country based on criteria.

### CountryLanguages

- **GET /countrylanguages**: Retrieve all country languages or filter by attributes.
- **POST /countrylanguages**: Add a new country language.
- **PUT /countrylanguages**: Update an existing country language.
- **DELETE /countrylanguages**: Delete a country language based on criteria.

## Postman Collection

A Postman collection file (`WorldApp.postman_collection.json`) is provided for testing all endpoints. To import the collection:

1. Open Postman.
2. Click the `Import` button.
3. Select the provided JSON file and import.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Make sure to follow the project's coding standards and write clear, concise commit messages.

### Steps to Contribute

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Author

[SELİM SERCAN ÇINAR](https://github.com/unknown1fsh)

-------
TURKISH
-------
# WorldApp Java Projesi

## Açıklama

WorldApp, MySQL veritabanından şehirler, ülkeler ve diller hakkında bilgi yönetimi ve sorgulama işlemleri yapan kapsamlı bir Java tabanlı web uygulamasıdır. Uygulama, Jetty web sunucusunu kullanır ve şehir, ülke ve ülke dili verilerini yönetmek için sağlam CRUD işlemleri içerir.

## İçindekiler

- [Proje Yapısı](#proje-yapısı)
- [Özellikler](#özellikler)
- [Kullanılan Teknolojiler](#kullanılan-teknolojiler)
- [Kurulum](#kurulum)
- [Yapılandırma](#yapılandırma)
- [Kullanım](#kullanım)
- [API Uç Noktaları](#api-uç-noktaları)
  - [Şehirler](#şehirler)
  - [Ülkeler](#ülkeler)
  - [Ülke Dilleri](#ülke-dilleri)
- [Postman Koleksiyonu](#postman-koleksiyonu)
- [Katkıda Bulunma](#katkıda-bulunma)
- [Lisans](#lisans)
- [Yazar](#yazar)

## Proje Yapısı

- **Main.java**: Uygulamanın giriş noktası.
- **model package**: Veri model sınıflarını içerir:
  - `City.java`: Şehir varlığını temsil eder.
  - `Country.java`: Ülke varlığını temsil eder.
  - `CountryLanguage.java`: Ülke dili varlığını temsil eder.
- **repository package**: Veritabanı etkileşimleri için repository sınıflarını içerir:
  - `CityRepository.java`: Şehir varlığı için veritabanı işlemlerini yönetir.
  - `CountryRepository.java`: Ülke varlığı için veritabanı işlemlerini yönetir.
  - `CountryLanguageRepository.java`: Ülke dili varlığı için veritabanı işlemlerini yönetir.
- **service package**: İş mantığını yönetmek için servis sınıflarını içerir:
  - `CityService.java`: Şehirle ilgili işlemler için iş mantığını sağlar.
  - `CountryService.java`: Ülkeyle ilgili işlemler için iş mantığını sağlar.
  - `CountryLanguageService.java`: Ülke diliyle ilgili işlemler için iş mantığını sağlar.
- **servlet package**: HTTP istek ve yanıtlarını yönetmek için servlet sınıflarını içerir:
  - `CityServlet.java`: Şehir verileri için HTTP işlemlerini yönetir.
  - `CountryServlet.java`: Ülke verileri için HTTP işlemlerini yönetir.
  - `CountryLanguageServlet.java`: Ülke dili verileri için HTTP işlemlerini yönetir.
- **JettyServer.java**: Jetty sunucusunu yapılandırır ve başlatır.

## Özellikler

- Şehirler, ülkeler ve ülke dilleri için CRUD işlemleri.
- Sorunsuz entegrasyon için RESTful API uç noktaları.
- Kalıcı veri depolama için MySQL veritabanı entegrasyonu.
- Kapsamlı hata yönetimi ve doğrulama.
- Kolay bakım ve ölçeklenebilirlik için modüler ve genişletilebilir kod tabanı.

## Kullanılan Teknolojiler

- **Java**: Ana programlama dili.
- **Jetty**: HTTP isteklerini işlemek için web sunucusu.
- **MySQL**: Veri depolama için veritabanı.
- **Maven**: Proje ve bağımlılık yönetim aracı.
- **Postman**: API test aracı.

## Kurulum

### Gereksinimler

- Java Development Kit (JDK) 8 veya daha üstü
- Maven
- MySQL

### Adımlar

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

## Yapılandırma

`src/main/resources/application.properties` dosyasındaki veritabanı bağlantı ayarlarını güncelleyin:

```properties
db.url=jdbc:mysql://localhost:3306/world
db.username=root
db.password=password
```

## Kullanım

Uygulama, veritabanıyla etkileşimde bulunmak için aşağıdaki RESTful API uç noktalarını sağlar.

## API Uç Noktaları

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

## Postman Koleksiyonu

Tüm uç noktaları test etmek için bir Postman koleksiyon dosyası (`WorldApp.postman_collection.json`) sağlanmıştır. Koleksiyonu içe aktarmak için:

1. Postman'i açın.
2. `Import` butonuna tıklayın.
3. Sağlanan JSON dosyasını seçin ve içe aktarın.

## Katkıda Bulunma

Katkılar memnuniyetle karşılanır! Lütfen depoyu fork edin ve değişikliklerinizle bir pull request oluşturun. Projenin kodlama standartlarına uyduğunuzdan ve açık, özlü commit mesajları yazdığınızdan emin olun.

### Katkıda Bulunma Adımları

1. Depoyu fork edin.
2. Yeni bir dal oluşturun (`git checkout -b feature-branch`).
3. Değişikliklerinizi yapın.
4. Değişikliklerinizi commit edin (`git commit -m 'Yeni özellik ekle'`).
5. Dalınıza push edin (`git push origin feature-branch`).
6. Yeni bir pull request oluşturun.

## Lisans

Bu proje MIT Lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakın.

## Yazar

[SELİM SERCAN ÇINAR](https://github.com/unknown1fsh)
