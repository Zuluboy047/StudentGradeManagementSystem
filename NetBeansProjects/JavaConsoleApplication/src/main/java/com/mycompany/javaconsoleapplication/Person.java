package com.mycompany.javaconsoleapplication;

// Superclass (demonstrates inheritance)
public class Person {
    private String name;  // information hiding
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    // Display info
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}
