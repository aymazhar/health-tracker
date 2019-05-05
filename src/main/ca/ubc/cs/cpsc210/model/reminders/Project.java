package ca.ubc.cs.cpsc210.model.reminders;

//CODE SET UP COURTESY OF UBC CPSC 210 - STANDARD PROJECT
//IMPLEMENTATION OF METHODS BY STUDENT

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order in which tasks are added to project is preserved
public class Project implements Serializable {
    private String projdescrip;
    private ArrayList<Task> tasks;

    
    // REQUIRES: description is non-empty
    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    public Project(String description) {
        this.projdescrip = description;
        this.tasks = new ArrayList<>();

    }
    
    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    public void add(Task task) {
        if (!(tasks.contains(task))) {
            this.tasks.add(task);
        }
    }
    
    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: removes task from this project
    public void remove(Task task) {
        tasks.remove(task);
    }
    
    // EFFECTS: returns the description of this project
    public String getDescription() {
        return this.projdescrip;
    }
    
    // EFFECTS: returns an unmodifiable list of tasks in this project.
    public List<Task> getTasks() {
        return this.tasks;
    }

    // EFFECTS: returns the number of tasks in this project
    public int getNumberOfTasks() {
        return this.tasks.size();
    }
    
    // EFFECTS: returns true if every task in this project is completed
    //     returns false if this project has no tasks!
    public boolean isCompleted() {
        int counter = 0;
        for (Task t: tasks) {
            if (t.getStatus().equals(Status.DONE)) {
                counter++;
            }
        }
        return counter == this.tasks.size();
    }
    
    // REQUIRES: task != null
    // EFFECTS: returns true if this project contains the task
    public boolean contains(Task task) {
        return this.tasks.contains(task);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return projdescrip.equals(project.projdescrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projdescrip);
    }
}