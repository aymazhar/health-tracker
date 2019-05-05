package ca.ubc.cs.cpsc210.model.userdata.usertraits;

/* An abstract class to represent meds and vitamins individuals have taken/are taking.
   Common properties: name, treats (treats which condition), dosage, frequency, currently taking, date started/stopped
   Common behaviors: change/set, get, for each of the above
 */

import javafx.beans.property.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class MVEntry implements Serializable {

    private String mvName;             //Name of item
    private String mvTreats;           //What the item is used for
    private String mvDose;             //What the item dosage is
    private double mvPerDay;              //How often the item is used a day
    private boolean mvStatus;          //If user is currently taking (true) or previously took the item (false)
    private UserDateRange mvDateRange;     //Range of dates user has been taking item (start, stop)


    //REQUIRES: name, treats, and dose are non-empty strings, amount per day is >0, and stop date after start date
    //MODIFIES: this
    //EFFECTS: constructs entry (med or vitamin), with name, treats, dose, amount per day, date started/stopped
    MVEntry(String mvName, String mvTreats, String mvDose, int mvPerDay, boolean mvStatus) {
        this.mvName = mvName;
        this.mvTreats = mvTreats;
        this.mvDose = mvDose;
        this.mvPerDay = mvPerDay;
        this.mvStatus = mvStatus;
        this.mvDateRange = new UserDateRange();
    }

    //EFFECTS: gets med/vit name
    public String getmvName() {
        return mvName;
    }

    //EFFECTS: gets what med/vit treats
    public String getmvTreats() {
        return mvTreats;
    }

    //EFFECTS: gets med/vit dose
    public String getmvDose() {
        return mvDose;
    }

    //EFFECTS: gets med/vit frequency of dose
    public double getmvPerDay() {
        return mvPerDay;
    }

    //EFFECTS: gets med/vit status (true if taking currently, false otherwise)
    public boolean getmvStatus() {
        return mvStatus;
    }

    //EFFECTS: gets med/vit date started
    public Date getmvDateStart() {
        return mvDateRange.getDate(0);
    }

    //EFFECTS: gets med/vit date stopped
    public Date getmvDateStop() {
        return mvDateRange.getDate(1);
    }

    //REQUIRES: non-empty string
    //MODIFIES: this
    //EFFECTS: sets med/vit name
    public void setmvName(String newName) {
        this.mvName = newName;
    }

    //REQUIRES: non-empty string
    //MODIFIES: this
    //EFFECTS: sets what med/vit treats
    public void setmvTreats(String newTreats) {
        this.mvTreats = newTreats;
    }

    //REQUIRES: non-empty string
    //MODIFIES: this
    //EFFECTS: sets med/vit dose
    public void setmvDose(String newDose) {
        this.mvDose = newDose;
    }

    //REQUIRES: non-zero double
    //MODIFIES: this
    //EFFECTS: sets med/vit dose frequency
    public void setmvPerDay(double newPerDay) {
        this.mvPerDay = newPerDay;
    }

    //MODIFIES: this
    //EFFECTS: sets med/vit status to true if currently taking, false otherwise
    public void setMvStatus(boolean newStatus) {
        this.mvStatus = newStatus;
    }

    //REQUIRES: date != null (must provide valid year, month, day)
    //MODIFIES: this
    //EFFECTS: sets med/vit start date
    public void setmvDateStart(int year, int month, int day) {
        mvDateRange.addStartDate(year, month, day);
    }

    //REQUIRES: date != null (must provide valid year, month, day)
    //MODIFIES: this
    //EFFECTS: sets med/vit stop date
    public void setmvDateStop(int year, int month, int day) {
        mvDateRange.addDateStop(year, month, day);
    }

    //REQUIRES: date range != null
    //MODIFIES: this
    //EFFECTS: sets med/vit start and stop dates
    //public void setStartAndStopDate(UserDateRange dr) {
    //  this.mvDateRange = dr;
    //}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MVEntry mvEntry = (MVEntry) o;
        if (Double.compare(mvEntry.mvPerDay, mvPerDay) == 0) {
            if (mvStatus == mvEntry.mvStatus) {
                if (mvName.equals(mvEntry.mvName)) {
                    if ((mvTreats.equals(mvEntry.mvTreats)) && (mvDose.equals(mvEntry.mvDose))) {
                        return mvDateRange.equals(mvEntry.mvDateRange);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mvName, mvTreats, mvDose, mvPerDay, mvStatus, mvDateRange);
    }


    public StringProperty mvName() {
        return new SimpleStringProperty(this.mvName);
    }

    public StringProperty mvDose() {
        return new SimpleStringProperty(this.mvDose);
    }

    public DoubleProperty mvPDay() {
        return new SimpleDoubleProperty(this.mvPerDay);
    }

    public BooleanProperty mvStatus() {
        return new SimpleBooleanProperty(this.mvStatus);
    }

    public StringProperty mvStartDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return new SimpleStringProperty(sdf.format(this.getmvDateStart()));
    }

    public StringProperty mvEndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return new SimpleStringProperty(sdf.format(this.getmvDateStop()));
    }

    public StringProperty mvTreats() {
        return new SimpleStringProperty(this.mvTreats);
    }

}