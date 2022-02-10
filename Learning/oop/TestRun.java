package oop;

import oop.bank.Bank;

import java.util.Scanner;

public class TestRun {
    public static void main(String[] args) {
        Bank bank = new Bank("HSBC");
        Scanner scanner = new Scanner(System.in);
        bank.mainMenu(scanner);
    }
}
