# CodePhoenix AI 🔥

**Agentic Legacy Modernization Platform**

CodePhoenix AI is an intelligent platform that analyzes legacy codebases, generates AI-powered insights, and provides a comprehensive modernization roadmap to transform outdated systems into modern, scalable applications.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [System Architecture](#system-architecture)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
  - [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Environment Configuration](#environment-configuration)
- [Usage Guide](#usage-guide)
- [Key Features & Workflows](#key-features--workflows)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

CodePhoenix AI revolutionizes legacy system modernization through:

- **Intelligent Code Analysis**: Deep scanning and parsing of legacy codebases
- **AI-Powered Insights**: Google Gemini API integration for intelligent analysis
- **Architecture Visualization**: Interactive diagrams of system dependencies and components
- **Modernization Roadmap**: Automated generation of step-by-step modernization strategies
- **Security Assessment**: Identify security vulnerabilities and compliance issues
- **Technical Debt Calculation**: Quantify and prioritize technical debt
- **Comprehensive Reporting**: Detailed PDF/CSV reports with actionable recommendations

---

## Features

### 🔍 Code Analysis
- Multi-language project scanning (Java, Python, JavaScript, and more)
- File structure analysis and dependency mapping
- Code metrics collection (complexity, coverage, maintainability)
- Automatic detection of outdated patterns and anti-patterns

### 🤖 AI-Powered Insights
- GPT-style analysis using Google Gemini API
- Smart documentation generation
- Pattern recognition for modernization opportunities
- Context-aware recommendations

### 📊 Visualization & Reporting
- Interactive architecture graphs
- Dependency tree visualization
- Health score dashboards
- Technical debt assessments
- Exportable reports (CSV, JSON)

### 🛡️ Security & Compliance
- Vulnerability scanning
- Security pattern detection
- Compliance assessment
- Risk scoring

### 🚀 Modernization Roadmap
- Prioritized migration strategies
- Effort estimation
- Risk analysis per modernization phase
- Technology recommendations

### 👥 Agent-Based Processing
- Autonomous agents for different analysis domains
- Parallel processing capabilities
- Intelligent workflow orchestration

---

## Tech Stack

### Backend
| Component | Technology | Version |
|-----------|-----------|---------|
| Framework | Spring Boot | 3.5.4 |
| Language | Java | 21 |
| Build Tool | Maven | Latest |
| Database | PostgreSQL | 14+ |
| Authentication | JWT | Custom Implementation |
| AI Service | Google Gemini API | Latest |
| File Processing | Apache Commons | Latest |
| Documentation | Springdoc OpenAPI (Swagger) | 2.x |

### Frontend
| Component | Technology | Version |
|-----------|-----------|---------|
| Framework | React | 18+ |
| Build Tool | Vite | 5+ |
| Styling | CSS/Tailwind | Latest |
| HTTP Client | Axios | Latest |
| State Management | React Context | Built-in |
| Visualization | D3.js / Custom | Latest |

### Database
- **PostgreSQL** 14+ with JPA/Hibernate ORM
- Connection pooling with Hikari CP
- Automatic schema generation and updates

---

## System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     Client Layer (React)                     │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │  Dashboard   │  │   Analysis   │  │   Reports    │       │
│  │  Components  │  │   Views      │  │  & Export    │       │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
└──────────────────────┬──────────────────────────────────────┘
                       │ (REST API)
┌──────────────────────▼──────────────────────────────────────┐
│                   API Gateway Layer                          │
│         Spring Security & JWT Authentication                │
└──────────────────────┬──────────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────────┐
│               Business Logic Layer                           │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Controllers                                         │   │
│  │  • ProjectController                                │   │
│  │  • AnalysisController                               │   │
│  │  • ReportController                                 │   │
│  │  • AgentController                                  │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Services & Agents                                   │   │
│  │  • ProjectService                                   │   │
│  │  • CodeAnalysisService                              │   │
│  │  • ArchitectureAnalysisAgent                        │   │
│  │  • ModernizationAgent                               │   │
│  │  • SecurityAnalysisAgent                            │   │
│  │  • ReportGenerationService                          │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  AI Integration Layer                                │   │
│  │  • Google Gemini API Client                         │   │
│  │  • Prompt Engineering                               │   │
│  │  • Response Parsing & Aggregation                   │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Core Analysis Modules                               │   │
│  │  • CodeParser                                       │   │
│  │  • DependencyAnalyzer                               │   │
│  │  • ImpactAnalyzer                                   │   │
│  │  • SecurityScanner                                  │   │
│  │  • DocumentationGenerator                          │   │
│  └──────────────────────────────────────────────────────┘   │
└──────────────────────┬──────────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────────┐
│             Data Persistence Layer                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │ Repositories │  │  JPA/ORM     │  │  Entities    │       │
│  │  (Database)  │  │  (Hibernate) │  │  (Domain)    │       │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
└──────────────────────┬──────────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────────┐
│         PostgreSQL Database + File Storage                  │
│  ┌──────────────────────────────────────────────────────┐   │
│  │ Tables: Projects, Analysis, Reports, Users, Agents   │   │
│  │ File Storage: /uploads/projects                      │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘

External Services:
├─ Google Gemini API (AI Analysis)
├─ JWT Auth Token
└─ File Upload Handler
```

### Architecture Layers

1. **Presentation Layer**: React frontend with interactive UI components
2. **API Gateway**: Spring Security with JWT-based authentication
3. **Business Logic**: Controllers, Services, and Intelligent Agents
4. **AI Integration**: Google Gemini API for intelligent analysis
5. **Data Access**: JPA Repositories with Hibernate ORM
6. **Database**: PostgreSQL with automatic schema management

---

## Project Structure

```
CodePhoenix-AI/
├── backend/
│   ├── src/main/java/com/codephoenix/
│   │   ├── agents/              # AI Agent implementations
│   │   ├── ai/                  # AI service integrations
│   │   ├── analyzer/            # Code analysis engines
│   │   ├── architecture/        # Architecture analysis
│   │   ├── config/              # Spring configurations
│   │   ├── controller/          # REST controllers
│   │   ├── documentation/       # Doc generation
│   │   ├── dto/                 # Data transfer objects
│   │   ├── entity/              # JPA entities
│   │   ├── impact/              # Impact analysis
│   │   ├── modernization/       # Modernization logic
│   │   ├── parser/              # Code parsers
│   │   ├── report/              # Report generation
│   │   ├── repository/          # Database repositories
│   │   ├── scanner/             # Code scanners
│   │   ├── security/            # Security configs
│   │   ├── service/             # Business services
│   │   ├── util/                # Utility classes
│   │   └── CodePhoenixApplication.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── prompts/             # AI prompt templates
│   ├── pom.xml                  # Maven dependencies
│   ├── target/                  # Build output
│   └── uploads/                 # Uploaded projects & reports
├── frontend/
│   ├── src/
│   │   ├── components/          # React components
│   │   │   ├── AgentResultCard.jsx
│   │   │   ├── ArchitectureGraph.jsx
│   │   │   ├── DependencyTree.jsx
│   │   │   ├── FileUploader.jsx
│   │   │   ├── HealthScoreCard.jsx
│   │   │   ├── ModernizationRoadmap.jsx
│   │   │   ├── ReportViewer.jsx
│   │   │   ├── SecurityCard.jsx
│   │   │   └── TechnicalDebtCard.jsx
│   │   ├── pages/               # Page components
│   │   ├── services/            # API services
│   │   ├── context/             # React context
│   │   ├── utils/               # Utilities
│   │   ├── assets/              # Static assets
│   │   ├── App.jsx
│   │   └── main.jsx
│   ├── package.json
│   ├── vite.config.js
│   └── index.html
├── docs/                        # Documentation
├── sample-projects/             # Sample legacy projects
│   ├── BuggyEmployeeSystem/
│   ├── LegacyBankingSystem/
│   ├── LegacyInventorySystem/
│   └── OutdatedSpringProject/
├── local_postgres_data/         # PostgreSQL data directory
├── architecture/                # Architecture documentation
└── README.md                    # This file
```

---

## Prerequisites

### System Requirements
- **Java**: JDK 21 or higher
- **Node.js**: 18+ with npm or yarn
- **PostgreSQL**: 14 or higher
- **RAM**: Minimum 4GB (8GB recommended)
- **Disk Space**: 5GB free space (for projects & uploads)

### Accounts & APIs
- **Google Gemini API Key**: [Get here](https://aistudio.google.com/app/apikeys)
- (Optional) **GitHub Account**: For cloning sample projects

---

## Installation & Setup

### 1. Backend Setup

#### Step 1: Clone or Navigate to Backend
```bash
cd backend
```

#### Step 2: Install Maven Dependencies
```bash
mvn clean install
```

#### Step 3: Update Application Properties
Edit `src/main/resources/application.properties`:

```properties
# Server Configuration
spring.application.name=CodePhoenix-AI
server.port=8080

# PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/codephoenix
spring.datasource.username=postgres
spring.datasource.password=your_password_here
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# File Upload Configuration
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB
file.upload-dir=uploads/projects

# JWT Configuration
jwt.secret=CodePhoenixSecretKeyForJWTAuthentication2026CodePhoenix
jwt.expiration=86400000

# Google Gemini API
gemini.api.key=YOUR_GEMINI_API_KEY_HERE

# Logging
logging.level.org.springframework.security=DEBUG
```

#### Step 4: Build the Backend
```bash
mvn clean package -DskipTests
# Or with tests:
mvn clean package
```

### 2. Frontend Setup

#### Step 1: Navigate to Frontend Directory
```bash
cd frontend
```

#### Step 2: Install Dependencies
```bash
npm install
# or
yarn install
```

#### Step 3: Create Environment Configuration
Create `frontend/.env`:
```
VITE_API_URL=http://localhost:8080/api
VITE_APP_NAME=CodePhoenix AI
```

#### Step 4: Build Frontend (Optional)
```bash
npm run build
# or
yarn build
```

### 3. Database Setup

#### Step 1: Install PostgreSQL
- **Windows**: Download from [postgresql.org](https://www.postgresql.org/download/windows/)
- **Mac**: `brew install postgresql`
- **Linux**: `sudo apt-get install postgresql postgresql-contrib`

#### Step 2: Start PostgreSQL Service
```bash
# Windows
pg_ctl -D "C:\path\to\data" start

# Mac
brew services start postgresql

# Linux
sudo service postgresql start
```

#### Step 3: Create Database & User
```bash
# Connect to PostgreSQL
psql -U postgres

# In psql terminal:
CREATE DATABASE codephoenix;
CREATE USER codephoenix_user WITH PASSWORD 'password123';
ALTER ROLE codephoenix_user SET client_encoding TO 'utf8';
ALTER ROLE codephoenix_user SET default_transaction_isolation TO 'read committed';
ALTER ROLE codephoenix_user SET default_transaction_deferrable TO on;
ALTER ROLE codephoenix_user SET default_transaction_read_only TO off;
GRANT ALL PRIVILEGES ON DATABASE codephoenix TO codephoenix_user;
\q
```

#### Step 4: Verify Connection
```bash
psql -U codephoenix_user -d codephoenix -h localhost
```

---

## Running the Application

### Option 1: Development Mode

#### Terminal 1 - Backend (Spring Boot)
```bash
cd backend
mvn spring-boot:run
```
✅ Backend available at: `http://localhost:8080`

#### Terminal 2 - Frontend (Vite Dev Server)
```bash
cd frontend
npm run dev
```
✅ Frontend available at: `http://localhost:5173`

### Option 2: Production Mode

#### Build Backend
```bash
cd backend
mvn clean package -DskipTests
java -jar target/codephoenix-ai-1.0.0.jar
```

#### Build Frontend
```bash
cd frontend
npm run build
# Serve dist folder with a static server
npx serve dist
```

### Option 3: Docker (Optional)
```bash
# Build backend image
docker build -t codephoenix-ai-backend ./backend

# Build frontend image
docker build -t codephoenix-ai-frontend ./frontend

# Run with docker-compose
docker-compose up -d
```

---

## API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication
All protected endpoints require JWT token in header:
```
Authorization: Bearer <jwt_token>
```

### Core Endpoints

#### 1. **Authentication**
```
POST /auth/register          - Register new user
POST /auth/login             - Login user
POST /auth/refresh-token     - Refresh JWT token
```

#### 2. **Projects**
```
POST /projects/upload        - Upload project for analysis
GET /projects                - List all projects
GET /projects/{id}           - Get project details
DELETE /projects/{id}        - Delete project
```

#### 3. **Analysis**
```
POST /analysis/scan          - Scan project code
GET /analysis/{projectId}    - Get analysis results
POST /analysis/ai-insights   - Generate AI insights via Gemini
```

#### 4. **Architecture**
```
GET /architecture/{projectId} - Get architecture graph
GET /dependencies/{projectId} - Get dependency tree
```

#### 5. **Modernization**
```
GET /modernization/{projectId} - Get modernization roadmap
GET /modernization/phases/{projectId} - Get modernization phases
```

#### 6. **Security**
```
GET /security/{projectId}    - Get security assessment
GET /vulnerabilities/{projectId} - Get vulnerability scan
```

#### 7. **Reports**
```
POST /reports/generate       - Generate report
GET /reports/{projectId}     - Get project reports
GET /reports/download/{id}   - Download report (CSV/PDF)
```

#### 8. **Health**
```
GET /health                  - Health check
GET /health/database         - Database connection status
```

### Sample API Calls

#### Upload a Project
```bash
curl -X POST http://localhost:8080/api/projects/upload \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -F "file=@project.zip" \
  -F "projectName=My Legacy System" \
  -F "language=java"
```

#### Get Analysis Results
```bash
curl -X GET http://localhost:8080/api/analysis/PROJECT_ID \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Generate Modernization Roadmap
```bash
curl -X GET http://localhost:8080/api/modernization/PROJECT_ID \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## Environment Configuration

### Backend Configuration Files

#### `application.properties`
Located at: `backend/src/main/resources/application.properties`

Key configurations:
- **Database**: PostgreSQL connection parameters
- **JWT**: Token secret and expiration time
- **File Upload**: Max file size limits
- **Gemini API**: AI service integration
- **Logging**: Application log levels

### Frontend Configuration Files

#### `.env`
Located at: `frontend/.env`

```
VITE_API_URL=http://localhost:8080/api
VITE_APP_NAME=CodePhoenix AI
VITE_LOG_LEVEL=info
```

#### `vite.config.js`
Development server configuration

---

## Usage Guide

### Workflow 1: Analyze a Legacy Project

1. **Upload Project**
   - Navigate to Dashboard → Upload Project
   - Select ZIP file containing your codebase
   - Choose programming language
   - Click "Analyze"

2. **View Analysis Results**
   - Architecture graph showing system components
   - Dependency tree visualization
   - Code metrics and statistics

3. **Generate AI Insights**
   - System automatically generates insights using Gemini API
   - Review intelligent recommendations
   - Check identified patterns and anti-patterns

4. **Assess Technical Debt**
   - View technical debt score
   - Analyze components with highest debt
   - Identify quick wins

5. **Security Assessment**
   - Review security vulnerabilities
   - Check compliance issues
   - Get security recommendations

### Workflow 2: Generate Modernization Roadmap

1. **Review Analysis**
   - Ensure project analysis is complete
   - Check identified migration blockers

2. **Generate Roadmap**
   - Click "Generate Modernization Plan"
   - System creates phases with effort estimates

3. **Review Phases**
   - Phase 1: Foundation & Infrastructure
   - Phase 2: Core Component Migration
   - Phase 3: Integration & Testing
   - Phase 4: Optimization & Monitoring

4. **Export Roadmap**
   - Export as CSV or JSON
   - Share with team members
   - Track progress

### Workflow 3: Generate Reports

1. **Create Report**
   - Select analysis completed
   - Choose report format (PDF/CSV)
   - Configure report sections

2. **Download Report**
   - High-level executive summary
   - Detailed technical analysis
   - Modernization recommendations
   - Risk assessment

3. **Share Results**
   - Distribute to stakeholders
   - Use for planning and budgeting

---

## Key Features & Workflows

### 🔍 Multi-Language Code Analysis
Supports analysis of:
- Java (Spring, J2EE, Enterprise systems)
- Python (Django, Flask)
- JavaScript/Node.js
- C# / .NET
- Go, Rust, and more

### 🤖 AI-Powered Insights
- Contextual analysis using Google Gemini
- Pattern recognition
- Intelligent recommendations
- Auto-generated documentation

### 📊 Real-time Dashboards
- Project health scores
- Technical debt metrics
- Security posture
- Migration progress tracking

### 🛡️ Security Analysis
- Vulnerability scanning
- Dependency security checks
- Compliance assessment
- Risk scoring

### 📈 Detailed Reports
- Executive summaries
- Technical deep dives
- Cost-benefit analysis
- Timeline estimations

---

## Troubleshooting

### Common Issues

#### 1. Database Connection Error
**Error**: `org.postgresql.util.PSQLException: Connection refused`

**Solution**:
```bash
# Check PostgreSQL is running
psql -U postgres

# Verify database credentials
# Check application.properties has correct:
# - spring.datasource.url
# - spring.datasource.username
# - spring.datasource.password
```

#### 2. Gemini API Error
**Error**: `401 Unauthorized - Invalid API Key`

**Solution**:
```bash
# Verify API key in application.properties
# Get new key from: https://aistudio.google.com/app/apikeys
# Ensure key has Gemini API access enabled
```

#### 3. Frontend Cannot Connect to Backend
**Error**: `CORS error` or `Cannot connect to http://localhost:8080`

**Solution**:
```bash
# Check backend is running on port 8080
# Verify VITE_API_URL in .env
# Clear browser cache
# Check CORS configuration in backend
```

#### 4. File Upload Failed
**Error**: `413 Payload Too Large`

**Solution**:
```bash
# Update in application.properties:
spring.servlet.multipart.max-file-size=200MB
spring.servlet.multipart.max-request-size=200MB

# Rebuild backend
mvn clean package
```

#### 5. JWT Token Expired
**Error**: `401 Unauthorized - Token Expired`

**Solution**:
```bash
# Use refresh-token endpoint to get new token
POST /api/auth/refresh-token

# Or login again
POST /api/auth/login
```

### Debug Mode

#### Enable Debug Logging
Edit `application.properties`:
```properties
logging.level.root=DEBUG
logging.level.com.codephoenix=DEBUG
logging.level.org.springframework=DEBUG
```

#### View Backend Logs
```bash
# Spring Boot console output shows logs
# Also check: backend/logs/application.log
tail -f backend/logs/application.log
```

#### Browser Developer Tools
- **F12** or **Ctrl+Shift+I** - Open DevTools
- **Network Tab** - Inspect API calls
- **Console Tab** - Check JavaScript errors
- **Application Tab** - View local storage & cookies

---

## Testing

### Unit Tests
```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=ProjectServiceTest

# With coverage
mvn test jacoco:report
```

### Integration Tests
```bash
mvn verify
```

### Frontend Tests
```bash
cd frontend
npm test
npm run test:coverage
```

---

## Performance Optimization

### Backend
- Connection pooling configured (max 10 connections)
- Query optimization with proper indexing
- Caching strategy for frequently accessed data
- Async processing for heavy analysis tasks

### Frontend
- Code splitting with Vite
- Lazy loading of components
- Image optimization
- Service worker caching

### Database
- Indexes on frequently queried columns
- Partition large tables if needed
- Regular maintenance and vacuuming
- Connection pooling (Hikari CP)

---

## Deployment

### Production Checklist

- [ ] Database backups configured
- [ ] SSL/TLS certificates installed
- [ ] Environment variables secured
- [ ] JWT secret rotated
- [ ] API rate limiting configured
- [ ] Monitoring and alerting setup
- [ ] Log aggregation configured
- [ ] CORS properly configured
- [ ] Security headers added
- [ ] Load balancer configured (if needed)

### Deployment Platforms

#### AWS
- RDS for PostgreSQL
- EC2 for backend
- CloudFront + S3 for frontend
- ALB for load balancing

#### GCP
- Cloud SQL for PostgreSQL
- Compute Engine for backend
- Cloud Storage + CDN for frontend
- Cloud Load Balancing

#### Azure
- Azure Database for PostgreSQL
- App Service for backend
- Static Web App for frontend
- Application Gateway for load balancing

---

## Contributing

### How to Contribute

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

### Code Standards

- Follow Spring Boot best practices
- Use meaningful variable names
- Add comments for complex logic
- Write unit tests for new features
- Keep functions small and focused

---

## Support & Documentation

### Resources
- **GitHub Issues**: Report bugs and request features
- **Wiki**: [CodePhoenix Documentation](https://github.com/yourusername/codephoenix-ai/wiki)
- **Discord Community**: [Join us](https://discord.gg/codephoenix)
- **Email Support**: support@codephoenix.ai

### Useful Links
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Google Gemini API](https://ai.google.dev/)

---

## License

This project is licensed under the MIT License - see [LICENSE](LICENSE) file for details.

---

## Acknowledgments

- Built with ❤️ using Spring Boot and React
- Powered by Google Gemini AI
- Community contributions and feedback

---

## Quick Start Summary

```bash
# 1. Clone repository
git clone https://github.com/yourusername/codephoenix-ai.git
cd CodePhoenix-AI

# 2. Setup Database
# - Install PostgreSQL
# - Create database 'codephoenix'
# - Create user with credentials

# 3. Backend
cd backend
mvn clean install
mvn spring-boot:run
# Backend running on http://localhost:8080

# 4. Frontend (new terminal)
cd frontend
npm install
npm run dev
# Frontend running on http://localhost:5173

# 5. Open Browser
# Navigate to http://localhost:5173
# Upload your legacy project
# Get AI-powered modernization insights!
```

---

**Ready to modernize your legacy systems? Let's phoenixify your code! 🔥**

For updates and news, follow us on [Twitter](https://twitter.com/codephoenix) | [LinkedIn](https://linkedin.com/company/codephoenix)

---

*Last Updated: 2026*
*Version: 1.0.0*
