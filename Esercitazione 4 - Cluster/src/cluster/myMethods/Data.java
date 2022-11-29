package cluster.myMethods;

/**https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html*/
import java.time.LocalDate;

public class Data{
	
	public final static String DEFAULT_DAY = "NO_DAY";
	public final static String DEFAULT_MONTH = "NO_MONTH";
	public final static String DEFAULT_YEAR = "NO_YEAR";
	
	public final static int CURRENT_DAY = LocalDate.now().getDayOfMonth(); //31;
	public final static int CURRENT_MONTH = LocalDate.now().getMonthValue(); //12;
	public final static int CURRENT_YEAR = LocalDate.now().getYear(); //2022;
	
	public final static String TODAY = DataManipulationMethods.convertTo4Format(CURRENT_YEAR) + DataManipulationMethods.convertTo2Format(CURRENT_MONTH) + DataManipulationMethods.convertTo2Format(CURRENT_DAY);
	
	private String day;
	private String month;
	private String year;
	
	public Data() {
		this(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);
	}
	
	/**	/**Creates an instance of Timestamps with the Format: YYYYMMDD*/
	public Data(String date) {
		this(date.substring(6), date.substring(4, 6), date.substring(0, 4));
	}
	
	public Data(String day, String month, String year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	/* GETTERS */
	
	/**@return the day as int*/
	public int getDay() {
		return Integer.parseInt(day);
	}
	
	/**@return the month as int*/
	public int getMonth() {
		return Integer.parseInt(this.month);
	}

	/**@return the year as int*/
	public int getYear() {
		return Integer.parseInt(this.year);
	}
	
	/**@return the day as it's saved into the instance (String with (eventually) zero before)*/
	public String getRawDay() {
		return day;
	}
	
	/**@return the month as it's saved into the instance (String with (eventually) zero before)*/
	public String getRawMonth() {
		return month;
	}
	
	/**@return the year as it's saved into the instance (String with (eventually) zero before)*/
	public String getRawYear() {
		return year;
	}
	
	/**@return the static date set as default in Date() */
	public static String getDEFAULT_DATE() {
		// equivalent to return new Date().toString();
		return DEFAULT_YEAR+DEFAULT_MONTH+DEFAULT_DAY; 
	}
	
	/* SETTERS */
	
	/* ALTRI METODI */
	
	/**
	 * Returns an int representing how many years have passed since the date
	 * (Only considering the year, ignoring day and month
	 * */
	public int yearsPassed() {
		return CURRENT_YEAR - getYear();
	}
	
	/**Return true if the instance of the object data is between the two dates passed as parameter*/
	public boolean isIntoPeriod(String dataInzio, String dataFine) {
		return isIntoPeriod(new Data(dataInzio), new Data(dataFine));
	}
	
	/**Return true if the instance of the object data is between the two dates passed as parameter*/
	public boolean isIntoPeriod(Data dataInizio, Data dataFine) {
		return isAfterOrEquals(dataInizio) && isBeforeOrEquals(dataFine);
	}
	
	/**
	 * Return true if the instance of Data represent a date that came/come/will come 
	 * AFTER or IS HE SAME AS the one passed as parameter
	 * */
	public boolean isAfterOrEquals(Data data) {
		return isAfter(data) || this.equals(data);
	}
	
	/**Return true if the instance of Data represent a date that came/come/will come 
	 * BEFORE or IS THE SAME AS the one passed as parameter
	 * */
	public boolean isBeforeOrEquals(Data data) {
		return isBefore(data) || this.equals(data);
	}
	
	
	/**
	 * Return true if the instance of Data represent a date that came/come/will come 
	 * AFTER the one passed as parameter
	 * */
	public boolean isAfter(Data data) {
		return this.compareTo(data) > 0;
	}
	
	/**
	 * Return true if the instance of Data represent a date that came/come/will come 
	 * BEFORE the one passed as parameter
	 * */
	public boolean isBefore(Data data) {
		return this.compareTo(data) < 0;
	}
	
	/**
	 * Returns the number of days between the instance of the date and the date passed as parameter, 
	 * 0 if they are equal, 
	 * a negative number if the instance (this) comes before the parameter
	 * a positive number if the instance (this) comes after the parameter
	 * */
	public int compareTo(Data other) {		
		int diffYears = this.getYear() - other.getYear();
		int diffMonths = this.getMonth() - other.getMonth();
		int diffDays = this.getDay() - other.getDay();
		
		return diffYears * 365 + diffMonths * 30 + diffDays;
	}
	
	/**toString() method in the format YYYYMMDD*/
	@Override
	public String toString() {
		return year+month+day;
	}
	
	/**Controls if the the object passed by parameter coincides in value with the instance*/
	@Override
	public boolean equals(Object o) {
		
		// If the object is compared with itself then return true 
		if (o == this) {
			return true;
		}

		//Check if o is an instance of Complex or not "null instanceof [type]" also returns false */
		if (!(o instanceof Data)) {
			return false;
		}
		
		return this.compareTo((Data) o) == 0;
	}

}
