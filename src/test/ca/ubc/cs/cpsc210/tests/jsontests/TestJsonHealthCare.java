package ca.ubc.cs.cpsc210.tests.jsontests;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidName;
import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.userprofile.UserProfile;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ubc.cs.cpsc210.persistence.JsonForHealthCare.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestJsonHealthCare {

    BloodPressure bp1 = new BloodPressure(120,80,"Normal BP");
    BloodPressure bp2 = new BloodPressure(110,60,"at rest");
    HeartRate hr1 = new HeartRate(55, "resting");
    HeartRate hr2 = new HeartRate(60, "other");
    HealthConditions hc1 = new HealthConditions("Allergy","Peanuts");
    HealthConditions hc2 = new HealthConditions("Diabetes","type 1");
    Medications m1 = new Medications("Metformin", "Diabetes", "100mg", 1, false);
    Medications m2 = new Medications("Insulin", "Diabetes", "1 unit", 1, true);
    Vaccinations v1 = new Vaccinations("Measles");
    Vaccinations v2 = new Vaccinations("Polio");
    Vitamins vit1 = new Vitamins("Multi","general","1 tablet", 2,true);
    Vitamins vit2 = new Vitamins("B12","Iron","1 tablet", 2,true);

    UserProfile up;

    public TestJsonHealthCare() throws InvalidMeasurement {
    }

    @BeforeEach
    private void runBefore() throws InvalidName, InvalidMeasurement {
        up = new UserProfile("John","Doe",20);
        up.setWeight(120);
        up.setHeight(165);
        up.setWaistcirc(38);
        up.addBP(bp1);
        up.addBP(bp2);
        up.addHR(hr1);
        up.addHR(hr2);
        up.addHC(hc1);
        up.addHC(hc2);
        up.addMed(m1);
        up.addMed(m2);
        up.addVac(v1);
        up.addVac(v2);
        up.addVit(vit1);
        up.addVit(vit2);
    }

    @BeforeEach
    void setDates() {
        v1.setVacDate(2010, 4, 3);
        v2.setVacDate(2013, 4, 23);
        bp1.setBpMeasurementDate(2019, 10, 11);
        bp2.setBpMeasurementDate(2022, 10, 11);
        hc1.setDiagnosisDate(2019, 10, 11);
        hc2.setDiagnosisDate(2029, 11, 11);
        hr1.setHrMeasurementDate(2019, 10, 11);
        hr2.setHrMeasurementDate(2009, 3, 11);
        vit1.setmvDateStart(2010, 4, 3);
        vit1.setmvDateStop(2020, 11, 1);
        vit2.setmvDateStart(2000, 2, 23);
        vit2.setmvDateStop(2013, 3, 1);
        m1.setmvDateStart(2019, 10, 11);
        m1.setmvDateStop(2020, 11, 1);
        m2.setmvDateStart(2009, 3, 11);
        m2.setmvDateStop(2020, 4, 2);
    }

    @Test
    void testBPtoJson() {
        assertEquals("Normal BP", bptojson(bp1).getString("comments"));
        assertEquals(10, bptojson(bp1).getInt("month"));
        assertEquals(11, bptojson(bp1).getInt("day"));
        assertEquals(2019, bptojson(bp1).getInt("year"));
        assertEquals(80, bptojson(bp1).getInt("diastolic value"));
        assertEquals(120, bptojson(bp1).getInt("systolic value"));
    }

    @Test
    void testHCtoJson() {
        assertEquals("Allergy", hctojson(hc1).getString("diagnosis"));
        assertEquals(10, hctojson(hc1).getInt("month"));
        assertEquals(11, hctojson(hc1).getInt("day"));
        assertEquals(2019, hctojson(hc1).getInt("year"));
        assertEquals("Peanuts", hctojson(hc1).getString("comments"));
    }

    @Test
    void testHRtoJson() {
        assertEquals(55, hrtojson(hr1).getInt("heart rate"));
        assertEquals(10, hrtojson(hr1).getInt("month"));
        assertEquals(11, hrtojson(hr1).getInt("day"));
        assertEquals(2019, hrtojson(hr1).getInt("year"));
        assertEquals("resting", hrtojson(hr1).getString("comments"));
    }

    @Test
    void testMedtoJson() {
        assertEquals("Metformin", medtoJson(m1).getString("medication name"));
        assertEquals("Diabetes", medtoJson(m1).getString("treatment"));
        assertEquals("100mg", medtoJson(m1).getString("dose"));
        assertEquals(1, medtoJson(m1).getInt("dose frequency"));
        assertEquals(false, medtoJson(m1).getBoolean("is currently taking"));
        assertEquals(11, medtoJson(m1).getInt("sday"));
        assertEquals(10, medtoJson(m1).getInt("smonth"));
        assertEquals(2019, medtoJson(m1).getInt("syear"));
        assertEquals(1, medtoJson(m1).getInt("stopday"));
        assertEquals(11, medtoJson(m1).getInt("stopmonth"));
        assertEquals(2020, medtoJson(m1).getInt("stopyear"));
    }

    @Test
    void testVittoJson() {
        assertEquals("Multi", vittojson(vit1).getString("vitamin name"));
        assertEquals("general", vittojson(vit1).getString("treatment"));
        assertEquals("1 tablet", vittojson(vit1).getString("dose"));
        assertEquals(2, vittojson(vit1).getInt("dose frequency"));
        assertEquals(true, vittojson(vit1).getBoolean("is currently taking"));
        assertEquals(3, vittojson(vit1).getInt("sday"));
        assertEquals(4, vittojson(vit1).getInt("smonth"));
        assertEquals(2010, vittojson(vit1).getInt("syear"));
        assertEquals(1, vittojson(vit1).getInt("stopday"));
        assertEquals(11, vittojson(vit1).getInt("stopmonth"));
        assertEquals(2020, vittojson(vit1).getInt("stopyear"));
    }

    @Test
    void testVactoJson() {
        assertEquals("Measles", vactojson(v1).getString("vaccination name"));
        assertEquals(3, vactojson(v1).getInt("day"));
        assertEquals(4, vactojson(v1).getInt("month"));
        assertEquals(2010, vactojson(v1).getInt("year"));
    }

    @Test
    void testMedList(){
        JSONObject med1 = medtoJson(m1);
        JSONObject med2 = medtoJson(m2);
        assertEquals(med1.toString(), medlisttojson(up).getJSONObject(0).toString());
        assertEquals(med2.toString(), medlisttojson(up).getJSONObject(1).toString());
        assertTrue(medlisttojson(up).getJSONObject(0).similar(med1));
        assertTrue(medlisttojson(up).getJSONObject(1).similar(med2));
        assertFalse(medlisttojson(up).getJSONObject(0).similar(med2));

    }

    @Test
    void testBPList(){
        JSONObject jbp1 = bptojson(bp1);
        JSONObject jbp2 = bptojson(bp2);
        assertEquals(jbp1.toString(), bplisttojson(up).getJSONObject(0).toString());
        assertEquals(jbp2.toString(), bplisttojson(up).getJSONObject(1).toString());
        assertTrue(bplisttojson(up).getJSONObject(0).similar(jbp1));
        assertTrue(bplisttojson(up).getJSONObject(1).similar(jbp2));
        assertFalse(bplisttojson(up).getJSONObject(0).similar(jbp2));
    }

    @Test
    void testHRList(){
        JSONObject jhr1 = hrtojson(hr1);
        JSONObject jhr2 = hrtojson(hr2);
        assertEquals(jhr1.toString(), hrlisttojson(up).getJSONObject(0).toString());
        assertEquals(jhr2.toString(), hrlisttojson(up).getJSONObject(1).toString());
        assertTrue(hrlisttojson(up).getJSONObject(0).similar(jhr1));
        assertTrue(hrlisttojson(up).getJSONObject(1).similar(jhr2));
        assertFalse(hrlisttojson(up).getJSONObject(0).similar(jhr2));
    }

    @Test
    void testHCList(){
        JSONObject jhc1 = hctojson(hc1);
        JSONObject jhc2 = hctojson(hc2);
        assertEquals(jhc1.toString(), hclisttojson(up).getJSONObject(0).toString());
        assertEquals(jhc2.toString(), hclisttojson(up).getJSONObject(1).toString());
        assertTrue(hclisttojson(up).getJSONObject(0).similar(jhc1));
        assertTrue(hclisttojson(up).getJSONObject(1).similar(jhc2));
        assertFalse(hclisttojson(up).getJSONObject(0).similar(jhc2));
    }

    @Test
    void testVacList(){
        JSONObject jvac1 = vactojson(v1);
        JSONObject jvac2 = vactojson(v2);
        assertEquals(jvac1.toString(), vaclisttojson(up).getJSONObject(0).toString());
        assertEquals(jvac2.toString(), vaclisttojson(up).getJSONObject(1).toString());
        assertTrue(vaclisttojson(up).getJSONObject(0).similar(jvac1));
        assertTrue(vaclisttojson(up).getJSONObject(1).similar(jvac2));
        assertFalse(vaclisttojson(up).getJSONObject(0).similar(jvac2));
    }

    @Test
    void testVitList(){
        JSONObject jvit1 = vittojson(vit1);
        JSONObject jvit2 = vittojson(vit2);
        assertEquals(jvit1.toString(), vitlisttojson(up).getJSONObject(0).toString());
        assertEquals(jvit2.toString(), vitlisttojson(up).getJSONObject(1).toString());
        assertTrue(vitlisttojson(up).getJSONObject(0).similar(jvit1));
        assertTrue(vitlisttojson(up).getJSONObject(1).similar(jvit2));
        assertFalse(vitlisttojson(up).getJSONObject(0).similar(jvit2));
    }

    @Test
    void testUserProfile() {
        JSONObject jup = usertojson(up);
        assertEquals("John", jup.get("first name"));
        assertEquals("Doe", jup.get("last name"));
        assertEquals(20, jup.get("age"));
        assertEquals(120.0, jup.get("weight"));
        assertEquals(165.0, jup.get("height"));
        assertEquals(38.0, jup.get("waist circumference"));
    }


    @Test
    void testUserProfileHRList() {
        JSONObject jup = usertojson(up);
        JSONArray hrlist = new JSONArray();
        hrlist.put(hrtojson(hr1));
        hrlist.put(hrtojson(hr2));
        assertEquals(jup.getJSONArray("heart rate measures").toString(), hrlist.toString());
    }

    @Test
    void testUserProfileHCList() {
        JSONObject jup = usertojson(up);
        JSONArray hclist = new JSONArray();
        hclist.put(hctojson(hc1));
        hclist.put(hctojson(hc2));
        assertEquals(jup.getJSONArray("health conditions").toString(), hclist.toString());
    }

    @Test
    void testUserProfileBPList() {
        JSONObject jup = usertojson(up);
        JSONArray bplist = new JSONArray();
        bplist.put(bptojson(bp1));
        bplist.put(bptojson(bp2));
        assertEquals(jup.getJSONArray("blood pressure measures").toString(), bplist.toString());
    }

    @Test
    void testUserProfileMedList() {
        JSONObject jup = usertojson(up);
        JSONArray medlist = new JSONArray();
        medlist.put(medtoJson(m1));
        medlist.put(medtoJson(m2));
        assertEquals(jup.getJSONArray("medications").toString(), medlist.toString());
    }

    @Test
    void testUserProfileVitList() {
        JSONObject jup = usertojson(up);
        JSONArray vitlist = new JSONArray();
        vitlist.put(vittojson(vit1));
        vitlist.put(vittojson(vit2));
        assertEquals(jup.getJSONArray("vitamins").toString(), vitlist.toString());
    }

    @Test
    void testUserProfileVacList() {
        JSONObject jup = usertojson(up);
        JSONArray vaclist = new JSONArray();
        vaclist.put(vactojson(v1));
        vaclist.put(vactojson(v2));
        assertEquals(jup.getJSONArray("vaccinations").toString(), vaclist.toString());
    }


}
