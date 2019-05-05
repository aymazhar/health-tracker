package ca.ubc.cs.cpsc210.tests.patientdatatests.usertraitstests;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.HealthConditions;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.HeartRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;


class TestHeartRate {

    private int hr;
    private String comment;
    private HeartRate newhr;
    private SimpleDateFormat sdf;


    @BeforeEach
    void runBefore() throws InvalidMeasurement {
        hr = 70;
        comment = "Resting heart rate";
        newhr = new HeartRate(hr,comment);
        newhr.setHrMeasurementDate(2019,2,2);
        sdf = new SimpleDateFormat("yyyy/MMM/dd");
    }

    //Test Constructor + Getters
    @Test
    void checkConstructorFieldsAndGetters() {
        assertEquals(70, newhr.getRestinghr());
        assertEquals("2019/Mar/02", sdf.format(newhr.getHrMeasurementDate()));
        assertEquals("Resting heart rate", newhr.getHrComment());
    }

    @Test
    void checkSetters() throws InvalidMeasurement {
        newhr.setRestinghr(100);
        assertEquals(100, newhr.getRestinghr());
        newhr.setHrMeasurementDate(2019, 1, 12);
        assertEquals("2019/Feb/12", sdf.format(newhr.getHrMeasurementDate()));
        newhr.setHrComment("Need to visit doctor - resting hr too high");
        assertEquals("Need to visit doctor - resting hr too high", newhr.getHrComment());
    }

    @Test
    void checksameHRTrue() throws InvalidMeasurement {
        HeartRate otherhr = new HeartRate(hr, comment);
        otherhr.setHrMeasurementDate(2019,2,2);
        assertEquals(newhr, otherhr);
    }

    @Test
    void checkNotSameHR() throws InvalidMeasurement {
        HeartRate otherhr = new HeartRate(hr-10, comment);
        otherhr.setHrMeasurementDate(2019,2,2);
        assertNotEquals(newhr, otherhr);
    }

    @Test
    void checkNotSameComment() throws InvalidMeasurement {
        HeartRate otherhr = new HeartRate(hr, "Newest Resting HR");
        otherhr.setHrMeasurementDate(2019,2,2);
        assertNotEquals(newhr, otherhr);
    }

    @Test
    void checkNotSameHRDate() throws InvalidMeasurement {
        HeartRate otherhr = new HeartRate(hr, comment);
        otherhr.setHrMeasurementDate(2010,2,2);
        assertNotEquals(newhr, otherhr);
    }

    @Test
    void checkSameHRNull() {
        HealthConditions newobj = new HealthConditions("Diabetes", "Type 1");
        assertNotEquals(newhr,null);
        assertNotEquals(newhr, newobj);
        assertEquals(-1970429112, newhr.hashCode());
    }


    }
