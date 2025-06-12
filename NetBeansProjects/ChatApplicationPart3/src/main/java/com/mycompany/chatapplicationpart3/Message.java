package com.mycompany.chatapplicationpart3;

import java.util.Objects;

public class Message {
    private final String recipientNumber;
    private final String content;
    private final int messageId;

    public Message(String recipientNumber, String content, int messageId) {
        this.recipientNumber = recipientNumber;
        this.content = content;
        this.messageId = messageId;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public String getContent() {
        return content;
    }

    public int getMessageId() {
        return messageId;
    }

    public String sentMessage() {
        if (content.length() <= 250 && recipientNumber.startsWith("+27") && recipientNumber.length() == 12) {
            return "Message successfully sent.";
        } else {
            return "Message failed to send. Invalid content or phone number.";
        }
    }

    public String printMessages() {
        return "Message ID: " + getMessageID() +
               "\nRecipient: " + recipientNumber +
               "\nMessage: " + content;
    }

    // ✅ These are the expected methods from LoginSystem

    public String getMessageID() {
        return "MSG" + messageId;
    }

    public String getMessageContent() {
        return content;
    }

    public String getRecipient() {
        return recipientNumber;
    }

    public String getStatus() {
        return (content.length() <= 250 && recipientNumber.startsWith("+27") && recipientNumber.length() == 12)
                ? "Sent"
                : "Failed";
    }

    public String getMessageHash() {
        return Integer.toHexString(Objects.hash(recipientNumber, content, messageId));
    }
}
