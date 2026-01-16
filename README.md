# Sorting Visualizer Backend

Backend API for Sorting Visualizer built with Spring Boot + PostgreSQL.

## Tech Stack

- Java 17
- Spring Boot 3.2.x
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL (production) / H2 (development)

## Local Development

```bash
mvn spring-boot:run
```

App runs at `http://localhost:8080` (H2 in-memory database).

## API Endpoints

| Method | Endpoint | Auth |
|--------|----------|------|
| POST | `/api/auth/register` | ❌ |
| POST | `/api/auth/login` | ❌ |
| GET | `/api/auth/me` | ✅ |
| POST | `/api/history` | ✅ |
| GET | `/api/history` | ✅ |
| GET | `/api/statistics` | ✅ |

## Deploy to Render

1. Create **PostgreSQL** database (free)
2. Create **Web Service** with Docker runtime
3. Set environment variables:

```
DATABASE_URL=jdbc:postgresql://host:5432/dbname
DATABASE_USERNAME=your_user
DATABASE_PASSWORD=your_pass
JWT_SECRET=your-secret-key-min-32-chars
CORS_ALLOWED_ORIGINS=https://your-frontend.vercel.app
```
