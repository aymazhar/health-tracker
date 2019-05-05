package ca.ubc.cs.cpsc210.model.userdata.usertraits;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

//Health Condition with name, comment, date of diagnosis
public class HealthConditions implements Serializable {
    private String diagnosisName;               //diagnosis
    private Date diagnosisDate;                 //date of diagnosis
    private String diagnosisComment;            //comments about diagnosis


    //REQUIRES: non-empty string for diagnosis name
    //MODIFIES: this
    //EFFECTS: creates a health condition with diagnosis name, date and comment
    public HealthConditions(String diagnosisName, String diagnosisComment) {
        this.diagnosisName = diagnosisName;
        this.diagnosisComment = diagnosisComment;
    }

    //EFFECTS: gets diagnosis name
    public String getDiagnosisName() {
        return diagnosisName;
    }

    //EFFECTS: gets diagnosis date
    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    //EFFECTS: gets diagnosis comment
    public String getDiagnosisComment() {
        return diagnosisComment;
    }

    //REQUIRES: non-empty string for diagnosis name
    //MODIFIES: this
    //EFFECTS: sets diagnosis name
    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    //REQUIRES: date != null, (year, month, date must be valid ints)
    //MODIFIES: this
    //EFFECTS: sets diagnosis date
    public void setDiagnosisDate(int year, int month, int date) {
        Calendar hccal = Calendar.getInstance();
        hccal.clear();
        hccal.set(year,month,date);
        this.diagnosisDate = hccal.getTime();
    }

    //MODIFIES: this
    //EFFECTS: sets diagnosis comment
    public void setDiagnosisComment(String diagnosisComment) {
        this.diagnosisComment = diagnosisComment;
    }

    //EFFECTS: compares two Health Conditions, returns true if the same,  false otherwise
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HealthConditions that = (HealthConditions) o;
        if (diagnosisName.equals(that.diagnosisName)) {
            if (diagnosisDate.equals(that.diagnosisDate)) {
                return Objects.equals(diagnosisComment, that.diagnosisComment);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diagnosisName, diagnosisDate, diagnosisComment);
    }


    public StringProperty hcName() {
        return new SimpleStringProperty(this.diagnosisName);
    }

    public StringProperty hcDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return new SimpleStringProperty(sdf.format(this.diagnosisDate));
    }

    public StringProperty hcComment() {
        return new SimpleStringProperty(this.diagnosisComment);
    }

}