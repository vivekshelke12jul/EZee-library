# EZee Library

## Overview
EZee Library is a simple console-based **Library Management System** application designed to help librarians efficiently manage books

## Features
- Add book (ID, Title, Author, Genre, Availability)
- List all books
- Search for a book by ID
- Search for books by author
- Update book (Title, Author, Genre, Availability)
- Delete book

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/vivekshelke12jul/EZee-library.git
   cd EZee-Library
   ```

2. Build and Run locally:
   ```sh
   ./gradlew clean build
   java -jar build/libs/EZeeLibrary-0.0.1-SNAPSHOT.jar
   ```
   OR in Docker
   ```sh
   docker build -t ezee-library .
   docker run -it ezee-library
   ```