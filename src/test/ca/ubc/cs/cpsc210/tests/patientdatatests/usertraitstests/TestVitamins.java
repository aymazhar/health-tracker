package ca.ubc.cs.cpsc210.tests.patientdatatests.usertraitstests;

import ca.ubc.cs.cpsc210.model.userdata.usertraits.HealthConditions;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.Vitamins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestVitamins {
    private String name;
    private String treats;
    private String dose;
    private int dosepday;
    private Vitamins newv;
    private SimpleDateFormat sdf;

    @BeforeEach
    void runBefore() {
        name = "B12";
        treats = "Low Iron";
        dose = "250mg";
        dosepday = 1;
        newv = new Vitamins(name, treats, dose,dosepday, false);
        sdf = new SimpleDateFormat("yyyy/MMM/dd");

    }

    @Test
    void testConstructor() {
        assertEquals("B12", newv.getmvName());
        assertEquals("Low Iron", newv.getmvTreats());
        assertEquals("250mg", newv.getmvDose());
        assertEquals(1, newv.getmvPerDay());
        assertFalse(newv.getmvStatus());
    }

    @Test
    void testCurrentlyTakingDates() {
        newv.setMvStatus(true);
        assertTrue(newv.getmvStatus());
        newv.setmvDateStart(2010,10,10);
        assertEquals("2010/Nov/10", sdf.format(newv.getmvDateStart()));
        newv.setmvDateStop(2019, 11, 2);
        assertEquals("2019/Dec/02", sdf.format(newv.getmvDateStop()));

    }

    @Test
    void testSetters() {
        newv.setmvName("MultiVitamins");
        assertEquals("MultiVitamins", newv.getmvName());
        newv.setmvDose("1 tablet");
        assertEquals("1 tablet", newv.getmvDose());
        newv.setMvStatus(true);
        assertTrue(newv.getmvStatus());
        newv.setmvTreats("General Health");
        assertEquals("General Health",newv.getmvTreats());
        newv.setmvPerDay(2);
        assertEquals(2,newv.getmvPerDay());
    }

    @Test
    void testSameVitTrue(){
        Vitamins otherm = new Vitamins(name, treats, dose, dosepday, false);
        assertEquals(newv, otherm);
    }

    @Test
    void testDiffVitName(){
        Vitamins otherm = new Vitamins("MultiVit", treats, dose, dosepday,false);
        assertNotEquals(newv, otherm);
    }

    @Test
    void testDiffVitTreats(){
        Vitamins otherm = new Vitamins(name, "General Health", dose, dosepday,false);
        assertNotEquals(newv, otherm);
    }

    @Test
    void testDiffMedDose(){
        Vitamins otherm = new Vitamins(name, treats, "140mg", dosepday,false);
        assertNotEquals(newv, otherm);
    }

    @Test
    void testDiffVitDosePerDay(){
        Vitamins otherm = new Vitamins(name, treats, dose, 3,false);
        assertNotEquals(newv, otherm);
    }

    @Test
    void testDiffVitStatus(){
        Vitamins otherm = new Vitamins(name, treats, dose, dosepday,true);
        assertNotEquals(newv, otherm);
    }

    @Test
    void checkSameVitNull() {
        HealthConditions newobj = new HealthConditions("Diabetes", "Type 1");
        assertNotEquals(newv,null);
        assertNotEquals(newv, newobj);
        assertEquals(-338638986,newv.hashCode());
    }


}
