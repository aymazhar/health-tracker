package ca.ubc.cs.cpsc210.tests.remindertests;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.reminders.DueDate;
import ca.ubc.cs.cpsc210.model.userdata.usertraits.HeartRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


class TestDueDate {
    private DueDate dd;
    private SimpleDateFormat sdf;

    @BeforeEach
     void runBefore(){
        dd = new DueDate();
        Calendar newcal = Calendar.getInstance();
        newcal.clear();
        newcal.set(2019,Calendar.FEBRUARY,14,23,59);
        dd.setDueDate(newcal.getTime());
        sdf = new SimpleDateFormat("EEE MMM d YYYY h:mm a");
    }

    @Test
     void testConstructor(){
        assertEquals("Thu Feb 14 2019 11:59 PM", sdf.format(dd.getDate()));
    }

    @Test
     void testConstructorWithDateParam() {
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(2020,Calendar.NOVEMBER,10,19,30);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertEquals("Tue Nov 10 2020 7:30 PM", sdf.format(duedate.getDate()));
    }

    @Test
     void testSetDueDate() {
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(2020,Calendar.NOVEMBER,10,19,30);
        Date newdate = diffdd.getTime();
        dd.setDueDate(newdate);
        assertEquals("Tue Nov 10 2020 7:30 PM", sdf.format(dd.getDate()));
    }

    @Test
     void testSetDueTime() {
        dd.setDueTime(5,30);
        assertEquals("Thu Feb 14 2019 5:30 AM", sdf.format(dd.getDate()));
        dd.setDueTime(14,0);
        assertEquals("Thu Feb 14 2019 2:00 PM", sdf.format(dd.getDate()));
    }

    @Test
     void testPostponeOneDay(){
        dd.postponeOneDay();
        assertEquals("Fri Feb 15 2019 11:59 PM", sdf.format(dd.getDate()));

        //postpone by one day at the end of Feb 28 to Mar 1
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(2019,Calendar.FEBRUARY,28,23,59);
        Date newdate = diffdd.getTime();
        dd.setDueDate(newdate);
        assertEquals("Thu Feb 28 2019 11:59 PM", sdf.format(dd.getDate()));
        dd.postponeOneDay();
        assertEquals("Fri Mar 1 2019 11:59 PM", sdf.format(dd.getDate()));
    }

    @Test
     void testPostponeOneWeek(){
        dd.postponeOneWeek();
        assertEquals("Thu Feb 21 2019 11:59 PM", sdf.format(dd.getDate()));
        dd.postponeOneWeek();
        assertEquals("Thu Feb 28 2019 11:59 PM", sdf.format(dd.getDate()));
        dd.postponeOneWeek();
        assertEquals("Thu Mar 7 2019 11:59 PM", sdf.format(dd.getDate()));
        dd.postponeOneDay();
        assertEquals("Fri Mar 8 2019 11:59 PM", sdf.format(dd.getDate()));
        dd.postponeOneWeek();
        assertEquals("Fri Mar 15 2019 11:59 PM", sdf.format(dd.getDate()));
    }

    @Test
     void testIsOverDue(){
        DueDate tionod = new DueDate();
        assertFalse(tionod.isOverdue());
        Calendar over = Calendar.getInstance();
        over.set(Calendar.YEAR, 2000);
        Date tiod = over.getTime();
        DueDate tiodd = new DueDate(tiod);
        assertTrue(tiodd.isOverdue());

        Calendar overyrs = Calendar.getInstance();
        overyrs.set(Calendar.MONTH,10);
        overyrs.set(Calendar.YEAR, 2023);
        Date overyrsd = overyrs.getTime();
        DueDate overyrsdd = new DueDate(overyrsd);
        assertFalse(overyrsdd.isOverdue());
    }

    @Test
     void testisDueToday(){
        DueDate td = new DueDate();
        assertTrue(td.isDueToday());
        td.postponeOneDay();
        assertFalse(td.isDueToday());
    }


    @Test
     void testisDueTodayDifMonth(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(Calendar.MONTH, Calendar.AUGUST);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertFalse(duedate.isDueToday());
    }

    @Test
    void testisDueTodayDifYear(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(Calendar.YEAR, 2041);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertFalse(duedate.isDueToday());
    }

    @Test
     void testisDueTodayDifTime(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(Calendar.HOUR_OF_DAY,2);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertTrue(duedate.isDueToday());
    }

    @Test
    void testisDueTmrw(){
        DueDate tidt = new DueDate();
        assertFalse(tidt.isDueTomorrow());
        tidt.postponeOneDay();
        assertTrue(tidt.isDueTomorrow());
        tidt.postponeOneDay();
        assertFalse(tidt.isDueTomorrow());
    }

    @Test
    void testisDueTmrwDifMonth(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(Calendar.MONTH,Calendar.AUGUST);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertFalse(duedate.isDueTomorrow());
    }

