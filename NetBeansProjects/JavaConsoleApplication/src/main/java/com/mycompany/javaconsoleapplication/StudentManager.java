package com.mycompany.javaconsoleapplication;


public class StudentManager {
    private Student[] students;
    private int count;

    // Constructor: initialize array of students
    public StudentManager(int size) {
        students = new Student[size];
        count = 0;
    }

    // Add student to array
    public void addStudent(Student s) {
        if (count < students.length) {
            students[count] = s;
            count++;
        } else {
            System.out.println("Student list is full!");
        }
    }

    // Bubble sort students by marks (ascending)
    public void sortByMarks() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (students[j].getMarks() > students[j + 1].getMarks()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    // Display report
    public void printReport() {
        System.out.println("===== Student Report =====");
        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }

    // Getter for unit tests
    public Student[] getStudents() {
        return students;
    }
}
