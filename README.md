# CarView-api-Java

CarView-api-Java is a simple RESTful API that allows users to view information about cars.

## Features

- View a list of cars
- View details about each car
- Add new cars to the list
- Register maintenance performed
- Suggest a date for the next maintenance

## Technologies Used

- Java 19
- Spring Boot
- PostgresSQL
- Docker
- Postman
- Swagger

## Installation

1. Clone the repository: `git clone https://github.com/fgost/CarView-api-Java.git`
2. Navigate to the project directory: `cd CarView-api-Java`
3. Add VM options: `-Dspring.profiles.active=local`
4. Build the application using Maven: `mvn clean package`
5. Run the application: `java -jar -Dserver.port=5001 target/CarView-api-Java-1.0.jar`

## Usage

1. Open a web browser and navigate to `http://localhost:5001`
2. Use the Postman collection to test API endpoints: `CarView-API.postman_collection.json`
3. Use Swagger UI to interactively explore and test the API: `http://localhost:5001/swagger-ui.html`
4. Alternatively, use Docker to deploy the application: `docker-compose up -d`

## Contributing

If you would like to contribute to this project, please follow these steps:

1. Fork the repository
2. Create a new branch: `git checkout -b feature-name`
3. Make your changes and commit them: `git commit -m "Add new feature"`
4. Push to the branch: `git push origin feature-name`
5. Create a pull request

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/fgost/CarView-api-Java/blob/master/LICENSE) file for details.

## Contact

If you have any questions or concerns, please feel free to contact the project maintainer: math.firmiano@gmail.com.