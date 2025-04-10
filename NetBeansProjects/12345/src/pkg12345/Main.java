//Create a method to calculate area and volume of a rectangle
//Area = L x B
//Volume = L x B x H 
package pkg12345;

import javax.swing.JOptionPane;

public class Main {

   
    public static void main(String[] args) {
       double area,volume, length, bredth, height;
       //JOptionPane.showMessageDialog(null, "Enter Length =>> ");
       length = Double.parseDouble( JOptionPane.showInputDialog("Please Enter Length =>>"));
       bredth = Double.parseDouble( JOptionPane.showInputDialog("Please Enter Breadth =>>"));
       height = Double.parseDouble( JOptionPane.showInputDialog("Please Enter Height =>>"));
       
       //area = length * breadth;
       //volume = length * breadth * height;
        JOptionPane.showMessageDialog(null, "Area = " + area_calculator(length, bredth));
        JOptionPane.showMessageDialog(null, "Volume = " + volume_calculator(length, bredth, height));        
        JOptionPane.showMessageDialog(null, "Area = " + area_calculator(length, bredth) + "\nVolume = " + volume_calculator(length, bredth,height));
      }
   
    public static double area_calculator(double length, double b){
        return length * b;
    }
    
    public static double volume_calculator(double length, double b, double h){
        return length * b * h;
    }
        
}
