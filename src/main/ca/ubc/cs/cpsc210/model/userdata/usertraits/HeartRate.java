package ca.ubc.cs.cpsc210.model.userdata.usertraits;

//Represents heart rate values for specific dates, with comments

import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class HeartRate implements Serializable {
    private int restinghr;                    //resting heart rate value
    private Date hrMeasurementDate;     //heart rate measurement date
    private String hrComment;                 //comment for HR value


    //REQUIRES: non-zero resting hr
    //MODIFIES: this
    //EFFECTS: creates heart rate object with heart rate and comment
    public HeartRate(int restinghr, String hrComment) throws InvalidMeasurement {
        if (restinghr == 0){
            throw new InvalidMeasurement();
        }
        this.restinghr = restinghr;
        this.hrComment = hrComment;
    }

    //EFFECTS: gets resting heart rate
    public int getRestinghr() {
        return restinghr;
    }

    //EFFECTS: gets resting heart rate measurement date
    public Date getHrMeasurementDate() {
        return hrMeasurementDate;
    }

    //EFFECTS: gets resting heart rate measurement comment
    public String getHrComment() {
        return hrComment;
    }

    //REQUIRES: non-zero resting hr
    //MODIFIES: this
    //EFFECTS: sets resting hr
    public void setRestinghr(int restinghr) throws InvalidMeasurement {
        if (restinghr == 0){
            throw new InvalidMeasurement();
        }
        this.restinghr = restinghr;
    }

    //REQUIRES: date != null, (year month date must be valid integers)
    //MODIFIES: this
    //EFFECTS: sets resting hr measurement date
    public void setHrMeasurementDate(int year, int month, int date) {
        Calendar hrcal = Calendar.getInstance();
        hrcal.clear();
        hrcal.set(year,month,date);
        this.hrMeasurementDate = hrcal.getTime();
    }


    //MODIFIES: this
    //EFFECTS: sets resting hr measurement comment
    public void setHrComment(String hrComment) {
        this.hrComment = hrComment;
    }

    //EFFECTS: compares two heart rate objects, returns true if the same,  false otherwise

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HeartRate heartRate = (HeartRate) o;
        if (restinghr == heartRate.restinghr) {
            if (hrMeasurementDate.equals(heartRate.hrMeasurementDate)) {
                return Objects.equals(hrComment, heartRate.hrComment);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(restinghr, hrMeasurementDate, hrComment);
    }



    public IntegerProperty hrVal() {
        return new SimpleIntegerProperty(this.restinghr);
    }

    public StringProperty hrDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return new SimpleStringProperty(sdf.format(this.hrMeasurementDate));
    }

    public StringProperty hrComment() {
        return new SimpleStringProperty(this.hrComment);
    }


}
