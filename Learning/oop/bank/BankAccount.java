package oop.bank;

public class BankAccount {
    private String owner;
    private double balance = 0;
    private int accountNumber;
    private int pin;

    private static int counter = 0;

//    Constructors
    public BankAccount(String owner, int pin) {
        counter++;

        this.owner = owner;
        this.accountNumber = counter;
        this.pin = pin;
    }

    public BankAccount(String owner, int pin, double initialBalance) {
        counter++;

        this.owner = owner;
        this.accountNumber = counter;
        this.pin = pin;

        balance = initialBalance;
    }

//    Getter and setter methods
    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public static int getCounter() {
        return counter;
    }

//    Methods
    public boolean withdraw(double amount) {
        if (this.balance < amount) {
            return false;
        }

        this.balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean checkPin(int pin) {
        return this.pin == pin;
    }

    public boolean changePing(int oldPin, int pin) {
        if (this.checkPin(oldPin)) {
            this.pin = pin;
            return true;
        }
        return false;
    }
}

