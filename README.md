# 📚 Library Management System (LMS)

A Spring Boot + MongoDB based **Library Management System** that allows managing libraries, books, and users, with borrowing/returning functionality.  
This project demonstrates a full CRUD API with advanced features such as validation, relationships, and status-based actions.

---

## 🔍 Features

- **Library Management**
  - Add, update, delete, and retrieve libraries.
  - Filter libraries by status (Active / Inactive).

- **Book Management**
  - Add, update, delete, and retrieve books.
  - Filter books by status (Available / Borrowed / Reserved).
  - Assign books to a library.

- **User Management**
  - Register, update, and delete users.
  - Track borrowed books per user.

- **Borrow/Return System**
  - Borrow a book (only if available).
  - Return a borrowed book.
  - Prevent duplicate borrowing.

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot, Spring Data MongoDB  
- **Database:** MongoDB  
- **Build Tool:** Maven  
- **Testing:** Postman / Swagger  
- **Version Control:** Git & GitHub  

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 17+
- Maven
- MongoDB running locally or remotely
- Git

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/library-management-system.git
