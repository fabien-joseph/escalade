package fr.fabien.escalade.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Dates {
    public List<Date> getThisWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        List<Date> dates = new ArrayList<>();
        dates.add(cal.getTime());
        for (int i = 1; i < 8; i++) {
            cal.add(Calendar.DATE, 1);
            dates.add(cal.getTime());
        }
        return dates;
    }
}
