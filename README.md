PrimePicks
PrimePicks is a comprehensive e-commerce web application developed using the Spring Framework. It leverages Spring MVC for handling web requests, Thymeleaf for rendering HTML views, Spring Security for securing the application, and Spring Data JPA with Hibernate for database interactions. All routes within this application are authorized based on user roles.

Table of Contents
Features
Technologies Used
Installation
Usage
Configuration
Contributing
License
Features
Role-based access control
Secure authentication and authorization using Spring Security
Dynamic HTML content rendering with Thymeleaf
Efficient database operations with Spring Data JPA and Hibernate
RESTful web services
MVC architecture
Technologies Used
Spring Framework: Core framework used for building the application.
Spring MVC: Used for handling web requests and implementing the MVC pattern.
Thymeleaf: Template engine for rendering HTML views.
Spring Security: Provides authentication and authorization mechanisms.
Spring Data JPA: Simplifies database interactions.
Hibernate: ORM library for database operations.
Java: Programming language used for development.
Maven: Build automation tool.
Installation
Clone the repository:
bash
Копировать код
git clone https://github.com/yourusername/primepicks.git
Navigate to the project directory:
bash
Копировать код
cd primepicks
Build the project using Maven:
bash
Копировать код
mvn clean install
Usage
Run the application:
bash
Копировать код
mvn spring-boot:run
Access the application:
Open your web browser and navigate to http://localhost:8080.
Configuration
Database
The database configuration is located in src/main/resources/application.properties.
Update the database URL, username, and password as per your setup:
properties
Копировать код
spring.datasource.url=jdbc:mysql://localhost:3306/primepicks
spring.datasource.username=root
spring.datasource.password=yourpassword
Security
User roles and security configurations are defined in src/main/java/com/primepicks/config/SecurityConfig.java.
Modify the security configuration to match your requirements.
Contributing
Fork the repository.
Create a new branch:
bash
Копировать код
git checkout -b feature/your-feature
Make your changes and commit them:
bash
Копировать код
git commit -m 'Add some feature'
Push to the branch:
bash
Копировать код
git push origin feature/your-feature
Create a new Pull Request.
License
This project is licensed under the MIT License. See the LICENSE file for details.
