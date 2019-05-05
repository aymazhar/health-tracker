package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.HeartRate;
import org.json.JSONObject;

public class HeartRateParser {

    public static HeartRate hrparse(JSONObject hro) throws InvalidMeasurement {
        int hr = hro.getInt("heart rate");
        int day = hro.getInt("day");
        int month = hro.getInt("month");
        int year = hro.getInt("year");
        String comment = hro.getString("comments");
        HeartRate hr1 = new HeartRate(hr, comment);
        hr1.setHrMeasurementDate(year,month,day);

        return hr1;
    }
}
