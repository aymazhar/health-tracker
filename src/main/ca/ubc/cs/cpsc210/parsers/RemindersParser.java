package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.reminders.Tag;
import ca.ubc.cs.cpsc210.model.reminders.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

import static ca.ubc.cs.cpsc210.parsers.DueDateParser.duedateparse;
import static ca.ubc.cs.cpsc210.parsers.PriorityParser.priorityparse;
import static ca.ubc.cs.cpsc210.parsers.StatusParser.statusparser;
import static ca.ubc.cs.cpsc210.parsers.TagParser.taglistparse;

public class RemindersParser {

    public static ArrayList<Task> parse(String input) {
        ArrayList<Task> tasklist = new ArrayList<>();
        JSONArray tasksarray = new JSONArray(input);
        for (Object object : tasksarray) {
            JSONObject taskJson = (JSONObject) object;
            Task t = new Task(taskJson.getString("description"));
            t.setPriority(priorityparse(taskJson));
            t.setDueDate(duedateparse(taskJson));
            t.setStatus(statusparser(taskJson));
            Set<Tag> tagslist = taglistparse(taskJson);
            for (Tag p: tagslist) {
                t.addTag((p.getName()));
            }
            tasklist.add(t);
        }
        return tasklist;
    }

}
