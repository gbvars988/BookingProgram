package bookingprogram;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {
	private final InputStream systemIn = System.in;
	private final PrintStream systemOut = System.out;
	
	private Student student;
	private Booking booking;
	
	//private ByteArrayOutputStream testOut;
	private ByteArrayInputStream testIn;
	private ByteArrayOutputStream errorMessage;
	
	@Before
	public void setUp() throws Exception {
		student = new Student();
		booking = new Booking();
		booking.readCourses("course.csv");
		//testOut = new ByteArrayOutputStream();
		errorMessage = new ByteArrayOutputStream();
		//System.setOut(new PrintStream(testOut));
		System.setErr(new PrintStream(errorMessage));
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(systemIn);
		System.setOut(systemOut);
	}
	
	/**
	 * Test enrolling a student into their first course.
	 * Course object in student courses should match course object in booking courses.
	 */
	@Test
	public void testEnrol_firstCourse() {
		student.enrol(booking.getCourses().get(0));
		assertEquals(student.getStudentCourses().get(0), booking.getCourses().get(0));
	}
	
	/**
	 * Test enrolling a student into a course already enrolled.
	 * Expected size 1.
	 */
	@Test
	public void testEnrol_duplicate() {
		student.enrol(booking.getCourses().get(0));
		student.enrol(booking.getCourses().get(0));
		assertEquals(1, student.getStudentCourses().size());
	}
	
	
	// - - - - - - - - - Tests below this line require manual input: refer to README for instructions - - - - - - - - //
	// Attempts were made to automate input but unsuccessful.
	
	/**
	 * Test withdraw method with valid input.
	 * Enrolls into 1 course then subsequently withdraws with input "1".
	 * Should return a size of 0.
	 * @throws MenuException
	 */
	@Test 
	public void testWithdraw_validInput() throws MenuException {
		student.enrol(booking.getCourses().get(0));
		testIn = new ByteArrayInputStream("1".getBytes());
		System.setIn(testIn);
		student.withdraw();
		assertEquals(0, student.getStudentCourses().size());
	}
	
	/**
	 * Test withdraw method with out of range input.
	 * Enrolls into 1 course then tries to withdraw with input "2".
	 * Should return MenuException error message.
	 * @throws MenuException
	 */
	@Test
	public void testWithdraw_InputOutOfRange() throws MenuException {
		student.enrol(booking.getCourses().get(0));
		String input = "2";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
		student.withdraw();
		assertEquals("Index out of range\n\n", errorMessage.toString());
	}
	
	/**
	 * Test withdraw method with non digit input.
	 * Enrolls into 1 course then tries to withdraw by inputting "abc".
	 * Should return NumberFormatException error message.
	 * @throws MenuException
	 */
	@Test
	public void testWithdraw_InputNonDigit() throws MenuException {
		student.enrol(booking.getCourses().get(0));
		String input = "abc";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
		student.withdraw();
		assertEquals("PLEASE ENTER A NUMBER\n\n", errorMessage.toString());
	}
	
	/**
	 * Test withdraw method with empty input.
	 * Enrolls into 1 course then tries to withdraw with empty input (i.e. hitting ENTER/RETURN)
	 * Should return NumberFormatException error message.
	 * @throws MenuException
	 */
	@Test
	public void testWithdraw_EmptyInput() throws MenuException {
		student.enrol(booking.getCourses().get(0));
		String input = "";
		testIn = new ByteArrayInputStream(input.getBytes());
		System.setIn(testIn);
		student.withdraw();
		assertEquals("PLEASE ENTER A NUMBER\n\n", errorMessage.toString());
	}
	
	

}
