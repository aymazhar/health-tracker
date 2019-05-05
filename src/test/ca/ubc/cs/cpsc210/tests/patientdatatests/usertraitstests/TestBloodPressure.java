package ca.ubc.cs.cpsc210.tests.patientdatatests.usertraitstests;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.BloodPressure;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.HealthConditions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class TestBloodPressure {

    private int systolic, diastolic;
    private String comment;
    private BloodPressure newbp;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd");

    @BeforeEach
    void runBefore() throws InvalidMeasurement {
        systolic = 120;
        diastolic = 80;
        comment = "This is my normal resting bp";
        newbp = new BloodPressure(systolic, diastolic, comment);
        newbp.setBpMeasurementDate(2019, 9, 20);
    }

    //Test Constructor + Getters
    @Test
    void checkConstructorFieldsAndGetters() {
        assertEquals(120, newbp.getSystolic());
        assertEquals(80, newbp.getDiastolic());
        assertEquals("2019/Oct/20", sdf.format(newbp.getBpMeasurementDate()));
        assertEquals("This is my normal resting bp", newbp.getBpComment());
    }

    @Test
    void checkSetters() throws InvalidMeasurement {
        newbp.setSystolic(90);
        assertEquals(90, newbp.getSystolic());
        newbp.setDiastolic(50);
        assertEquals(50, newbp.getDiastolic());
        newbp.setBpMeasurementDate(2019, 9, 21);
        assertEquals("2019/Oct/21", sdf.format(newbp.getBpMeasurementDate()));
        newbp.setBpComment("Need to visit doctor - bp too low");
        assertEquals("Need to visit doctor - bp too low", newbp.getBpComment());
    }

    @Test
    void checksameBPTrue() throws InvalidMeasurement {
        BloodPressure otherbp = new BloodPressure(systolic,diastolic,comment);
        otherbp.setBpMeasurementDate(2019,9,20);
        assertEquals(newbp, otherbp);
    }

    @Test
    void checkNotSameBPSystolic() throws InvalidMeasurement {
        BloodPressure otherbp = new BloodPressure(systolic - 10,diastolic,comment);
        otherbp.setBpMeasurementDate(2019,9,20);
        assertNotEquals(newbp, otherbp);
    }

    @Test
    void checkNotSameBPDiastolic() throws InvalidMeasurement {
        BloodPressure otherbp = new BloodPressure(systolic,diastolic - 10,comment);
        otherbp.setBpMeasurementDate(2019,9,20);
        assertNotEquals(newbp, otherbp);
    }

    @Test
    void checkNotSameBPDate() throws InvalidMeasurement {
        BloodPressure otherbp = new BloodPressure(systolic,diastolic,comment);
        otherbp.setBpMeasurementDate(2019,10,20);
        assertNotEquals(newbp, otherbp);
    }

    @Test
    void checkNotSameBPComment() throws InvalidMeasurement {
        BloodPressure otherbp = new BloodPressure(systolic,diastolic,"Normal BP");
        otherbp.setBpMeasurementDate(2019,9,20);
        assertNotEquals(newbp, otherbp);
    }

    @Test
    void checkSameBPNull(){
        HealthConditions newobj = new HealthConditions("Diabetes","Type 1");
        assertNotEquals(newbp,null);
        assertNotEquals(newbp, newobj);
        assertEquals(1696492645, newbp.hashCode());
    }

}