    @Test
    void testisDueTmrwDifMonthBefore(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.add(Calendar.DAY_OF_MONTH,1);
        diffdd.set(Calendar.MONTH,Calendar.SEPTEMBER);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertFalse(duedate.isDueTomorrow());
    }

    @Test
    void testisDueTmrwSameDayDifTime(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.add(Calendar.DAY_OF_MONTH,1);
        diffdd.set(Calendar.HOUR_OF_DAY, 4);
        diffdd.set(Calendar.MINUTE, 4);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertTrue(duedate.isDueTomorrow());
    }

    @Test
    void testisDueTmrwSameDayDifTimeAfter(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.add(Calendar.DAY_OF_MONTH,1);
        diffdd.set(Calendar.SECOND, 30);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertTrue(duedate.isDueTomorrow());
    }

    @Test
    void testisDueTmrwDifYear(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.add(Calendar.DAY_OF_MONTH,1);
        diffdd.set(Calendar.YEAR,2002);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertFalse(duedate.isDueTomorrow());
    }

    @Test
    void testisDueTmrwDifYearBefore(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(Calendar.YEAR,2004);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertFalse(duedate.isDueTomorrow());
    }

    @Test
    void testisDueTmrwDifTime(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.set(Calendar.DAY_OF_MONTH,6);
        diffdd.set(Calendar.MONTH,Calendar.AUGUST);
        diffdd.set(Calendar.YEAR,2050);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertFalse(duedate.isDueTomorrow());
    }

    @Test
    void testisDueTmrwBefore(){
        Calendar diffdd = Calendar.getInstance();
        diffdd.add(Calendar.DAY_OF_MONTH,1);
        Date newdate = diffdd.getTime();
        DueDate duedate = new DueDate(newdate);
        assertTrue(duedate.isDueTomorrow());
    }

    @Test
    void testisDueWithinWeek(){
        DueDate tidww = new DueDate();
        assertTrue(tidww.isDueWithinAWeek());
        tidww.postponeOneDay();
        assertTrue(tidww.isDueWithinAWeek());
        tidww.postponeOneWeek();
        assertFalse(tidww.isDueWithinAWeek());
    }

    @Test
    void testisDueWithinWeekBeforeEndpointBeforeTodaysDate(){
        Calendar newdate = Calendar.getInstance();
        newdate.set(2000,Calendar.FEBRUARY,1);
        Date date = newdate.getTime();
        DueDate tidww = new DueDate(date);
        assertFalse(tidww.isDueWithinAWeek());
    }

    @Test
    void testToString(){
        assertEquals("Thu Feb 14 2019 11:59 PM", dd.toString());
        dd.postponeOneDay();
        assertEquals("Fri Feb 15 2019 11:59 PM", dd.toString());
        dd.setDueTime(10,30);
        assertEquals("Fri Feb 15 2019 10:30 AM", dd.toString());
        dd.postponeOneWeek();
        dd.postponeOneWeek();
        assertEquals("Fri Mar 1 2019 10:30 AM", dd.toString());
    }

    @Test
    void testSameDueDate(){
        assertEquals(dd, dd);
        DueDate newobj = new DueDate();
        Calendar newcal = Calendar.getInstance();
        newcal.clear();
        newcal.set(2019,Calendar.FEBRUARY,14,23,59);
        newobj.setDueDate(newcal.getTime());
        assertEquals(dd, newobj);
        }

    @Test
    void testDiffDueDateNullorNotSameClass() throws InvalidMeasurement {
        HeartRate newhr = new HeartRate(60, "resting hr");
        assertNotEquals(dd,null);
        assertNotEquals(dd, newhr);
    }

    @Test
    void testDiffDay(){
        Calendar newcal = Calendar.getInstance();
        newcal.clear();
        newcal.set(2019,Calendar.FEBRUARY,15,23,59);
        DueDate newobj = new DueDate(newcal.getTime());
        assertNotEquals(dd, newobj);
    }

    @Test
    void testDiffMonth(){
        Calendar newcal = Calendar.getInstance();
        newcal.clear();
        newcal.set(2019,Calendar.MARCH,14,23,59);
        DueDate newobj = new DueDate(newcal.getTime());
        assertNotEquals(dd, newobj);
    }

    @Test
    void testDiffYear(){
        Calendar newcal = Calendar.getInstance();
        newcal.clear();
        newcal.set(2029,Calendar.FEBRUARY,14,23,59);
        DueDate newobj = new DueDate(newcal.getTime());
        assertNotEquals(dd, newobj);
    }

    @Test
    void testDiffHour(){
        Calendar newcal = Calendar.getInstance();
        newcal.clear();
        newcal.set(2019,Calendar.FEBRUARY,14,10,59);
        DueDate newobj = new DueDate(newcal.getTime());
        assertNotEquals(dd, newobj);
    }

    @Test
    void testDiffMin(){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2019,Calendar.FEBRUARY,14,23,10);
        DueDate newobj = new DueDate(cal.getTime());
        assertNotEquals(dd, newobj);

        dd.hashCode();
    }




}
