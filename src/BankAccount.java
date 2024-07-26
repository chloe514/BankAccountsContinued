import java.util.Scanner;

public class BankAccount {
    private double balance;
    private String accountHolderName;
    private String accountNumber;

    public BankAccount(double balance, String accountHolderName, String accountNumber) {
        this.balance = balance;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
    }

    public BankAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is the name for the account? ");
        this.accountHolderName = scanner.nextLine();
        System.out.print("What is the beginning balance for the account? ");
        this.balance = scanner.nextDouble();
        scanner.nextLine(); // consume the newline
        System.out.print("Enter the account number: ");
        this.accountNumber = scanner.nextLine();
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            targetAccount.deposit(amount);
            System.out.println("Transferred $" + amount + " to " + targetAccount.getAccountHolderName());
        } else {
            System.out.println("Invalid transfer amount or insufficient funds.");
        }
    }

    @Override
    public String toString() {
        return "Account Holder: " + accountHolderName + "\nBalance: $" + balance + "\nAccount Number: " + accountNumber;
    }
}






