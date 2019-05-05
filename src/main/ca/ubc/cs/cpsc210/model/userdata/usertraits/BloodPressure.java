package ca.ubc.cs.cpsc210.model.userdata.usertraits;

//Represents blood pressure with specific dates

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

public class BloodPressure implements Serializable {
    private int systolic;                       //systolic bp value
    private int diastolic;                      //diastolic bp value
    private Date bpMeasurementDate;             //bp measurement date
    private String bpComment;                   //comment for bp value


    //REQUIRES: non-zero values for systolic/diastolic
    //MODIFIES: this
    //EFFECTS: creates a blood pressure object with systolic, diastolic, and comment
    public BloodPressure(int systolic, int diastolic, String bpComment) throws InvalidMeasurement {
        if (systolic == 0 || diastolic == 0){
            throw new InvalidMeasurement();
        } else {
            this.systolic = systolic;
            this.diastolic = diastolic;
            this.bpComment = bpComment;
        }
    }

    //EFFECTS: gets systolic value
    public int getSystolic() {
        return systolic;
    }

    //EFFECTS: gets diastolic value
    public int getDiastolic() {
        return diastolic;
    }

    //EFFECTS: gets date of measurement
    public Date getBpMeasurementDate() {
        return bpMeasurementDate;
    }

    //EFFECTS: gets comment for bp
    public String getBpComment() {
        return bpComment;
    }

    //REQUIRES: non-zero values for systolic
    //MODIFIES: this
    //EFFECTS: sets systolic value
    public void setSystolic(int systolic) throws InvalidMeasurement {
        if (systolic == 0) {
            throw new InvalidMeasurement();
        }
        this.systolic = systolic;
    }

    //REQUIRES: non-zero values for diastolic
    //MODIFIES: this
    //EFFECTS: sets diastolic value
    public void setDiastolic(int diastolic) throws InvalidMeasurement {
        if (diastolic == 0) {
            throw new InvalidMeasurement();
        }
        this.diastolic = diastolic;
    }


    //MODIFIES: this
    //EFFECTS: sets comment for measurement
    public void setBpComment(String bpComment) {
        this.bpComment = bpComment;
    }

    //REQUIRES: date != null (year, month, date must be valid integers)
    //MODIFIES: this
    //EFFECTS: sets measurement date
    public void setBpMeasurementDate(int year, int month, int date) {
        Calendar bpcal = Calendar.getInstance();
        bpcal.clear();
        bpcal.set(year, month, date);
        this.bpMeasurementDate = bpcal.getTime();
    }



    //EFFECTS: compares two BP measures
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BloodPressure that = (BloodPressure) o;
        if (systolic == that.systolic) {
            if (diastolic == that.diastolic) {
                if (bpMeasurementDate.equals(that.bpMeasurementDate)) {
                    return bpComment.equals(that.bpComment);
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(systolic, diastolic, bpMeasurementDate, bpComment);
    }


    public IntegerProperty bpSys() {
        return new SimpleIntegerProperty(this.systolic);
    }

    public IntegerProperty bpDias() {
        return new SimpleIntegerProperty(this.diastolic);
    }

    public StringProperty bpDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return new SimpleStringProperty(sdf.format(this.bpMeasurementDate));
    }

    public StringProperty bpComment() {
        return new SimpleStringProperty(this.bpComment);
    }
}
