package com.mycompany.chatapplication1;

import javax.swing.JOptionPane;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.regex.*;

public final class Message {
    private final String messageID;
    private final int numMessagesSent;
    private final String recipient;
    private final String messageContent;
    private final String messageHash;
    private static int totalMessages = 0;

    public Message(String recipient, String messageContent, int numMessagesSent) {
        this.messageID = generateMessageID();
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
        totalMessages++;
    }

    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + rand.nextInt(900000000);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        Pattern pattern = Pattern.compile("^(?:\\+27)[6-8][0-9]{8}$");
        Matcher matcher = pattern.matcher(recipient);
        return matcher.matches();
    }

    public String createMessageHash() {
        String[] words = messageContent.split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        return (messageID.substring(0, 2) + ":" + numMessagesSent + ":" + 
               firstWord.toUpperCase() + lastWord.toUpperCase());
    }

    public String sentMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        int choice = JOptionPane.showOptionDialog(null, 
            "What would you like to do with this message?", 
            "Message Options", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);
        
        switch (choice) {
            case 0 -> {
                return "Message successfully sent."; 
            }
            case 1 -> {
                totalMessages--;
                return "Press 0 to delete message.";
            } 
            case 2 -> {
                storeMessage();
                return "Message successfully stored.";
            }
            default -> {
                return "No action taken.";
            }
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageContent + "\n" +
               "Total Messages Sent: " + totalMessages;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    public void storeMessage() {
        JSONObject messageJson = new JSONObject();
        messageJson.put("messageID", messageID);
        messageJson.put("messageHash", messageHash);
        messageJson.put("recipient", recipient);
        messageJson.put("messageContent", messageContent);
        messageJson.put("numMessagesSent", numMessagesSent);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(messageJson.toString() + "\n");
        } catch (IOException e) {
        }
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
    public String getMessageHash() { return messageHash; }
    public int getNumMessagesSent() { return numMessagesSent; }
}