# HarvestHub

HarvestHub is a full-stack farm management application designed to help agricultural teams manage crops, farm operations, expenses, harvest records, equipment maintenance, and rotation planning from a single platform.

The project combines:
- An Angular frontend for the user interface and dashboards
- A Spring Boot backend for REST APIs and business logic
- A MySQL database for persistent farm data

## Overview

HarvestHub provides a centralized workflow for managing day-to-day farm operations, including:

- Crop and field management
- Farm profile details
- Crop planning and rotation records
- Daily activity tracking
- Equipment monitoring
- Maintenance schedules
- Expense tracking
- Harvest records
- User authentication and access control

## Tech Stack

- Frontend: Angular 21
- Backend: Spring Boot 4.1.1
- Database: MySQL
- Build Tools: Maven, npm
- CI/CD: GitHub Actions

## Project Structure

```text
harvesthub/
├── .github/
│   └── workflows/
│       └── harvesthub.yml
├── angular-auth/          # Angular frontend
├── auth-backend/          # Spring Boot backend
├── Document/              # SQL and project documentation
├── .gitignore
├── README.md              # Project overview and setup guide
└── .idea/                 # IDE settings
```

## Features

### Frontend
The Angular app includes pages for:
- Login and signup
- Dashboard
- Crops
- Crop plans
- Fields
- Farm profile
- Activities
- Expenses
- Harvest
- Equipment
- Maintenance
- Rotation

### Backend
The Spring Boot application provides the API layer for handling:
- User authentication and registration
- Farm and field data management
- Crop and activity records
- Maintenance and equipment operations
- Harvest and expense tracking

## Prerequisites

Before running the project locally, make sure you have:

- Node.js 20+
- npm
- Java 17+ (the project is configured for Spring Boot and the repository CI validates using Java 17)
- MySQL Server running locally
- Git

## Database Setup

Create a MySQL database named `harvesthub`.

```sql
CREATE DATABASE IF NOT EXISTS harvesthub;
USE harvesthub;
```

The backend is configured in `auth-backend/src/main/resources/application.properties` with the following default settings:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/harvesthub?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Kolkata
spring.datasource.username=root
spring.datasource.password=abhi1234
server.port=8083
```

If your MySQL credentials differ, update those values in the config file before starting the backend.

## Running the Backend

Open a terminal and run:

```bash
cd auth-backend
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
cd auth-backend
./mvnw.cmd spring-boot:run
```

The backend will run on:

```text
http://localhost:8083
```

## Running the Frontend

Open a second terminal and run:

```bash
cd angular-auth
npm install
npm start
```

This starts the Angular development server on:

```text
http://localhost:4200
```

## Build Commands

### Frontend build

```bash
cd angular-auth
npm run build
```

### Backend build

```bash
cd auth-backend
./mvnw clean package
```

## Testing

### Frontend tests

```bash
cd angular-auth
npm test
```

### Backend tests

```bash
cd auth-backend
./mvnw test
```

## CI/CD

GitHub Actions is configured in `.github/workflows/harvesthub.yml` to validate the repository structure and build both the backend and frontend on pushes and pull requests to the main branch.

## Notes

- The SQL schema and sample queries are stored in `Document/Harvesthub.sql`.
- The frontend uses Angular routing for page navigation and authentication guard protection on protected routes.
- The backend is configured for JPA and MySQL-based persistence.

## License

This project is currently configured for internal or educational use unless a separate license is added.

## Contributing

If you are continuing development on this project:

1. Create a branch for your feature or bug fix.
2. Make your changes.
3. Validate frontend and backend builds.
4. Submit a pull request with a clear summary of the work.

---

HarvestHub is structured as a practical agricultural management solution and is ready to be extended with additional modules, APIs, and reporting features.
