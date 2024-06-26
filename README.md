# Restaurant Recommendation App
This application is a restaurant recommendation system built with Java 21. It consists of three modules:

## Common
Contains common classes shared across modules.

## Restaurant Service
Utilizes Elasticsearch as its database and provides CRUD operations for managing restaurants. It also includes an endpoint to recommend restaurants based on given latitude and longitude parameters. The recommendation algorithm considers both the restaurant rating and the distance between the provided coordinates and the restaurant location. Endpoints can be accessed via Swagger UI at http://localhost:8081/restaurant-recommendation-app/api/restaurant-service/swagger-ui/index.html.

### Configuration
Database should be configured using docker-compose.yml

## User Service
Uses PostgreSQL as its database. It offers CRUD operations for managing users and their reviews. Tests are available for both main functionality and database operations. Database configurations such as username, password, and database name should be set in the application.properties files for both main and test environments. Endpoints can be accessed via Swagger UI at http://localhost:8080/restaurant-recommendation-app/api/user-service/swagger-ui/index.html.

### Configuration
Ensure that the appropriate database configurations are set in the application.properties files for both the main and test environments of the User Service module.

## Running the Application
To run the application, simply execute the main class of restaurant_service and user_service. Ensure that the required dependencies are installed and configured properly.


## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests to improve the application.