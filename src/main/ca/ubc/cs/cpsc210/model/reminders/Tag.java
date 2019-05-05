package ca.ubc.cs.cpsc210.model.reminders;

//CODE SET UP COURTESY OF UBC CPSC 210 - STANDARD PROJECT
//IMPLEMENTATION OF METHODS BY STUDENT

// Represents a tag having a name

import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable {

    private String tagname;

    // REQUIRES: name is non-empty
    // MODIFIES: this
    // EFFECTS: creates a Tag with the given name
    public Tag(String name) {
        this.tagname = name;
    }

    // EFFECTS: returns the name of this tag
    public String getName() {
        return this.tagname;
    }

    // EFFECTS: returns the tag name preceded by #
    @Override
    public String toString() {
        return "#" + this.tagname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return tagname.equals(tag.tagname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagname);
    }
}