import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransactionFileHandler {
    private static final String TRANSACTIONS_FILE_PATH = "transactions.csv";

    public void addTransaction(Transaction transaction) {
        try (FileWriter writer = new FileWriter(TRANSACTIONS_FILE_PATH, true)) {
            String transactionData = transaction.getUserId() + "," + transaction.getTransactionType() + "," +
                    transaction.getAmount() + "\n";
            writer.write(transactionData);
        } catch (IOException e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }
    }

    public void displayTransactions(String userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] transactionInfo = line.split(",");
                if (transactionInfo[0].equals(userId)) {
                    String transactionType = transactionInfo[1];
                    double amount = Double.parseDouble(transactionInfo[2]);
                    System.out.println("Transaction Type: " + transactionType + ", Amount: " + amount);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }
    }
}
