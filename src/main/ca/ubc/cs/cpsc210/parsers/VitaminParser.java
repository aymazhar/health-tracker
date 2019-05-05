package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.userdata.usertraits.Vitamins;
import org.json.JSONObject;

public class VitaminParser {

    public static Vitamins vitparse(JSONObject hro) {
        String name = hro.getString("vitamin name");
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
        Vitamins vit = new Vitamins(name, treat,dose,freq,taking);
        vit.setmvDateStart(year1, month1, day1);
        vit.setmvDateStop(year2, month2, day2);
        return vit;
    }
}
