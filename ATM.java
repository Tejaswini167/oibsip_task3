import java.util.Scanner;

public class ATM {
    private UserFileHandler userFileHandler;
    private TransactionFileHandler transactionFileHandler;
    private User currentUser;

    public ATM() {
        userFileHandler = new UserFileHandler();
        transactionFileHandler = new TransactionFileHandler();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String pin = scanner.nextLine();

        currentUser = userFileHandler.authenticateUser(userId, pin);
        if (currentUser == null) {
            System.out.println("Invalid User ID or PIN. Exiting...");
            scanner.close();
            return;
        }

        System.out.println("Authentication Successful!");
        displayMenu(scanner);
    }

    private void displayMenu(Scanner scanner) {
        boolean quit = false;
        System.out.println("Balance:" + currentUser.getBalance());
        while (!quit) {
            System.out.println("\nMenu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayTransactionHistory();
                    break;
                case 2:
                    withdraw(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        transactionFileHandler.displayTransactions(currentUser.getUserId());
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        if (currentUser.getBalance() < amount) {
            System.out.println("Insufficient funds.");
            return;
        }

        currentUser.updateBalance(-amount);
        userFileHandler.updateUserBalance(currentUser.getUserId(), currentUser.getBalance());
        Transaction withdrawalTransaction = new Transaction(currentUser.getUserId(), "withdraw", amount);
        transactionFileHandler.addTransaction(withdrawalTransaction);

        System.out.println("Withdrawal successful.");
        System.out.println("Remaining Balance:" + currentUser.getBalance());
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        currentUser.updateBalance(amount);
        userFileHandler.updateUserBalance(currentUser.getUserId(), currentUser.getBalance());
        Transaction depositTransaction = new Transaction(currentUser.getUserId(), "deposit", amount);
        transactionFileHandler.addTransaction(depositTransaction);

        System.out.println("Deposit successful.");
        System.out.println("Current Balance:" + currentUser.getBalance());
    }

    private void transfer(Scanner scanner) {
        System.out.print("Enter recipient's user ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        User recipient = userFileHandler.getUser(recipientId);
        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        if (currentUser.getBalance() < amount) {
            System.out.println("Insufficient funds.");
            return;
        }

        currentUser.updateBalance(-amount);
        recipient.updateBalance(amount);

        userFileHandler.updateUserBalance(currentUser.getUserId(), currentUser.getBalance());
        userFileHandler.updateUserBalance(recipient.getUserId(), recipient.getBalance());

        Transaction senderTransaction = new Transaction(currentUser.getUserId(), "transfer", -amount);
        Transaction recipientTransaction = new Transaction(recipient.getUserId(), "transfer", amount);
        transactionFileHandler.addTransaction(senderTransaction);
        transactionFileHandler.addTransaction(recipientTransaction);

        System.out.println("Transfer successful.");
        System.out.println("Remaining Balance:" + currentUser.getBalance());
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
