package com.company;

import com.company.controllers.UserController;
import com.company.models.Book;
import com.company.models.User;
import com.company.models.Client;
import com.company.models.Role;

import java.util.List;
import java.util.Scanner;

public class MyApplication {

    private final Scanner scanner = new Scanner(System.in);
    private final UserController controller;
    private final User currentUser;

    public MyApplication(UserController controller) {
        this.controller = controller;
        this.currentUser = new User(1, "Admin", Role.ADMIN);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- LIBRARY SYSTEM ---");
            System.out.println("1. Show all books");
            System.out.println("2. Show all authors");
            System.out.println("3. Add new author");
            System.out.println("4. Add new book");
            System.out.println("5. Show all clients");
            System.out.println("6. Add new client");
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
            default -> System.out.println("Wrong option");
        }
    }

    private void showBooks() {
        List<Book> books = controller.getAllBooks();
        books.forEach(System.out::println);
    }

    private void showAuthors() {
        List<User> users = controller.getAllUsers();
        users.forEach(System.out::println);
    }

    private void addAuthor() {
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.println(controller.addAuthor(name) ? "Success" : "Failed");
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author ID: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        System.out.println(controller.addBook(currentUser, title, authorId));
    }

    private void showClients() {
        List<Client> clients = controller.getAllClients();
        clients.forEach(System.out::println);
    }

    private void addClient() {
        System.out.print("Enter client first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter client last name: ");
        String lastName = scanner.nextLine();

        System.out.println(controller.addClient(firstName, lastName));
    }
}