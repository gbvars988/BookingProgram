
package bookingprogram;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Student class stores an ArrayList of courses 
 * that the student is currently enrolled.
 * Provides functionality to withdraw, enrol, 
 * and display enrolled course(s).
 * @author ronaldho
 *
 */
public class Student {
	
	private ArrayList<Course> student_courses = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	
	public Student() {}
	
	public ArrayList<Course> getStudentCourses() {
		return this.student_courses;
	}
	
	/**
	 * Method called by Booking class
	 * to enrol in a selected course if
	 * not already enrolled.
	 * @param Course subject
	 */
	public void enrol(Course subject) {
		if (student_courses.contains(subject)) {
			System.out.println("You are already enrolled in: " + subject.getName() + "\n");
		}
		else {
			student_courses.add(subject);
			System.out.println("You have enrolled in the course " + subject.getName() + "!\n");
		}
	}
	
	/**
	 * Method to print course details from 
	 * 'student_courses' ArrayList.
	 */
	public void showCourses() {
		if (student_courses.size() == 0) {
			System.out.println("You don't have any courses enrolled.\n");
		}
		else {
			for (int i = 0; i < student_courses.size(); ++i) {
				System.out.println("    " + (i+1) + ") " + student_courses.get(i).getName() +
						"  " + student_courses.get(i).getDelivery() + "  " + 
						student_courses.get(i).getDay() + "  " + 
						student_courses.get(i).getTime() + "  " + student_courses.get(i).getDuration()
						+ " hr(s)");
			}
			System.out.println();
		}
	}
	
	/**
	 * Method called by Booking class to 
	 * withdraw from subject contained in 'student_courses'
	 * ArrayList.
	 * @throws MenuException
	 */
	public void withdraw() throws MenuException {
			this.showCourses();
			// Try catch block for input validation.
			// Handles MenuException and NumberFormatException.
			try {
				if (student_courses.size() > 0) {
					System.out.print("Please select: ");
					String userInput = sc.nextLine();
					if (Integer.parseInt(userInput) >  (student_courses.size()) || Integer.parseInt(userInput) < 1 || userInput.isEmpty()) {
						throw new MenuException("Index out of range\n");
					}
					System.out.println("You have withdrawn from " + student_courses.get(Integer.parseInt(userInput) - 1).getName() + "\n");
					student_courses.remove(Integer.parseInt(userInput) - 1);
					
				}
			}
			catch (MenuException e) {
				System.err.println(e.getMessage());
			}
			catch (NumberFormatException e) {
				System.err.println("PLEASE ENTER A NUMBER\n");
			}
		
		
	}
	
	
	
	
}
