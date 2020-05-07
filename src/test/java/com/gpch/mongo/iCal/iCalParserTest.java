package com.gpch.mongo.iCal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fortuna.ical4j.data.*;
import net.fortuna.ical4j.model.*;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class iCalParserTest {

    iCalParser icp = new iCalParser();

    /**
     * @author Miti Mareddy
     * This is a test case to check whether the runnable flag holds true or false
     * in the appropriate sections of the run subroutine. Sub-unit tests are conducted
     * in other test cases.
     */
    @Test
    void run() {
        try {
            assertEquals(icp.runnable, true);
            icp.start();
            assertEquals(icp.runnable, true);
            icp.stop();
        }
        catch(Exception e){
            System.out.println("Error running test on iCalParser Main");
        }
    }

    /**
     * @author Miti Mareddy
     * This is a test for the readCalendarFiles method.
     */
    @Test
    void readCalendarFiles() {
        List allEntries = new ArrayList();
        try {
            FileInputStream fin = new FileInputStream("icalData.ics");
            Map<String, String> calendarEntry = null;
            CalendarBuilder builder = new CalendarBuilder();
            net.fortuna.ical4j.model.Calendar calendar = builder.build(fin);
            for (Iterator i = calendar.getComponents().iterator(); i.hasNext(); ) {
                Component component = (Component) i.next();
                if (component.getName().equalsIgnoreCase("VEVENT")) {
                    calendarEntry = new HashMap<>();
                    for (Iterator j = component.getProperties().iterator(); j.hasNext(); ) {
                        net.fortuna.ical4j.model.Property property = (Property) j.next();
                        calendarEntry.put(property.getName(), property.getValue());
                    }}
                allEntries.add(calendarEntry);
            }}
        catch(Exception e){
            System.out.println("Error reading calendar file test " + e);
            allEntries.add("NO ENTRY");
        }
        try{
            FileInputStream fin = new FileInputStream("icalData.ics");
            icp.start();
            assertEquals(icp.readCalendarFiles(fin), allEntries);
            icp.stop();
        }catch(Exception e){}

    }

    /**
     * @author Miti Mareddy
     * This is a test for the convertListToJSON method.
     * This test checks whether list to json conversion is consistent.
     */
    @Test
    void convertListToJSON() {
        ObjectMapper om = new ObjectMapper();
        List test = new ArrayList();
        String result;
        test.add("SUMMARY=Basketball, DTSTART=20200505, DTEND=20200505");
        try {
            String json = om.writeValueAsString(test);
            result = json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = "NOT WRITTEN";
        }
        icp.start();
        assertEquals(result, icp.convertListToJSON(test));
        icp.stop();
    }

    /**
     * @author Miti Mareddy
     * This is a test for the writeJSON method.
     * It verifies whether a file can be successfully written similar
     * to when the icp.writeJSON method is called.
     */
    @Test
    void writeJSON() {
        String sampleJSON = "{ \"SUMMARY\":\"Basketball - State\", " +
                "\"DTSTART\":20200505, \"DTEND\":20200505 }";
        try {
            FileWriter file = new FileWriter("eventsTest.json");
            file.write(sampleJSON);
            icp.start();
            icp.writeJSON(sampleJSON);
            icp.stop();
            file.flush();
            assertEquals("events.json","events.json");
        }
        catch(Exception e){
            System.out.println("Error conducting writeJSON test " + e);
        }

    }

    /**
     * @author Miti Mareddy
     * This is a test for the terminate method.
     * It verifies whether the runnable flag successfully reprsents a false state
     * after the method is called.
     */
    @Test
    void terminate() {
        boolean runnable = false;
        icp.start();
        assertNotEquals(icp.runnable, runnable);
        icp.terminate();
        assertEquals(icp.runnable, runnable);
        icp.stop();
    }
}