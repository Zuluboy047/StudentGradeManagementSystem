package com.mycompany.chatapplication1;

public class User {

    private final String firstName;
    private final String lastName;
    private String username, password, cellNumber;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCellNumber() { return cellNumber; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setCellNumber(String cellNumber) { this.cellNumber = cellNumber; }
}
