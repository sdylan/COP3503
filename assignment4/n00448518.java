/* This program reads vehicle entries from a file that the user specifies when the program is run
 * then stores each entry as a certain type of vehicle object. It then prints all of the entries to 
 * the console. Next the program sorts the entries by the associated email addresses and prints the
 * newly sorted list. Then the total amount of entries is printed. Next the program processes the list
 * and prints only the entries for the bicycles and trucks. Last the program prints the entries which 
 * have a phone number in the area code 987.
 *
 *    Written By: Samuel Schwartz
 *    Last Update: 10/19/2017 8:03 PM
 */

import java.util.*;
import java.io.*;
import java.text.*;

public class n00448518{
	public static void main (String[] args) throws FileNotFoundException, ParseException{
		ArrayList<Vehicle> vehicleRecord = new ArrayList<>(); //List to store each Vehicle object to be processed later in the program
		Scanner input = new Scanner(new File(args[0]));       //create a scanner to read from the file input by the user at program start
      DateFormat format = new SimpleDateFormat("M/d/yyyy");	//This is used to parse the date read from the truck entries in the given format
      	
      //Loop to read all the entries from the file provided, create an object from them and store in our list   
		while (input.hasNext()){
      
			String entryVehicleType = input.nextLine();  //reads the vehicle type from the file in order to tell what type of object to make
			
         //creates a Vehicle object and stores it in our list from the info read from the file 
			if (entryVehicleType.equals("vehicle"))
				vehicleRecord.add(new Vehicle(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine()));
         //creates a Car object and stores it in our list from the info read from the file 
			if (entryVehicleType.equals("car"))
				vehicleRecord.add(new Car(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), 
                                      Boolean.parseBoolean(input.nextLine()), input.nextLine()));
         //creates a AmericanCar object and stores it in our list from the info read from the file 
			if (entryVehicleType.equals("american car"))
				vehicleRecord.add(new AmericanCar(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), Boolean.parseBoolean(input.nextLine()), 
                                              input.nextLine(), Boolean.parseBoolean(input.nextLine()), Boolean.parseBoolean(input.nextLine())));
         //creates a ForeignCar object and stores it in our list from the info read from the file        				
			if (entryVehicleType.equals("foreign car"))
				vehicleRecord.add(new ForeignCar(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), Boolean.parseBoolean(input.nextLine()), 
                                             input.nextLine(), input.nextLine(), input.nextFloat()));
         //creates a Bicycle object and stores it in our list from the info read from the file 		
			if (entryVehicleType.equals("bicycle"))
				vehicleRecord.add(new Bicycle(input.nextLine(), input.nextLine(), input.nextLine(), 
														input.nextLine(), input.nextInt()));                                                     
			//creates a Truck object and stores it in our list from the info read from the file 
			if (entryVehicleType.equals("truck"))
				vehicleRecord.add(new Truck(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(),
													 input.nextFloat(), input.nextFloat(), format.parse(input.next())));              
		}
		
		input.close(); //close the scanner to the file we read from
      
      printAll(vehicleRecord); //call the method for printing all the entries
      
      sortByEmailAndPrint(vehicleRecord); //call the method for sorting entries by email and printing 
      
      amountOfRecords(vehicleRecord); //call method to determine the amount of entries and print the number
      
      printBicyclesAndTrucks(vehicleRecord); //call the method to print only the entries for bicycles and trucks
      
      vehiclesIn987AreaCode(vehicleRecord); //call the method to print only the entries in the 987 area code    
	}
   
   //This method receives the list of vehicles as an argument and prints each entry to the console
   public static void printAll(ArrayList<Vehicle> vehicleRecord){
      for (Vehicle i : vehicleRecord){
   	   System.out.print(i);
      }   	
	}
   //This method receives the list of vehicles as an argument, sorts by email and prints each entry sorted
   public static void sortByEmailAndPrint(ArrayList<Vehicle> vehicleRecord){
      Collections.sort(vehicleRecord);
      System.out.println("\nThe following is the list of vehicle records sorted by email.");
      printAll(vehicleRecord);   
   }
   //This method receives the list of vehicles as an argument and prints the number of entries
   public static void amountOfRecords(ArrayList<Vehicle> vehicleRecord){
      System.out.println("\nThe number of records is " + vehicleRecord.size() + ".");
   }
   //This method receives the list of vehicles as an argument and pritns only the bicycles and trucks
   public static void printBicyclesAndTrucks(ArrayList<Vehicle> vehicleRecord){
      System.out.println("\nThe following is the list of bicycle and truck records.");
      for (Vehicle i : vehicleRecord){
         if (i instanceof Bicycle || i instanceof Truck)
            System.out.print(i);
      }
   }
   //This method receives the list of vehicles as an argument and prints the entries from the 987 area code
   public static void vehiclesIn987AreaCode (ArrayList<Vehicle> vehicleRecord){
      int size = vehicleRecord.size();
      System.out.println("\nThe following is the list of vehicles in the 987 area code.");
      for (Vehicle i : vehicleRecord){
         if (i.getPhone().substring(0,4).equals("(987"))
            System.out.print(i);
      }
   }      

}

