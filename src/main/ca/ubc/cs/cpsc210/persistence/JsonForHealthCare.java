package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.model.userdata.userprofile.UserProfile;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;


public class JsonForHealthCare {


    // EFFECTS: returns JSONObject representing blood pressure
    public static JSONObject bptojson(BloodPressure bp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(bp.getBpMeasurementDate());
        JSONObject bptojson = new JSONObject();

        bptojson.put("systolic value",bp.getSystolic());
        bptojson.put("diastolic value",bp.getDiastolic());
        bptojson.put("day",cal.get(Calendar.DAY_OF_MONTH));
        bptojson.put("month",cal.get(Calendar.MONTH));
        bptojson.put("year",cal.get(Calendar.YEAR));
        bptojson.put("comments",bp.getBpComment());

        return bptojson;
    }

    // EFFECTS: returns JSONObject representing health condition
    public static JSONObject hctojson(HealthConditions hc) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(hc.getDiagnosisDate());
        JSONObject  hctojson = new JSONObject();

        hctojson.put("diagnosis",hc.getDiagnosisName());
        hctojson.put("day",cal.get(Calendar.DAY_OF_MONTH));
        hctojson.put("month",cal.get(Calendar.MONTH));
        hctojson.put("year",cal.get(Calendar.YEAR));
        hctojson.put("comments",hc.getDiagnosisComment());

        return hctojson;
    }

    // EFFECTS: returns JSONObject representing heart rate
    public static JSONObject hrtojson(HeartRate hr) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(hr.getHrMeasurementDate());
        JSONObject hrtojson = new JSONObject();

        hrtojson.put("heart rate",hr.getRestinghr());
        hrtojson.put("day",cal.get(Calendar.DAY_OF_MONTH));
        hrtojson.put("month",cal.get(Calendar.MONTH));
        hrtojson.put("year",cal.get(Calendar.YEAR));
        hrtojson.put("comments",hr.getHrComment());

        return hrtojson;
    }

    // EFFECTS: returns JSONObject representing medication
    public static JSONObject medtoJson(Medications m) {
        Calendar start = Calendar.getInstance();
        start.setTime(m.getmvDateStart());
        Calendar stop = Calendar.getInstance();
        stop.setTime(m.getmvDateStop());
        JSONObject medtojson = new JSONObject();
        medtojson.put("medication name",m.getmvName());
        medtojson.put("treatment",m.getmvTreats());
        medtojson.put("dose",m.getmvDose());
        medtojson.put("dose frequency",m.getmvPerDay());
        medtojson.put("is currently taking",m.getmvStatus());
        medtojson.put("sday",start.get(Calendar.DAY_OF_MONTH));
        medtojson.put("smonth",start.get(Calendar.MONTH));
        medtojson.put("syear",start.get(Calendar.YEAR));
        medtojson.put("stopday",stop.get(Calendar.DAY_OF_MONTH));
        medtojson.put("stopmonth",stop.get(Calendar.MONTH));
        medtojson.put("stopyear", stop.get(Calendar.YEAR));
        return medtojson;
    }

    // EFFECTS: returns JSONObject representing a vitamin
    public static JSONObject vittojson(Vitamins v) {
        Calendar start = Calendar.getInstance();
        start.setTime(v.getmvDateStart());
        Calendar stop = Calendar.getInstance();
        stop.setTime(v.getmvDateStop());
        JSONObject vittojson = new JSONObject();

        vittojson.put("vitamin name",v.getmvName());
        vittojson.put("treatment",v.getmvTreats());
        vittojson.put("dose",v.getmvDose());
        vittojson.put("dose frequency",v.getmvPerDay());
        vittojson.put("is currently taking",v.getmvStatus());
        vittojson.put("sday",start.get(Calendar.DAY_OF_MONTH));
        vittojson.put("smonth",start.get(Calendar.MONTH));
        vittojson.put("syear",start.get(Calendar.YEAR));
        vittojson.put("stopday",stop.get(Calendar.DAY_OF_MONTH));
        vittojson.put("stopmonth",stop.get(Calendar.MONTH));
        vittojson.put("stopyear", stop.get(Calendar.YEAR));
        return vittojson;
    }

    // EFFECTS: returns JSONObject representing a vaccination
    public static JSONObject vactojson(Vaccinations v) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(v.getVacDate());
        JSONObject vactojson = new JSONObject();

        vactojson.put("vaccination name",v.getVacName());
        vactojson.put("day",cal.get(Calendar.DAY_OF_MONTH));
        vactojson.put("month",cal.get(Calendar.MONTH));
        vactojson.put("year",cal.get(Calendar.YEAR));
        return vactojson;
    }


    // EFFECTS: returns JSONArray representing a list of medications
    public static JSONArray medlisttojson(UserProfile u) {
        JSONArray medsArray = new JSONArray();

        for (Medications m: u.getMedList()) {
            medsArray.put(medtoJson(m));
        }

        return medsArray;
    }

    // EFFECTS: returns JSONArray representing a list of blood pressure measurements
    public static JSONArray bplisttojson(UserProfile u) {
        JSONArray bpArray = new JSONArray();

        for (BloodPressure bp: u.getBPList()) {
            bpArray.put(bptojson(bp));
        }
        return bpArray;
    }

    // EFFECTS: returns JSONArray representing a list of heart rate measurements
    public static JSONArray hrlisttojson(UserProfile u) {
        JSONArray hrArray = new JSONArray();

        for (HeartRate hr: u.getHRList()) {
            hrArray.put(hrtojson(hr));
        }
        return hrArray;
    }

    // EFFECTS: returns JSONArray representing a list of health conditions
    public static JSONArray hclisttojson(UserProfile u) {
        JSONArray hcArray = new JSONArray();

        for (HealthConditions hc: u.getHCList()) {
            hcArray.put(hctojson(hc));
        }
        return hcArray;
    }

    // EFFECTS: returns JSONArray representing a list of vitamins
    public static JSONArray vitlisttojson(UserProfile u) {
        JSONArray vitArray = new JSONArray();

        for (Vitamins v: u.getVitList()) {
            vitArray.put(vittojson(v));
        }
        return vitArray;
    }

    // EFFECTS: returns JSONArray representing a list of vaccinations
    public static JSONArray vaclisttojson(UserProfile u) {
        JSONArray vacArray = new JSONArray();

        for (Vaccinations v: u.getVacList()) {
            vacArray.put(vactojson(v));
        }
        return vacArray;
    }

    // EFFECTS: returns JSONObject representing a user profile
    public static JSONObject usertojson(UserProfile u) {
        JSONObject usertojson = new JSONObject();

        usertojson.put("first name",u.getFirstName());
        usertojson.put("last name",u.getLastName());
        usertojson.put("age",u.getAge());
        usertojson.put("height",u.getHeight());
        usertojson.put("weight",u.getWeight());
        usertojson.put("waist circumference",u.getWaistCirc());
        usertojson.put("heart rate measures", hrlisttojson(u));
        usertojson.put("blood pressure measures", bplisttojson(u));
        usertojson.put("health conditions", hclisttojson(u));
        usertojson.put("medications", medlisttojson(u));
        usertojson.put("vitamins", vitlisttojson(u));
        usertojson.put("vaccinations", vaclisttojson(u));

        return usertojson;
    }




}


