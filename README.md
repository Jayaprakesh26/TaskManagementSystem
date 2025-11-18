**📝 Task Management System**

A simple Task Management application built using Java, Spring Boot, and MySQL.
This project allows Admins and Users to manage tasks with a clear role-based workflow.

---

**✨ Features**

• Create new tasks

• Assign tasks to users

• Role-based access (Admin & User)

• Update task status (Pending → In Progress → Completed)

• View all tasks

• View tasks by user

• Delete tasks

• Basic Spring Security (without JWT)

---

**🔐 Role-Based Workflow**

The application supports two roles: ADMIN and USER.
Each role has specific permissions and responsibilities.


**👑 ADMIN ROLE**

Admins have full control over the system.

Admin Can:

• Create tasks

• Assign tasks to users

• Update any task

• Delete any task

• View all tasks

• Change any task’s status

• View users and their assigned 

**👤 USER ROLE**

Users have limited permissions. They can only work on tasks assigned to them.

User Can:

• View only their assigned tasks

• Start their task → Status becomes IN PROGRESS

• Mark their task as COMPLETED

• Update only their own task status

• Cannot create, delete, or view others' tasks

---

**🧰 Tech Stack**

• Java

• Spring Boot

• Spring Data JPA

• Spring Security

• MySQL

• Postman (for testing)
