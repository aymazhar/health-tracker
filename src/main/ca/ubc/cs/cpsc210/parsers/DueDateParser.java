package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.reminders.DueDate;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class DueDateParser {

    public static DueDate duedateparse(JSONObject p) {
        JSONObject duedate = (JSONObject) p.get("duedate");
        int day = duedate.getInt("day");
        int month = duedate.getInt("month");
        int year = duedate.getInt("year");
        int hour = duedate.getInt("hour");
        int mins = duedate.getInt("mins");
        Calendar newcal = Calendar.getInstance();
        newcal.clear();
        newcal.set(year,month,day,hour,mins);
        DueDate newdd = new DueDate(newcal.getTime());
        return newdd;
    }
}
