package ca.ubc.cs.cpsc210.tests.remindertests;

import ca.ubc.cs.cpsc210.model.reminders.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static ca.ubc.cs.cpsc210.model.reminders.Status.*;
import static org.junit.jupiter.api.Assertions.*;

class TestTask {

    private Task newtask;

    @BeforeEach
    void runBefore() {
        String d = "Pick up medication";
        newtask = new Task(d);
    }

    @Test
    void testConstructor() {
        assertNull(newtask.getDueDate());
        assertEquals("Pick up medication", newtask.getDescription());
        assertEquals("TODO", newtask.getStatus().getDescription());
        assertFalse(newtask.getPriority().isUrgent());
        assertFalse(newtask.getPriority().isImportant());
    }

    @Test
    void testAddTag() {
        assertEquals(0, newtask.getTags().size());
        newtask.addTag("medications");
        assertEquals(1, newtask.getTags().size());
        newtask.addTag("refills");
        assertEquals(2, newtask.getTags().size());
    }

    @Test
    void testRemoveTag(){
        newtask.addTag("medications");
        newtask.addTag("refills");
        assertEquals(2, newtask.getTags().size());
        newtask.removeTag("refills");
        assertEquals(1,newtask.getTags().size());
        assertTrue(newtask.containsTag("medications"));
    }

    @Test
    void testSetStatus() {
        newtask.setStatus(UP_NEXT);
        assertEquals("UP NEXT", newtask.getStatus().getDescription());
        newtask.setStatus(IN_PROGRESS);
        assertEquals("IN PROGRESS", newtask.getStatus().getDescription());
        newtask.setStatus(DONE);
        assertEquals("DONE", newtask.getStatus().getDescription());
    }

    @Test
    void testSetPriority() {
        Priority np1 = new Priority(1);
        Priority np2 = new Priority(2);
        Priority np3 = new Priority(3);
        Priority np4 = new Priority(4);
        newtask.setPriority(np1);
        assertTrue(newtask.getPriority().isImportant());
        assertTrue(newtask.getPriority().isUrgent());
        newtask.setPriority(np2);
        assertTrue(newtask.getPriority().isImportant());
        assertFalse(newtask.getPriority().isUrgent());
        newtask.setPriority(np3);
        assertFalse(newtask.getPriority().isImportant());
        assertTrue(newtask.getPriority().isUrgent());
        newtask.setPriority(np4);
        assertFalse(newtask.getPriority().isImportant());
        assertFalse(newtask.getPriority().isUrgent());
    }

    @Test
    void testSetDueDate() {
        Calendar test = Calendar.getInstance();
        test.set(2020,Calendar.MARCH,20,15,30);
        Date dates = test.getTime();
        DueDate newdd = new DueDate(dates);
        newtask.setDueDate(newdd);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d YYYY h:mm a");
        assertEquals("Fri Mar 20 2020 3:30 PM", sdf.format(newdd.getDate()));
    }

    @Test
    void testContainsTag() {
        newtask.addTag("Meds");
        assertTrue(newtask.containsTag("Meds"));
        assertFalse(newtask.containsTag("Vitamins"));
    }

    @Test
    void testDescription() {
        newtask.setDescription("Get medication");
        assertEquals("Get medication", newtask.getDescription());
    }


    @Test
    void testToString() {
        newtask.addTag("meds");
        DueDate newdd = new DueDate();
        Calendar newcal = Calendar.getInstance();
        newcal.set(2019, Calendar.FEBRUARY, 15,23,59);
        Date date = newcal.getTime();
        newdd.setDueDate(date);
        newtask.setDueDate(newdd);
        String des = "Description: Pick up medication" +
                "\nDue date: Fri Feb 15 2019 11:59 PM" +
                "\nStatus: TODO" +
                "\nPriority: DEFAULT" +
                "\nTag: #meds";

        assertEquals(des, newtask.toString());

    }

    @Test
    void testToStringMoreTags() {
        newtask.addTag("meds");
        newtask.addTag("refills");
        DueDate newdd = new DueDate();
        Calendar newcal = Calendar.getInstance();
        newcal.set(2019, Calendar.FEBRUARY, 15,23,59);
        Date date = newcal.getTime();
        newdd.setDueDate(date);
        newtask.setDueDate(newdd);
        String des = "Description: Pick up medication" +
                "\nDue date: Fri Feb 15 2019 11:59 PM" +
                "\nStatus: TODO" +
                "\nPriority: DEFAULT" +
                "\nTag: #meds, #refills";

        assertEquals(des, newtask.toString());

    }

    @Test
    void testSameTask(){
        assertEquals(newtask, newtask);
        Task othertask = new Task("Pick up medication");
        assertEquals(newtask, othertask);
    }

    @Test
    void testDifPriorityTask(){
        Task othertask = new Task("Pick up medication");
        Priority newp = new Priority();
        newp.setUrgent(true);
        othertask.setPriority(newp);
        assertNotEquals(newtask, othertask);
    }

    @Test
    void testDifDuedateTask(){
        Task othertask = new Task("Pick up medication");
        DueDate newdd = new DueDate();
        othertask.setDueDate(newdd);
        assertNotEquals(newtask, othertask);
    }

    @Test
    void testDifStatusTask(){
        Task othertask = new Task("Pick up medication");
        othertask.setStatus(UP_NEXT);
        assertNotEquals(newtask, othertask);
    }


    @Test
    void testDiffTask() {
        assertNotEquals(newtask,null);

        Task othertask = new Task("Prepare meds");
        assertNotEquals(newtask, othertask);

        Priority newpri = new Priority();
        assertNotEquals(newtask, newpri);

        newtask.hashCode();
    }
}


