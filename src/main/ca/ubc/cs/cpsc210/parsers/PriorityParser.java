package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.reminders.Priority;
import org.json.JSONObject;

public class PriorityParser {

    public static Priority priorityparse(JSONObject p) {
        JSONObject priority = (JSONObject) p.get("priority");
        Priority newp = new Priority();
        newp.setUrgent(priority.getBoolean("urgent"));
        newp.setImportant(priority.getBoolean("important"));
        return newp;
    }
}
