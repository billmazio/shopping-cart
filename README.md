# Project Documentation - Shopping Cart

## Project Overview

This document provides an overview of the Maven Spring Boot project for a Shopping Cart application. The project's core concept involves managing seminar room bookings and allowing students to add cards to their cart, complete the checkout process, and receive confirmation emails. Administrators have access to a secure form for managing seminar cards.

## Technologies Used

The project leverages the following technologies:

- ![Spring Boot](https://img.icons8.com/color/48/000000/spring-logo.png) **Spring Boot:** A Java-based framework for building web applications.
- ![MySQL Database](https://img.icons8.com/color/48/000000/mysql-logo.png) **MySQL Database:** A popular open-source relational database management system used for data storage.
- ![Thymeleaf](https://img.icons8.com/color/48/000000/thymeleaf.png) **Thymeleaf:** A Java-based template engine for creating dynamic web pages.
- ![Spring Security](https://img.icons8.com/color/48/000000/lock.png) **Spring Security:** Provides authentication and authorization features.
-  **Validation:** Adds validation support.
- **Java Validation API:** Provides validation API.
- ![JAXB](https://img.icons8.com/color/48/000000/xml-file.png) **Java XML Binding API (JAXB):** Supports XML data binding.
- ![Spring Boot Starter Mail](https://img.icons8.com/color/48/000000/email.png) **Spring Boot Starter Mail:** Enables email functionality.
- ![Project Lombok](https://img.icons8.com/color/48/000000/l.png) **Project Lombok:** Simplifies Java code by reducing boilerplate code.
-  **JUnit:** A testing framework for Java.


## Security

- **Authentication:** The project implements user authentication for administrators.
- **Authorization:** Different roles (buyer, administrator) have different levels of access.

## Workflow

- **Students Workflow:**
  - Students can add seminar cards to their cart.
   ![Στιγμιότυπο οθόνης (439)](https://github.com/billmazio/shopping-cart/assets/116730698/9ec6bb81-3618-41aa-a347-c8f1f4a9dbcd)
   ![Στιγμιότυπο οθόνης (440)](https://github.com/billmazio/shopping-cart/assets/116730698/78646935-ce1a-4a4a-a3f1-ed20a20b845b)
   ![Στιγμιότυπο οθόνης (442)](https://github.com/billmazio/shopping-cart/assets/116730698/fd20f4fc-f43c-4e9b-addb-391d7825ec82)

  - At checkout, buyers must provide their name, surname, email, phone, town, and zip code.
    ![Στιγμιότυπο οθόνης (443)](https://github.com/billmazio/shopping-cart/assets/116730698/41fb2424-f2c7-4ed7-ad2d-1415a5149f3c)

  - Confirmation emails are sent upon successful order placement.
  ![Στιγμιότυπο οθόνης (444)](https://github.com/billmazio/shopping-cart/assets/116730698/5410ea05-43bf-4809-b16d-ae2efcacccb1)

- **Administrator Workflow:**
  - Administrators have access to a secure form.
  ![Στιγμιότυπο οθόνης (445)](https://github.com/billmazio/shopping-cart/assets/116730698/0d8b103b-377e-4a0b-a703-ad2935ae5839)

  - They can manage seminar cards and create new ones.
    ![Στιγμιότυπο οθόνης (446)](https://github.com/billmazio/shopping-cart/assets/116730698/f2e5fe5b-d7f0-4c19-bc99-fa246f452592)
    ![Στιγμιότυπο οθόνης (448)](https://github.com/billmazio/shopping-cart/assets/116730698/79b6e24f-663b-47d5-96f7-284934544765)
    ![Στιγμιότυπο οθόνης (449)](https://github.com/billmazio/shopping-cart/assets/116730698/5554247a-63ba-4244-83b3-bd4dbf88913a)
  - Authentication is required for administrator access.

# Pagination Feature Documentation

## Overview
This document outlines the implementation of the pagination feature in the Shopping Cart application. The pagination functionality is essential for handling large datasets efficiently, providing an improved user experience by displaying the data in smaller, more manageable chunks.

## Key Features
- **Dynamic Pagination**: Automatically adjusts the number of items displayed per page.
- **Search Integration**: Works in conjunction with the search feature to paginate filtered results.
- **User-Friendly Navigation**: Provides intuitive controls for navigating between pages.

## Implementation Details

### Service Layer
The service layer handles the logic for fetching paginated data based on provided criteria.

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
