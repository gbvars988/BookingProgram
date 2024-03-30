package bookingprogram;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Booking class provides the main functionality of the course booking system.
 * @author ronaldho
 *
 */
public class Booking {
	
	private Scanner sc = new Scanner(System.in);
	private ArrayList<Course> all_courses;
	private Student curr_student; 
	
	/**
	 * Construct a booking object.
	 * Create ArrayList of courses and a Student object. 
	 */
	public Booking() {
		this.all_courses = new ArrayList<>();
		this.curr_student = new Student();
	}
	
	/**
	 * Method to read courses from a CSV file
	 * into ArrayList all_courses.
	 * @param filename
	 */
	public void readCourses(String filename) {
		// Try catch block to handle
		// NullPointerexception and FileNotFoundException
		try {
			Scanner scFile = new Scanner(new File(filename));
			String line = scFile.nextLine();
			
			// Discard first line as it contains category headings
			line = scFile.nextLine();
			
			
			while (scFile.hasNextLine()) {
				String[] attributes = line.split(",");
				all_courses.add(new Course(attributes));
				line = scFile.nextLine();
			}
			// Last line of CSV
				String[] attributes = line.split(",");
				all_courses.add(new Course(attributes));
		}
		catch (NullPointerException e) {
			System.err.println("File is null.");
			// When testing, we do not stop the program.
			// Do not execute below code for testing.
			//System.exit(1);
		}
		catch (FileNotFoundException e) {
			System.err.println("Check file name.");
			// When doing testing, we do not stop the program. 
			// Do not execute below code for testing.
			//System.exit(1);
		}
	}
	
	/**
	 * @return all_courses ArrayList object.
	 */
	public ArrayList<Course> getCourses() {
		return this.all_courses;
	}
	
	/**
	 * Entry point into program. Provides main menu logic.
	 * Calls various other methods in this class
	 * corresponding to user input.
	 * @throws MenuException
	 */
	public void run() throws MenuException {
		boolean exit = false;
		do {
			this.printMenu();
			String userInput = sc.nextLine(); 
			
			// Try catch block for input validation.
			// Catches and handles MenuException and NumberFormatException
			try {
				if (userInput.isEmpty() || Integer.parseInt(userInput) > 4 || Integer.parseInt(userInput) < 1) {
					throw new MenuException("PLEASE SELECT A VALID OPTION\n");
				}
			}
			catch (MenuException e) {
				System.err.println(e.getMessage());
			}
			catch (NumberFormatException e) {
				System.err.println("PLEASE ENTER A NUMBER...\n");
			}

			switch (userInput) {
				case "1": 
					this.courseSearch();
					break;
				case "2":
					this.showCourses();;
					break;
				case "3":
					this.withdraw();
					break;
				case "4":
					exit = true;
					break;
			}
			
		}
		while (!exit);
		
		System.exit(0);
	}
	
	/**
	 * Method called by run() to 
	 * print main menu options.
	 */
	private void printMenu() {
		System.out.println("Welcome to MyTimetable!");
		System.out.println("---------------------------------------------------------");
		System.out.println("> Select from main menu");
		System.out.println("---------------------------------------------------------");
		System.out.printf("	  %s\n", "1) Search by keyword to enrol");
		System.out.printf("	  %s\n", "2) Show my enrolled courses");
		System.out.printf("	  %s\n", "3) Withdraw from a course");
		System.out.printf("	  %s\n", "4) Exit");
		System.out.print("Please select: ");
		
	}
	
	/**
	 * Search 'all_courses' ArrayList for matching courses 
	 * based on keyword input.
	 * Calls enrol() method from Student class if 
	 * course selected to enroll.
	 * @throws MenuException
	 */
	private void courseSearch() throws MenuException {
		String keyword;
		ArrayList<Course> matching_list = new ArrayList<>();
		
		System.out.println("Please provide a keyword: ");
		keyword = sc.nextLine().toLowerCase();
		System.out.println("---------------------------------------------------------");
		System.out.println("> Select from matching list");
		System.out.println("---------------------------------------------------------");
		
		// Count variable stores the digit to 'Go back to main menu'.
		int count = 1;
		// Display courses if course name contains keyword
		for (int i = 0; i < all_courses.size(); ++i) {
			if (all_courses.get(i).getName().toLowerCase().contains(keyword)) {
				System.out.println("   " + String.valueOf(count) + ") " + all_courses.get(i).getName());
				matching_list.add(all_courses.get(i));
				++count;
			}
		}
		System.out.println("   " + String.valueOf(count) + ") Go to main menu");
		System.out.println("Please select: ");
		String userInput; 
		userInput = sc.nextLine();
		// Enrol into selected matching courses.
		// Try catch block for input validation.
		// Catches and handles MenuException and NumberFormatException
		try {
			if (userInput.isEmpty() || Integer.parseInt(userInput) > count || Integer.parseInt(userInput) < 1) {
				throw new MenuException("PLEASE SELECT A VALID OPTION");
			}
			if(matching_list.size() > 0) {
				while(Integer.valueOf(userInput) != count) {
					if (Integer.valueOf(userInput) <= (matching_list.size()+1)) {
						this.curr_student.enrol(matching_list.get(Integer.valueOf(userInput) - 1));
						break;
					}
					else {
						System.out.print("Please select a valid option: ");
						userInput = sc.nextLine();
					}
				}
			}
			else {
				System.out.println("No courses found with matching keyword...");
				while(Integer.valueOf(userInput) != count) {
					System.out.print("Please select a valid option: ");
					userInput = sc.nextLine();	
				}
				this.run();
			}
		}
		catch (NumberFormatException e) {
			System.err.println("PLEASE ENTER A NUMBER...");
		}
		catch (MenuException e) {
			System.err.println(e.getMessage());
		}
	
	}
	
	/**
	 * Method called by run() to display enrolled course(s)
	 * if any, by calling showCourses() in Student class.
	 * @throws MenuException
	 */
	public void showCourses() throws MenuException{
		System.out.println("---------------------------------------------------------");
		System.out.println("You have enrolled into the following course(s):");
		System.out.println("---------------------------------------------------------");
		curr_student.showCourses();
		this.run();
	}
	
	/**
	 * Method called by run() to withdraw from course(s).
	 * Calls withdraw() method from Student class.
	 * @throws MenuException
	 */
	public void withdraw() throws MenuException{
		System.out.println("---------------------------------------------------------");
		System.out.println("Please choose a course to withdraw:");
		System.out.println("---------------------------------------------------------");
		curr_student.withdraw();
		this.run();
	}
	
	
}
