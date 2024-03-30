package bookingprogram;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookingTest {
	
	private Booking booking;
	private ByteArrayOutputStream errorMessage;
	

	@Before
	public void setUp() throws Exception {
		booking = new Booking();
		errorMessage = new ByteArrayOutputStream();
		// When calling System.err.println() the content will be
		// in a byte array output stream referred to by the errorMessage
		// reference. 
		System.setErr(new PrintStream(errorMessage));
	}

	@After
	public void tearDown() throws Exception {
		System.setErr(System.err);
	}
	
	/** 
	 * Test readCourses method with null file path parameter
	 * Should return "File is null"
	 */
	@Test
	public void testReadCourses_FilePathNull() {
		booking.readCourses(null);
		assertEquals("File is null.\n", errorMessage.toString());
	}
	
	/** 
	 * Test readCourses method with non existent file name parameter
	 * Should return "Check file name."
	 */
	@Test 
	public void testReadCourses_FileNotFound() {
		booking.readCourses("random.csv");
		assertEquals("Check file name.\n", errorMessage.toString());
	}
	
	/**
	 * Test readCourses method with existing file name parameter
	 * Should return expected size and first element.
	 */
	@Test
	public void testReadCourses_validFile() {
		booking.readCourses("course.csv");
		// Check size of the course list. Expect 7.
		assertEquals(7, booking.getCourses().size());
		// Check the first course attributes
		assertEquals("Java programming", booking.getCourses().get(0).getName());
		assertEquals("120", booking.getCourses().get(0).getCapacity());
		assertEquals("Year 1", booking.getCourses().get(0).getYear());
		assertEquals("Face-to-face", booking.getCourses().get(0).getDelivery());
		assertEquals("Wednesday", booking.getCourses().get(0).getDay());
		assertEquals("11:30", booking.getCourses().get(0).getTime());
		assertEquals("2", booking.getCourses().get(0).getDuration());
	}
}
