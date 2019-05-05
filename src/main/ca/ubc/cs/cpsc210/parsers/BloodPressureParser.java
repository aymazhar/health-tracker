package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.BloodPressure;
import org.json.JSONObject;

public class BloodPressureParser {

    public static BloodPressure bloodPressureParser(JSONObject hro) throws InvalidMeasurement {
        int sys = hro.getInt("systolic value");
        int dia = hro.getInt("diastolic value");
        int day = hro.getInt("day");
        int month = hro.getInt("month");
        int year = hro.getInt("year");
        String comment = hro.getString("comments");
        BloodPressure bp1 = new BloodPressure(sys, dia, comment);
        bp1.setBpMeasurementDate(year,month,day);

        return bp1;
    }
}
