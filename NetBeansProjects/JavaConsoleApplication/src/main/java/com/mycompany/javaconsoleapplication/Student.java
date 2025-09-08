package com.mycompany.javaconsoleapplication;


// Subclass (inherits from Person)
public class Student extends Person {
    private int id;
    private double marks;

    // Constructor calls superclass constructor
    public Student(String name, int age, int id, double marks) {
        super(name, age); // calls Person constructor
        this.id = id;
        this.marks = marks;
    }

    public int getId() { return id; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return super.toString() + ", ID: " + id + ", Marks: " + marks;
    }
}
