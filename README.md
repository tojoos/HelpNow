# HelpNow - Spring Boot Backend Application

<img width="60" alt="faviconhelpnow icon" src="https://github.com/tojoos/HelpNow/assets/79639840/e640caae-73bd-4816-b7ff-57e4d218f153">

HelpNow is a modern online application developed as part of an engineer's thesis. Its main goal is to provide immigrants in Poland with access to resources and support during the immigration process. The application aims to assist immigrants by offering information about the immigration process, connecting them to necessary legal and social services, and supporting them in enforcing their rights.

## System Architecture

<img width="436" alt="system-arch - Copy" src="https://github.com/tojoos/HelpNow/assets/79639840/fcf7f0f7-7999-4ee1-b460-2608ce0b86ef">

HelpNow follows the popular Model-View-Controller (MVC) architectural pattern in its design. The solution consists of three major components:

1. Relational Database: This component is responsible for storing all the objects used in the application. THe technology used here is MySQL

2. Server Application: The Spring Boot server application contains the business logic of the project. It provides the necessary endpoints for communication with other elements of the project.

3. Client Application: The client application is used to create interactive web pages and serves as the user interface for the application. It was designed using Angular framework that uses popular TypeScript. It can be found in this [repository](https://github.com/tojoos/HelpNowFrontEnd).

## SQL Entity Diagram

<img width="393" alt="UML" src="https://github.com/tojoos/HelpNow/assets/79639840/e127ce03-0d41-4b51-9208-7bf0553d3f1c">

## User Group Permissions Schema

![Untitled Diagram drawio (4)](https://github.com/tojoos/HelpNow/assets/79639840/8b425d7b-6199-4f35-a28f-7c1d2dc574f5)

## Development Process

The development of HelpNow was divided into several stages, with each stage focusing on implementing individual functionalities and integrating them with existing components. At the end of each stage, the proposed solutions were verified for correctness.

The primary focus throughout the development process was to ensure the achievement of most project assumptions. The result is a fully functional application that can be accessed through a web browser.

## Getting Started

### Frontend

To run client application locally using `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

### Backend

To run the HelpNow backend application locally, follow these steps:

1. Clone the repository: `git clone <repository-url>`

2. Navigate to the project directory: `cd HelpNow`

3. Install the necessary dependencies: `mvn install`

4. Configure environment variables, make sure that configuration file contains appropriate database datasource.

5. Run DB and frontend app on chosen port. 

6. Start the application: `mvn spring-boot:run`

7. The backend server should now be running on `http://localhost:8080`

## Application Preview

![image](https://github.com/tojoos/HelpNow/assets/79639840/b2ef4dda-6e4b-4ff7-b30d-772285e85f71)

![image](https://github.com/tojoos/HelpNow/assets/79639840/c633ebd0-1094-4bc3-acae-2b4d916e3dc6)

![image](https://github.com/tojoos/HelpNow/assets/79639840/5d9a3876-1544-4d85-b5bd-ede385a495c8)

![image](https://github.com/tojoos/HelpNow/assets/79639840/7922fbea-215f-4edc-817e-8943aafd80d8)

## Usage

The HelpNow backend application provides various endpoints for accessing and manipulating data related to the immigration process. These endpoints can be utilized by the client application to create a seamless user experience.

## Contributing

Contributions are welcome! If you have any suggestions, improvements, or new features to add, please submit a pull request. Make sure to follow the existing code style and write unit tests for any new functionality.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute the application as per the terms of the license.


