package ca.ubc.cs.cpsc210.model.reminders;

//CODE SET UP COURTESY OF UBC CPSC 210 - STANDARD PROJECT
//IMPLEMENTATION OF METHODS BY STUDENT

import javafx.beans.property.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;


// Represents a Task having a description, status, priorities, set of tags and due date.
public class Task implements Serializable {
    private static final DueDate NO_DUE_DATE = null;
    private String description;
    private Priority priority;
    private DueDate duedate;
    private Set<Tag> tags;
    private Status status;


    // REQUIRES: description is non-empty
    // MODIFIES: this
    // EFFECTS: constructs a task with the given description
    //    parses the description to extract meta-data (i.e., tags, status, priority and deadline).
    //    If description does not contain meta-data, the task is set to have no due date,
    //    status of 'To Do', and default priority level (i.e., not important nor urgent)
    public Task(String description) {
        this.description = description;
        this.duedate = NO_DUE_DATE;
        this.status = Status.TODO;
        this.priority = new Priority();
        this.tags = new HashSet<>();

    }

    // REQUIRES: name is non-empty
    // MODIFIES: this
    // EFFECTS: creates a tag with name tagName and adds it to this task
    // Note: no two tags are to have the same name
    public void addTag(String tagName) {
        Tag newTag = new Tag(tagName);
        tags.add(newTag);
    }

    // REQUIRES: name is non-empty
    // MODIFIES: this
    // EFFECTS: removes the tag with name tagName from this task
    public void removeTag(String tagName) {
        ArrayList<Tag> newlist = new ArrayList<>();
        for (Tag t : tags) {
            if (t.getName().equals(tagName)) {
                newlist.add(t);
            }
        }
        for (Tag l : newlist) {
            tags.remove(l);
        }
    }

    // EFFECTS: returns an unmodifiable set of tags
    public Set<Tag> getTags() {
        return this.tags;
    }

    // EFFECTS: returns the priority of this task
    public Priority getPriority() {
        return priority;
    }

    // REQUIRES: priority != null
    // MODIFIES: this
    // EFFECTS: sets the priority of this task
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    // EFFECTS: returns the status of this task
    public Status getStatus() {
        return this.status;
    }

    // REQUIRES: status != null
    // MODIFIES: this
    // EFFECTS: sets the status of this task
    public void setStatus(Status status) {
        this.status = status;

    }

    // EFFECTS: returns the description of this task
    public String getDescription() {
        return this.description;
    }

    // REQUIRES: description is non-empty
    // MODIFIES: this
    // EFFECTS:  sets the description of this task
    //     parses the description to extract meta-data (i.e., tags, status, priority and deadline).
    public void setDescription(String description) {
        this.description = description;
    }

    // EFFECTS: returns the due date of this task
    public DueDate getDueDate() {
        return this.duedate;
    }

    // MODIFIES: this
    // EFFECTS: sets the due date of this task
    public void setDueDate(DueDate dueDate) {
        this.duedate = dueDate;
    }

    // EFFECTS: returns true if task contains a tag with tagName,
    //     returns false otherwise
    public boolean containsTag(String tagName) {
        for (Tag t : this.tags) {
            if (t.getName().equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns a string representation of this task in the following format
    //    {
    //        Description: Read collaboration policy of the term project
    //        Due date: Sat Feb 2 2019 11:59
    //        Status: IN PROGRESS
    //        Priority: IMPORTANT & URGENT
    //        Tag: #cpsc210, #project
    //    }
    @Override
    public String toString() {
        String des = "Description: " + this.description;
        String dd = "Due date: " + this.duedate.toString();
        String stat = "Status: " + this.status.toString();
        String pri = "Priority: " + this.priority.toString();
        String tg = "Tag: ";
        List<String> list = new ArrayList<>();
        for (Tag t : this.tags) {
            list.add(t.toString());
        }
        list.sort(String.CASE_INSENSITIVE_ORDER);
        for (String s : list) {
            if (list.indexOf(s) == (list.size() - 1)) {
                tg = tg.concat(s);
            } else {
                tg = tg.concat(s + ", ");
            }
        }
        return des + "\n" + dd + "\n" + stat + "\n" + pri + "\n" + tg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        if (description.equals(task.description)) {
            if (priority.equals(task.priority)) {
                if (Objects.equals(duedate, task.duedate)) {
                    return status == task.status;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, priority, duedate, status);
    }


    //EFFECTS: returns new string property of description
    public StringProperty taskDesc() {
        return new SimpleStringProperty(this.getDescription());
    }

    //EFFECTS: returns new string property of priority
    public StringProperty taskPriority() {
        return new SimpleStringProperty(this.getPriority().toString());
    }

    //EFFECTS: returns new string property of task
    public StringProperty taskDD() {
        return new SimpleStringProperty(this.getDueDate().toString());
    }

    //EFFECTS: returns new string property of task status
    public StringProperty taskStat() {
        return new SimpleStringProperty(this.getStatus().toString());
    }

    //EFFECTS: returns new string property of task tag
    public StringProperty taskTag() {
        return new SimpleStringProperty(this.getTags().toString());
    }

}
