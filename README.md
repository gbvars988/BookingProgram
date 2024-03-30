## Booking Program
BookingProgram is a simple booking system program where students can search, view, enrol into and withdraw from courses at a University. Students can enrol into a course by searching for a course by keyword (e.g. algorithms), the program will list all courses with the matching input (case insensitive), and the student can choose to enrol into one from the list. Once a course is selected, the course will be added to the students course list and they can continue to enrol into additional courses, display their currently enrolled courses or withdraw from a course. 

**The program contains 5 classes**:

*Main.java*: This class serves as the entry point into the program, containing the main method.

*Course.java*:This class stores attributes of each course. 

*Student.java*: This class stores information about the student and an ArrayList of Course objects to storecurrently enrolled courses.

*Booking.java*: This class provides the functionality and logic of the booking system via a main menu. The Booking class has a Student object and the methods call methods from the student class based on user input. The class also has an ArrayList of Course objects of which all available courses are read in from a provided csv file.

*MenuException.java*:This class extends the java Exception class and is used for custom exceptions involving invalid user input i.e. Out of range, or empty input.


**There are also 2 test classes utilising JUnit**:

*BookingTest.java,    StudentTest.java*

### Compile and Run

1. Set JAVA_HOME environment variable in your system
2. Download source code and navigate to root directory
3. To compile, run command: 

`javac ./src/bookingprogram/Booking.java ./src bookingprogram/Course.java ./src/bookingprogram/Student.java ./src/bookingprogram/MenuException.java ./src/bookingprogram/Main.java`

4. Then run the command:

`java -cp ./src bookingprogram.Main`
