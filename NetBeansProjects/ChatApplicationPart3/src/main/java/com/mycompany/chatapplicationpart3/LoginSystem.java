package com.mycompany.chatapplicationpart3;

import javax.swing.JOptionPane;
import java.util.regex.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class LoginSystem {
    private User user;
    private final Message[] messages = new Message[100];
    private int messageCount = 0;

    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password validation
    public boolean checkPasswordComplexity(String password) {
        boolean lengthOK = password.length() >= 8;
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[^A-Za-z0-9].*");
        return lengthOK && hasUpper && hasNumber && hasSpecial;
    }

    // Phone number validation
    public boolean checkCellPhoneNumber(String cellNumber) {
        Pattern pattern = Pattern.compile("^(\\+27)[6-8][0-9]{8}$");
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }

    // Register user and save to file
    public void registerUser() {
        JOptionPane.showMessageDialog(null, "== Registration ==", "Register", JOptionPane.INFORMATION_MESSAGE);

        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        user = new User(firstName, lastName);

        String username;
        do {
            username = JOptionPane.showInputDialog("Enter username (must contain '_' and be ≤ 5 characters):");
            if (!checkUserName(username)) {
                JOptionPane.showMessageDialog(null,
                        "Username must contain at least one underscore ('_') and be no more than 5 characters.");
            }
        } while (!checkUserName(username));
        user.setUsername(username);

        String password;
        do {
            password = JOptionPane.showInputDialog("Enter password (≥8 characters, 1 capital letter, 1 number, 1 special char):");
            if (!checkPasswordComplexity(password)) {
                JOptionPane.showMessageDialog(null,
                        "Password must be at least 8 characters long and include:\n- One capital letter\n- One number\n- One special character.");
            }
        } while (!checkPasswordComplexity(password));
        user.setPassword(password);

        String cellNumber;
        do {
            cellNumber = JOptionPane.showInputDialog("Enter a South African phone number (e.g., +27831234567):");
            if (!checkCellPhoneNumber(cellNumber)) {
                JOptionPane.showMessageDialog(null,
                        "Phone number must start with '+27' and be 11 digits long, starting with 6, 7, or 8.");
            }
        } while (!checkCellPhoneNumber(cellNumber));
        user.setCellNumber(cellNumber);

        storeUser();
        JOptionPane.showMessageDialog(null, "Registration successful!\nUser credentials captured and saved.");
    }

    private void storeUser() {
        JSONObject userJson = new JSONObject();
        userJson.put("firstName", user.getFirstName());
        userJson.put("lastName", user.getLastName());
        userJson.put("username", user.getUsername());
        userJson.put("password", user.getPassword());
        userJson.put("cellNumber", user.getCellNumber());

        try (FileWriter writer = new FileWriter("user.json")) {
            writer.write(userJson.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save user: " + e.getMessage());
        }
    }

    private boolean loadUser() {
        try {
            String content = Files.readString(Paths.get("user.json"));
            JSONObject obj = new JSONObject(content);
            user = new User(obj.getString("firstName"), obj.getString("lastName"));
            user.setUsername(obj.getString("username"));
            user.setPassword(obj.getString("password"));
            user.setCellNumber(obj.getString("cellNumber"));
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No registered user found. Please register first.");
            return false;
        }
    }

    public boolean loginUser() {
        JOptionPane.showMessageDialog(null, "== Login ==", "Login", JOptionPane.INFORMATION_MESSAGE);

        if (!loadUser()) {
            return false;
        }

        String inputUsername = JOptionPane.showInputDialog("Enter your username:");
        String inputPassword = JOptionPane.showInputDialog("Enter your password:");

        if (inputUsername.equals(user.getUsername()) && inputPassword.equals(user.getPassword())) {
            JOptionPane.showMessageDialog(null,
                    "Welcome " + user.getFirstName() + " " + user.getLastName() + ". You are logged in successfully.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect.");
            return false;
        }
    }

    public void startMessaging() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        while (true) {
            String input = JOptionPane.showInputDialog("""
                    Choose an option:
                    1) Send Messages
                    2) Show Sent Messages
                    3) Display the longest Message
                    4) Search for messageID
                    5) Search messages by recipient
                    6) Delete a message using message hash
                    7) Display Report
                    8) Quit""");

            if (input == null || input.equals("8")) break;

            switch (input) {
                case "1" -> sendMessages();
                case "2" -> showMessages();
                case "3" -> displayLongestMessage();
                case "4" -> searchByMessageID();
                case "5" -> searchByRecipient();
                case "6" -> deleteByMessageHash();
                case "7" -> displayReport();
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }

    private void sendMessages() {
        if (messageCount >= messages.length) {
            JOptionPane.showMessageDialog(null, "Message storage full. Cannot send more messages.");
            return;
        }

        String numStr = JOptionPane.showInputDialog("How many messages do you want to send?");
        try {
            int count = Integer.parseInt(numStr);

            for (int i = 0; i < count; i++) {
                String recipient = JOptionPane.showInputDialog("Enter recipient phone (+27...):");
                while (!checkCellPhoneNumber(recipient)) {
                    recipient = JOptionPane.showInputDialog("Invalid. Enter valid South African number (+27...):");
                }

                String content = JOptionPane.showInputDialog("Enter message (max 250 characters):");
                while (content != null && content.length() > 250) {
                    content = JOptionPane.showInputDialog("Too long. Enter message (≤250 chars):");
                }

                if (content == null) break;

                Message msg = new Message(recipient, content, messageCount + 1);
                String result = msg.sentMessage();
                JOptionPane.showMessageDialog(null, result);

                if (result.equals("Message successfully sent.") && messageCount < messages.length) {
                    messages[messageCount++] = msg;
                    JOptionPane.showMessageDialog(null, msg.printMessages());
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number of messages.");
        }
    }

    private void showMessages() {
        if (messageCount == 0) {
            JOptionPane.showMessageDialog(null, "No messages sent yet.");
        } else {
            StringBuilder output = new StringBuilder("Sent Messages:\n");
            for (int i = 0; i < messageCount; i++) {
                output.append(messages[i].printMessages()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, output.toString());
        }
    }

    private void displayLongestMessage() {
        if (messageCount == 0) {
            JOptionPane.showMessageDialog(null, "No messages sent yet.");
            return;
        }

        Message longest = messages[0];
        for (int i = 1; i < messageCount; i++) {
            if (messages[i].getMessageContent().length() > longest.getMessageContent().length()) {
                longest = messages[i];
            }
        }

        JOptionPane.showMessageDialog(null,
            "Longest message (" + longest.getMessageContent().length() + " characters):\n" +
            longest.getMessageContent());
    }

    private void searchByMessageID() {
        String searchID = JOptionPane.showInputDialog("Enter message ID to search:");
        if (searchID == null || searchID.trim().isEmpty()) return;

        for (int i = 0; i < messageCount; i++) {
            if (messages[i].getMessageID().equals(searchID)) {
                JOptionPane.showMessageDialog(null,
                    """
                    Message found:
                    Recipient: """ + messages[i].getRecipient() + "\n" +
                    "Message: " + messages[i].getMessageContent());
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No message found with ID: " + searchID);
    }

    private void searchByRecipient() {
        String recipient = JOptionPane.showInputDialog("Enter recipient phone number to search:");
        if (recipient == null || recipient.trim().isEmpty()) return;

        StringBuilder result = new StringBuilder("Messages for " + recipient + ":\n");
        boolean found = false;

        for (int i = 0; i < messageCount; i++) {
            if (messages[i].getRecipient().equals(recipient)) {
                result.append("- ").append(messages[i].getMessageContent()).append("\n");
                found = true;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(null, result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No messages found for recipient: " + recipient);
        }
    }

    private void deleteByMessageHash() {
        String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
        if (hash == null || hash.trim().isEmpty()) return;

        for (int i = 0; i < messageCount; i++) {
            if (messages[i].getMessageHash().equals(hash)) {
                for (int j = i; j < messageCount - 1; j++) {
                    messages[j] = messages[j + 1];
                }
                messages[--messageCount] = null;
                JOptionPane.showMessageDialog(null, "Message successfully deleted.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No message found with hash: " + hash);
    }

    private void displayReport() {
        if (messageCount == 0) {
            JOptionPane.showMessageDialog(null, "No messages to display in report.");
            return;
        }

        StringBuilder report = new StringBuilder("=== MESSAGE REPORT ===\n\n");
        report.append("Total Messages: ").append(messageCount).append("\n\n");
        report.append("=== Message Details ===\n");

        for (int i = 0; i < messageCount; i++) {
            report.append("Message ID: ").append(messages[i].getMessageID()).append("\n");
            report.append("Recipient: ").append(messages[i].getRecipient()).append("\n");
            report.append("Message: ").append(messages[i].getMessageContent()).append("\n");
            report.append("Status: ").append(messages[i].getStatus()).append("\n");
            report.append("Message Hash: ").append(messages[i].getMessageHash()).append("\n");
            report.append("----------------------\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void main(String[] args) {
        LoginSystem system = new LoginSystem();

        String[] options = {"Register", "Login", "Exit"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to QuickChat\nChoose an option:",
                "QuickChat", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 0 -> {
                system.registerUser();
                if (system.loginUser()) {
                    system.startMessaging();
                }
            }
            case 1 -> {
                if (system.loginUser()) {
                    system.startMessaging();
                }
            }
            default -> JOptionPane.showMessageDialog(null, "Goodbye!");
        }
    }
}

