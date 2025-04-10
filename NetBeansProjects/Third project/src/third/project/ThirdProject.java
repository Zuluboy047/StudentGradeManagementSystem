package third.project;

import javax.swing.JOptionPane;

public class ThirdProject {

    public static void main(String[] args) {
        //declare variable named x
        String name = "Zuluboy";
        String surname = "Nxumalo";
        String gender = "male";
        int age = 19;
        System.out.println("My name is " + name +" and my surname is " + surname + ". \nI'm " + gender + " who is " + age + " years old.");
        JOptionPane.showMessageDialog(null, "My name is " + name +" and my surname is " + surname + ". \nI'm " + gender + " who is " + age + " years old.", "Zuluboy!!!",JOptionPane.WARNING_MESSAGE);
    }
    
}
