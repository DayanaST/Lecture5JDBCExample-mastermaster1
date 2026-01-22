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
            System.out.println("4. Add new book");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            try {
                int option = scanner.nextInt();
                if (option == 0) break;
                handleOption(option);
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1 -> showBooks();
            case 2 -> showAuthors();
            case 3 -> addAuthor();
            case 4 -> addBook();
            default -> System.out.println("Wrong option");
        }
    }

    private void showBooks() {
        List<Book> books = controller.getAllBooks();
        if (books == null || books.isEmpty()) {
            System.out.println("No books found");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void showAuthors() {
        List<User> users = controller.getAllUsers();
        if (users == null || users.isEmpty()) {
            System.out.println("No authors found");
        } else {
            users.forEach(System.out::println);
        }
    }

    private void addAuthor() {
        scanner.nextLine();
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.println(controller.addAuthor(name) ? "Success" : "Failed");
    }

    private void addBook() {
        scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author ID: ");
        int authorId = scanner.nextInt();

        System.out.println(
                controller.addBook(title, authorId)
                        ? "Book added"
                        : "Failed"
        );
    }
}
