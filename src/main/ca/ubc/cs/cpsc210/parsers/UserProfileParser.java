package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidName;
import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.userprofile.UserProfile;
import org.json.JSONArray;
import org.json.JSONObject;

import static ca.ubc.cs.cpsc210.parsers.BloodPressureParser.bloodPressureParser;
import static ca.ubc.cs.cpsc210.parsers.HealthConditionParser.hcparse;
import static ca.ubc.cs.cpsc210.parsers.HeartRateParser.hrparse;
import static ca.ubc.cs.cpsc210.parsers.MedicationParser.medparse;
import static ca.ubc.cs.cpsc210.parsers.VaccinationParser.vacparse;
import static ca.ubc.cs.cpsc210.parsers.VitaminParser.vitparse;

public class UserProfileParser {

    public static UserProfile parse(String input) throws InvalidName, InvalidMeasurement {
        JSONObject userObject = new JSONObject(input);
        String fname = userObject.getString("first name");
        String lname = userObject.getString("last name");
        int age = userObject.getInt("age");
        double weight = userObject.getDouble("weight");
        double height = userObject.getDouble("height");
        double waistcir = userObject.getDouble("waist circumference");
        UserProfile up = new UserProfile(fname, lname, age);
        up.setWeight(weight);
        up.setHeight(height);
        up.setWaistcirc(waistcir);
        heartratelist(userObject, up);
        bplist(userObject,up);
        vitlist(userObject,up);
        medslist(userObject,up);
        vaclist(userObject,up);
        hcslist(userObject,up);
        return up;
    }

    public static void heartratelist(JSONObject userObject, UserProfile up) throws InvalidMeasurement {
        JSONArray userhr = userObject.getJSONArray("heart rate measures");
        for (Object hro : userhr) {
            JSONObject hrJson = (JSONObject) hro;
            up.addHR(hrparse(hrJson));
        }
    }

    public static void bplist(JSONObject userObject, UserProfile up) throws InvalidMeasurement {
        JSONArray userbp = userObject.getJSONArray("blood pressure measures");
        for (Object hro : userbp) {
            JSONObject bpJson = (JSONObject) hro;
            up.addBP(bloodPressureParser(bpJson));
        }
    }

    public static void medslist(JSONObject userObject, UserProfile up) {
        JSONArray usermds = userObject.getJSONArray("medications");
        for (Object hro : usermds) {
            JSONObject mdJson = (JSONObject) hro;
            up.addMed(medparse(mdJson));
        }
    }

    public static void vitlist(JSONObject userObject, UserProfile up) {
        JSONArray uservits = userObject.getJSONArray("vitamins");
        for (Object hro : uservits) {
            JSONObject vitJson = (JSONObject) hro;
            up.addVit(vitparse(vitJson));
        }
    }

    public static void vaclist(JSONObject userObject, UserProfile up) {
        JSONArray uservacs = userObject.getJSONArray("vaccinations");
        for (Object hro : uservacs) {
            JSONObject vacJson = (JSONObject) hro;
            up.addVac(vacparse(vacJson));
        }
    }

    public static void hcslist(JSONObject userObject, UserProfile up) {
        JSONArray userhcs = userObject.getJSONArray("health conditions");
        for (Object hro : userhcs) {
            JSONObject hcJson = (JSONObject) hro;
            up.addHC(hcparse(hcJson));
        }
    }



}

