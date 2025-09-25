package ayan.dev;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=====Welcome---Banking---System=====");

        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        String currentUser = null;

        while (true) {
            System.out.println("\n===== Banking Menu =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.next();
                    System.out.print("Enter email: ");
                    String email = sc.next();
                    System.out.print("Enter password: ");
                    String password = sc.next();
                    userDAO.registerUser(name, email, password);
                    break;
                case 2:
                    System.out.print("Enter email: ");
                    email = sc.next();
                    System.out.print("Enter password: ");
                    password = sc.next();
                    if (userDAO.loginUser(email, password)) {
                        currentUser = email;
                    }
                    break;
                case 3:
                    if (currentUser != null) {
                        System.out.print("Enter amount to deposit: ");
                        double dep = sc.nextDouble();
                        userDAO.deposit(currentUser, dep);
                    } else {
                        System.out.println("⚠️ Please login first!");
                    }
                    break;
                case 4:
                    if (currentUser != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double wd = sc.nextDouble();
                        userDAO.withdraw(currentUser, wd);
                    } else {
                        System.out.println("⚠️ Please login first!");
                    }
                    break;
                case 5:
                    if (currentUser != null) {
                        userDAO.checkBalance(currentUser);
                    } else {
                        System.out.println("⚠️ Please login first!");
                    }
                    break;
                case 6:
                    System.out.println("👋 Exiting...");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
