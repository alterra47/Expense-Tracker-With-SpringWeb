# 💸 Expense Tracker API

A cloud-deployed REST API built with **Java 25** and **Spring Boot 4**, featuring persistent expense management and an **AI-powered financial assistant using Spring AI and Google Gemini**.

The project demonstrates REST API development, PostgreSQL persistence, generative AI integration, containerization, automated CI/CD, and cloud deployment.

## 🚀 Live Environment

The API is deployed on Render:

**Base URL:**  
`https://expense-tracker-backend-156g.onrender.com`

### Quick Test

```bash
curl https://expense-tracker-backend-156g.onrender.com/expenses
```

---

## 🛠 Tech Stack

| Layer | Technology |
| --- | --- |
| **Language** | Java 25 (OpenJDK) |
| **Framework** | Spring Boot 4.0+ |
| **AI Framework** | Spring AI |
| **LLM** | Google Gemini |
| **Database** | Neon PostgreSQL (Serverless) |
| **Persistence** | Spring Data JPA / Hibernate |
| **Containerization** | Docker (Multi-stage build) |
| **CI/CD** | GitHub Actions |
| **Infrastructure** | Render (PaaS) |

---

## ✨ Features

- RESTful CRUD operations for expense management
- Persistent storage using PostgreSQL
- AI-powered financial assistant
- Spring AI integration with Google Gemini
- Input validation using Jakarta Validation
- Environment-based secret and configuration management
- Multi-stage Docker containerization
- Automated CI/CD pipeline using GitHub Actions
- Cloud deployment on Render
- Serverless PostgreSQL database hosted on Neon

---

## 🏗 Architecture

```text
                         ┌─────────────────────┐
                         │    Google Gemini    │
                         └──────────▲──────────┘
                                    │
                              Spring AI
                                    │
                                    │
Client ───────► Render ───────► Spring Boot API
                                    │
                              Spring Data JPA
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │  Neon PostgreSQL    │
                         └─────────────────────┘
```

The application follows a cloud-native architecture:

1. **REST API Layer** — Spring MVC exposes endpoints for expense management and AI interaction.

2. **Persistence Layer** — Spring Data JPA handles database access while Neon provides serverless PostgreSQL persistence.

3. **AI Integration** — Spring AI's `ChatClient` integrates the application with Google Gemini to provide financial and budgeting assistance.

4. **Multi-Stage Dockerization** — The application is built separately from its runtime image to reduce the final container footprint.

5. **Environment-Driven Configuration** — Database credentials and AI API keys are injected through environment variables rather than stored in source control.

6. **Automated Deployment** — GitHub Actions and Render provide the CI/CD and cloud deployment workflow.

---

## 🚦 API Endpoints

### Expense Management

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/expenses` | Retrieve all expenses, newest first |
| `POST` | `/expenses` | Create a new expense |
| `GET` | `/expenses/{id}` | Retrieve an expense by ID |
| `GET` | `/expenses/expensive/{amt}` | Retrieve expenses above a specified amount |
| `DELETE` | `/expenses/{id}` | Delete an expense |

### AI Assistant

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/chat` | Send a financial or expense-related question to the AI assistant |

---

## 📝 API Examples

### Create an Expense

```bash
curl -X POST \
  "https://expense-tracker-backend-156g.onrender.com/expenses" \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Server Hosting",
    "amount": 15.0,
    "dateTime": "2026-11-20T14:40:00"
  }'
```

### Get All Expenses

```bash
curl "https://expense-tracker-backend-156g.onrender.com/expenses"
```

### Find Expensive Expenses

```bash
curl "https://expense-tracker-backend-156g.onrender.com/expenses/expensive/1000"
```

### Ask the AI Assistant

```bash
curl -X POST \
  "https://expense-tracker-backend-156g.onrender.com/chat" \
  -H "Content-Type: application/json" \
  -d '{
    "message": "How can I reduce my monthly expenses?"
  }'
```

Example response:

```json
{
  "response": "Review recurring subscriptions, identify discretionary spending, and set monthly limits for non-essential categories."
}
```

> The current AI assistant provides general financial and budgeting guidance. It does not directly access stored expense records.

---

## 🤖 Spring AI Integration

The application uses **Spring AI** to integrate Google Gemini with the Spring Boot backend.

A dedicated `/chat` endpoint accepts user questions and uses Spring AI's `ChatClient` abstraction to communicate with Gemini.

```text
POST /chat
      │
      ▼
ChatController
      │
      ▼
Spring AI ChatClient
      │
      ▼
Google Gemini
```

The Gemini API key is supplied securely through the `GEMINI_API_KEY` environment variable and is never committed to the repository.

---

## 💻 Local Development

### Prerequisites

- Java 25
- Maven or included Maven Wrapper
- PostgreSQL database
- Gemini API key
- Docker (optional)

### Environment Variables

```bash
export SPRING_DATASOURCE_URL='jdbc:postgresql://your-neon-host.aws.neon.tech/expenses?sslmode=require'
export SPRING_DATASOURCE_USERNAME='neondb_owner'
export SPRING_DATASOURCE_PASSWORD='your_password'
export GEMINI_API_KEY='your_gemini_api_key'
```

### Run the Application

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

---

## 🐳 Docker Deployment

The project uses a multi-stage Docker build optimized for the Java 25 runtime.

### Build

```bash
docker build -t expense-tracker-api .
```

### Run

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="$SPRING_DATASOURCE_URL" \
  -e SPRING_DATASOURCE_USERNAME="$SPRING_DATASOURCE_USERNAME" \
  -e SPRING_DATASOURCE_PASSWORD="$SPRING_DATASOURCE_PASSWORD" \
  -e GEMINI_API_KEY="$GEMINI_API_KEY" \
  expense-tracker-api
```

---

## 🔐 Configuration & Security

Sensitive configuration is managed through environment variables:

| Variable | Purpose |
| --- | --- |
| `SPRING_DATASOURCE_URL` | PostgreSQL connection URL |
| `SPRING_DATASOURCE_USERNAME` | Database username |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `GEMINI_API_KEY` | Google Gemini API authentication |

Secrets are not stored in the source code or Docker image.

---

## 🔄 CI/CD

The project uses **GitHub Actions** and **Render** for automated deployment.

```text
Code Change
    │
    ▼
GitHub Repository
    │
    ▼
GitHub Actions
    │
    ▼
Docker Build
    │
    ▼
Render Deployment
    │
    ├────► Spring Boot API
    │
    ├────► Neon PostgreSQL
    │
    └────► Google Gemini
```

This allows changes pushed to the repository to be automatically built and deployed to the cloud.

---

## 🔮 Possible Future Improvements

- Connect the AI assistant to stored expense data for personalized analysis
- Add expense categories and category-based analytics
- Add monthly spending summaries and budget tracking
- Add authentication and per-user expense management
- Add conversational memory
- Add API documentation using OpenAPI/Swagger
- Add integration and containerized database tests

---

## 📌 Project Highlights

This project demonstrates practical experience with:

**Java 25 · Spring Boot 4 · Spring AI · REST APIs · Google Gemini · Spring Data JPA · PostgreSQL · Neon · Docker · GitHub Actions · Render · CI/CD**