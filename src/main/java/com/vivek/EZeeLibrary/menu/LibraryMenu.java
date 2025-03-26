package com.vivek.EZeeLibrary.menu;

import com.vivek.EZeeLibrary.model.Book;
import com.vivek.EZeeLibrary.service.BookService;
import de.vandermeer.asciitable.AsciiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Profile("!test")
@Component
public class LibraryMenu implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {

        while (true) {
            System.out.println("""
                
                ==========================
                
                Welcome to EZeeLibrary
                1. Add Book
                2. List All Books
                3. Search Book by ID
                4. Search Book by Author
                5. Update Book
                6. Delete Book
                7. Exit
                Enter your choice:
                """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> displayBooks();
                case 3 -> searchBookById();
                case 4 -> searchBookByAuthor();
                case 5 -> updateBook();
                case 6 -> deleteBook();
                case 7 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addBook() {
        System.out.println("Enter book ID:");

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter book title:");
        String title = scanner.nextLine();

        System.out.println("Enter book author:");
        String author = scanner.nextLine();

        System.out.println("Enter book genre:");
        String genre = scanner.nextLine();

        System.out.println("Is the book available? (y/n):");
        boolean available = scanner.nextLine().equalsIgnoreCase("y");

        Book book = new Book(id, title, author, genre, available);
        bookService.save(book);
        System.out.println("Book added successfully.");
    }

    private void printTable(List<Book> books) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Title", "Author", "Genre", "Available");
        table.addRule();
        for (Book book : books) {
            table.addRow(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.isAvailable() ? "Yes" : "No");
        }
        table.addRule();
        System.out.println(table.render());
    }

    private void displayBooks() {
        List<Book> books = bookService.findAll();

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        printTable(books);
    }

    private void searchBookById() {
        System.out.println("Enter book ID to search:");

        int id = scanner.nextInt();
        scanner.nextLine();

        Book book = bookService.findById(id);
        if (book != null) {
            System.out.println("Book Found: " + book);
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    private void searchBookByAuthor() {
        System.out.println("Enter Author to search:");

        String author = scanner.nextLine();

        List<Book> books = bookService.findbyAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No book found with Author: " + author);
        } else {
            System.out.println("Books Found with Author: " + author);
            printTable(books);
        }
    }

    private void updateBook() {
        System.out.println("Enter book ID to update:");

        int id = scanner.nextInt();
        scanner.nextLine();

        Book existingBook = bookService.findById(id);
        if (existingBook == null) {
            System.out.println("No book found with ID: " + id);
            return;
        }

        System.out.println("Enter new title (or press Enter to keep current: " + existingBook.getTitle() + "):");
        String title = scanner.nextLine();
        if (title.isEmpty()) title = existingBook.getTitle();

        System.out.println("Enter new author (or press Enter to keep current: " + existingBook.getAuthor() + "):");
        String author = scanner.nextLine();
        if (author.isEmpty()) author = existingBook.getAuthor();

        System.out.println("Enter new genre (or press Enter to keep current: " + existingBook.getGenre() + "):");
        String genre = scanner.nextLine();
        if (genre.isEmpty()) genre = existingBook.getGenre();

        System.out.println("Is the book available? (y/n) (or press Enter to keep current: " + (existingBook.isAvailable() ? "Yes" : "No") + "):");
        String availableInput = scanner.nextLine();
        boolean available = availableInput.isEmpty() ? existingBook.isAvailable() : availableInput.equalsIgnoreCase("y");

        Book updatedBook = new Book(id, title, author, genre, available);
        bookService.save(updatedBook);
        System.out.println("Book updated successfully.");
    }

    private void deleteBook() {
        System.out.println("Enter book ID to delete:");

        int id = scanner.nextInt();
        scanner.nextLine();

        Book book = bookService.findById(id);
        if (book == null) {
            System.out.println("No book found with ID: " + id);
            return;
        }

        bookService.delete(id);
        System.out.println("Book deleted successfully.");
    }
}
