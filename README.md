PrimePicks
PrimePicks is a robust e-commerce web application developed using Spring Boot. It features secure authentication and authorization with Spring Security, efficient database management with Spring Data JPA and Hibernate, and a user-friendly UI built with Spring MVC and Thymeleaf. The application supports different user roles, each with specific permissions and functionalities.

Table of Contents
Features
Technologies Used
Installation
Usage
Configuration
User Roles
Contributing
License
Features
Role-based Access Control: Different functionalities based on user roles (ROLE_USER, ROLE_ADMIN, ROLE_SALES_MANAGER).
Secure Authentication and Authorization: Powered by Spring Security.
Dynamic HTML Content Rendering: Using Thymeleaf for responsive and interactive web pages.
Efficient Database Operations: Managed with Spring Data JPA and Hibernate.
MVC Architecture: Ensures a clean separation of concerns.
Technologies Used
Spring Boot: Core framework for the application.
Spring MVC: Handles web requests and implements the MVC pattern.
Thymeleaf: Template engine for rendering HTML views.
Spring Security: Provides authentication and authorization.
Spring Data JPA: Simplifies database operations.
Hibernate: ORM library for database interactions.
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
User Roles
ROLE_USER: Standard user functionalities such as browsing products, adding to cart, and making purchases.
ROLE_ADMIN: Administrative functionalities including managing products, user activities, and performing administrative tasks.
ROLE_SALES_MANAGER: Sales management functionalities including overseeing sales data and managing customer relationships.
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
