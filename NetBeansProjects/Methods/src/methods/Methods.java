/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package methods;

/**
 *
 * @author Zuluboy
 */
public class Methods {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        displayMessage();
        sum();
     }
    //accessor static returntyp identifier(){}
    public static void displayMessage(){
     //print "This is my method"
    System.out.println("This is my method");
     }
    
    //Method called sum
    public static void sum(){
      int a = 3;
      int b = 5;
      int add = a + b;
      System.out.println("Sum of " + a + " + " + b + " = " + add);
        
    }
}
