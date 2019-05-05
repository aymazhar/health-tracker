package ca.ubc.cs.cpsc210.model.userdata.usertraits;

import java.io.Serializable;

public class Vitamins extends MVEntry implements Serializable {

    //REQUIRES: non-empty strings, doses and frequencies >=0
    //MODIFIES: this
    //EFFECTS: creates vitamin entry with name, what it treats, dose, frequency, status (currently taking or not)
    public Vitamins(String mvName, String mvTreats, String mvDose, int mvPerDay, boolean mvStatus) {
        super(mvName, mvTreats, mvDose, mvPerDay, mvStatus);
    }

    //EFFECTS: compares two vitamins
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

