public class User {
    private String userId;
    private String pin;
    private double balance;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double amount) {
        balance += amount;
    }
}
