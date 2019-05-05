package ca.ubc.cs.cpsc210.tests.remindertests;

import ca.ubc.cs.cpsc210.model.reminders.Priority;
import ca.ubc.cs.cpsc210.model.reminders.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestTag {
    private Tag newt = new Tag("test tag");

    @BeforeEach
    void runBefore(){
    }

    @Test
    void testConstructor(){
        assertEquals("test tag", newt.getName());
    }

    @Test
    void testToString(){
        assertEquals("#test tag", newt.toString());
    }

    @Test
    void testSameTag(){
        assertEquals(newt, newt);
        Tag othertag = new Tag("test tag");
        assertEquals(newt, othertag);
    }

    @Test
    void testDiffTag(){
        assertNotEquals(newt,null);

        Tag othertag = new Tag("Meds list");
        assertNotEquals(newt, othertag);

        Priority newpri = new Priority();
        assertNotEquals(newt, newpri);

        newt.hashCode();
    }
}
