import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserFileHandler {
    private static final String USERS_FILE_PATH = "users.csv";

    public User authenticateUser(String userId, String pin) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo[0].equals(userId) && userInfo[1].equals(pin)) {
                    double balance = Double.parseDouble(userInfo[2]);
                    return new User(userId, pin, balance);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        return null;
    }

    public User getUser(String userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo[0].equals(userId)) {
                    String pin = userInfo[1];
                    double balance = Double.parseDouble(userInfo[2]);
                    return new User(userId, pin, balance);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        return null;
    }

    public void updateUserBalance(String userId, double newBalance) {
        String tempFile = "temp_users.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_PATH));
                FileWriter writer = new FileWriter(tempFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo[0].equals(userId)) {
                    writer.write(userId + "," + userInfo[1] + "," + newBalance + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating user balance: " + e.getMessage());
        }

        // Rename the temp file to replace the original users file
        try {
            java.nio.file.Files.move(java.nio.file.Path.of(tempFile), java.nio.file.Path.of(USERS_FILE_PATH),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error updating user balance: " + e.getMessage());
        }
    }
}
