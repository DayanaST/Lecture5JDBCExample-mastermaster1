package com.company;

import com.company.controllers.UserController;
import com.company.models.Book;
import com.company.models.User;
import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final UserController controller;

    public MyApplication(UserController controller) {
        this.controller = controller;
    }

    public void start() {
        while (true) {
            System.out.println("\n--- LIBRARY SYSTEM ---");
            System.out.println("1. Show all books");
            System.out.println("2. Show all authors");
            System.out.println("3. Add new author");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            try {
                int option = scanner.nextInt();
                if (option == 0) break;
                handleOption(option);
            } catch (Exception e) {
                System.out.println("Error: Invalid input.");
                scanner.nextLine(); // очистка буфера
            }
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1 -> showBooks();
            case 2 -> showAuthors();
            case 3 -> addAuthor();
            default -> System.out.println("Wrong option.");
        }
    }

    private void showBooks() {
        List<Book> books = controller.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Database is empty.");
        } else {
            System.out.println("\nList of Books:");
            books.forEach(System.out::println);
        }
    }

    private void showAuthors() {
        List<User> authors = controller.getAllUsers();
        if (authors.isEmpty()) {
            System.out.println("No authors found.");
        } else {
            System.out.println("\nList of Authors:");
            authors.forEach(System.out::println);
        }
    }

    private void addAuthor() {
        System.out.print("Enter name: ");
        String name = scanner.next();
        String result = controller.addAuthor(name) ? "Success!" : "Failed!";
        System.out.println(result);
    }

    private void addReview() {
        System.out.print("Enter Client ID: ");
        int clientId = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter your comment: ");
        scanner.nextLine();
        String comment = scanner.nextLine();
        Object clientName = null;
        boolean success = controller.addReview(bookId,clientName,comment);
        System.out.println(success ? "Review added!" : "Failed to add review.");
    }
}