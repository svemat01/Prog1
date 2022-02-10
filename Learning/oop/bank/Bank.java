package oop.bank;

import java.util.HashMap;
import java.util.Scanner;

public class Bank {
    private String name;
    private HashMap<Integer, BankAccount> accounts = new HashMap<>();

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int createAccount(String name, int pin) {
        BankAccount account = new BankAccount(name, pin);
        accounts.put(account.getAccountNumber(), account);

        return account.getAccountNumber();
    }

    public BankAccount login(int accountNumber, int pin) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null && account.checkPin(pin)) {
            return account;
        } else {
            throw new IllegalArgumentException("Invalid account number or pin");
        }
    }

    public boolean transfer(BankAccount fromAccount, int toAccount, double amount) {
        BankAccount to;
        try {
             to = accounts.get(toAccount);
        } catch (NullPointerException e) {
            System.out.println("Invalid account number");
            return false;
        }

        if (fromAccount.withdraw(amount)) {
            to.deposit(amount);
            return true;
        }
        System.out.println("Insufficient funds");
        return false;
    }

    public void loggedInMenu(Scanner scanner, BankAccount account) {
        boolean exit = false;

        while (!exit) {
            System.out.printf("\nWelcome %s\n", account.getOwner());
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance");
            System.out.println("4. transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: \n" + "> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: \n" + "> ");
                    double amount = scanner.nextDouble();
                    account.deposit(amount);
                    break;
                case 2:
                    System.out.print("Enter amount: \n" + "> ");
                    amount = scanner.nextDouble();
                    if (account.withdraw(amount)) {
                        System.out.println("Withdrawal successful");
                    } else {
                        System.out.println("Insufficient funds");
                    }
                    break;
                case 3:
                    System.out.printf("Balance: %.2f\n", account.getBalance());
                    break;
                case 4:
                    System.out.print("Enter account number: \n" + "> ");
                    int toAccount = scanner.nextInt();

                    System.out.print("Enter amount: \n" + "> ");
                    amount = scanner.nextDouble();

                    transfer(account, toAccount, amount);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void mainMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.printf("\nWelcome to %s\n", this.getName());
            System.out.println("1. Create account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: \n" + "> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: \n" + "> ");
                    String name = scanner.next();
                    System.out.print("Enter your pin: \n" + "> ");
                    int pin_1 = scanner.nextInt();
                    System.out.printf("Your account number is: %d\n", createAccount(name, pin_1));
                    break;
                case 2:
                    System.out.print("Enter your account number: \n" + "> ");
                    int accountNumber = scanner.nextInt();
                    System.out.print("Enter your pin: \n" + "> ");
                    int pin_2 = scanner.nextInt();

                    try {
                        BankAccount account = this.login(accountNumber, pin_2);
                        loggedInMenu(scanner, account);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Pin incorrect");
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
