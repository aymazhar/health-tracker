package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.model.reminders.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class JsonForReminders {

    // EFFECTS: returns JSON representation of Due date
    public static JSONObject duedatetojson(DueDate dd) {
        JSONObject ddtojson = new JSONObject();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dd.getDate());

        ddtojson.put("day", cal.get(Calendar.DAY_OF_MONTH));
        ddtojson.put("month", cal.get(Calendar.MONTH));
        ddtojson.put("year", cal.get(Calendar.YEAR));
        ddtojson.put("hour", cal.get(Calendar.HOUR_OF_DAY));
        ddtojson.put("mins", cal.get(Calendar.MINUTE));

        return ddtojson;
    }

    // EFFECTS: returns JSON representation of priority
    public static JSONObject prioritytojson(Priority p) {
        JSONObject prioritytojson = new JSONObject();

        prioritytojson.put("urgent", p.isUrgent());
        prioritytojson.put("important", p.isImportant());

        return prioritytojson;
    }

    // EFFECTS: returns JSON representation of tag
    public static JSONObject tagtojson(Tag t) {
        JSONObject tagtojson = new JSONObject();

        tagtojson.put("name", t.getName());

        return tagtojson;
    }

 /*   // EFFECTS: returns JSON representation of status
    public static JSONObject statustojson(Status s) {
        JSONObject statustojson = new JSONObject();
        statustojson.put("description", s.getDescription());

        return statustojson;
    }
*/

    // EFFECTS: returns JSONArray representation of tag list
    public static JSONArray tagslisttojson(Task t) {
        JSONArray taglisttojson = new JSONArray();

        for (Tag tag : t.getTags()) {
            JSONObject jsontag = tagtojson(tag);
            taglisttojson.put(jsontag);
        }

        return taglisttojson;
    }

    // EFFECTS: returns JSON representation of task
    public static JSONObject tasktojson(Task t) {
        JSONObject tasktojson = new JSONObject();

        tasktojson.put("description", t.getDescription());
        tasktojson.put("priority", prioritytojson(t.getPriority()));
        tasktojson.put("duedate", duedatetojson(t.getDueDate()));
        tasktojson.put("tags", tagslisttojson(t));
        tasktojson.put("status", t.getStatus());

        return tasktojson;
    }

    // EFFECTS: returns JSONArray representation of a list of tasks
    public static JSONArray tasklisttojson(ArrayList<Task> tasks) {
        JSONArray tasklisttojson = new JSONArray();

        for (Task t: tasks) {
            tasklisttojson.put(tasktojson(t));
        }

        return tasklisttojson;
    }



}