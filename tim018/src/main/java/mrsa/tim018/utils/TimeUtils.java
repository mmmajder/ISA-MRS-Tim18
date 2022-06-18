package mrsa.tim018.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import mrsa.tim018.model.TimeRange;

public class TimeUtils {
	
	
	public static LocalDate getLocalDate(String date) {
		String[] elems = date.split("-");
		return LocalDate.of(Integer.parseInt(elems[0]), Integer.parseInt(elems[1]), Integer.parseInt(elems[2]));
	}
	
	public static boolean isExactSameTimeRange(TimeRange timeRange1, TimeRange timeRange2) {
		LocalDateTime startDate1 = timeRange1.getFromDateTime();
		LocalDateTime startDate2 = timeRange2.getFromDateTime();
		LocalDateTime endDate1 = timeRange1.getFromDateTime();
		LocalDateTime endDate2 = timeRange2.getFromDateTime();
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
	public static boolean isInTimeRange(TimeRange timeRange1, TimeRange timeRange2) {
		LocalDateTime startDate1 = timeRange1.getFromDateTime();
		LocalDateTime startDate2 = timeRange2.getFromDateTime();
		LocalDateTime endDate1 = timeRange1.getToDateTime();
		LocalDateTime endDate2 = timeRange2.getToDateTime();
		if((startDate1.isAfter(startDate2) || startDate1.isEqual(startDate2)) &&
			(endDate1.isBefore(endDate2)) || endDate1.isEqual(endDate2))
		{
			return true;
		}
		return false;
	}
	
	public static String formatToString(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
		return date.format(formatter);
	}
	
	public static String formatToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		return date.format(formatter);
	}
	
	public static LocalDate getLocalDate(Date date) {
		 ZoneId zone = ZoneId.of("Europe/Belgrade");
		 
		 return date.toInstant()
			      .atZone(zone)
			      .toLocalDate();
	}
	
	public static String formatYearMonth(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
	}
	
	public static String formatYear(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy"));
	}
	
	public static String formatYearMonthDay(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static LocalDate parseDateForReport(String date) {
		if ("none".equals(date)) 
			return null;
		
		return LocalDate.now();
	}

}
