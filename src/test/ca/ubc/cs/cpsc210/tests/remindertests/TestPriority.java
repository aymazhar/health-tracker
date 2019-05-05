package ca.ubc.cs.cpsc210.tests.remindertests;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.reminders.Priority;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.HeartRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestPriority {
    private Priority newp;

    @BeforeEach
    void runBefore(){
        newp = new Priority();
    }

    @Test
    void testConstructor(){
        assertFalse(newp.isImportant());
        assertFalse(newp.isUrgent());

    }

    @Test
    void testConstructorWithParams(){
        Priority tcwp1 = new Priority(1);
        assertTrue(tcwp1.isImportant());
        assertTrue(tcwp1.isUrgent());

        Priority tcwp2 = new Priority(2);
        assertTrue(tcwp2.isImportant());
        assertFalse(tcwp2.isUrgent());

        Priority tcwp3 = new Priority(3);
        assertTrue(tcwp3.isUrgent());
        assertFalse(tcwp3.isImportant());

        Priority tcwp4 = new Priority(4);
        assertFalse(tcwp4.isImportant());
        assertFalse(tcwp4.isUrgent());

    }

    @Test
    void testSetImportant(){
        newp.setImportant(true);
        assertTrue(newp.isImportant());
        assertFalse(newp.isUrgent());
        newp.setImportant(false);
        assertFalse(newp.isImportant());
    }

    @Test
    void testSetUrgent(){
        newp.setUrgent(true);
        assertTrue(newp.isUrgent());
        assertFalse(newp.isImportant());
        newp.setUrgent(false);
        assertFalse(newp.isUrgent());
    }

    @Test
    void testToString(){
        Priority tcwp1 = new Priority(1);
        assertTrue(tcwp1.isImportant());
        assertTrue(tcwp1.isUrgent());
        assertEquals("IMPORTANT & URGENT", tcwp1.toString());

        Priority tcwp2 = new Priority(2);
        assertTrue(tcwp2.isImportant());
        assertFalse(tcwp2.isUrgent());
        assertEquals("IMPORTANT", tcwp2.toString());

        Priority tcwp3 = new Priority(3);
        assertTrue(tcwp3.isUrgent());
        assertFalse(tcwp3.isImportant());
        assertEquals("URGENT", tcwp3.toString());

        Priority tcwp4 = new Priority(4);
        assertFalse(tcwp4.isImportant());
        assertFalse(tcwp4.isUrgent());
        assertEquals("DEFAULT", tcwp4.toString());

    }

    @Test
    void testPrioritySame(){
        assertEquals(newp, newp);
        Priority newpri = new Priority();
        assertEquals(newp, newpri);
    }

    @Test
    void testPriorityNotSame() throws InvalidMeasurement {
        Priority newpri = new Priority();
        HeartRate newhr = new HeartRate(60, "resting");

        assertNotEquals(newp,null);
        assertNotEquals(newp, newhr);

        newpri.setUrgent(true);
        assertNotEquals(newp, newpri);

        newpri.setImportant(true);
        assertNotEquals(newp, newpri);

        newpri.setUrgent(false);
        assertNotEquals(newp, newpri);

        newp.hashCode();
    }



}
