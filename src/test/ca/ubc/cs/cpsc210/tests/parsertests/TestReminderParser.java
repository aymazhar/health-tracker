package ca.ubc.cs.cpsc210.tests.parsertests;

import ca.ubc.cs.cpsc210.model.reminders.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static ca.ubc.cs.cpsc210.parsers.DueDateParser.duedateparse;
import static ca.ubc.cs.cpsc210.parsers.PriorityParser.priorityparse;
import static ca.ubc.cs.cpsc210.parsers.RemindersParser.parse;
import static ca.ubc.cs.cpsc210.parsers.TagParser.taglistparse;
import static ca.ubc.cs.cpsc210.persistence.JsonForReminders.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestReminderParser {

    private ArrayList<Task> tasks = new ArrayList<>();
    private Task t1 = new Task("pick up meds");
    private Task t2 = new Task("get blood test");
    private Calendar cal = Calendar.getInstance();
    private Calendar cal2 = Calendar.getInstance();
    private Priority p = new Priority();
    private Priority p2 = new Priority();

    @BeforeEach
    void runBefore() {
        cal.set(2020, Calendar.NOVEMBER, 2, 6,30);
        Date date = cal.getTime();
        t1.setDueDate(new DueDate(date));
        t1.setPriority(p);
        p.setUrgent(true);
        t1.addTag("refill");
        t1.addTag("diabetes");
        t1.setStatus(Status.TODO);

        cal2.set(2019, Calendar.MAY, 12, 13,45);
        Date date2 = cal2.getTime();
        t2.setDueDate(new DueDate(date2));
        t2.setPriority(p2);
        p2.setUrgent(true);
        t2.addTag("iron");
        t2.setStatus(Status.IN_PROGRESS);
        tasks.add(t1);
        tasks.add(t2);
    }

    @Test
    void testDueDateParser(){
        JSONObject newobj = tasktojson(t1);
        DueDate newdate = duedateparse(newobj);
        assertEquals(t1.getDueDate(),newdate);
    }

    @Test
    void testPriorityParser(){
        JSONObject newobj = tasktojson(t1);
        Priority newpriority = priorityparse(newobj);
        assertEquals(p, newpriority);
    }


    @Test
    void testTagListParser(){
        JSONObject taskjson = tasktojson(t1);
        Set<Tag> tagslist = taglistparse(taskjson);
        assertEquals(t1.getTags(), tagslist);
    }


    @Test
    void testTaskParser(){
        tasks.add(t1);
        tasks.add(t2);
        String tasklistjson = tasklisttojson(tasks).toString();
        ArrayList<Task> newtasklist = parse(tasklistjson);
        assertEquals(tasks, newtasklist);
    }



}
