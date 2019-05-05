package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.reminders.Tag;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class TagParser {

    public static Set<Tag> taglistparse(JSONObject taskinput) {
        JSONArray tagarray = taskinput.getJSONArray("tags");
        Set<Tag> tagslist = new HashSet<>();
        for (int i = 0; i < tagarray.length(); i++) {
            tagslist.add(tagparse(tagarray.getJSONObject(i)));
        }
        return tagslist;
    }

    public static Tag tagparse(JSONObject jtag) {
        String description = jtag.getString("name");
        return new Tag(description);
    }

}
