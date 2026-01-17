# 💸 Expense Tracker API

A production-ready, containerized REST API built with **Java 25** and **Spring Boot**. This project manages personal expenses with a focus on clean architecture, automated CI/CD, and cloud-native database integration.

## 🚀 Live Environment

The API is currently deployed and running at:
**[https://expense-tracker-backend-156g.onrender.com/expenses](https://www.google.com/url?sa=E&source=gmail&q=https://expense-tracker-backend-156g.onrender.com/expenses)**

---

## 🛠 Tech Stack

| Layer | Technology |
| --- | --- |
| **Language** | Java 25 (OpenJDK) |
| **Framework** | Spring Boot 3.4+ |
| **Database** | Neon PostgreSQL (Serverless) |
| **Containerization** | Docker (Multi-stage builds) |
| **CI/CD** | GitHub Actions |
| **Infrastructure** | Render (PaaS) |

---

## 🏗 System Architecture

The application utilizes a modern **Cloud-Native** design:

1. **Multi-Stage Dockerization:** Separates the build environment (JDK 25) from the runtime environment (JRE 25) to create a lean, secure production image.
2. **Serverless Persistence:** Integrates with Neon's serverless PostgreSQL, utilizing SNI routing for efficient project-based connections.
3. **Environment-Driven Configuration:** Uses Spring Boot's relaxed binding to override `application.properties` with cloud environment variables safely.

---

## 🚦 API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/expenses` | List all records (Sorted: Newest First) |
| `POST` | `/expenses` | Create a new expense entry |
| `GET` | `/expenses/{amount}` | List all records (Sorted: Newest First) above the specified amount |

#### 1. Data Model

| Field | Type | Description |
| --- | --- | --- |
| `id` | `Long` | Primary Key (Auto-generated) |
| `description` | `String` | What the money was spent on |
| `amount` | `Double` | The numerical cost |
| `category` | `String` | e.g., Food, Travel, Rent |
| `date` | `LocalDateTime` | Auto-set to current timestamp |

#### 2. Get All Expenses

**URL:** `/expenses`

**Method:** `GET`

**Success Response:** `200 OK`

```json
[
  {
    "id": 1,
    "description": "Lunch at Hostel",
    "amount": 120.50,
    "category": "Food",
    "date": "2026-01-17T10:00:00"
  }
]

```

#### 3. Create Expense

**URL:** `/expenses`

**Method:** `POST`

**Payload:**

```json
{
  "description": "New Keyboard",
  "amount": 1500.00,
  "category": "Electronics"
}

```

**Success Response:** `201 Created`

---

### Example Request (POST)

```bash
curl -X POST https://expense-tracker-backend-156g.onrender.com/expenses \
     -H "Content-Type: application/json" \
     -d '{"description": "Server Hosting", "amount": 15.0, "category": "Tech"}'

```

---

## 💻 Local Development (Arch Linux / Zsh)

### Setup Environment Variables

To connect your local instance to the Neon Cloud DB, set your Zsh variables using single quotes to handle special characters:

```zsh
export SPRING_DATASOURCE_URL='jdbc:postgresql://your-neon-host.aws.neon.tech/expenses?sslmode=require'
export SPRING_DATASOURCE_USERNAME='neondb_owner'
export SPRING_DATASOURCE_PASSWORD='your_password_here'

```

### Build and Run

```bash
# Ensure the wrapper is executable
chmod +x mvnw

# Run the application
./mvnw spring-boot:run

```

---

## 🐳 Docker Deployment

The project includes a multi-stage `Dockerfile` optimized for JDK 25.

```bash
# Build the image
docker build -t expense-tracker-api .

# Run the container locally
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL \
  -e SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME \
  -e SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD \
  expense-tracker-api

```

