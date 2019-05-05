package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.userdata.usertraits.Medications;
import org.json.JSONObject;

public class MedicationParser {

    public static Medications medparse(JSONObject hro) {
        String name = hro.getString("medication name");
        String treat = hro.getString("treatment");
        String dose = hro.getString("dose");
        int freq = hro.getInt("dose frequency");
        boolean taking = hro.getBoolean("is currently taking");
        int day1 = hro.getInt("sday");
        int month1 = hro.getInt("smonth");
        int year1 = hro.getInt("syear");
        int day2 = hro.getInt("stopday");
        int month2 = hro.getInt("stopmonth");
        int year2 = hro.getInt("stopyear");
        Medications med = new Medications(name, treat,dose,freq,taking);
        med.setmvDateStart(year1, month1, day1);
        med.setmvDateStop(year2, month2, day2);
        return med;
    }
}
