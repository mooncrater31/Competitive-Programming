import javax.swing.*;
public class InputTest2
{
 public static void main(String[] args)
 {
  String name=JOptionPane.showInputDialog("What is your name?");
  String input=JOptionPane.showInputDialog("How old are you?");
  int age=Integer.parseInt(input);
  System.out.println("Hello, "+ name + ". Next year you'll be "+ (age+1));
  System.exit(0);
 }
} 
