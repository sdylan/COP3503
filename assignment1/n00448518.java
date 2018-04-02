/*  This program calculates the cost of driving a certain distance in a car.
 *  It acquires the user's trip distance, fuel efficiency, and price of gas, 
 *  then computes the total cost of the trip. Finally the cost is displayed
 *  to the console.
 *
 *    Created by: Samuel Schwartz
 *    Last Update: 8/30/2017 12:37 PM 
 */
 
import java.util.Scanner; //imports Scanner class to be used in the program

public class n00448518 
{
    public static void main (String[] args)
    {
        //Create Scanner object
        Scanner input = new Scanner(System.in); 
        
        //Variables declared
        double drivingDistance;
        double milesPerGallon;
        double pricePerGallon;
        double costOfDriving;
        
        System.out.print("Enter the driving distance:  "); //Prompt user to enter driving distance
        drivingDistance = input.nextDouble();  //Store driving distance entered
        
        System.out.print("Enter miles per gallon:  "); //Prompt user to enter fuel efficiency
        milesPerGallon = input.nextDouble();  //Store fuel efficiency entered
        
        System.out.print("Enter price per gallon:  "); //Prompt user to enter gas price
        pricePerGallon = input.nextDouble();  //Store gas price entered
        
        //Calculate cost of driving
        costOfDriving = drivingDistance / milesPerGallon * pricePerGallon; 
        
        //Print the cost of driving
        System.out.printf("The cost of driving is $%4.2f\n", costOfDriving);
    }
}