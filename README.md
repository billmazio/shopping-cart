# Project Documentation - Shopping Cart

## Project Overview

This document provides an overview of the Maven Spring Boot project for a Shopping Cart application. The project's core concept involves managing seminar room bookings and allowing students to add cards to their cart, complete the checkout process, and receive confirmation emails. Administrators have access to a secure form for managing seminar cards.

## Technologies Used

The project leverages the following technologies:

- ![Spring Boot](https://img.icons8.com/color/48/000000/spring-logo.png) **Spring Boot:** A Java-based framework for building web applications.
- ![H2 Database](https://img.icons8.com/color/48/000000/database-restore.png) **H2 Database:** An in-memory database used for data storage.
- ![Thymeleaf](https://img.icons8.com/color/48/000000/thymeleaf.png) **Thymeleaf:** A Java-based template engine for creating dynamic web pages.
- ![Spring Security](https://img.icons8.com/color/48/000000/lock.png) **Spring Security:** Provides authentication and authorization features.
-  **Validation:** Adds validation support.
- **Java Validation API:** Provides validation API.
- ![JAXB](https://img.icons8.com/color/48/000000/xml-file.png) **Java XML Binding API (JAXB):** Supports XML data binding.
- ![Spring Boot Starter Mail](https://img.icons8.com/color/48/000000/email.png) **Spring Boot Starter Mail:** Enables email functionality.
- ![Project Lombok](https://img.icons8.com/color/48/000000/l.png) **Project Lombok:** Simplifies Java code by reducing boilerplate code.
-  **JUnit:** A testing framework for Java.

## Database Initialization

- **Database Type:** H2
- **Initialization Scripts:**
  - **schema.sql:** Contains the database schema definition.
  - **data.sql:** Contains initial data to populate the database.

## Security

- **Authentication:** The project implements user authentication for administrators.
- **Authorization:** Different roles (buyer, administrator) have different levels of access.

## Workflow

- **Students Workflow:**
  - Students can add seminar cards to their cart.
![Στιγμιότυπο οθόνης (421)](https://github.com/billmazio/shopping-cart/assets/116730698/ed134c28-6f08-4092-840c-d8e017a3362c)
![Στιγμιότυπο οθόνης (422)](https://github.com/billmazio/shopping-cart/assets/116730698/f4eb83c3-e568-43b0-baf8-ad423c05f0ed)

  - At checkout, buyers must provide their name, surname, email, phone, town, and zip code.
 ![Στιγμιότυπο οθόνης (423)](https://github.com/billmazio/shopping-cart/assets/116730698/81c20612-6af7-4987-858a-8b69bc67493c)

  - Confirmation emails are sent upon successful order placement.


![Στιγμιότυπο οθόνης (430)](https://github.com/billmazio/shopping-cart/assets/116730698/abbd0d15-f77a-4d93-b452-674bd12a90cf)

- **Administrator Workflow:**
  - Administrators have access to a secure form.
![Στιγμιότυπο οθόνης (413)](https://github.com/billmazio/shopping-cart/assets/116730698/5c7d17d9-4739-4af2-b6e8-51f38b681b86)

  - They can manage seminar cards and create new ones.
  - Authentication is required for administrator access.


## Testing

The project includes unit and integration tests to ensure the functionality of the Shopping Cart application.

## Build Configuration

The project uses the Spring Boot Maven Plugin for building.

## Conclusion

This documentation provides an overview of the Shopping Cart project and the technologies used to build it. For more detailed information, refer to the actual project source code, the `data.sql` and `schema.sql` files for database details, and security configurations.

For further documentation and project details, please refer to additional documentation or comments within the project source code.
## Additional Resources

In addition to the technologies mentioned above, the project also includes:

- **CSS:** Cascading Style Sheets (CSS) for styling the web pages.
- **JavaScript:** JavaScript for adding dynamic behavior to the website.

These resources are used to enhance the user experience and provide a visually appealing and interactive interface. For more details, please refer to the project's source code in the [Git repository](https://github.com/billmazio/shopping-cart.git).
