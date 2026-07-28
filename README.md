# Expense Tracker API

A RESTful API built with Spring Boot for tracking personal expenses with JWT authentication.

## Tech Stack
- Java 17 / Spring Boot 4.0
- Spring Security + JWT
- PostgreSQL
- Docker + Docker Compose
- Deployed on Render (migrated from AWS EC2)

## Features
- User registration and login with JWT auth
- Email validation on register
- Full CRUD for expenses
- Default and custom expense categories (seeded on startup)
- Spending summary by category (all time)
- Monthly spending breakdown
- Monthly stacked spending by category with year filter
- CORS configured for local and Netlify frontend

## Live API
https://expense-tracker-api-ckeh.onrender.com

## Endpoints
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | /api/auth/register | No | Register new user |
| POST | /api/auth/login | No | Login and get JWT token |
| GET | /api/expenses/user/{id} | Yes | Get user expenses |
| POST | /api/expenses | Yes | Add expense |
| PUT | /api/expenses/{id} | Yes | Update expense |
| DELETE | /api/expenses/{id} | Yes | Delete expense |
| GET | /api/expenses/user/{id}/summary | Yes | All time category summary |
| GET | /api/expenses/user/{id}/monthly | Yes | Monthly totals |
| GET | /api/expenses/user/{id}/monthly-stacked | Yes | Monthly stacked by category |
| GET | /api/expenses/user/{id}/monthly-category | Yes | Category breakdown for a month |
| GET | /api/categories/user/{id} | Yes | Get all categories |
| POST | /api/categories/user/{id} | Yes | Add custom category |
| DELETE | /api/categories/{id} | Yes | Delete custom category |

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
Initially deployed on **AWS EC2 (t3.micro)** using Docker Compose, with PostgreSQL running in a separate container. This demonstrated end-to-end cloud deployment including SSH access, security group configuration, and container orchestration on a Linux server.

Migrated to **Render** to enable HTTPS support required by the Netlify frontend. The Docker setup remains in the repo and can be used for local development or redeployment to any cloud VM.

## TODO
- Return userId in auth response so frontend can scope expenses per user
- Dynamic year range toggle on frontend (startYear to current year)
- Password recovery via email (SendGrid/Gmail SMTP)
- Shared expenses between users