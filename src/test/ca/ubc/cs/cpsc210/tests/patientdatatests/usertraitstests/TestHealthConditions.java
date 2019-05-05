package ca.ubc.cs.cpsc210.tests.patientdatatests.usertraitstests;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.HealthConditions;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.HeartRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class TestHealthConditions {
    private String diagnosis;
    private String comment;
    private HealthConditions newhc;
    private SimpleDateFormat sdf;

    @BeforeEach
    void runBefore(){
        diagnosis = "Allergy";
        comment = "Peanuts";
        newhc = new HealthConditions(diagnosis,comment);
        newhc.setDiagnosisDate(2010,2,2);
        sdf = new SimpleDateFormat("yyyy/MMM/dd");
    }

    @Test
    void checkConstructorAndGetters(){
        assertEquals("Allergy", newhc.getDiagnosisName());
        assertEquals("Peanuts",newhc.getDiagnosisComment());
        assertEquals("2010/Mar/02", sdf.format(newhc.getDiagnosisDate()));
    }


    @Test
    void checkSetters(){
        newhc.setDiagnosisName("Grass Allergy");
        assertEquals("Grass Allergy", newhc.getDiagnosisName());
        newhc.setDiagnosisComment("Sneezing");
        assertEquals("Sneezing", newhc.getDiagnosisComment());
        newhc.setDiagnosisDate(2017, 9, 21);
        assertEquals("2017/Oct/21", sdf.format(newhc.getDiagnosisDate()));
        }

    @Test
    void checksameHCTrue(){
        HealthConditions otherhc = new HealthConditions(diagnosis, comment);
        otherhc.setDiagnosisDate(2010,2,2);
        assertEquals(newhc, otherhc);
    }

    @Test
    void checkNotSameName(){
        HealthConditions otherhc = new HealthConditions("Flu", comment);
        otherhc.setDiagnosisDate(2010,2,2);
        assertNotEquals(newhc, otherhc);
    }

    @Test
    void checkNotSameComment(){
        HealthConditions otherhc = new HealthConditions(diagnosis, "Coughing");
        otherhc.setDiagnosisDate(2010,2,2);
        assertNotEquals(newhc, otherhc);
    }

    @Test
    void checkNotSameDate(){
        HealthConditions otherhc = new HealthConditions(diagnosis, comment);
        otherhc.setDiagnosisDate(2011,2,21);
        assertNotEquals(newhc, otherhc);
    }

    @Test
    void checkNotEqualNullCases() throws InvalidMeasurement {
        HeartRate objhr = new HeartRate(65, "resting hr, after meds");
        assertNotEquals(newhc, objhr);
        assertNotEquals(newhc,null);
        assertEquals(1421076714, newhc.hashCode());
    }


}

