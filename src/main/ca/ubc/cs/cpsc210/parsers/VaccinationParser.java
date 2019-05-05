package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.userdata.usertraits.Vaccinations;
import org.json.JSONObject;

public class VaccinationParser {

    public static Vaccinations vacparse(JSONObject hro) {
        String vname = hro.getString("vaccination name");
        int day = hro.getInt("day");
        int month = hro.getInt("month");
        int year = hro.getInt("year");
        Vaccinations v = new Vaccinations(vname);
        v.setVacDate(year,month,day);

        return v;
    }
}
