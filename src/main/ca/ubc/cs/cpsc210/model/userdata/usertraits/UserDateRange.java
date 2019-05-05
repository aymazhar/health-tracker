package ca.ubc.cs.cpsc210.model.userdata.usertraits;

import java.io.Serializable;
import java.util.*;


public class UserDateRange implements Serializable {
    private List<Date> daterange;

    //MODIFIES: this
    //EFFECTS: constructs an empty list for a pair of dates (start and stop date)
    public UserDateRange() {
        daterange = new ArrayList<>(2);
    }

    //REQUIRES: date != null, (year, month, day must be valid integers)
    //MODIFIES: this
    //EFFECTS: adds start date to list
    public void addStartDate(int year, int month, int day) {
        Calendar newDateStart = Calendar.getInstance();
        newDateStart.clear();
        newDateStart.set(year, month, day);
        Date startDate = newDateStart.getTime();
        if (daterange.isEmpty()) {
            daterange.add(startDate);
        } else if (startDate.before(daterange.get(1))) {
            daterange.set(0,startDate);
        }
    }

    //REQUIRES: date != null, (year, month, day must be valid integers)
    //MODIFIES: this
    //EFFECTS: creates a new end date and places it at index 1 if stop date is after start date,
    // else prints error
    public void addDateStop(int year, int month, int day) {
        Calendar newDateStop = Calendar.getInstance();
        newDateStop.clear();
        newDateStop.set(year, month, day);
        Date stopDate = newDateStop.getTime();
        Date startdate = daterange.get(0);

        if (stopDate.after(startdate)) {
            if (daterange.size() == 1) {
                daterange.add(stopDate);
            } else {
                daterange.set(1, stopDate);
            }
        }
    }

    //REQUIRES: index must be >= 0
    //EFFECTS: returns date at specified index
    public Date getDate(int index) {
        return daterange.get(index);
    }

    //REQUIRES: index must be >= 0
    //MODIFIES: this
    //EFFECTS: removes date at specified index
    public void removeDate(int index) {
        if (index == 0) {
            daterange.clear();
        } else {
            daterange.remove(index);
        }
    }

    //EFFECTS: returns size of list of dates
    public int getListSize() {
        return daterange.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDateRange that = (UserDateRange) o;
        return daterange.equals(that.daterange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daterange);
    }
}
