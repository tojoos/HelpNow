# HelpNow - Spring Boot Backend Application

HelpNow is a modern online application developed as part of an engineer's thesis. Its main goal is to provide immigrants in Poland with access to resources and support during the immigration process. The application aims to assist immigrants by offering information about the immigration process, connecting them to necessary legal and social services, and supporting them in enforcing their rights.

## Architecture

HelpNow follows the popular Model-View-Controller (MVC) architectural pattern in its design. The solution consists of three major components:

1. Relational Database: This component is responsible for storing all the objects used in the application. THe technology used here is MySQL

2. Server Application: The Spring Boot server application contains the business logic of the project. It provides the necessary endpoints for communication with other elements of the project.

3. Client Application: The client application is used to create interactive web pages and serves as the user interface for the application. It was designed using Angular framework that uses popular TypeScript. It can be found in this [repository](https://github.com/tojoos/HelpNowFrontEnd).

## Development Process

The development of HelpNow was divided into several stages, with each stage focusing on implementing individual functionalities and integrating them with existing components. At the end of each stage, the proposed solutions were verified for correctness.

The primary focus throughout the development process was to ensure the achievement of most project assumptions. The result is a fully functional application that can be accessed through a web browser.

## Getting Started

To run the HelpNow backend application locally, follow these steps:

1. Clone the repository: `git clone <repository-url>`

2. Navigate to the project directory: `cd HelpNow`

3. Install the necessary dependencies: `mvn install`

4. Configure environment variables, make sure that configuration file contains appropriate database datasource.

5. Run DB and frontend app on chosen port. 

6. Start the application: `mvn spring-boot:run`

7. The backend server should now be running on `http://localhost:8080`

## Usage

The HelpNow backend application provides various endpoints for accessing and manipulating data related to the immigration process. These endpoints can be utilized by the client application to create a seamless user experience.

## Contributing

Contributions are welcome! If you have any suggestions, improvements, or new features to add, please submit a pull request. Make sure to follow the existing code style and write unit tests for any new functionality.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute the application as per the terms of the license.
