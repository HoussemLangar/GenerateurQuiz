# 📝 Quiz Generator

A **Java Maven project** to create, manage, and export quizzes.  
The system uses **MySQL** to store questions/answers and **Apache PDFBox** to generate PDF reports.

---

## 📂 Project Structure

```
Quiz_Generator/
├── ProjetJava.iml
├── .idea/                # IntelliJ configuration
├── .vs/                  # Visual Studio configuration
├── Projet/
│   ├── pom.xml           # Maven configuration
│   ├── mvnw / mvnw.cmd   # Maven Wrapper
│   ├── src/              # Java source code
│   └── .idea/            # Project-specific IDE settings
└── out/                  # Compiled files
```

---

## 🛠️ Technologies Used
- **Java 17+**
- **Maven**
- **MySQL Connector J (8.3.0)**
- **Apache PDFBox (3.0.2)**

---

## ⚙️ Installation & Usage

### 1. Clone the repository
```bash
git clone https://github.com/HoussemLangar/Quiz_Generator
cd Quiz_Generator/Projet
```

### 2. Build & Run with Maven
```bash
./mvnw clean install
./mvnw exec:java
```

*(or use `mvn` if Maven is already installed)*

### 3. Database Setup
Create a MySQL database and update the connection settings in the source code (e.g., in `application.properties` or Java config class).

```sql
CREATE DATABASE quizdb;
CREATE USER 'quizuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON quizdb.* TO 'quizuser'@'localhost';
```

---

## 📌 Features
- [ ] Create quizzes with questions/answers
- [ ] Store quiz data in MySQL
- [ ] Export results as PDF
- [ ] GUI with Swing/JavaFX *(depending on implementation)*

---
## 👤 Author

Developed by **Houssem LANGAR**  
📧 Email: houssemlangar3@gmail.com  

---

## 📄 License

This project is licensed under the MIT License – feel free to modify and use it.  
