package ca.ubc.cs.cpsc210.tests.patientdatatests.userprofiletests;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidName;
import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.userprofile.UserProfile;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TestUserProfile {
    private UserProfile up, newp;
    private int age = 20;


    @BeforeEach
    void runBefore() throws InvalidName, InvalidMeasurement {
        String fn = "John";
        String ln = "Doe";
        up = new UserProfile(fn, ln, age);
        up.setWaistcirc(30);
        up.setHeight(160);
        up.setWeight(103);

        newp = new UserProfile("John","Doe", 20);
        newp.setWaistcirc(30);
        newp.setHeight(160);
        newp.setWeight(103);


    }


    @Test
    void testConstructorFirstNameException() {
        try {
            up = new UserProfile("", "Doe", age);
        } catch (InvalidName f) {
            System.out.println("Error: must provide first name");
        }
    }

    @Test
    void testConstructorLastNameException() {
        try {
            up = new UserProfile("John", "", age);
        } catch (InvalidName f) {
            System.out.println("Threw name exception");
        }
    }

    @Test
    void testConstructorNormal() {
        assertEquals("John", up.getFirstName());
        assertEquals("Doe", up.getLastName());
        assertEquals(20, up.getAge());
    }


    @Test
    void testSetHeight() {
        try {
            up.setHeight(160);
        } catch (InvalidMeasurement invalidMeasurement) {
            fail("Error: threw invalid measurement exception for height");
        }
        assertEquals(160, up.getHeight());
    }

    @Test
    void testSetHeightBelowMin() {
        try {
            up.setHeight(-2.7);
        } catch (InvalidMeasurement invalidMeasurement) {
            System.out.println("Error: height must be greater than 0 cms");
        }
    }

    @Test
    void testSetWeight() {
        try {
            up.setWeight(180);
        } catch (InvalidMeasurement invalidMeasurement) {
            fail("Error: threw invalid measurement exception for weight");
        }
        assertEquals(180, up.getWeight());
    }

    @Test
    void testSetWeightBelowMin() {
        try {
            up.setWeight(-2.7);
        } catch (InvalidMeasurement invalidMeasurement) {
            System.out.println("Error: weight must be greater than 0 lbs");
        }
    }

    @Test
    void testSetWaist() {
        try {
            up.setWaistcirc(60);
        } catch (InvalidMeasurement invalidMeasurement) {
            fail("Error: threw invalid measurement exception for waist circ");
        }
        assertEquals(60, up.getWaistCirc());
    }

    @Test
    void testSetWaistBelow() {
        try {
            up.setWaistcirc(-60);
        } catch (InvalidMeasurement invalidMeasurement) {
            System.out.println("Error: waist circumference must be above 0 cm");
        }
    }



    @Test
    void testaddremoveBP() throws InvalidMeasurement {
        BloodPressure newbp = new BloodPressure(120, 80, "resting");
        up.addBP(newbp);
        assertEquals(1, up.bplistSize());
        up.removeBP(newbp);
        assertEquals(0,up.bplistSize());
    }

    @Test
    void testaddDoubleBP() throws InvalidMeasurement {
        BloodPressure newbp = new BloodPressure(120, 80, "resting");
        newbp.setBpMeasurementDate(2019,10,10);
        up.addBP(newbp);
        assertEquals(1, up.bplistSize());
        up.addBP(newbp);
        assertEquals(1,up.bplistSize());
    }

    @Test
    void testaddremoveHR() throws InvalidMeasurement {
        HeartRate newhr = new HeartRate(80, "resting");
        up.addHR(newhr);
        assertEquals(1, up.hrlistSize());
        up.addHR(newhr);
        assertEquals(1,up.hrlistSize());
        up.removeHR(newhr);
        assertEquals(0,up.hrlistSize());
    }

    @Test
    void testaddremoveHC(){
        HealthConditions newhc = new HealthConditions("Diabetes", "Type 2");
        up.addHC(newhc);
        assertEquals(1, up.hclistSize());
        up.addHC(newhc);
        assertEquals(1, up.hclistSize());
        up.removeHC(newhc);
        assertEquals(0,up.hclistSize());
    }

    @Test
    void testaddremoveMed(){
        Medications newmd = new Medications("Glucophage", "Diabetes","100mg",2,true);
        up.addMed(newmd);
        assertEquals(1, up.medlistSize());
        up.addMed(newmd);
        assertEquals(1, up.medlistSize());
        up.removeMed(newmd);
        assertEquals(0,up.medlistSize());
    }

    @Test
    void testaddremoveVac(){
        Vaccinations newvac = new Vaccinations("Polio");
        up.addVac(newvac);
        assertEquals(1, up.vaclistSize());
        up.addVac(newvac);
        assertEquals(1, up.vaclistSize());
        up.removeVac(newvac);
        assertEquals(0,up.vaclistSize());
    }

    @Test
    void testaddremoveVit(){
        Vitamins newv = new Vitamins("B12","Low Iron","250mg",1,true);
        up.addVit(newv);
        assertEquals(1, up.vitlistSize());
        up.addVit(newv);
        assertEquals(1, up.vitlistSize());
        up.removeVit(newv);
        assertEquals(0,up.vitlistSize());
    }


    @Test
    void testGetBPList() throws InvalidMeasurement {
        BloodPressure bp1 = new BloodPressure(120, 80, "resting");
        BloodPressure bp2 = new BloodPressure(114, 55, "resting hr");
        BloodPressure bp3 = new BloodPressure(121, 82, "resting");
        bp1.setBpMeasurementDate(2019,10,20);
        bp2.setBpMeasurementDate(2019,10,21);
        bp3.setBpMeasurementDate(2019,10,22);
        up.addBP(bp1);
        up.addBP(bp2);
        up.addBP(bp3);
        assertEquals(up.getBPList().get(0),bp1);
        assertEquals(up.getBPList().get(1),bp2);
        assertEquals(up.getBPList().get(2),bp3);
    }

    @Test
    void testGetHRList() throws InvalidMeasurement {
        HeartRate hr1 = new HeartRate(60, "resting");
        HeartRate hr2 = new HeartRate(55, "resting");
        HeartRate hr3 = new HeartRate(68, "resting");
        hr1.setHrMeasurementDate(2019,10,20);
        hr2.setHrMeasurementDate(2019,10,21);
        hr3.setHrMeasurementDate(2019,10,22);
        up.addHR(hr1);
        up.addHR(hr2);
        up.addHR(hr3);
        assertEquals(up.getHRList().get(0),hr1);
        assertEquals(up.getHRList().get(1),hr2);
        assertEquals(up.getHRList().get(2),hr3);
    }

    @Test
    void testGetMedList() {
        Medications m1 = new Medications("Glucophage", "Diabetes","100mg",2,true);
        Medications m2 = new Medications("Metformin", "Diabetes","250mg",2,false);
        Medications m3 = new Medications("Benzaclin", "Topical Acne Cream","100mg",1,true);

        m1.setmvDateStart(2019,10,20);
        m1.setmvDateStop(2019,12,20);
        m2.setmvDateStart(2020,6,1);
        m2.setmvDateStop(2021,6,20);
        m3.setmvDateStart(2010,10,22);
        m3.setmvDateStop(2011,10,22);
        up.addMed(m1);
        up.addMed(m2);
        up.addMed(m3);
        assertEquals(up.getMedList().get(0),m1);
        assertEquals(up.getMedList().get(1),m2);
        assertEquals(up.getMedList().get(2),m3);
    }

    @Test
    void testGetVitList() {
        Vitamins v1 = new Vitamins("B12","Low Iron","250mg",1,false);
        Vitamins v2 = new Vitamins("Multivitamins","General","250mg",1,true);

        v1.setmvDateStart(2019,10,20);
        v1.setmvDateStop(2019,12,20);
        v2.setmvDateStart(2020,6,1);
        v2.setmvDateStop(2021,6,20);

        up.addVit(v1);
        up.addVit(v2);
        assertEquals(up.getVitList().get(0),v1);
        assertEquals(up.getVitList().get(1),v2);
    }

    @Test
    void testGetVacList() {
        Vaccinations v1 = new Vaccinations("Polio");
        Vaccinations v2 = new Vaccinations("Measles");

        v1.setVacDate(2001,10,20);
        v2.setVacDate(2011,10,21);

        up.addVac(v1);
        up.addVac(v2);

        assertEquals(up.getVacList().get(0),v1);
        assertEquals(up.getVacList().get(1),v2);
    }

    @Test
    void testGetHCList() {
        HealthConditions h1 = new HealthConditions("Diabetes", "Type 2");
        HealthConditions h2 = new HealthConditions("PCOS", "");
        h1.setDiagnosisDate(2019,3,20);
        h2.setDiagnosisDate(2014,11,21);
        up.addHC(h1);
        up.addHC(h2);
        assertEquals(up.getHCList().get(0),h1);
        assertEquals(up.getHCList().get(1),h2);
    }

    @Test
    void checksameProfTrue() throws InvalidName, InvalidMeasurement {
        assertEquals(up, up);
        assertEquals(newp, up);

    }

    @Test
    void checkSameProfFalse() throws InvalidName {
        UserProfile newp = new UserProfile("Jane","Doe", 22);
        assertNotEquals(newp,up);

        assertNotEquals(up,null);

        HealthConditions h1 = new HealthConditions("Diabetes", "Type 2");
        assertNotEquals(up,h1);

        newp.hashCode();
    }

    @Test
    void checkSameProfFalseDifName() throws InvalidName, InvalidMeasurement {
        UserProfile other = new UserProfile("John", "Smith", 20);
        other.setWaistcirc(30);
        other.setHeight(160);
        other.setWeight(103);
        assertNotEquals(other, up);
    }

    @Test
    void checkSameProfFalseDifFirstName() throws InvalidName, InvalidMeasurement {
        UserProfile other = new UserProfile("Jane", "Doe", 20);
        other.setWaistcirc(30);
        other.setHeight(160);
        other.setWeight(103);
        assertNotEquals(other, up);
    }

    @Test
    void checkSameProfFalseDifAge() throws InvalidName, InvalidMeasurement {
        UserProfile other = new UserProfile("John", "Doe", 23);
        other.setWaistcirc(30);
        other.setHeight(160);
        other.setWeight(103);
        assertNotEquals(other, up);
    }

    @Test
    void checkSameProfFalseDifWeight() throws InvalidName, InvalidMeasurement {
        UserProfile other = new UserProfile("John", "Doe", 20);
        other.setWaistcirc(30);
        other.setHeight(160);
        other.setWeight(133);
        assertNotEquals(other, up);
    }

    @Test
    void checkSameProfFalseDifHeight() throws InvalidName, InvalidMeasurement {
        UserProfile other = new UserProfile("John", "Doe", 20);
        other.setWaistcirc(30);
        other.setHeight(163);
        other.setWeight(103);
        assertNotEquals(other, up);
    }

    @Test
    void checkSameProfFalseDifWaist() throws InvalidName, InvalidMeasurement {
        UserProfile other = new UserProfile("John", "Doe", 20);
        other.setWaistcirc(33);
        other.setHeight(160);
        other.setWeight(103);
        assertNotEquals(other, up);

        up.hashCode();
    }




}

