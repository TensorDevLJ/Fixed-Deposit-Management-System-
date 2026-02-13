# Fixed Deposit Management System (FDMS)

## Project Overview
The **Fixed Deposit Management System (FDMS)** is a robust backend application designed to simulate the lifecycle of fixed deposits in a bank. It allows users to create fixed deposit accounts, calculate maturity amounts, track active deposits, and handle premature withdrawals. The system also includes an administrative module for managing interest rates dynamically and advanced features like scheduled notifications and analytics.

This project demonstrates core FinTech concepts, handling financial calculations, entity relationships, and scheduled tasks in a modern Java ecosystem.

## Tech Stack
-   **Language**: Java 17
-   **Framework**: Spring Boot 3.2.3
-   **Database**: MySQL
-   **ORM**: Hibernate / Spring Data JPA
-   **Build Tool**: Maven
-   **Tools**: Lombok, Spring Web

## Features

### Core Features
1.  **User Management**:
    -   User Registration with role assignment (USER/ADMIN).
    -   Secure Login (Basic authentication flow).
2.  **Fixed Deposit Operations**:
    -   **Create FD**: Users can open a new FD with a specific principal amount and tenure. The system automatically fetches the applicable interest rate based on administrative configuration.
    -   **Maturity Calculation**: Automatically calculates the maturity amount using Simple Interest logic upon creation.
    -   **View Deposits**: Users can view all their active and closed deposits.
    -   **Early Withdrawal**: Supports premature closure of FDs. Includes logic placeholders for applying penalties on interest earn.

### Admin Module
-   **Interest Rate Management**: Admins can configure interest rates for different tenure ranges (e.g., 1-12 months: 6.5%, 13-36 months: 7.2%).
-   **Dynamic Rate Application**: New FDs automatically pick up the latest configured rates.

### Advanced Features
-   **Scheduled Notifications**: A background job runs daily at 9:00 AM to identify FDs maturing on the current day and logs a notification (simulation of SMS/Email).
-   **Analytics API**: Provides real-time statistics on total active deposits and total principal amount invested in the system.

## Setup and Installation

### Prerequisites
-   Java 17 Development Kit (JDK)
-   Maven 3.8+
-   MySQL Server 8.0+

### Database Setup
1.  Open your MySQL client (Workbench, DBeaver, or CLI).
2.  Create the database:
    ```sql
    CREATE DATABASE fd_db;
    ```
3.  The application is configured to automatically create tables (`users`, `fixed_deposits`, `interest_rates`) on startup.

### Configuration
Update the `src/main/resources/application.properties` file with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fd_db?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### Running the Application
**Method 1: Using Terminal (Recommended)**
Navigate to the project root directory and run:
```bash
mvn spring-boot:run
```

**Method 2: Using IDE**
Import the project into IntelliJ IDEA, Eclipse, or VS Code as a Maven project. Locate `FdSystemApplication.java` and click **Run**.

The application will start on **port 8080**.

## How to Use (API Endpoints)

You can use Postman or `curl` to interact with the APIs.

### 1. Setup Interest Rates (Admin)
**POST** `http://localhost:8080/api/admin/rates`
```json
{
  "minTenureMonths": 1,
  "maxTenureMonths": 12,
  "interestRate": 6.5
}
```

### 2. Register User
**POST** `http://localhost:8080/api/users/register`
```json
{
  "username": "john_doe",
  "password": "password123",
  "fullName": "John Doe",
  "email": "john@example.com",
  "role": "USER"
}
```

### 3. Create Fixed Deposit
**POST** `http://localhost:8080/api/fds/{userId}` (Replace `{userId}` with actual ID, e.g., 1)
```json
{
  "principalAmount": 10000,
  "tenureMonths": 12
}
```

### 4. View Analytics
**GET** `http://localhost:8080/api/analytics/summary`

## Future Scope
-   **Security**: Implement Spring Security with JWT for robust authentication and authorization.
-   **Email Integration**: Replace console logs with real email notifications using JavaMailSender.
-   **Frontend**: Build a React/Angular dashboard for users to visualize their portfolio.
-   **Complex Interest Logic**: Upgrade from Simple Interest to Compound Interest with quarterly/monthly compounding options.
-   **Payment Gateway**: Integrate a payment gateway for real fund transfers.
