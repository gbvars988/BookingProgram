package bookingprogram;

/**
 * The Course class provides the attributes of
 * an individual course. 
 * @author ronaldho
 *
 */
public class Course {
	
	private String course_name;
	private String capacity;
	private String year;
	private String delivery_mode;
	private String lecture_day;
	private String lecture_time;
	private String lecture_duration;
	
	public Course(String name, String capacity,String year, String delivery_mode, 
			String lecture_day, String lecture_time, String lecture_duration) {
		this.course_name = name;
		this.capacity = capacity;
		this.year = year;
		this.delivery_mode = delivery_mode;
		this.lecture_day = lecture_day;
		this.lecture_time = lecture_time;
		this.lecture_duration = lecture_duration;
	}
	
	public Course(String attributes[]) {
		this.course_name = attributes[0];
		this.capacity = attributes[1];
		this.year = attributes[2];
		this.delivery_mode = attributes[3];
		this.lecture_day = attributes[4];
		this.lecture_time = attributes[5];
		this.lecture_duration = attributes[6];
	}
	
	public String getName() {
		return course_name;
	}
	
	public String getDelivery() {
		return delivery_mode;
	}
	
	public String getDay() {
		return lecture_day;
	}
	
	public String getTime() {
		return lecture_time;
	}
	
	public String getDuration() {
		return lecture_duration;
	}
	
	public String getCapacity() {
		return capacity;
	}
	
	public String getYear() {
		return year;
	}
}
