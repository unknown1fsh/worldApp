# WorldApp Java Project

## Description

WorldApp Java Project is a web application built with Java that manages and retrieves information about cities, countries, and languages from a MySQL database. It uses Jetty as the web server and includes CRUD operations for managing city, country, and country language data.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL

### Installation

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

## Usage

The application provides the following endpoints:

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

## Author

[SELİM SERCAN ÇINAR](https://github.com/unknown1fsh)
