package ca.ubc.cs.cpsc210.model.userdata.usertraits;

import java.io.Serializable;

public class Medications extends MVEntry implements Serializable {

    //REQUIRES: non-empty strings, doses and frequencies >=0
    //MODIFIES: this
    //EFFECTS: creates medication entry with med name, what it treats, dose, frequency, status (currently taking or not)
    public Medications(String mvName, String mvTreats, String mvDose, int mvPerDay, boolean mvStatus) {
        super(mvName, mvTreats, mvDose, mvPerDay, mvStatus);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

