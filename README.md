# 🌾 HarvestHub – Farm Management & Crop Planning

HarvestHub is a full-stack farm management application designed to simplify agricultural operations through a centralized platform. It helps users manage crops, fields, farm profiles, crop plans, activities, expenses, harvest records, equipment, maintenance schedules, and crop rotation.

The project is built using **Angular, Spring Boot, and MySQL**, with REST APIs connecting the frontend and backend.

## ✨ Features

- **User Authentication:** Login and signup functionality.
- **Dashboard:** Overview of farm statistics and records.
- **Crop Management:** Maintain crop information.
- **Field Management:** Organize fields and their details.
- **Farm Profile:** Manage farm information.
- **Crop Planning:** Track sowing dates, expected harvest dates, and crop status.
- **Activity Management:** Record and monitor farming activities.
- **Expense Tracking:** Store farming expenses and descriptions.
- **Harvest Management:** Maintain harvest quantities, selling prices, and quality.
- **Equipment Management:** Keep records of agricultural equipment.
- **Maintenance Scheduling:** Track maintenance dates, costs, and service status.
- **Crop Rotation:** Maintain crop rotation records for different fields.
- **REST API Integration:** Connect frontend components with backend services.
- **MySQL Persistence:** Store application data in a relational database.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Angular 21 | Frontend |
| TypeScript | Frontend logic |
| HTML5 and CSS3 | User interface |
| Java | Backend |
| Spring Boot 4.1.1 | REST APIs and business logic |
| Spring Data JPA / Hibernate | Database operations |
| MySQL | Database |
| Maven | Backend build tool |
| npm | Frontend package management |
| Postman | API testing |
| Git and GitHub | Version control |
| GitHub Actions | Automated build workflow |

## 📁 Project Structure

```text
HarvestHub/
├── .github/
│   └── workflows/
│       └── harvesthub.yml
├── Document/
│   ├── Harvesthub.sql
│   └── SRS Report.docx
├── angular-auth/
│   └── Angular frontend source
├── auth-backend/
│   └── Spring Boot backend source
├── .gitignore
└── README.md
```

## ⚙️ Prerequisites

Install the following before running the project:

- Java JDK compatible with the backend configuration
- Node.js and npm
- MySQL Server
- Git

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/abhishekraj01432-code/HarvestHub.git
cd HarvestHub
```

### 2. Configure MySQL

Create the database in MySQL:

```sql
CREATE DATABASE IF NOT EXISTS harvesthub;
```

Configure the database connection in:

`auth-backend/src/main/resources/application.properties`

Use your own local MySQL credentials. Do not commit database passwords, API keys, or other secrets to GitHub.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/harvesthub
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
server.port=8083
```

Set `DB_USERNAME` and `DB_PASSWORD` in your local environment before starting the backend. Keep your actual credentials private.

If needed, use the SQL script in `Document/Harvesthub.sql` to initialize or inspect the database structure.

### 3. Run the Backend

Open a terminal in the repository root:

```powershell
cd auth-backend
.\mvnw.cmd spring-boot:run
```

The backend is configured to run at:

`http://localhost:8083`

### 4. Run the Frontend

Open a second terminal in the repository root:

```powershell
cd angular-auth
npm install
npm start
```

Open the application at:

`http://localhost:4200`

Ensure that MySQL and the backend are running before testing frontend features that use the APIs.

## 🧪 Testing

Postman can be used to test backend REST APIs, including operations for creating, retrieving, updating, and deleting records.

The application can be tested locally by verifying:

- Login and signup flows
- CRUD operations across farm management modules
- Dashboard statistics
- Database persistence
- User-specific data access

## 🏗️ Build and Test Commands

### Frontend

```bash
cd angular-auth
npm run build
npm test
```

### Backend

```powershell
cd auth-backend
.\mvnw.cmd clean package
.\mvnw.cmd test
```

Run the test commands supported by the respective project configuration.

## 🔄 GitHub Actions

The repository includes a GitHub Actions workflow at `.github/workflows/harvesthub.yml` for automated project validation and build checks on configured pushes and pull requests.

Check the **Actions** tab in the repository to review workflow results.

## 📚 Documentation

Additional project documentation is available in the `Document/` directory:

- `Harvesthub.sql` – SQL database script
- `SRS Report.docx` – Software Requirements Specification

## 🎯 Project Objective

HarvestHub aims to organize essential farm management activities in one application. By centralizing crop planning, field operations, expenses, harvest records, and equipment maintenance, it provides a structured way to maintain agricultural information.

## 👨‍💻 Author

**Abhishek Verma**

B.Tech – Computer Science and Engineering

GitHub: [abhishekraj01432-code](https://github.com/abhishekraj01432-code)

---

*Developed as a full-stack farm management and crop planning project.*
