package com.company;

import com.company.controllers.UserController;
import com.company.controllers.interfaces.IFeedbackController;
import com.company.models.Book;
import com.company.models.User;
import com.company.models.Client;
import com.company.models.Role;
import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final UserController controller;
    private final IFeedbackController feedbackController;
    private User currentUser;

    public MyApplication(UserController controller, IFeedbackController feedbackController) {
        this.controller = controller;
        this.feedbackController = feedbackController;
        this.currentUser = new User(1, "Admin", Role.ADMIN);
    }


    public void start() {
        System.out.println("Welcome to the Library System!");
        System.out.println("Select your role to log in:");
        System.out.println("1. ADMIN (Full access)");
        System.out.println("2. MANAGER (Can add books/authors)");
        System.out.println("3. USER (Read only)");
        System.out.print("Choice: ");

        try {
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (roleChoice) {
                case 1 -> currentUser = new User(1, "SystemAdmin", Role.ADMIN);
                case 2 -> currentUser = new User(2, "SystemManager", Role.MANAGER);
                default -> currentUser = new User(3, "GuestUser", Role.USER);
            }
        } catch (Exception e) {
            System.out.println("Invalid choice, logging in as Guest (USER).");
            currentUser = new User(3, "GuestUser", Role.USER);
            scanner.nextLine();
        }

        System.out.println("\nSuccessfully logged in as: " + currentUser.getName());
        System.out.println("Current Permissions: " + currentUser.getRole());

        while (true) {
            System.out.println("\n--- LIBRARY SYSTEM ---");
            System.out.println("1. Show all books");
            System.out.println("2. Show all authors");
            System.out.println("3. Add new author (Restricted)");
            System.out.println("4. Add new book (Restricted)");
            System.out.println("5. Show all clients");
            System.out.println("6. Add new client");
            System.out.println("7. Leave feedback");
            System.out.println("8. Show all categories");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option == 0) break;
                handleOption(option);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
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
            case 5 -> showClients();
            case 6 -> addClient();
            case 7 -> leaveFeedbackMenu();
            case 8 -> showCategories();
            default -> System.out.println("Wrong option");
        }
    }

    private void showCategories() {
        System.out.println("List of categories:");
        controller.getAllCategories().forEach(System.out::println);
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
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.println(controller.addAuthor(name) ? "Success" : "Failed");
    }

    private void addBook() {
        System.out.println("\n--- Adding a new book ---");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author ID: ");
        int authorId = scanner.nextInt();

        System.out.print("Enter published year: ");
        int year = scanner.nextInt();

        System.out.print("Enter category ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        String result = controller.addBook(currentUser, title, authorId, year, categoryId);
        System.out.println(result);
    }

    private void showClients() {
        List<Client> clients = controller.getAllClients();
        if (clients == null || clients.isEmpty()) {
            System.out.println("No clients found");
        } else {
            clients.forEach(System.out::println);
        }
    }

    private void addClient() {
        System.out.print("Enter client first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter client last name: ");
        String lastName = scanner.nextLine();

        System.out.println(controller.addClient(firstName, lastName));
    }

    private void leaveFeedbackMenu() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your feedback: ");
        String message = scanner.nextLine();
        System.out.print("Rate us (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        feedbackController.leaveFeedback(name, message, rating);
    }
}
