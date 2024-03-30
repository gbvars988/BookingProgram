package bookingprogram;

public class Main {

	public static void main(String[] args) throws MenuException {
		String fileName = "course.csv";
		
		// Create an instance of booking class
		Booking test = new Booking();
		
		// Read in courses from CSV
		test.readCourses(fileName);
		
		// Run the booking system
		test.run();

	}

}

