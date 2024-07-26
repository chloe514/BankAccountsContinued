import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello World! Welcome to the bank of Matt!");

        while (true) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("> "); // Prompt for input

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == -1) {
                System.out.println("Goodbye!");
                break;
            }

            if (choice == 2) {
                System.out.println("Let's make a new account!");
                createNewAccount();
            } else if (choice == 1) {
                interactWithExistingAccount();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createNewAccount() {
        BankAccount newAccount = new BankAccount();
        accounts.add(newAccount);
        System.out.println("Account created successfully!");
        mainMenu(newAccount);
    }

    private static void interactWithExistingAccount() {
        System.out.println("Available accounts:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountHolderName() + " (" + accounts.get(i).getAccountNumber() + ")");
        }

        System.out.print("Select an account number: ");
        int accountIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (accountIndex >= 0 && accountIndex < accounts.size()) {
            BankAccount selectedAccount = accounts.get(accountIndex);
            mainMenu(selectedAccount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void mainMenu(BankAccount account) {
        while (true) {
            System.out.println("Hello " + account.getAccountHolderName() + "! Welcome to the main menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdrawal");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit");
            System.out.print("> "); // Prompt for input

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Your account balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    account.deposit(depositAmount);
                    break;
                case 4:
                    transferMenu(account);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return; // Exit the main menu loop
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private static void transferMenu(BankAccount sourceAccount) {
        System.out.println("Available accounts for transfer:");
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount account = accounts.get(i);
            if (!account.equals(sourceAccount)) { // Exclude the source account
                System.out.println((i + 1) + ". " + account.getAccountHolderName() + " (" + account.getAccountNumber() + ")");
            }
        }

        System.out.print("Select the account number to transfer to: ");
        String targetAccountNumber = scanner.nextLine();

        BankAccount targetAccount = null;
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(targetAccountNumber)) {
                targetAccount = account;
                break;
            }
        }

        if (targetAccount != null) {
            System.out.print("Enter the amount to transfer: ");
            double transferAmount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            sourceAccount.transfer(targetAccount, transferAmount);
            System.out.println("The name on the account is: " + sourceAccount.getAccountHolderName() + " and they have a balance of $" + sourceAccount.getBalance());
            System.out.println("The name on the account is: " + targetAccount.getAccountHolderName() + " and they have a balance of $" + targetAccount.getBalance());
        } else {
            System.out.println("Account doesn't exist.");
        }
    }
}









