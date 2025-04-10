package project001;
import javax.swing.JOptionPane;

public class sumjopt {
    public static void main(String[] args) {
    String name;
    int numberOne;
    int numberTwo;
    int sum = 0;
    double average = 0.0;
    
    name = JOptionPane.showInputDialog("What is your name");
    JOptionPane.showMessageDialog(null, "Hi " + name + "\nWelcome to Java");
    numberOne = Integer.parseInt(JOptionPane.showInputDialog("Enter the 1st number"));
    numberTwo = Integer.parseInt(JOptionPane.showInputDialog("Enter the 2nd number"));
    
    sum = numberOne + numberTwo;
    average = sum / 2;
    
    JOptionPane.showMessageDialog(null, "sum is " + sum + "\nAverage is " + average);
    
    }
    
}
    

