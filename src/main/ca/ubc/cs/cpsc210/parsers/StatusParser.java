package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.reminders.Status;
import org.json.JSONObject;

public class StatusParser {

    public static Status statusparser(JSONObject s) {
        String description = s.getString("status");
        return Status.valueOf(description);
    }
}


