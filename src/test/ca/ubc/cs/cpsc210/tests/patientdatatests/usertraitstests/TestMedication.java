package ca.ubc.cs.cpsc210.tests.patientdatatests.usertraitstests;

import ca.ubc.cs.cpsc210.model.userdata.usertraits.HealthConditions;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.Medications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


 class TestMedication {
    private String name;
    private String treats;
    private String dose;
    private int dosepday;
    private Medications newmd;
    private SimpleDateFormat sdf;

    @BeforeEach
    void runBefore() {
        name = "Metformin";
        treats = "Diabetes";
        dose = "500mg";
        dosepday = 1;
        newmd = new Medications(name, treats, dose,dosepday, false);
        sdf = new SimpleDateFormat("yyyy/MMM/dd");

    }

    @Test
    void testConstructor() {
        assertEquals("Metformin", newmd.getmvName());
        assertEquals("Diabetes", newmd.getmvTreats());
        assertEquals("500mg", newmd.getmvDose());
        assertEquals(1, newmd.getmvPerDay());
        assertFalse(newmd.getmvStatus());
    }

    @Test
    void testCurrentlyTakingDates() {
        newmd.setMvStatus(true);
        assertTrue(newmd.getmvStatus());
        newmd.setmvDateStart(2010,10,10);
        assertEquals("2010/Nov/10", sdf.format(newmd.getmvDateStart()));
        newmd.setmvDateStop(2019, 11, 2);
        assertEquals("2019/Dec/02", sdf.format(newmd.getmvDateStop()));

    }

    @Test
    void testSetters() {
        newmd.setmvName("Claritin");
        assertEquals("Claritin", newmd.getmvName());
        newmd.setmvDose("10mg");
        assertEquals("10mg", newmd.getmvDose());
        newmd.setMvStatus(true);
        assertTrue(newmd.getmvStatus());
        newmd.setmvTreats("Allergy");
        assertEquals("Allergy",newmd.getmvTreats());
        newmd.setmvPerDay(2);
        assertEquals(2,newmd.getmvPerDay());
    }

    @Test
    void testSameMedTrue(){
        Medications otherm = new Medications(name, treats, dose, dosepday,false);
        assertEquals(newmd, otherm);
    }

    @Test
    void testDiffMedName(){
        Medications otherm = new Medications("Claritin", treats, dose, dosepday,false);
        assertNotEquals(newmd, otherm);
    }

    @Test
    void testDiffMedTreats(){
        Medications otherm = new Medications(name, "Allergy", dose, dosepday,false);
        assertNotEquals(newmd, otherm);
    }

    @Test
    void testDiffMedDose(){
        Medications otherm = new Medications(name, treats, "140mg", dosepday,false);
        assertNotEquals(newmd, otherm);
    }

    @Test
    void testDiffMedDosePerDay(){
        Medications otherm = new Medications(name, treats, dose, 2,false);
        assertNotEquals(newmd, otherm);
    }

    @Test
    void testDiffMedStatus(){
        Medications otherm = new Medications(name, treats, dose, 1,true);
        assertNotEquals(newmd, otherm);
    }

     @Test
     void checkSameMedNull() {
         HealthConditions newobj = new HealthConditions("Diabetes", "Type 1");
         assertNotEquals(newmd,null);
         assertNotEquals(newmd, newobj);

         newmd.setmvDateStart(2019, 1, 1);
         newmd.setmvDateStop(2019, 10, 10);
         assertEquals(newmd, newmd);
     }

     @Test
     void checkSameMedDiffDate() {
         Medications othermd = new Medications(name,treats,dose,dosepday,false);
         othermd.setmvDateStart(2019,2,2);
         othermd.setmvDateStop(2020,2,1);
         assertNotEquals(newmd, othermd);
         assertEquals(-2014499941,newmd.hashCode());

     }



 }
