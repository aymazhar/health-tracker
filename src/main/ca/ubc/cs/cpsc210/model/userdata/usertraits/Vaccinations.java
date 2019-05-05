package ca.ubc.cs.cpsc210.model.userdata.usertraits;

/* Vaccination information: name of vaccination and date received
   information: name and date
   behaviors: add, set, get
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Vaccinations implements Serializable {

    private String vacName;
    private Date vacDate;

    //REQUIRES: non-empty string
    //MODIFIES: this
    //EFFECTS: creates vaccination with name and date
    public Vaccinations(String vacName) {
        this.vacName = vacName;
    }

    //EFFECTS: gets vaccination name
    public String getVacName() {
        return vacName;
    }

    //EFFECTS: gets vaccination date
    public Date getVacDate() {
        return vacDate;
    }

    //REQUIRES: non-empty string
    //MODIFIES: this
    //EFFECTS: set vaccination name
    public void setVacName(String newVacName) {
        this.vacName =  newVacName;
    }

    //REQUIRES: date != null, (year, month, date must be valid integers)
    //MODIFIES: this
    //EFFECTS: sets vaccination date
    public void setVacDate(int year, int month, int date) {
        Calendar vaccal = Calendar.getInstance();
        vaccal.clear();
        vaccal.set(year, month, date);
        this.vacDate = vaccal.getTime();

    }

    //EFFECTS: compares two vaccinations
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vaccinations that = (Vaccinations) o;
        if (vacName.equals(that.vacName)) {
            return vacDate.equals(that.vacDate);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacName, vacDate);
    }

    public StringProperty vacName() {
        return new SimpleStringProperty(this.vacName);
    }

    public StringProperty vacDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return new SimpleStringProperty(sdf.format(this.vacDate));
    }


}
