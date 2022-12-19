package facets.util;

public class Timestamp implements Comparable<Timestamp> {
	
	private String year;
	private String month;
	private String day;
	
	private String hour;
	private String minute;
	private String second;
	
	public Timestamp(String timestamp) {
		super();
		//YYYYMMDD hh:mm:ss
		timestamp = timestamp.replace(":", "");
		timestamp = timestamp.replace(" ", "");
		//YYYYMMDDhhmmss 
		//substring inlude l'indice di inizio e esclude quello di fine
		this.year = timestamp.substring(0, 4);
		this.month = timestamp.substring(4, 6);
		this.day = timestamp.substring(6, 8);
		this.hour = timestamp.substring(8, 10);
		this.minute = timestamp.substring(10, 12);
		this.second = timestamp.substring(12, 14);
	}
	
	public Timestamp(String year, String month, String day, String hour, String minute, String second) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public int getYear() {
		return Integer.parseInt(year);
	}

	public int getMonth() {
		return Integer.parseInt(month);
	}

	public int getDay() {
		return Integer.parseInt(day);
	}

	public int getHour() {
		return Integer.parseInt(hour);
	}

	public int getMinute() {
		return Integer.parseInt(minute);
	}

	public int getSecond() {
		return Integer.parseInt(second);
	}
	
	@Override
	public String toString() {
		return year + month + day + " " + hour + ":" + minute + ":" + second;
	}

	@Override
	public int compareTo(Timestamp other) {
		
		int diffYear = getYear() - other.getYear();
		int diffMonth = getMonth() - other.getMonth();
		int diffDay = getDay() - other.getDay();
		int diffHour = getHour() - other.getHour();
		int diffMinute = getMinute() - other.getMinute();
		int diffSecond = getSecond() - other.getSecond();
		
		return diffYear*365*24*60*60 + diffMonth*365/12*24*60*60 + diffDay*24*60*60 + diffHour*60*60 + diffMinute*60 + diffSecond ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (obj == null) return false;
		
		if (getClass() != obj.getClass()) return false;
		
		Timestamp other = (Timestamp) obj;
		return compareTo(other) == 0;
	}
	
	
	

}
