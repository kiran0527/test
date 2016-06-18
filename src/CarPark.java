

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class CarPark
{
	final int ENTER_CAR = 1;
	final int DISPLAY_CARS = 2;
	final int DISPLAY_STATISTICS = 3;
	final int SEARCH_CARS = 4;
	final int SORT_CARS = 5;
	final int EXIT = 6;

	Scanner inputMenuChoice = new Scanner(System.in);
	static Car car[] = new Car[1];
	static int carsParked =-1;
	JPanel errorMessage = new JPanel();
	 private int getMenuItem()
	{
		System.out.println("\nPlease select from the following");
		System.out.println(ENTER_CAR + ". Enter licence plate and hours stayed");
		System.out.println(DISPLAY_CARS + ". Display all licence plates, hours and fees");
		System.out.println(DISPLAY_STATISTICS + ". Display Statistics");
		System.out.println(SEARCH_CARS + ". Search for car");
		System.out.println(SORT_CARS + ". Sort the cars");
		System.out.println(EXIT + ". Exit the application");
		System.out.print("Enter choice==> ");
		return inputMenuChoice.nextInt();
	}

	 //Menu to display options
	void processCars() throws IOException
	{   
		int choice = getMenuItem();
		switch (choice)
			{
				case ENTER_CAR:
					if (++carsParked<car.length){
						enterCar();
						
					}else{
						 JOptionPane.showMessageDialog(errorMessage, "Error- Maximum cars to be entered has been reached", "Enter Car",
							        JOptionPane.ERROR_MESSAGE);
						processCars();
					}
					break;
				case DISPLAY_CARS:
					displayAllCars();
					break;
				case DISPLAY_STATISTICS:
					displayStatistics();
					break;
				case SEARCH_CARS:
					searchCar();
					break;
				case SORT_CARS:
					sortCars();
					break;
				case EXIT:
					System.out.println("Thank you for using the Rocky car parking system");
					System.out.println("Program written by 12022837");
 					System.exit(1);

					
					break;
				default:
					System.out.println("ERROR choice not recognised");
			
			}
			choice = getMenuItem();
		
		
	}
	private void enterCar() throws IOException
	{     
		//numberPlateInput();
     String licencePlate = JOptionPane.showInputDialog(null,"Input Licence Plate","Input Licence Plate",JOptionPane.DEFAULT_OPTION);
     String hoursParked= null;
		if(licencePlate==null ) {
			carsParked--;
			  processCars();
		}
		else if(licencePlate.length() == 0)
		{

			  JOptionPane.showMessageDialog(errorMessage, "Error- License Plate Cannot be blank", "Input Licence Plate",
				        JOptionPane.ERROR_MESSAGE);
			  enterCar();
		
		}
		else{
			boolean correctInput = true;
			while(correctInput) {
			
		 	 hoursParked = JOptionPane.showInputDialog(null,"Enter number of hours the car was parked[1 - 12]:","Input hours parked",JOptionPane.DEFAULT_OPTION);
		 			try{
		 				
		 				if(hoursParked==null ) {
			 				  processCars();
			 			}
			 			 
						else if(Integer.parseInt(hoursParked)>=1 && Integer.parseInt(hoursParked)<=12) {
							car[carsParked] = new Car(licencePlate,hoursParked);
							correctInput =false;
						    System.out.println("Details for car "+(carsParked+1)+"entered");
							System.out.println("Licence Plate   Hours     Fee");
							System.out.println(car[carsParked].getlicenceNumber()+"\t\t"+car[carsParked].getparkingDuration()+"\t"+car[carsParked].calculateParkingCharge());
					 	try {
						 		processCars();
							} catch (IOException e) {
								e.printStackTrace();
							}

						
						}
		 			if(hoursParked==null ) {
		 				  processCars();
		 			}
		 			 
					else { 
						
						JOptionPane.showMessageDialog(errorMessage, "Error- Hours must be between 1 and 12", "Input Hours Parked",
							        JOptionPane.ERROR_MESSAGE);
						
					
					}
		 			} catch(NumberFormatException nfe) {
		 				JOptionPane.showMessageDialog(errorMessage, "Error- Hours must be between 1 and 12", "Input Hours Parked",
						        JOptionPane.ERROR_MESSAGE);
		 				
		 			}
	
			}
			
		}
	
	}
	//displays the list of cars
	private void displayAllCars() throws IOException
	{
		
		if(car == null || carsParked<0) {
			  JOptionPane.showMessageDialog(errorMessage, "You must enter at least one car", "Display all cars",
				        JOptionPane.ERROR_MESSAGE);
		}
		else {
		System.out.println("Licence Plate   Hours              Fee");
		for (int i = 0; i <= carsParked; i++) {
			System.out.println(car[i].getlicenceNumber()+"\t\t"+car[i].getparkingDuration()+" \t\t "+car[i].calculateParkingCharge());
		}
	}
		processCars();
	}
	//displays the statistics of cars
	private void displayStatistics() throws IOException
	{
		   if(carsParked<0) {
				  JOptionPane.showMessageDialog(errorMessage, "You must enter at least one car", "Display All Cars",
					        JOptionPane.ERROR_MESSAGE);
		   }
		   else {
		    double minDuration=car[0].getparkingDuration();
	        double maxDuration=car[0].getparkingDuration();
	        double totalCharge=0;
	        double parkingHours=0;
	        int minCar=0,maxCar=0;
	        for(int carNum=0;carNum<=carsParked;carNum++){
	            if(car[carNum].getparkingDuration()<minDuration){
	            	minDuration=car[carNum].getparkingDuration();
	            	minCar=carNum;
	            }
	            else if(car[carNum].getparkingDuration()>maxDuration){
	            	maxDuration=car[carNum].getparkingDuration();
	            	maxCar=carNum;
	            }
	            totalCharge+=car[carNum].calculateParkingCharge();
	            parkingHours+=car[carNum].getparkingDuration();
	        }
	        
	        System.out.println("Statistics for the car park");
	        System.out.println(car[minCar].getlicenceNumber()+" has the minimum stay of "+car[minCar].getparkingDuration()+" hour(s)");
	        System.out.println(car[maxCar].getlicenceNumber()+" has the maximum stay of  "+car[maxCar].getparkingDuration()+" hour(s)");
			System.out.println("Average stay is "+parkingHours/2+" hour(s)");
			System.out.println("The total fee collected is :$"+totalCharge);
		   }
		   
		   processCars();
	}
	
	private void searchCar() throws IOException
	{
		 if(car == null || carsParked<0) {
			   JOptionPane.showMessageDialog(errorMessage, "You must enter at least one car", "Search Cars",
				        JOptionPane.ERROR_MESSAGE);
			 
			 
		 }
		   else { 
			   inputCarNumber();
		   }
		 processCars();
	}

	//function to sort cars
	private void sortCars() throws IOException
	{	
		if(carsParked>=1) {
			ArrayList<String> parkedCars=new ArrayList<String>();
			System.out.println("\nCar Sorted\n");
			System.out.println("Licence Plate   Hours     Fee");
		
			for (int a = 0; a <= carsParked; a++) {
				parkedCars.add(car[a].getlicenceNumber()+"\t"+car[a].getparkingDuration()+"\t"+car[a].getparkingCharge(a));
			}
			Collections.sort(parkedCars);
			for (int b=0;b<parkedCars.size();b++) {
				System.out.println(parkedCars.get(b));
			}
		}
		else {
			JOptionPane.showMessageDialog(errorMessage, "Error-Enter at least two cars", "Sort cars",
			        JOptionPane.ERROR_MESSAGE);
			}
	processCars();
	}

	
	
	//Takes car number as input from frontend
	public void inputCarNumber() throws IOException {

		
	 	String searchCar = JOptionPane.showInputDialog(null,"Please Enter the licence plate of the car",null,JOptionPane.DEFAULT_OPTION);
		
	 	if(searchCar==null ) {
			  processCars();
		}
		 
		else if(searchCar.length() == 0) {
	 	 	 			JOptionPane.showMessageDialog(errorMessage, "Error- License Plate Cannot be blank", "Input Licence Plate",
						        JOptionPane.ERROR_MESSAGE);
	 	 	 		} else
					try {
						searchCarResult(searchCar);
					} catch (IOException e) {
						e.printStackTrace();
					}
	 	 	 		
	}
	//function to search car
	public void searchCarResult(String licencePlate) throws IOException {
	 	boolean iscar=true;
		for (int i = 0; i <= carsParked; i++) {
			if(car[i].getlicenceNumber().equals(licencePlate)){
				System.out.println("\nCar Found\n");
				System.out.println("Licence Plate   Hours     Fee");
				System.out.println(car[i].getlicenceNumber()+"\t\t"+car[i].getparkingDuration()+" \t "+car[i].calculateParkingCharge());
				iscar=false;
				try {
					processCars();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(iscar){
		 	  JOptionPane.showMessageDialog(errorMessage,licencePlate+" not found", "Search Car",
				        JOptionPane.ERROR_MESSAGE);
		 	  searchCar();
		}
		
	}
	
	public static void main(String [] args) throws IOException
	{
		System.out.println("Welcome to Rocky car parking system");
		CarPark cp = new CarPark();   //creating CarPark Object
		cp.processCars();  //calling menu
	}
	
}
