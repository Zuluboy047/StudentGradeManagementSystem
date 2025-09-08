package com.mycompany.javaconsoleapplication;


public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager(5);

        // Add students
        manager.addStudent(new Student("Zuluboy", 20, 101, 85.5));
        manager.addStudent(new Student("Lesiba", 22, 102, 74.0));
        manager.addStudent(new Student("Junior", 21, 103, 92.0));

        // Print before sorting
        System.out.println("Before Sorting:");
        manager.printReport();

        // Sort and print again
        manager.sortByMarks();
        System.out.println("\nAfter Sorting by Marks:");
        manager.printReport();
    }
}
