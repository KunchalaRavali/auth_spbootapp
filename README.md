This project is a Spring Boot REST API that manages users and roles without security layers (for simplicity). It allows creating users, creating roles, assigning roles to users, and fetching assigned roles. The application uses Spring Boot, Spring Data JPA, and MySQL for persistence.

Key Features:

Create User (POST /api/users)
Get All Users (GET /api/users)
Create Role (POST /api/roles)
Assign Role to User (POST /api/users/{username}/roles/{roleName})
Get Roles of a User (GET /api/users/{username}/roles)
