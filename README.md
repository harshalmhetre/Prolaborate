# ProLaborate

A simple web platform where engineering students can showcase their projects with photos, videos, and documents.

## What is ProLaborate?

ProLaborate lets engineering students:
- Upload their projects with images, videos, and documents
- Share project details like title, description, and domain
- Browse other students' projects by branch or domain
- Search for projects by student name or project title

## Features

**For Everyone:**
- View all student projects
- Search and filter projects
- See project details and media

**For Students (after login):**
- Create account and login
- Add new projects
- Upload project photos/videos/documents
- Edit or delete your own projects
- Manage your profile

## Technology Used

- **Frontend**: React
- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL

## Project Structure

```
prolaborate/
├── backend/          # Spring Boot API
├── frontend/         # React web app
└── README.md
```

## Getting Started

### What You Need
- Java 17+
- Node.js 16+
- PostgreSQL database

### Setup Database
1. Install PostgreSQL
2. Create a database called `prolaborate`
3. Create a user with access to this database

### Setup Backend
1. Go to backend folder
2. Update database settings in `application.yml`
3. Run: `mvn spring-boot:run`
4. Backend will start on http://localhost:8080

### Setup Frontend
1. Go to frontend folder
2. Run: `npm install`
3. Run: `npm start`
4. Frontend will open at http://localhost:3000

## Database Tables

**Students Table**: Store student information and login details
**Projects Table**: Store project information 
**Media Table**: Store uploaded files for each project

## API Endpoints

**Public (no login needed):**
- Get all projects: `GET /api/public/projects`
- Search projects: `GET /api/public/search`

**Student (login required):**
- Login: `POST /api/auth/login`
- Register: `POST /api/auth/register`
- Add project: `POST /api/student/projects`
- Upload files: `POST /api/student/projects/{id}/media`

## How to Use

1. **Register**: Create a student account
2. **Login**: Sign in with your credentials
3. **Add Project**: Fill project details and upload media
4. **Browse**: Explore other students' projects
5. **Search**: Find projects by name, branch, or domain

## File Upload

Students can upload:
- Images (JPG, PNG, GIF)
- Videos (MP4, MOV)
- Documents (PDF, DOC, DOCX)

## Learning Outcomes

This project helps learn:
- Building REST APIs with Spring Boot
- Creating React web applications
- Working with PostgreSQL database
- File upload and storage
- User authentication with JWT
- Frontend-backend integration

## Future Ideas

- Add project ratings and comments
- Email notifications for new projects
- Advanced search filters
- Project categories and tags
- Mobile app version

## Contact

For questions or issues, please create an issue in the GitHub repository.

---

**ProLaborate** - A student project showcase platform
