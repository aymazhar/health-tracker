package ca.ubc.cs.cpsc210.tests.parsertests;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidName;
import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.userprofile.UserProfile;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static ca.ubc.cs.cpsc210.parsers.BloodPressureParser.bloodPressureParser;
import static ca.ubc.cs.cpsc210.parsers.HealthConditionParser.hcparse;
import static ca.ubc.cs.cpsc210.parsers.HeartRateParser.hrparse;
import static ca.ubc.cs.cpsc210.parsers.MedicationParser.medparse;
import static ca.ubc.cs.cpsc210.parsers.UserProfileParser.parse;
import static ca.ubc.cs.cpsc210.parsers.VaccinationParser.vacparse;
import static ca.ubc.cs.cpsc210.parsers.VitaminParser.vitparse;
import static ca.ubc.cs.cpsc210.persistence.JsonForHealthCare.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestUserDataParsers {

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

    public TestUserDataParsers() throws InvalidMeasurement {
    }

    @BeforeEach
    private void runBefore() throws InvalidName, InvalidMeasurement {
        up = new UserProfile("Jane","Doe",23);
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
        v1.setVacDate(1992, 5, 12);
        v2.setVacDate(2013, 4, 23);
        bp1.setBpMeasurementDate(2019, 3, 20);
        bp2.setBpMeasurementDate(2022, 10, 11);
        hc1.setDiagnosisDate(2009, 8, 22);
        hc2.setDiagnosisDate(2029, 11, 11);
        hr1.setHrMeasurementDate(2012, 5, 12);
        hr2.setHrMeasurementDate(2009, 3, 11);
        vit1.setmvDateStart(2002, 2, 8);
        vit1.setmvDateStop(2013, 5, 23);
        vit2.setmvDateStart(2000, 2, 23);
        vit2.setmvDateStop(2013, 3, 1);
        m1.setmvDateStart(2012, 5, 12);
        m1.setmvDateStop(2013, 4, 20);
        m2.setmvDateStart(2009, 3, 11);
        m2.setmvDateStop(2020, 4, 2);
    }

    @Test
    void testBloodPressureParser() throws InvalidMeasurement {
        JSONObject jbp = bptojson(bp1);
        BloodPressure newbp = bloodPressureParser(jbp);
        assertEquals(newbp, bp1);
    }

    @Test
    void testHealthConditionParser(){
        JSONObject jbp = hctojson(hc1);
        HealthConditions newhc = hcparse(jbp);
        assertEquals(newhc, hc1);
    }

    @Test
    void testHeartRateParser() throws InvalidMeasurement {
        JSONObject jbp = hrtojson(hr1);
        HeartRate newhr = hrparse(jbp);
        assertEquals(newhr, hr1);
    }

    @Test
    void testMedParser(){
        JSONObject jbp = medtoJson(m1);
        Medications newmed = medparse(jbp);
        assertEquals(newmed, m1);
    }

    @Test
    void testVacParser(){
        JSONObject jbp = vactojson(v1);
        Vaccinations newvac = vacparse(jbp);
        assertEquals(newvac, v1);
    }

    @Test
    void testVitParser() {
        JSONObject jbp = vittojson(vit1);
        Vitamins newvit = vitparse(jbp);
        assertEquals(newvit, vit1);
    }

    @Test
    public void UserProfileParser() throws InvalidName, InvalidMeasurement {
        String newstring = usertojson(up).toString();
        UserProfile newprofile = parse(newstring);
        assertEquals(up, newprofile);
    }



}
