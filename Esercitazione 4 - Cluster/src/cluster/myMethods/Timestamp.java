package cluster.myMethods;

/**https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html*/
import java.time.LocalTime;

public class Timestamp extends Data implements Comparable<Timestamp>{
	
	public final static String DEFAULT_HOUR = "NO_HOUR"; 
	public final static String DEFAULT_MINUTE = "NO_MINUTE";
	public final static String DEFAULT_SECOND = "NO_SECOND";
	
	public final static int CURRENT_HOUR = LocalTime.now().getHour(); //0;
	public final static int CURRENT_MINUTE = LocalTime.now().getMinute(); //0;
	public final static int CURRENT_SECOND = LocalTime.now().getSecond(); //0;
	public final static String CURRENT_TIME = DataManipulationMethods.convertTo2Format(CURRENT_HOUR) + ":" + DataManipulationMethods.convertTo2Format(CURRENT_MINUTE) + ":" + DataManipulationMethods.convertTo2Format(CURRENT_SECOND);
	
	public final static String NOW = TODAY + " " + CURRENT_TIME;
	
	private String hour;
	private String minute;
	private String second;
	
	public Timestamp() {
		this(Data.getDEFAULT_DATE(), DEFAULT_HOUR, DEFAULT_MINUTE, DEFAULT_SECOND);
	}
	
	/**Creates an instance of Timestamps with the Format: YYYYMMDD HH:MM:SS */
	public Timestamp(String timestamp) {
		this(timestamp.substring(0,8), timestamp.substring(9));
	}
	
	public Timestamp(String data, String time) {
		this(data, time.substring(0, 2), time.substring(3, 5), time.substring(6));
	}

	public Timestamp(String data, String hour, String minute, String second) {
		super(data);
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	/* GETTERS */

	/**@return the hour as int*/
	public int getHour() {
		return Integer.parseInt(hour);
	}

	/**@return the minute as int*/
	public int getMinute() {
		return Integer.parseInt(minute);
	}

	/**@return the second as int*/
	public int getSecond() {
		return Integer.parseInt(second);
	}
	
	/**@return the hour as it's saved into the instance (String with (eventually) zero before)*/
	public String getRawHour() {
		return hour;
	}

	/**@return the minute as it's saved into the instance (String with (eventually) zero before)*/
	public String getRawMinute() {
		return minute;
	}
	
	/**@return the second as it's saved into the instance (String with (eventually) zero before)*/
	public String getRawSecond() {
		return second;
	}
	
	/**@return the static time set as default in Timestamp() */
	public static String getDEFAULT_TIME() {
		return DEFAULT_HOUR + ":" + DEFAULT_MINUTE + ":" + DEFAULT_DAY;
	}

	/**@return the static date and time set as default in Timestamp() */
	public static String getDEFAULT_TIMESTAMP() {
		return getDEFAULT_DATE() + " " + getDEFAULT_TIME();
	}
		
	/* SETTERS */
	
	/* ALTRI METODI */
	
	/**toString() method in the format YYYYMMDD HH:MM:SS*/
	@Override
	public String toString() {
		return super.toString() + " " + hour + ":" + minute + ":" + second;
	}
	
	/**
	 * Returns the number of days between the instance of the date and the date passed as parameter, 
	 * 0 if they are equal, 
	 * a negative number if the instance (this) comes before the parameter
	 * a positive number if the instance (this) comes after the parameter
	 * */
	@Override
	public int compareTo(Timestamp other) {
		int diffHours = this.getHour() - other.getHour();
		int diffMinutes = this.getMinute() - other.getMinute();
		int diffSeconds = this.getSecond() - other.getSecond();
		
		return super.compareTo(other)*24*60*60 + diffHours*60*60 + diffMinutes*60 + diffSeconds;
	}
	
	/**Controls if the the object passed by parameter coincides in value with the instance*/
	@Override
	public boolean equals(Object o) {
		
		// If the object is compared with itself then return true 
		if (o == this) {
			return true;
		}

		//Check if o is an instance of Complex or not "null instanceof [type]" also returns false */
		if (!(o instanceof Timestamp)) {
			return false;
		}
		
		if (!(super.equals(o))) {
			return false;
		}
		
		return this.compareTo((Timestamp) o) == 0;
	}
	
	
	
	
	
	
	
}
