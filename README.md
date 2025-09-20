Prolaborate - Student Project Showcase
About the Project
Prolaborate is a web app for engineering students to showcase their projects. It allows students to register, log in, create, edit, and delete projects with details like title, department, domain, and description, along with up to 5 images and 2 videos. Anyone can view all projects and student profiles without logging in. The app uses a secure backend with JWT authentication and a user-friendly React frontend.
What's Included

Backend: A Spring Boot REST API that handles user authentication, project management, and stores media (images/videos) in a PostgreSQL database.
Frontend: A React app with pages for registration, login, project creation, viewing projects, and a student list. It uses Material-UI for styling and Axios for API calls.
Security: JWT-based authentication ensures only logged-in students can manage their projects.
Database: PostgreSQL stores student and project data, with media saved as binary data.

How I Built It

Backend (Spring Boot):

Used Spring Boot 3.5.4 with Java 17 and Maven.
Set up PostgreSQL for data storage.
Created entities (Student, Project) and repositories for CRUD operations.
Implemented Spring Security with JWT for secure authentication.
Built REST controllers for user registration (/api/auth/register), login (/api/auth/login), and project management (/api/projects).
Configured file uploads to handle images/videos (max 50MB).


Frontend (React):

Created a React app with create-react-app.
Used React Router for navigation and Material-UI for styling.
Built components (Navbar, ProjectCard, ProjectForm) for reusable UI.
Added pages (Home, Login, Register, Dashboard, ProjectDetail, StudentList).
Managed authentication state with AuthContext and jwt-decode.
Used Axios to connect to backend APIs and react-hook-form for form handling.


Integration:

Connected frontend to backend via API calls (http://localhost:8080/api).
Ensured secure communication with JWT tokens stored in localStorage.
Tested with Postman for backend and browser for frontend.



How to Run

Backend:

Install Java 17, Maven, and PostgreSQL.
Create a PostgreSQL database: CREATE DATABASE prolaborate;.
Update prolaborate-backend/src/main/resources/application.properties with DB credentials.
Run:cd prolaborate-backend
mvn clean install
mvn spring-boot:run




Frontend:

Install Node.js (16.x or later).
Install dependencies:cd prolaborate-frontend
npm install
npm install @mui/material @emotion/react @emotion/styled react-hook-form jwt-decode react-router-dom axios


Run:npm start




Access:

Open http://localhost:3000 in your browser.
Register, log in, and start creating projects!



Notes

Ensure the backend is running before starting the frontend.
Media files are stored in the database but not displayed yet (needs additional backend endpoints).
For issues, check terminal and browser console logs.
