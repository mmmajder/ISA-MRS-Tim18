package mrsa.tim018.service.comparators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class WeekComparator  implements Comparator<LocalDate> {

    @Override
    public int compare(LocalDate o1, LocalDate o2) {
        int result = getWeekOfYear(o1) - getWeekOfYear(o2);
        if (result == 0) {
            result = o1.compareTo(o2);
        }
        return result;
    }
    
    protected static int getWeekOfYear(LocalDate date) {
    	ZoneId defaultZoneId = ZoneId.systemDefault();
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(date.atStartOfDay(defaultZoneId).toInstant()));
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

}