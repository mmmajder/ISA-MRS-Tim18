package mrsa.tim018.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import mrsa.tim018.model.TimeRange;

public class TimeUtils {
	
	
	public static LocalDate getLocalDate(String date) {
		String[] elems = date.split("-");
		return LocalDate.of(Integer.parseInt(elems[0]), Integer.parseInt(elems[1]), Integer.parseInt(elems[2]));
	}
	
	public static boolean isSameTimeRange(TimeRange timeRange1, TimeRange timeRange2) {
		LocalDateTime startDate1 = timeRange1.getFromDateTime();
		LocalDateTime startDate2 = timeRange1.getFromDateTime();
		LocalDateTime endDate1 = timeRange1.getFromDateTime();
		LocalDateTime endDate2 = timeRange1.getFromDateTime();
		if(startDate1.getYear() == startDate2.getYear() &&
		   startDate1.getMonth() == startDate2.getMonth()	&&
		   startDate1.getDayOfMonth() == startDate2.getDayOfMonth() &&
		   
		   endDate1.getYear() == endDate2.getYear() &&
		   endDate1.getMonth() == endDate2.getMonth() &&
		   endDate1.getDayOfMonth() == endDate2.getDayOfMonth() ) 
		{
			return true;
		}
		return false;
	}
	
	public static String FormatToString(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
		return date.format(formatter);
	}

}
