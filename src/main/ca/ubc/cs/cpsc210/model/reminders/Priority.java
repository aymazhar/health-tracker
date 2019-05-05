package ca.ubc.cs.cpsc210.model.reminders;

//CODE SET UP COURTESY OF UBC CPSC 210 - STANDARD PROJECT
//IMPLEMENTATION OF METHODS BY STUDENT

import java.io.Serializable;
import java.util.Objects;

// To model priority of a task according to the Eisenhower Matrix
//     https://en.wikipedia.org/wiki/Time_management#The_Eisenhower_Method
public class Priority implements Serializable {
    /* urgent (true) and important (true) = 1
       not urgent (false) and important (true) = 2
       urgent (true) and non important (false) = 3
       not urgent (false) and not important (false) = 4
    */

    private boolean urgent;
    private boolean important;
    
    // MODIFIES: this
    // EFFECTS: construct a default priority (i.e., not important nor urgent)
    public Priority() {
        this.urgent = false;
        this.important = false;
    }
    
    // REQUIRES: 1 <= quadrant <= 4
    // MODIFIES: this
    // EFFECTS: constructs a Priority according to the value of "quadrant"
    //     the parameter "quadrant" refers to the quadrants of the Eisenhower Matrix
    public Priority(int quadrant) {
        if (quadrant == 1) {
            this.urgent = true;
            this.important = true;
        } else if (quadrant == 2) {
            this.urgent = false;
            this.important = true;
        } else if (quadrant == 3) {
            this.urgent = true;
            this.important = false;
        } else {
            this.urgent = false;
            this.important = false;
        }
    }
    
    // EFFECTS: returns the importance of Priority
    //     (i.e., whether it is "important" or not)
    public boolean isImportant() {
        return this.important;
    }
    
    // MODIFIES: this
    // EFFECTS: updates the importance of Priority
    public void setImportant(boolean important) {
        this.important = important;
    }
    
    // EFFECTS: returns the urgency of Priority
    //     (i.e., whether it is "urgent" or not)
    public boolean isUrgent() {
        return this.urgent;
    }
    
    // MODIFIES: this
    // EFFECTS: updates the urgency of Priority
    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }
    
    // EFFECTS: returns one of the four string representation of Priority
    //    "IMPORTANT & URGENT",  "IMPORTANT", "URGENT", "DEFAULT"
    @Override
    public String toString() {
        if (this.urgent && this.important) {
            return "IMPORTANT & URGENT";
        }
        if ((!this.urgent) && this.important) {
            return "IMPORTANT";
        }
        if (this.urgent) {
            return "URGENT";
        } else {
            return "DEFAULT";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Priority priority = (Priority) o;
        if (urgent == priority.urgent) {
            return important == priority.important;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(urgent, important);
    }
}