# Bookstore API Documentation

This API allows you to interact with a simple bookstore database, providing information about books, their authors, prices, and quantities.

## Prerequisites
Create a MySQL database.

Run the following SQL script to create the books table:
```sql
CREATE TABLE books (
id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255) NOT NULL,
author VARCHAR(255) NOT NULL,
price DOUBLE NOT NULL,
quantity INT NOT NULL
);
```

Populate the database. The following query will add the top 20 bestselling novels with dummy prices and quantities:
```sql
INSERT INTO books (title, author, price, quantity)
VALUES
('Don Quixote', 'Miguel de Cervantes', 12.45, 130),
('A Tale of Two Cities', 'Charles Dickens', 11.75, 75),
('The Lord of the Rings', 'J.R.R. Tolkien', 14.20, 150),
('The Alchemist', 'Paulo Coelho', 10.50, 100),
('The Little Prince', 'Antoine de Saint-Exupéry', 9.80, 90),
('Dream of the Red Chamber', 'Cao Xueqin', 13.15, 180),
('And Then There Were None', 'Agatha Christie', 11.35, 40),
('She: A History of Adventure', 'H. Rider Haggard', 10.90, 120),
('The Catcher in the Rye', 'J.D. Salinger', 9.25, 60),
('The Da Vinci Code', 'Dan Brown', 12.75, 110),
('To Kill a Mockingbird', 'Harper Lee', 11.00, 85),
('The Great Gatsby', 'F. Scott Fitzgerald', 10.20, 95),
('The Hobbit', 'J.R.R. Tolkien', 14.50, 170),
('The Lion, the Witch and the Wardrobe', 'C.S. Lewis', 13.00, 130),
('One Hundred Years of Solitude', 'Gabriel García Márquez', 11.90, 140),
('The Girl with the Dragon Tattoo', 'Stieg Larsson', 10.65, 110),
('Gone with the Wind', 'Margaret Mitchell', 12.00, 160),
('The Hunchback of Notre-Dame', 'Victor Hugo', 9.75, 75),
('The Godfather', 'Mario Puzo', 13.40, 190),
('The Adventures of Sherlock Holmes', 'Arthur Conan Doyle', 10.30, 100);
```

Clone this repository.

Build and run the application.


## Using the APIs

Public Postman collection can be found [here](https://www.postman.com/payload-astronaut-23412629/workspace/hannah-startsteps/collection/31451012-205271ed-4b77-455b-8ed6-e97be72a0006?action=share&creator=31451012)

Each of the saved requests has a comment outlining how it should be used.
