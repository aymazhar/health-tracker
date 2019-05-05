package ca.ubc.cs.cpsc210.tests.remindertests;

import ca.ubc.cs.cpsc210.model.reminders.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestProject {
    private Project newp;

    @BeforeEach
    void runBefore(){
        String des = "Medication Tasks";
        newp = new Project(des);
    }

    @Test
    void testConstructor(){
        assertEquals("Medication Tasks", newp.getDescription());
    }

   @Test
   void testAddTask(){
        Task newt = new Task("pick up meds");
        newp.add(newt);
        assertEquals(1, newp.getNumberOfTasks());
    }

    @Test
    void testAddTaskInAlready(){
        Task newt = new Task("pick up meds");
        newp.add(newt);
        assertEquals(1, newp.getNumberOfTasks());
        newp.add(newt);
        newp.add(newt);
        newp.add(newt);
        assertEquals(1, newp.getNumberOfTasks());
    }


    @Test
    void testGetDescription(){
        Task newt = new Task("pick up meds");
        newp.add(newt);
        Task rem = new Task("pick up vitamins");
        newp.add(rem);
        assertEquals(2,newp.getNumberOfTasks());
        newp.remove(rem);
        assertEquals(1,newp.getNumberOfTasks());
    }

    @Test
    void testGetTasks(){
        Task newt = new Task("pick up meds");
        newp.add(newt);
        Task rem = new Task("pick up vitamins");
        newp.add(rem);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(newt);
        tasks.add(rem);
        assertEquals(tasks,newp.getTasks());
    }
    @Test
    void testisDone(){
        Task newt = new Task("pick up meds");
        newt.setStatus(Status.DONE);
        newp.add(newt);
        Task rem = new Task("pick up vitamins");
        rem.setStatus(Status.DONE);
        newp.add(rem);
        assertTrue(newp.isCompleted());
    }

    @Test
    void testisNotDone(){
        Task newt = new Task("pick up meds");
        newp.add(newt);
        Task rem = new Task("pick up vitamins");
        rem.setStatus(Status.DONE);
        newp.add(rem);
        assertFalse(newp.isCompleted());
    }

    @Test
    void testContains(){
        Task newt = new Task("pick up meds");
        Task rem = new Task("pick up vitamins");
        newp.add(newt);
        assertTrue(newp.contains(newt));
        assertFalse(newp.contains(rem));
    }

    @Test
    void testSameProj(){
        assertEquals(newp, newp);
        Project otherproj = new Project("Medication Tasks");
        assertEquals(newp, otherproj);
    }

    @Test
    void testDifProj() {
        Project otherproj = new Project("Medication to Pickup");
        assertNotEquals(newp, otherproj);

        Priority prior = new Priority();
        assertNotEquals(newp, prior);
        assertNotEquals(newp,null);

        newp.hashCode();
    }

}
