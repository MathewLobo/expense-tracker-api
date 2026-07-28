# Expense Tracker API

A RESTful API built with Spring Boot for tracking personal expenses with JWT authentication.

## Tech Stack
- Java 17 / Spring Boot 4.0
- Spring Security + JWT
- PostgreSQL
- Docker + Docker Compose
- Deployed on Render

## Features
- User registration and login with JWT auth
- Full CRUD for expenses
- Default and custom expense categories
- Spending summary by category
- Email validation

## Live API
https://expense-tracker-api-ckeh.onrender.com

## Endpoints
| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | /api/auth/register | Register new user | No |
| POST | /api/auth/login | Login and get token | No |
| GET | /api/expenses/user/{id} | Get user expenses | Yes |
| POST | /api/expenses | Add expense | Yes |
| PUT | /api/expenses/{id} | Update expense | Yes |
| DELETE | /api/expenses/{id} | Delete expense | Yes |
| GET | /api/expenses/user/{id}/summary | Category summary | Yes |
| GET | /api/categories/user/{id} | Get categories | Yes |
| POST | /api/categories/user/{id} | Add custom category | Yes |

## Running Locally
1. Clone the repo
2. Start PostgreSQL with Docker:
```bash
   docker compose up -d postgres
```
3. Run the app:
```bash
   ./mvnw spring-boot:run
```
4. API available at `http://localhost:8080`

## Environment Variables
| Variable | Description |
|----------|-------------|
| SPRING_DATASOURCE_URL | PostgreSQL connection URL |
| SPRING_DATASOURCE_USERNAME | Database username |
| SPRING_DATASOURCE_PASSWORD | Database password |
| JWT_SECRET | Secret key for JWT signing |


## Deployment History
The API was initially deployed on **AWS EC2 (t3.micro)** using Docker Compose, 
with PostgreSQL running in a separate container. This demonstrated end-to-end 
cloud deployment including SSH access, security group configuration, and 
container orchestration on a Linux server.

The deployment was later migrated to **Render** to enable HTTPS support, 
which is required for the Netlify-hosted frontend. The Docker setup and 
AWS deployment experience remain part of the project history and are 
documented in the codebase.