package ca.ubc.cs.cpsc210.model.userdata.userprofile;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidName;
import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.*;

import java.io.Serializable;
import java.util.*;

/* User profile with:
    Basic information: name, dob, age
    Clinical values: height, weight, waist circumference, resting heart rate, blood pressure
    List of Health Conditions
    List of Medications
    List of Vitamins
    List of Vaccinations
 */

public class UserProfile implements Serializable {

    //FIELDS FOR BASIC INFORMATION
    private String firstName;
    private String lastName;
    private int age;


    //FIELDS FOR CLINICAL VALUES
    private double height;
    private double weight;
    private double waistcirc;
    private double minimumMeasurement = 0.0;
    private ArrayList<HeartRate> hrlist;
    private ArrayList<BloodPressure> bplist;


    //FIELDS FOR USERS HEALTH CONDITIONS, MEDICATIONS, VITAMINS, VACCINATIONS
    private ArrayList<HealthConditions> hclist;
    private ArrayList<Medications> medlist;
    private ArrayList<Vitamins> vitlist;
    private ArrayList<Vaccinations> vaclist;


    //METHODS FOR USER PROFILE CONSTRUCTION AND BASIC INFORMATION
    //REQUIRES: non-empty string for names
    //MODIFIES: this
    //EFFECTS: creates a user profile with name, age
    public UserProfile(String firstName, String lastName, int age) throws InvalidName {
        if (firstName.equals("") || lastName.equals("")) {
            throw new InvalidName();
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        this.hrlist = new ArrayList<>();
        this.bplist = new ArrayList<>();
        this.hclist = new ArrayList<>();
        this.medlist = new ArrayList<>();
        this.vitlist = new ArrayList<>();
        this.vaclist = new ArrayList<>();

    }

    //METHODS FOR CLINICAL VALUES
    //REQUIRES: user height be in cm's, and be >0
    //MODIFIES: this
    //EFFECTS: sets user height
    public void setHeight(double height) throws InvalidMeasurement {
        if (height > minimumMeasurement) {
            this.height = height;
        } else {
            throw new InvalidMeasurement();
        }
    }


    //REQUIRES: user weight in lbs must be >0
    //MODIFIES: this
    //EFFECTS: sets user weight
    public void setWeight(double weight) throws InvalidMeasurement {
        if (weight > minimumMeasurement) {
            this.weight = weight;
        } else {
            throw new InvalidMeasurement();
        }
    }

    //REQUIRES: user waist-circumference in cm must be >0
    //MODIFIES: this
    //EFFECTS: sets user waist-circumference
    public void setWaistcirc(double waistcirc) throws InvalidMeasurement {
        if (waistcirc > minimumMeasurement) {
            this.waistcirc = waistcirc;
        } else {
            throw new InvalidMeasurement();
        }
    }

    //EFFECTS: gets user height
    public double getHeight() {
        return this.height;
    }

    //EFFECTS: gets user weight
    public double getWeight() {
        return this.weight;
    }

    //EFFECTS: gets user waist circ
    public double getWaistCirc() {
        return this.waistcirc;
    }

    //EFFECTS: returns first name
    public String getFirstName() {
        return this.firstName;
    }

    //EFFECTS: returns last name
    public String getLastName() {
        return this.lastName;
    }

    //EFFECTS: returns age
    public int getAge() {
        return this.age;
    }


//ADD METHODS

    // REQUIRES: bp != null
    // MODIFIES: this
    // EFFECTS: bp is added to list (if it was not already part of it)
    public void addBP(BloodPressure bp) {
        if (!bplist.contains(bp)) {
            this.bplist.add(bp);
        }
    }

    // REQUIRES: health cond != null
    // MODIFIES: this
    // EFFECTS: health cond is added to list (if it was not already part of it)
    public void addHC(HealthConditions hc) {
        if (!hclist.contains(hc)) {
            this.hclist.add(hc);
        }
    }

    // REQUIRES: heart rate != null
    // MODIFIES: this
    // EFFECTS: heart rate is added to list (if it was not already part of it)
    public void addHR(HeartRate hr) {
        if (!hrlist.contains(hr)) {
            this.hrlist.add(hr);
        }
    }

    // REQUIRES: medication != null
    // MODIFIES: this
    // EFFECTS: medication is added to list (if it was not already part of it)
    public void addMed(Medications m) {
        if (!medlist.contains(m)) {
            this.medlist.add(m);
        }
    }

    // REQUIRES: vaccination != null
    // MODIFIES: this
    // EFFECTS: vaccination is added to list (if it was not already part of it)
    public void addVac(Vaccinations v) {
        if (!vaclist.contains(v)) {
            this.vaclist.add(v);
        }
    }

    // REQUIRES: vitamin != null
    // MODIFIES: this
    // EFFECTS: vaccination is added to list (if it was not already part of it)
    public void addVit(Vitamins vit) {
        if (!vitlist.contains(vit)) {
            this.vitlist.add(vit);
        }
    }


//REMOVE METHODS

    // REQUIRES: bp != null
    // MODIFIES: this
    // EFFECTS: removes bp from this list
    public void removeBP(BloodPressure bp) {
        this.bplist.remove(bp);
    }

    // REQUIRES: health condition rate != null
    // MODIFIES: this
    // EFFECTS: removes health condition from this list
    public void removeHC(HealthConditions hc) {
        this.hclist.remove(hc);
    }

    // REQUIRES: hr != null
    // MODIFIES: this
    // EFFECTS: removes hr from this list
    public void removeHR(HeartRate hr) {
        hrlist.remove(hr);
    }

    // REQUIRES: med != null
    // MODIFIES: this
    // EFFECTS: removes med from this list
    public void removeMed(Medications m) {
        medlist.remove(m);
    }

    // REQUIRES: vac != null
    // MODIFIES: this
    // EFFECTS: removes vac from this list
    public void removeVac(Vaccinations vac) {
        vaclist.remove(vac);
    }

    // REQUIRES: vitamin != null
    // MODIFIES: this
    // EFFECTS: removes vitamin from this list
    public void removeVit(Vitamins v) {
        vitlist.remove(v);
    }


    //GET LIST SIZE METHODS
    //EFFECTS: gets bp list size
    public int bplistSize() {
        return this.bplist.size();
    }

    //EFFECTS: gets hr list size
    public int hrlistSize() {
        return this.hrlist.size();
    }

    //EFFECTS: gets med list size
    public int medlistSize() {
        return this.medlist.size();
    }

    //EFFECTS: gets vit list size
    public int vitlistSize() {
        return this.vitlist.size();
    }

    //EFFECTS: gets vaccination list size
    public int vaclistSize() {
        return this.vaclist.size();
    }

    //EFFECTS: gets health condition list size
    public int hclistSize() {
        return this.hclist.size();
    }


    //GET LIST METHODS
    //EFFECTS: gets bp list
    public ArrayList<BloodPressure> getBPList() {
        return this.bplist;
    }

    //EFFECTS: gets hr list
    public ArrayList<HeartRate> getHRList() {
        return this.hrlist;
    }

    //EFFECTS: gets med list
    public ArrayList<Medications> getMedList() {
        return this.medlist;
    }

    //EFFECTS: gets vit list
    public ArrayList<Vitamins> getVitList() {
        return this.vitlist;
    }

    //EFFECTS: gets vaccination list
    public ArrayList<Vaccinations> getVacList() {
        return this.vaclist;
    }

    //EFFECTS: gets health condition list
    public ArrayList<HealthConditions> getHCList() {
        return this.hclist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserProfile that = (UserProfile) o;
        if (age == that.age) {
            if (Double.compare(that.height, height) == 0) {
                if (Double.compare(that.weight, weight) == 0) {
                    if (Double.compare(that.waistcirc, waistcirc) == 0) {
                        return (firstName.equals(that.firstName)) && (lastName.equals(that.lastName));
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, height, weight, waistcirc);
    }
}

