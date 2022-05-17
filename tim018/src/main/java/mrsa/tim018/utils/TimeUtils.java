package mrsa.tim018.utils;

import java.time.LocalDate;

public class TimeUtils {
	public static LocalDate getLocalDate(String date) {
		String[] elems = date.split("-");
		return LocalDate.of(Integer.parseInt(elems[0]), Integer.parseInt(elems[1]), Integer.parseInt(elems[2]));
	}

}
