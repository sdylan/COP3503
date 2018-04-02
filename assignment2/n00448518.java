/*  This program prompts the user for an odd positive integer and then sums two separate
 *  series. The first is the series that follows the pattern 1/3 + 3/5 +...+(n-2)/n, where n
 *  is the integer entered by the user. Then the program calculates the approximate value of
 *  pi for the series 4(1 - 1/3 + 1/5 - 1/7 +...+(-1)^(i+1)/(2i-1)), where (2i-1) is the user
 *  defined integer from above. Last the program prints the two sums to the console.
 * 
 *    Created by: Samuel Schwartz
 *    Last Update: 9/11/2017 11:50 AM 
 */
 
import java.util.Scanner; //imports Scanner class to be used in the program
import java.lang.Math;    //imports Math class for power method used in program

public class n00448518 
{
    public static void main (String[] args)
    {
        //Create Scanner object
        Scanner input = new Scanner(System.in); 
        
        //Variables declared
        int    lastDenominator;
        double sumFirstSeries = 0; 
        double sumPiSeries = 0;
        
        System.out.print("Enter a single odd positive integer:  "); //Prompt user for input
        lastDenominator = input.nextInt();  //Store integer entered
            
        //Calculate the sum of the first series
        for (int count = 1; count <= (lastDenominator / 2); count ++){
            sumFirstSeries += (double)(2 * count - 1)/(2 * count + 1);
            }
         
        //Calculate the value of Pi    
        for (int count = 1; count <= (lastDenominator / 2 + 1); count++){
            sumPiSeries += (double)(Math.pow(-1.0, (count + 1))/(2 * count - 1)) * 4.0;
            }
            
        System.out.printf("The sum of the first series is %.12f\n", sumFirstSeries);
        System.out.printf("The approximate value of pi is %.12f\n", sumPiSeries);
    }
}    