public class Transaction {
    private String userId;
    private String transactionType;
    private double amount;

    public Transaction(String userId, String transactionType, double amount) {
        this.userId = userId;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }
}
