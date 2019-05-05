package ca.ubc.cs.cpsc210.model.reminders;

//CODE SET UP COURTESY OF UBC CPSC 210 - STANDARD PROJECT
//IMPLEMENTATION OF METHODS BY STUDENT

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Objects;

// Note: Any library in JDK 8 may be used to implement this class, however,
//     you must not use any third-party library in your implementation
// Hint: Explore https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html

// Represents the due date of a Task
public class DueDate implements Serializable {
    private Calendar newdd;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int min;

    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline at end of day today (i.e., today at 11:59 PM)
    public DueDate() {
        newdd = Calendar.getInstance();
        this.newdd.set(Calendar.HOUR_OF_DAY,23);
        this.newdd.set(Calendar.MINUTE,59);
        this.newdd.set(Calendar.SECOND,0);
        this.newdd.set(Calendar.MILLISECOND,0);
        setCalendarEntries(newdd);

    }

    // REQUIRES: date != null
    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline of the given date
    public DueDate(Date date) {
        newdd = Calendar.getInstance();
        newdd.setTime(date);
        setCalendarEntries(newdd);

    }

    // REQUIRES: date != null
    // MODIFIES: this
    // EFFECTS: changes the due date to the given date
    public void setDueDate(Date date) {
        this.newdd.setTime(date);
        setCalendarEntries(newdd);
    }

    // REQUIRES: 0 <= hh <= 23 && 0 <= mm <= 59
    // MODIFIES: this
    // EFFECTS: changes the due time to hh:mm leaving the month, day and year the same
    public void setDueTime(int hh, int mm) {
        this.newdd.set(Calendar.HOUR_OF_DAY,hh);
        this.newdd.set(Calendar.MINUTE,mm);

    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by one day (leaving the time the same as
    //     in the original due date) based on the rules of the Gregorian calendar.
    public void postponeOneDay() {
        this.newdd.add(Calendar.DAY_OF_MONTH, 1);

    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by 7 days
    //     (leaving the time the same as in the original due date)
    //     based on the rules of the Gregorian calendar.
    public void postponeOneWeek() {
        this.newdd.add(Calendar.DAY_OF_MONTH,7);
    }

    // EFFECTS: returns the due date
    public Date getDate() {
        return this.newdd.getTime();
    }

    // EFFECTS: returns true if due date (and due time) is passed
    public boolean isOverdue() {
        Calendar rightnow = Calendar.getInstance();
        return  rightnow.after(this.newdd);
    }

    // EFFECTS: returns true if due date is at any time today, and false otherwise
    public boolean isDueToday() {
        Calendar todaysdate = Calendar.getInstance();
        int day = this.newdd.get(Calendar.DAY_OF_MONTH);
        int month = this.newdd.get(Calendar.MONTH);
        int year = this.newdd.get(Calendar.YEAR);
        int tday = todaysdate.get(Calendar.DAY_OF_MONTH);
        int tmonth = todaysdate.get(Calendar.MONTH);
        int tyear = todaysdate.get(Calendar.YEAR);
        return ((day == tday) && (month == tmonth) && (year == tyear));
    }

    // EFFECTS: returns true if due date is at any time tomorrow, and false otherwise
    public boolean isDueTomorrow() {
        Calendar todaysdate = Calendar.getInstance();
        todaysdate.add(Calendar.DAY_OF_MONTH, 1);
        int day = this.newdd.get(Calendar.DAY_OF_MONTH);
        int month = this.newdd.get(Calendar.MONTH);
        int year = this.newdd.get(Calendar.YEAR);
        int tday = todaysdate.get(Calendar.DAY_OF_MONTH);
        int tmonth = todaysdate.get(Calendar.MONTH);
        int tyear = todaysdate.get(Calendar.YEAR);
        return ((day == tday) && (month == tmonth) && (year == tyear));
    }

    // EFFECTS: returns true if due date is within the next seven days, irrespective of time of the day,
    // and false otherwise
    // Example, assume it is 8:00 AM on a Monday
    // then any task with due date between 00:00 AM today (Monday) and 11:59PM the following Sunday is due within a week
    public boolean isDueWithinAWeek() {
        Calendar endpoint = Calendar.getInstance();
        endpoint.add(Calendar.DAY_OF_MONTH, 6);
        endpoint.set(Calendar.HOUR_OF_DAY,23);
        endpoint.set(Calendar.MINUTE,59);
        Calendar todaysdate = Calendar.getInstance();
        todaysdate.set(Calendar.HOUR_OF_DAY, 0);
        todaysdate.set(Calendar.MINUTE, 0);
        return (newdd.before(endpoint) && (newdd.after(todaysdate)));
    }

    private void setCalendarEntries(Calendar cal) {
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.hour = cal.get(Calendar.HOUR_OF_DAY);
        this.min = cal.get(Calendar.MINUTE);
    }


    // EFFECTS: returns a string representation of due date in the following format
    //     day-of-week month day year hour:minute
    //  example: Sun Jan 25 2019 10:30 AM
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d YYYY h:mm a");
        return sdf.format(newdd.getTime());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DueDate dueDate = (DueDate) o;
        if (this.day == dueDate.day) {
            if (this.month == dueDate.month) {
                if (this.year == dueDate.year) {
                    if (this.hour == dueDate.hour) {
                        return this.min == dueDate.min;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.day, this.month, this.year, this.hour, this.min);
    }
}