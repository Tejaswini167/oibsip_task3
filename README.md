# ATM Interface

Welcome to the ATM Interface project, a Java-based console application that simulates the functionalities of an Automated Teller Machine (ATM). This project, developed as part of the Oasis Infobyte Java Internship, showcases the practical application of Java in creating complex, real-world systems.

## Overview

The ATM Interface is a sophisticated simulation of an ATM's operations. Built using Java, it incorporates multiple classes to handle various banking transactions. This console-based application starts with user authentication and offers a range of ATM services upon successful login.

## Features

1. **Transactions History**: Users can view their transaction history, providing transparency and tracking of all past banking activities.
2. **Withdraw**: This feature allows users to withdraw money, adjusting their account balance accordingly.
3. **Deposit**: Users can deposit funds into their account, which updates their balance in real-time.
4. **Transfer**: This functionality enables users to transfer funds to other users, showcasing inter-account transactions.
5. **Quit**: Safely exits the application, ensuring that all user data is securely stored.

## Technical Implementation

- **User Authentication**: The application begins by requesting the user ID and PIN. It then verifies these credentials against stored data, ensuring secure access to the ATM functionalities.
- **Transaction Management**: Each transaction type (withdrawal, deposit, transfer) is handled distinctly, reflecting the changes in the user's account balance and updating the transaction history.
- **File Handling**: The application uses CSV files (`users.csv` and `transactions.csv`) for persistent storage of user data and transaction logs. This demonstrates practical file I/O operations in Java.
- **Classes and Their Roles**:
  - [`ATM`](https://github.com/Tejaswini167/oibsip_task3/blob/main/ATM.java): The core class that initializes the application, handles user input, and displays the menu options.
  - [`User`](https://github.com/Tejaswini167/oibsip_task3/blob/main/User.java): Represents a bank customer with attributes like userID, PIN, and balance.
  - [`Transaction`](https://github.com/Tejaswini167/oibsip_task3/blob/main/Transaction.java): Defines the structure of a banking transaction, including the user ID, transaction type, and amount.
  - [`UserFileHandler`](https://github.com/Tejaswini167/oibsip_task3/blob/main/UserFileHandler.java) & [`TransactionFileHandler`](https://github.com/Tejaswini167/oibsip_task3/blob/main/TransactionFileHandler.java): Manage the reading and writing of user and transaction data to and from CSV files, showcasing data persistence and management.

## How to Run

1. Clone the repository to your local machine.
2. Ensure Java is installed on your system.
3. Navigate to the directory containing the project files.
4. Compile the Java files (`javac ATM.java`) and run the application (`java ATM`).
