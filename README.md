# BibliotekaPS
A client-server application for library management built using Java, SQL, and Java Swing.
# Table of Contents
Introduction

Features

Technologies Used

Getting Started

Installation

Usage


# Introduction
BibliotekaPS is a client-server application designed to manage a library's operations efficiently. It allows librarians to manage books, users, and transactions while providing a user-friendly interface for library members to search for books and check their borrowing status. This project was developed as part of a bachelor's thesis.
Features
User authentication and authorization

Book management (add, update, delete)

User management (add, update, delete)

Transaction management (borrow, return)

Search functionality for books

Real-time notifications for due dates


# Technologies Used
Frontend: Java Swing

Backend: Java

Database: SQL (MySQL)

# Getting Started
To get started with BibliotekaPS, you need to have Java and MySQL installed on your machine. Follow the steps below to set up the project locally.
Prerequisites
Java Development Kit (JDK)
MySQL
Git

# Installation
Clone the repository:
bash
Copy
git clone https://github.com/Braxon2/BibliotekaPS.git
cd BibliotekaPS

Set up the database:

Create a MySQL database named biblioteka.

Make tables in database for: bibliotekar,clan, user_profile,primerak,stavka_iznajmljivanja,iznajmljivanje

Make a file dbconfig.properties with its content:

password=password_for_database

port=7777

user=user_of_database

url=url_connection_to_database

Configure the database connection:


# Run the application
First run server application BibliotekaServerPS in package start  Start.java
Then run client side BibliotekaKlijentPS in package start Start.java
Create an account or log in to start managing the library.
