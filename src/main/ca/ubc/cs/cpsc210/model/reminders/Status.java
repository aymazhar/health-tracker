package ca.ubc.cs.cpsc210.model.reminders;

//CODE SET UP COURTESY OF UBC CPSC 210 - STANDARD PROJECT
//IMPLEMENTATION OF METHODS BY STUDENT

import java.io.Serializable;

// Represents the status of a Task
//     Status depicts the stage of Task on a Personal Kanban Board
//     https://en.wikipedia.org/wiki/Kanban_board
public enum Status implements Serializable {
    TODO("TODO"),
    UP_NEXT("UP NEXT"),
    IN_PROGRESS("IN PROGRESS"),
    DONE("DONE");
    
    private String description;
    
    // EFFECTS: sets description of Status
    Status(String description) {
        this.description = description;
    }
    
    // EFFECTS: returns description of Status
    public String getDescription() {
        return description;
    }
    
    // EFFECTS: returns description of Status
    @Override
    public String toString() {
        return description;
    }
}
