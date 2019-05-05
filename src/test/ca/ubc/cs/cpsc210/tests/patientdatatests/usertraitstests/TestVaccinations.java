package ca.ubc.cs.cpsc210.tests.patientdatatests.usertraitstests;

import ca.ubc.cs.cpsc210.model.userdata.usertraits.HealthConditions;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.Vaccinations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class TestVaccinations {

    private String name;
    private Vaccinations newv;
    private SimpleDateFormat sdf;

    @BeforeEach
    void runBefore() {
        name = "Hepatitis A";
        newv = new Vaccinations(name);
        newv.setVacDate(2010, 2, 2);
        sdf = new SimpleDateFormat("yyyy/MMM/dd");
    }

    @Test
    void checkConstructorAndGetters() {
        assertEquals("Hepatitis A", newv.getVacName());
        assertEquals("2010/Mar/02", sdf.format(newv.getVacDate()));
    }

    @Test
    void checkSetters() {
        newv.setVacName("Measles");
        assertEquals("Measles", newv.getVacName());
        newv.setVacDate(2017, 9, 21);
        assertEquals("2017/Oct/21", sdf.format(newv.getVacDate()));
    }

    @Test
    void checkSameVacTrue() {
        Vaccinations otherv = new Vaccinations(name);
        otherv.setVacDate(2010, 2, 2);
        assertEquals(newv, otherv);
    }

    @Test
    void checkNotSameName() {
        Vaccinations otherv = new Vaccinations("Measles");
        otherv.setVacDate(2010, 2, 2);
        assertNotEquals(newv, otherv);
    }

    @Test
    void checkNotSameDate(){
        Vaccinations otherv = new Vaccinations(name);
        otherv.setVacDate(2011,2,21);
        assertNotEquals(newv, otherv);
        }

    @Test
    void checkSameVacNull() {
        HealthConditions newobj = new HealthConditions("Diabetes", "Type 1");
        assertNotEquals(newv,null);
        assertNotEquals(newv, newobj);
        assertEquals(-1534505972,newv.hashCode());
    }

}
