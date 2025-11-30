// Name: Khadijat Folarin
// Student ID: 7966826
// Date: November 9th, 2025
// Instructor: Dr. Heather Matheson

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Properties;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner;

public class DatabaseInterface {
	static Connection connection;

	public static void main(String[] args) throws Exception {

		// startup sequence
		MyDatabase db = new MyDatabase();
		runConsole(db);

		System.out.println("Exiting the database...");
	}

	public static void runConsole(MyDatabase db) {
		Scanner console = new Scanner(System.in);
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.print("        AIRBNB AND NEIGHBOURHOOD INSIGHTS FOR THE CITY OF TORONTO");
		System.out.println("\n-----------------------------------------------------------------------------------");
		System.out.println("\nMain Menu: ");
		printMainMenu();
		
		System.out.print("\nEnter a number: ");
		
		while (console.hasNext()) {
			try {
				if (!console.hasNextInt()) {
					System.out.println("Please enter a valid number.");
					console.nextLine(); // Clear invalid input
					System.out.print("\nEnter a number: ");
					continue;
				}
				
				int choice = console.nextInt();
				console.nextLine(); // Consume newline
				
				// Exit condition
				if (choice == 20) {
					System.out.println("Exiting...");
					break;
				}
				
				switch (choice) {
					case 1:
						//db.showAllNeighbourhoods();
						// Show all crime in a specific neighbourhood
						System.out.print("Enter neighbourhood id: ");
						if (!console.hasNextInt()) {
							System.out.println("Invalid neighbourhood id. Must be a number.");
							console.nextLine(); // Clear invalid input
							break;
						}
						int neighbourhood = console.nextInt();
						console.nextLine(); // Consume newline
						
						if (neighbourhood <= 0) {
							System.out.println("Neighbourhood id must be positive.");
							break;
						}
						//db.showCrimeInNeighbourhood(neighbourhood);
						break;
						
					case 2:
						//db.showListings();
						// List all associated amenities by a specific Airbnb listing
						System.out.print("Enter Airbnb listing ID: ");
						if (!console.hasNextInt()) {
							System.out.println("Invalid listing id. Must be a number.");
							console.nextLine(); // Clear invalid input
							break;
						}
						int listingId = console.nextInt();
						console.nextLine(); // Consume newline
						
						if (listingId <= 0) {
							System.out.println("Listing id must be positive.");
							break;
						}
						//db.listAmenitiesByListing(listingId);
						break;
						
					case 3:
						//db.showAllNeighbourhoods();
						// Rank listings by price in a neighbourhood
						System.out.print("Enter neighbourhood id: ");
						if (!console.hasNextInt()) {
							System.out.println("Invalid neighbourhood id. Must be a number.");
							console.nextLine(); // Clear invalid input
							break;
						}
						neighbourhood = console.nextInt();
						console.nextLine(); // Consume newline
						
						if (neighbourhood <= 0) {
							System.out.println("Neighbourhood id must be positive.");
							break;
						}
						//db.rankListingsByPrice(neighbourhood);
						break;
						
					case 4:
						// List all Airbnb listing with host and neighbourhood
						//db.listAllAirbnbWithHostAndNeighbourhood();
						break;
						
					case 5:
						// Top N neighbourhoods with most crime
						System.out.print("Enter number of neighbourhoods (N): ");
						if (!console.hasNextInt()) {
							System.out.println("Invalid number. Must be an integer.");
							console.nextLine(); // Clear invalid input
							break;
						}
						int n = console.nextInt();
						console.nextLine(); // Consume newline
						
						if (n <= 0) {
							System.out.println("Number must be positive.");
							break;
						}
						if (n > 1000) {
							System.out.println("Number too large. Maximum is 1000.");
							break;
						}
						//db.topNNeighbourhoodsWithMostCrime(n);
						break;
						
					case 6:
						// Count Airbnb listings per neighbourhood
						//db.countAirbnbPerNeighbourhood();
						break;
						
					case 7:
						// Crime handled per police force
						//db.crimeHandledPerPoliceForce();
						break;
						
					case 8:
						// Guests with both Airbnb & attraction visits
						//db.guestsWithAirbnbAndAttractions();
						break;
						
					case 9:
						// Crime rate vs Airbnb listings analysis
						//db.crimeRateVsAirbnbListings();
						break;
						
					case 10:
						// Attractions vs crime comparisons
						//db.attractionsVsCrime();
						break;
						
					case 11:
						// Attractions vs Airbnb price
						//db.attractionsVsAirbnbPrice();
						break;
						
					case 12:
						// Crime, attractions, and police stations analysis
						//db.crimeAttractionsPoliceAnalysis();
						break;
						
					case 13:
						// Repeat offender vs attractions
						//db.repeatOffenderVsAttractions();
						break;
						
					case 14:
						// Amenities vs booking analysis
						//db.amenitiesVsBooking();
						break;
						
					case 15:
						// Amenities vs pricing analysis
						//db.amenitiesVsPricing();
						break;
						
					case 16:
						// Host who are also guests
						//db.hostWhoAreAlsoGuests();
						break;
						
					case 17:
						// Reviews vs bookings analysis
						//db.reviewsVsBookings();
						break;
						
					case 18:
						// Unapproved listings analysis
						//db.unapprovedListings();
						break;
						
					case 19:
						// Criminals in multi-unit neighbourhoods
						//db.criminalsInMultiUnitNeighbourhoods();
						break;
						
					case 21:
						// Help (show this menu)
						printMainMenu();
						break;
						
					case 22:
						// Reset database
						System.out.print("Are you sure you want to reset the database? (yes/no): ");
						String confirmation = console.nextLine().trim().toLowerCase();
						if (confirmation.equals("yes")) {
							System.out.println("Resetting database...");
							//db.resetDatabase();
						} else {
							System.out.println("Reset cancelled.");
						}
						break;
						
					default:
						System.out.println("Invalid option. Please enter a number between 1 and 22.");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				console.nextLine(); // Clear invalid input
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}

			System.out.print("\nEnter a number: ");
		}

		console.close();
}

	private static void printMainMenu() {
		System.out.println("1. Show all crimes in a specific neighbourhood");
		System.out.println("2. List all associated amenities for a specific Airbnb listing");
		System.out.println("3. Rank listings by price in a neighbourhood");
		System.out.println("4. List all airbnb listings with host and neighbourhood");
		System.out.println("5. Top N neighbourhoods with the most crimes");
		System.out.println("6. Count Airbnb listings per neighbourhood");
		System.out.println("7. Crimes handled per police station");
		System.out.println("8. Guests with both Airbnb and Attraction visits");
		System.out.println("9. Crime rate vs Airbnb listings analysis");
		System.out.println("10. Attractions vs Crime comparisons");
		System.out.println("11. Attractions vs Airbnb prices");
		System.out.println("12. Crimes, attractions and police stations analysis");
		System.out.println("13. Repeat offenders vs attractions");
		System.out.println("14. Amenities vs booking analysis");
		System.out.println("15. Amenities vs pricing analysis");
		System.out.println("16. Host who are also guests");
		System.out.println("17. Reviews vs bookings analysis");
		System.out.println("18. Unapproved listings analysis");
		System.out.println("19. Criminals in multi-unit neighbourhoods");
		System.out.println("20. Exit");
		System.out.println("21. Help (show this menu)");
		System.out.println("22. Reset database");
	}

}

class MyDatabase {
    private Connection connection;

    public MyDatabase() {
        try {
            // Read credentials from config file
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("auth.cfg");
            props.load(in);
            in.close();
            
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            
            String connectionUrl = "jdbc:sqlserver://uranium.cs.umanitoba.ca:1433;"
                    + "database=cs3380;"
                    + "user=" + username + ";"
                    + "password=" + password + ";"
                    + "encrypt=false;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";
            
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to Uranium database successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace(System.out);
        } catch (IOException e) {
            System.out.println("Failed to read config file.");
            e.printStackTrace(System.out);
        }
    }
    
    // Your other methods here...
}


	