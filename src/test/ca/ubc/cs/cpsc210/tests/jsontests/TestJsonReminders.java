package ca.ubc.cs.cpsc210.tests.jsontests;

import ca.ubc.cs.cpsc210.model.reminders.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static ca.ubc.cs.cpsc210.persistence.JsonForReminders.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestJsonReminders {

    ArrayList<Task> tasks = new ArrayList<Task>();
    Task t1 = new Task("pick up meds");
    Task t2 = new Task("get blood test");
    Calendar cal = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    Priority p = new Priority();
    Priority p2 = new Priority();

    @BeforeEach
    void runBefore() {
        cal.set(2020, 10, 2, 6,30);
        Date date = cal.getTime();
        t1.setDueDate(new DueDate(date));
        t1.setPriority(p);
        p.setUrgent(true);
        t1.addTag("refill");
        t1.addTag("diabetes");
        t1.setStatus(Status.TODO);

        cal2.set(2019, 4, 12, 13,45);
        Date date2 = cal2.getTime();
        t2.setDueDate(new DueDate(date2));
        t2.setPriority(p2);
        p2.setUrgent(true);
        p2.setImportant(true);
        t2.addTag("iron");
        t2.setStatus(Status.IN_PROGRESS);
        tasks.add(t1);
        tasks.add(t2);
    }


    @Test
    void testDueDateJson() {
        assertEquals(2, duedatetojson(t1.getDueDate()).getInt("day"));
        assertEquals(10, duedatetojson(t1.getDueDate()).getInt("month"));
        assertEquals(2020, duedatetojson(t1.getDueDate()).getInt("year"));
        assertEquals(6, duedatetojson(t1.getDueDate()).getInt("hour"));
        assertEquals(30, duedatetojson(t1.getDueDate()).getInt("mins"));
    }

    @Test
    void testPriorityJson() {
        assertEquals(true, prioritytojson(t1.getPriority()).getBoolean("urgent"));
        assertEquals(false, prioritytojson(t1.getPriority()).getBoolean("important"));

    }

    @Test
    void testTagListJson() {
        assertEquals("refill", tagslisttojson(t1).getJSONObject(0).get("name"));
        assertEquals("diabetes", tagslisttojson(t1).getJSONObject(1).getString("name"));
        System.out.println(tagslisttojson(t1));
    }

    @Test
    void testTaskToJson() {
        assertEquals("pick up meds", tasktojson(t1).get("description"));
        assertEquals(prioritytojson(t1.getPriority()).toString(), tasktojson(t1).get("priority").toString());
        assertEquals(duedatetojson(t1.getDueDate()).toString(), tasktojson(t1).get("duedate").toString());
        assertEquals(tagslisttojson(t1).toString(), tasktojson(t1).get("tags").toString());
        assertEquals("TODO", tasktojson(t1).get("status").toString());

    }

    @Test
    void testTaskListToJson() {
        System.out.println(tasklisttojson(tasks));
        assertEquals("pick up meds", tasklisttojson(tasks).getJSONObject(0).get("description"));
        assertEquals("get blood test", tasklisttojson(tasks).getJSONObject(1).get("description"));
        assertEquals(prioritytojson(t1.getPriority()).toString(), tasklisttojson(tasks).getJSONObject(0).get("priority").toString());
        assertEquals(prioritytojson(t2.getPriority()).toString(), tasklisttojson(tasks).getJSONObject(1).get("priority").toString());
        assertEquals(duedatetojson(t1.getDueDate()).toString(), tasklisttojson(tasks).getJSONObject(0).get("duedate").toString());
        assertEquals(duedatetojson(t2.getDueDate()).toString(), tasklisttojson(tasks).getJSONObject(1).get("duedate").toString());
        assertEquals(tagslisttojson(t1).toString(), tasklisttojson(tasks).getJSONObject(0).get("tags").toString());
        assertEquals(tagslisttojson(t2).toString(), tasklisttojson(tasks).getJSONObject(1).get("tags").toString());
        assertEquals("TODO", tasklisttojson(tasks).getJSONObject(0).get("status").toString());
        assertEquals("IN PROGRESS", tasklisttojson(tasks).getJSONObject(1).get("status").toString());
    }
}