//This class provides a structure to store a generic vehicle entry as an object with its associated information
class Vehicle implements Comparable<Vehicle> {

   //Vehicle object parameters
	private String owner;  
	private String address;
	private String phone;
	private String email;
	
   //no args constructor
	public Vehicle(){
	}
	
   //constructor that recieves an argument for each of its parameters
	public Vehicle(String owner, String address, String phone, String email){
		this.owner = owner;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
   
   //returns a string so one can print a generic vehicles object's information
	public String toString(){
		return ("\nvehicle\n" + owner + "\n" + address + "\n" + phone + "\n" + email + "\n");
	}
   
   //used to sort the vehicles in a list. the .sort() method will compare character by character of the emails to determine the proper order
   public int compareTo(Vehicle o){
      int count = 0;
      do{
         if (this.getEmail().charAt(count) < o.getEmail().charAt(count))
            return -1;
         if (this.getEmail().charAt(count) > o.getEmail().charAt(count))
            return 1;
         count++;
         }while(this.getEmail().charAt(count-1) == o.getEmail().charAt(count-1));
      return 0;       
   }
	//below are the rest of the methods for getting and setting the individual parameters for a Vehicle object
	public String getOwner(){
		return owner;
	}
	
	public void setOwner(String owner){
		this.owner = owner;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
}

//This class provides a structure to store a generic car entry as an object with its associated information
class Car extends Vehicle{

   //Car object parameters
	private boolean convertible;
	private String color;
	
   //no args constructor
	public Car(){
	}
	
   //constructor that recieves an argument for each of its parameters
	public Car(String owner, String address, String phone, String email, boolean convertible, String color){
		super(owner, address, phone, email);
		this.convertible = convertible;
		this.color = color;
	}
   //returns a string so one can print a Car object's information
	public String toString(){
		return ("\ncar" + super.toString().substring(8,super.toString().length()) + convertible + "\n" + color + "\n");
	}
	//below are the rest of the methods for getting and setting the individual parameters for a Car object
	public boolean getConvertible(){
		return convertible;
	}
	
	public void setConvertible(boolean convertible){
		this.convertible = convertible;
	}
	
	public String getColor(){
		return color;
	}
	
	public void setColor(String color){
		this.color = color;
	}
}
//This class provides a structure to store an american car entry as an object with its associated information
class AmericanCar extends Car{

   //AmericanCar object parameters
	private boolean madeInDetroit;
	private boolean unionShop;
	
   //no args constructor
	public AmericanCar(){
	}
	
   //constructor that recieves an argument for each of its parameters
	public AmericanCar(String owner, String address, String phone, String email, 
					  boolean convertible, String color, boolean madeInDetroit, boolean unionShop){
		super(owner, address, phone, email, convertible, color);
		this.madeInDetroit = madeInDetroit;
		this.unionShop = unionShop;
	}
   
   //returns a string so one can print an AmericanCar object's information
   public String toString(){
		return ("\namerican car" + super.toString().substring(4,super.toString().length()) + madeInDetroit + "\n" + unionShop + "\n");
	}
	//below are the rest of the methods for getting and setting the individual parameters for an AmericanCar object
	public boolean getMadeInDetroit(){
		return madeInDetroit;
	}
	
	public void setMadeInDetroit(boolean madeInDetroit){
		this.madeInDetroit = madeInDetroit;
	}
	
	public boolean getUnionShop(){
		return unionShop;
	}
	
	public void setUnionShop(boolean unionShop){
		this.unionShop = unionShop;
	}
}
//This class provides a structure to store a foreign car entry as an object with its associated information
class ForeignCar extends Car{

   //FoeriegnCar object parameters
	private String manufacturingCountry;
	private float importDuty;
	
   //no args constructor
	public ForeignCar(){
	}
	
   //constructor that recieves an argument for each of its parameters
	public ForeignCar(String owner, String address, String phone, String email, 
					  boolean convertible, String color, String manufacturingCountry, float importDuty){
		super(owner, address, phone, email, convertible, color);
		this.manufacturingCountry = manufacturingCountry;
		this.importDuty = importDuty;
	}
   
   //returns a string so one can print a ForeignCar object's information
   public String toString(){
		return ("\nforeign car" + super.toString().substring(4,super.toString().length()) + manufacturingCountry + "\n" + importDuty + "\n");
	}
	//below are the rest of the methods for getting and setting the individual parameters for a ForeignCar object
	public String getManufacturingCompany(){
		return manufacturingCountry;
	}
	
	public void setManufacturingCompany(String manufacturingCountry){
		this.manufacturingCountry = manufacturingCountry;
	}
	
	public float getImportDuty(){
		return importDuty;
	}
	
	public void setImportDuty(float importDuty){
		this.importDuty = importDuty;
	}
}
//This class provides a structure to store a bicycle entry as an object with its associated information
class Bicycle extends Vehicle{

   //Bicycle object parameters
	private int numberOfSpeeds;
	
   //no args constructor
	public Bicycle(){
	}
	
   //constructor that recieves an argument for each of its parameters
	public Bicycle(String owner, String address, String phone, String email, int numberOfSpeeds){
		super(owner, address, phone, email);
		this.numberOfSpeeds = numberOfSpeeds;
	}
   
   //returns a string so one can print a Bicycle object's information
   public String toString(){
		return ("\nbicycle" + super.toString().substring(8,super.toString().length()) + numberOfSpeeds + "\n");
	}
	//below are the rest of the methods for getting and setting the individual parameters for a Bicycle object
	public int getNumberOfSpeeds(){
		return numberOfSpeeds;
	}
	
	public void setNumberOfSpeeds(int numberOfSpeeds){
		this.numberOfSpeeds = numberOfSpeeds;
	}
}
//This class provides a structure to store a truck entry as an object with its associated information
class Truck extends Vehicle{

   //Truck object parameters
	private float tons;
	private float cost;
	private Date datePurchased;
	
   //no args constructor
	public Truck(){
	}
	
   //constructor that recieves an argument for each of its parameters
	public Truck(String owner, String address, String phone, String email, 
				 float tons, float cost, Date datePurchased){
		super(owner, address, phone, email);
		this.tons = tons;
		this.cost = cost;
		this.datePurchased = datePurchased;
	}
   
   //returns a string so one can print a Truck object's information
   public String toString(){
      SimpleDateFormat printingFormat = new SimpleDateFormat("M/d/yyyy");
		return ("\ntruck" + super.toString().substring(8,super.toString().length()) + tons + "\n" + cost + "\n" + printingFormat.format(datePurchased) + "\n");
	}
	//below are the rest of the methods for getting and setting the individual parameters for a Truck object
	public float getTons(){
		return tons;
	}
	
	public void setTons(float tons){
		this.tons = tons;
	}
	
	public float getCost(){
		return cost;
	}
	
	public void setCost(float cost){
		this.cost = cost;
	}
	
   
	public Date getDatePurchased(){
		return datePurchased;
	}
	
	public void setDatePurchased(Date datePurchased){
		this.datePurchased = datePurchased;
	}
}
	
