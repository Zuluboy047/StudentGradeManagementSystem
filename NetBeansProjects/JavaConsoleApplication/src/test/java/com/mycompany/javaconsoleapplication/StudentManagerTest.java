package com.mycompany.javaconsoleapplication;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentManagerTest {

    @Test
    public void testSortByMarks() {
        StudentManager manager = new StudentManager(3);
        manager.addStudent(new Student("Zuluboy", 20, 101, 85.5));
        manager.addStudent(new Student("Lesiba", 22, 102, 74.0));
        manager.addStudent(new Student("Junior", 21, 103, 92.0));

        manager.sortByMarks();

        Student[] sorted = manager.getStudents();
        assertEquals("Zuluboy", sorted[0].getName()); // lowest marks first
        assertEquals("Lesiba", sorted[1].getName());
        assertEquals("Junior", sorted[2].getName());
    }
}
