/*  This program begins by prompting the user for 18 integers and stores the first 9 into one 3x3 matrix
 *  and the last 9 into a different 3x3 matrix. Then the program processes the two matrices and determines
 *  and displays whether the matrices are identical, how many cells are identical, how many cells are 
 *  identical along the main diagonal and the average of all 18 numbers.  It will then print the matrices in
 *  a readable format with the even numbers replaced by a blank space. Last the program determines whether or 
 *  not the values entered are greater than 1 and less than or equal to 10. Then it displays the result.
 * 
 *    Created by: Samuel Schwartz
 *    Last Update: 9/23/2017 12:49 PM 
 */

import java.util.Scanner; //imports Scanner class to be used in the program

public class n00448518
{
   public static void main (String[] args)
   {
      //Create Scanner object
      Scanner input = new Scanner(System.in);
      
      //Variables declared
      int[][] m1 = new int[3][3];
      int[][] m2 = new int[3][3];
      
      //Prompt user for input
      System.out.print("Enter 18 integers from 0-99 separated by a space then press enter:  ");
      
      //Store first 9 integers entered into first matrix
      for (int row = 0; row < m1.length; row++)
      {
         for (int column = 0; column < m1[row].length; column++)
         {
            m1[row][column] = input.nextInt();
         }
      }
      
      //Store next 9 integers entered into second matrix
      for (int row = 0; row < m2.length; row++)
      {
         for (int column = 0; column < m2[row].length; column++)
         {
            m2[row][column] = input.nextInt();
         }
      }
      
      //Call equals method and prints whether the two matrices are identical
      if (Strict.equals(m1,m2))
         System.out.print("\nThey are identical.\n");
      else 
         System.out.print("\nThey are not identical.\n");
      
      //Call howmany method and prints how many equivalent cells there are in the two matrices  
      System.out.print("\nThere are " + Strict.howmany(m1,m2) + " identical cells.\n");
      
      //Call diagonal method and prints how many identical cells there are along the main diagonal
      System.out.print("\nThere are " + Strict.diagonal(m1,m2) + " cells identical along the diagonal.\n");
      
      //Call average method and prints the average rounded to two decimals for all numbers in the two matrices
      System.out.printf("\nThe average of the 18 numbers is %5.2f.\n",Strict.average(m1,m2));
      
      //Call display method which prints the matrices with only odd numbers and blanks for even numbers
      Strict.display(m1,m2);
      
      //Call silly method and prints whether all the values satisfy 1 < number <= 10
      if (Strict.silly(m1,m2))
         System.out.print("\nThe values satisfy 1 < number <= 10.\n");
      else
         System.out.print("\nThe values do not satisfy 1 < number <= 10.\n");
   }
}

class Strict
{
   //This method compares the two matrices cell by cell and determines if they are identical
   public static boolean equals (int[][] m1, int[][] m2)
   {
      int equalsCount = 0; //variable to count the amount of identical cells
      
      for (int row = 0; row < m1.length; row++)
      {
         for (int column = 0; column < m1[row].length; column++)
         {
            if (m1[row][column] == m2[row][column]) 
               equalsCount++;           //add one to counter if the current cells are identical
         }
      }
      
      if (equalsCount == 9) //return true if the amount of identical cells counted 
                            //is the same as the total number of cells
         return true;
         
      return false;
      
   }
   
   //This method compares the two cells and determines how many cells are identical
   public static int howmany (int[][] m1, int[][] m2)
   {
      int equalsCount = 0; //variable to count the amount of identical cells
      
      for (int row = 0; row < m1.length; row++)
      {
         for (int column = 0; column < m1[row].length; column++)
         {
            if (m1[row][column] == m2[row][column])
               equalsCount++;           //add one to counter if the current cells are identical
         }
      }
      
      return equalsCount; //return the amount of identical cells
   }
   
   //This method compares the diagonal of the two matrices and determines how many values are identical
   public static int diagonal (int[][] m1, int[][] m2)
   {
      int equalsCount = 0; //variable to count the amount of identical cells
      
      for (int row = 0; row < m1.length; row++)
      {
         for (int column = 0; column < m1[row].length; column++)
         {
            if (m1[row][column] == m2[row][column] && row == column) //if cells are in diagonal and values match
               equalsCount++;                                        //add one to counter
         }
      }
      
      return equalsCount; //return the amount of identical cells in diagonal
   }
   
   
   //This method sums all 18 values entered and returns the average 
   public static double average (int[][] m1, int[][] m2)
   {
      int sum = 0; //variable to store the running sum as the matrices are processed
      
      for (int row = 0; row < m1.length; row++)
      {
         for (int column = 0; column < m1[row].length; column++)
         {
            sum += m1[row][column] + m2[row][column]; //add the values in the current cells to sum
         }
      }
      
      return (double)sum / 18; 

   }
   
   //This method takes the two matrices as arguments and prints them in a 
   //readable rectangular matrix format with the even values replaced by blanks.
   public static void display (int[][] m1, int[][] m2)
   {
      System.out.print("\nMatrix 1:\n");  //Print header for first matrix
      
      for (int row = 0; row < m1.length; row++)
      {
         System.out.print("[");  //Print opening bracket
         
         for (int column = 0; column < m1[row].length; column++)
         {
            if (m1[row][column] % 2 == 0) //Determine if the value in cell is even
               System.out.print("   ");   //if so print blanks
            else 
               System.out.printf("%3d",m1[row][column]); // Otherwise print the number which is odd
         }
   
         System.out.print("]\n"); //Print closing bracket and go to next line
      }
      
      System.out.print("\nMatrix 2:\n"); //Print header for the second matrix
      
      for (int row = 0; row < m2.length; row++) 
      {
         System.out.print("["); //Print opening bracket to begin row
      
         for (int column = 0; column < m2[row].length; column++)
         {
            if (m2[row][column] % 2 == 0) //Determine if the value in cell is even
               System.out.print("   ");   //if so print blanks
            else 
               System.out.printf("%3d",m2[row][column]); //Otherwise print the number which is odd
         }
         
         System.out.print("]\n"); //Print closing bracket and go to next line
      }
   }
   
   //This method goes through each cell of the two matrices and if anything does not fit in the range returns false
   public static boolean silly (int[][] m1, int[][] m2)
   {      
      for (int row = 0; row < m1.length; row++)
      {
         for (int column = 0; column < m1[row].length; column++)
         {
            if (!(m1[row][column] > 1 && m1[row][column] <= 10 && m2[row][column] > 1 && m2[row][column] <= 10))
               return false;
         }
      }      
      return true;
               
   }
}