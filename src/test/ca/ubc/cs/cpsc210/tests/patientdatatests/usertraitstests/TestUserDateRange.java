package ca.ubc.cs.cpsc210.tests.patientdatatests.usertraitstests;

import ca.ubc.cs.cpsc210.model.userdata.usertraits.HealthConditions;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.UserDateRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;


class TestUserDateRange {
    private UserDateRange drange;
    private SimpleDateFormat sdf;

    @BeforeEach
    void runBefore(){
        drange = new UserDateRange();
        drange.addStartDate(2019,1,1);
        drange.addDateStop(2019,10,10);
        sdf = new SimpleDateFormat("yyyy/MMM/dd");
    }

    @Test
    void testConstructor(){
        assertEquals("2019/Feb/01",sdf.format(drange.getDate(0)));
        assertEquals("2019/Nov/10",sdf.format(drange.getDate(1)));
    }

    @Test
    void testChangeStartDate(){
        drange.addStartDate(2010,10,2);
        assertEquals("2010/Nov/02",sdf.format(drange.getDate(0)));
        assertEquals("2019/Nov/10",sdf.format(drange.getDate(1)));
    }

    @Test
    void testChangeEndDateWorks(){
        drange.addDateStop(2020,10,10);
        assertEquals("2019/Feb/01",sdf.format(drange.getDate(0)));
        assertEquals("2020/Nov/10",sdf.format(drange.getDate(1)));
    }

    @Test
    void testChangeEndDateDoesntChange(){
        drange.addDateStop(2009,10,10);
        assertEquals("2019/Feb/01",sdf.format(drange.getDate(0)));
        assertEquals("2019/Nov/10",sdf.format(drange.getDate(1)));
    }

    @Test
    void testChangeStartDateToAfterStop(){
        drange.addStartDate(2020,10,2);
        assertEquals("2019/Feb/01",sdf.format(drange.getDate(0)));
        assertEquals("2019/Nov/10",sdf.format(drange.getDate(1)));
    }

    @Test
    void testRemoveStartDate() {
        drange.removeDate(0);
        assertEquals(0, drange.getListSize());
    }

    @Test
    void testRemoveStopDate() {
        drange.removeDate(1);
        assertEquals("2019/Feb/01",sdf.format(drange.getDate(0)));
        assertEquals(1, drange.getListSize());
    }

    @Test
    void checkSameDRNull() {
        HealthConditions newobj = new HealthConditions("Diabetes", "Type 1");
        assertNotEquals(drange,null);
        assertNotEquals(drange, newobj);
        assertEquals(drange, drange);
        assertEquals(-1366554394,drange.hashCode());
    }


}

