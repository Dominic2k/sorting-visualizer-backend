# Sorting Visualizer Backend

Backend API for Sorting Visualizer application built with Spring Boot.

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.x**
- **Spring Security + JWT**
- **Spring Data JPA**
- **MySQL** (production) / **H2** (development)

## Local Development

### Prerequisites
- Java 17+
- Maven 3.8+

### Run locally

```bash
# Using Maven
mvn spring-boot:run

# Or build and run JAR
mvn clean package -DskipTests
java -jar target/sorting-visualizer-backend-0.0.1-SNAPSHOT.jar
```

The application will start at `http://localhost:8080`.

H2 Console available at `http://localhost:8080/h2-console`.

## API Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/auth/register` | Register new user | ❌ |
| POST | `/api/auth/login` | Login, returns JWT | ❌ |
| GET | `/api/auth/me` | Get current user info | ✅ |
| POST | `/api/history` | Save sorting run | ✅ |
| GET | `/api/history` | Get user's history | ✅ |
| GET | `/api/statistics` | Get all statistics | ✅ |
| GET | `/api/statistics/{algo}` | Get stats by algorithm | ✅ |

## Deployment (Render)

1. Create a new **Web Service** on Render
2. Connect your GitHub repository
3. Set **Root Directory**: `sorting-visualizer-backend`
4. Set **Build Command**: (Docker will be used)
5. Add a **MySQL** database
6. Configure environment variables:

```env
DATABASE_URL=jdbc:mysql://your-db-host:3306/sorting_db
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
JWT_SECRET=your-256-bit-secret-key-here
CORS_ALLOWED_ORIGINS=https://your-frontend.vercel.app
```

## Project Structure

```
src/main/java/com/sortingvisualizer/
├── config/           # Security & CORS configuration
├── controller/       # REST API endpoints
├── dto/              # Request/Response objects
├── exception/        # Global exception handling
├── model/            # JPA entities
├── repository/       # Data access layer
├── security/         # JWT authentication
└── service/          # Business logic
```
