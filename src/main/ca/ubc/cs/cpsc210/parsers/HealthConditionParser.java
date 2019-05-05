package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.userdata.usertraits.HealthConditions;
import org.json.JSONObject;

public class HealthConditionParser {
    public static HealthConditions hcparse(JSONObject hro) {
        String diag = hro.getString("diagnosis");
        int day = hro.getInt("day");
        int month = hro.getInt("month");
        int year = hro.getInt("year");
        String comment = hro.getString("comments");
        HealthConditions hc1 = new HealthConditions(diag, comment);
        hc1.setDiagnosisDate(year,month,day);

        return hc1;
    }

}
