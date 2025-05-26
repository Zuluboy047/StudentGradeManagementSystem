package com.mycompany.chatapplication1;

import javax.swing.*;
import java.util.regex.*;

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

    // Registration process
    public void registerUser() {
        JOptionPane.showMessageDialog(null, "Registration Window", "Register", JOptionPane.INFORMATION_MESSAGE);

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

        JOptionPane.showMessageDialog(null, "Registration successful!\nUser credentials captured.");
    }

    // Login process
    public boolean loginUser() {
        JOptionPane.showMessageDialog(null, "Login Window", "Login", JOptionPane.INFORMATION_MESSAGE);

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

    // Messaging menu
    public void startMessaging() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        while (true) {
            String input = JOptionPane.showInputDialog("""
                    Choose an option:
                    1) Send Messages
                    2) Show Sent Messages
                    3) Quit""");

            if (input == null || input.equals("3")) {
                break;
            }

            switch (input) {
                case "1" -> sendMessages();
                case "2" -> showMessages();
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }

    private void sendMessages() {
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

    // Main method
    public static void main(String[] args) {
        LoginSystem system = new LoginSystem();
        system.registerUser();
        if (system.loginUser()) {
            system.startMessaging();
        }
    }
}