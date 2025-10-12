--------------------------------------------"🏦 Online Banking System (JavaFX + MySQL)"--------------------------------

A modern and secure Online Banking System built using JavaFX, MySQL, and JDBC.
This project demonstrates user authentication, account registration, deposits, withdrawals, and transaction tracking — all within a clean and interactive JavaFX interface.

------------------------🚀 Features--------------------

✅ User Registration & Login (with database validation)
✅ Deposit & Withdraw system (real-time balance updates)
✅ Transaction History saved in MySQL
✅ Stylish modern UI with gradients and animations
✅ Scene switching using a custom SceneSwitcher utility
✅ MVC Project Structure for clean and scalable code

---------------------🧠 Tech Stack--------------------
Technology	Description
JavaFX	UI Framework for building modern desktop apps
MySQL	Relational database for user and transaction data
JDBC	Database connectivity between JavaFX and MySQL
FXML	Declarative UI design
CSS	Styling and UI polish

--------------------📁 Project Structure-----------
OnlineBankingSystem/
├── ayan/dev/
│   ├── BankApp.java              # Main entry point
│   ├── LoginController.java      # Handles login logic
│   ├── RegisterController.java   # Handles registration logic
│   ├── DashboardController.java  # Handles deposit, withdraw, logout
│   ├── dao/
│   │   ├── UserDAO.java          # User data access logic
│   │   ├── TransactionDAO.java   # Transaction CRUD logic
│   ├── utils/
│   │   └── SceneSwitcher.java    # Utility class for scene transitions
│   └── DBConnection.java         # MySQL database connection
├── ayan/dev/view/
│   ├── login.fxml
│   ├── register.fxml
│   └── dashboard.fxml
└── README.md

-------------------⚙️ How to Run the Project----------

🧩 Prerequisites:
Install JDK 17+
Install JavaFX SDK
Install MySQL and create a database named banking

--------------💾 Import Database-----------------------

Run the following SQL in MySQL:

CREATE DATABASE banking;
USE banking;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100)
);

CREATE TABLE transactions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_email VARCHAR(100),
  type VARCHAR(20),
  amount DOUBLE,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--------------▶️ Run Steps----------------------

Open the project in IntelliJ IDEA or VS Code
Add JavaFX SDK to your project module path
Update your MySQL credentials in DBConnection.java

Run:
      "BankApp.java"

------------🌱 Future Improvements----------------

Add Profile Management
Add Dark Mode / Light Mode toggle
Export Transaction Report (PDF/Excel)
Add Admin Dashboard

--------------~Author~-------------------------

👤 Syed Mohd Ayan
📧 [syedayan4128@gmail.com]
🌐 https://syedayan-portfolio.netlify.app/
🐙 https://github.com/syedayan-05
