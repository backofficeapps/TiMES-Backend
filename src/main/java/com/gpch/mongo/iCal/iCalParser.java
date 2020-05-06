package com.gpch.mongo.iCal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fortuna.ical4j.data.*;
import net.fortuna.ical4j.model.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


/**
 *
 * @author Miti Mareddy
 *
 * This class handles iCalendar parsing and conversion to a JSON file format.
 *
 * The iCal data file is predefined in this version - dependent on the rSchoolsToday
 * access protocol for obtaining iCalendar data.
 *
 * This class extends Thread as a result of
 */
public class iCalParser extends Thread {

    // This variable represents the flag for status of the program
    public boolean runnable = true;

    /**
     *
     * @author Miti Mareddy
     * @param None
     * @return void
     *
     * The run() method is the main subroutine of the iCalParser class.
     * The prupose of the run routine is to allow the CalendarBuilder method
     * from the ical4j framework to run without error.
     * This method utlizes the global variable runnable as its flag variable, and reads in an iCalendar file
     * denoted by the .ics filtype as a FileInputStream. This input stream object is then sent to the readCalendarFiles()
     * subroutine. The resulting parsed entries are stored in a generic List data structure which is then sent to the
     * helper method convertListToJSON(), which then stores the resulting JSON array as a String for file writing purposes.
     * Finally, the last method in the pipeline is the writeJSON() subroutine which writes the given input as a JSON file.
     * The run() method also captures a generic Exception type in the event of failure in the pipeline.
     *
     * @Deprecated The CalendarBuilder method from the ical4j framework can be called under a new subroutine,
     * and will be created in the iCalParser class. [ NOTE: This has been removed in the current version since the CalendarBuilder
     * function is not thread safe, and needs to be called under the main subroutine.]
     */
    public void run() {
        while(runnable) {
            try {

                /**
                 * HELPFUL ICAL ELEMENTS:
                 * SUMMARY is the name of the event
                 * DESCRIPTION is parameters of the event
                 * COMMENTS inlcude notes on events
                 * DTSTART is the parameter for the actual date (may also include time)
                 */

                FileInputStream fin = new FileInputStream("icalData.ics");

                List data = readCalendarFiles(fin);

                String json = convertListToJSON(data);

                writeJSON(json);

                terminate();

            } catch (Exception e) {
                System.out.println("Could not parse iCalendar data! " + e);
                terminate();
            }
        }
    }

    /**
     * @author Miti Mareddy
     * @param fin - FileInputStream
     * @return List datatype representing an ArrayList of parsed entries
     * @throws IOException
     * @throws ParserException
     *
     * The readCalendarFiles() subroutine takes in a FileInputStream as input and returns a List of parsed entries. This method relies
     * on the CalendarBuilder object found in the ical4j framework, as well as a Calendar model based on the input parameter.
     * The main loop of this subroutine parses an iCalendar file by entity name and property in order to obtain the relevant information
     * needed for the optimization systems.
     */
    public List readCalendarFiles(FileInputStream fin) throws IOException, ParserException {
        List allEntries = new ArrayList();
        Map<String, String> calendarEntry = null;
        CalendarBuilder builder = new CalendarBuilder();
        net.fortuna.ical4j.model.Calendar calendar = builder.build(fin);


        //This is the main loop which iterates through all entries in the calendar object built above.
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext(); ) {

            // Next element as a Component of an iCalendar file (i.e. Description, DSTART, etc.)
            Component component = (Component) i.next();

            // Obtains the start of an object capture group specified by the iCalendar tag VEVENT
            if (component.getName().equalsIgnoreCase("VEVENT")) {
                /* Generating a new HashMap for each calendar entry allows for dynamic allocations
                * to account for events of varying information length and the difference between
                * Google and Apple iCalendar files. For more information on the reasoning behind this
                * contact the author listed above.
                */
                calendarEntry = new HashMap<>();

                // This sub-loop goes through each property on a per event basis
                for (Iterator j = component.getProperties().iterator(); j.hasNext(); ) {
                    // Property class as per the ical4j framework
                    net.fortuna.ical4j.model.Property property = (Property) j.next();
                    // Map<String,String> for property name and property value
                    calendarEntry.put(property.getName(), property.getValue());
                }
            }
            // Entries added to ArrayList of all calendarEntries
            allEntries.add(calendarEntry);
        }
        return allEntries;
    }

    /**
     * @author Miti Mareddy
     * @param entries - Generic List datatype which represents the list of parsed calendar entries.
     * @return String - A string object representing a JSON Array
     *
     * This subroutine converts a list of calendar entries represented by the input parameter into a JSON object stored
     * in a String dataype in order to preserve the initial null structure. This is a helper method which facilitates the conversion
     * of iCalendar data once parsed into a JSON format for the optimization and scheduling systems.
     */
    public String convertListToJSON(List entries) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // maps each Map<String,String> object in the input list as a unified string
            String json = objectMapper.writeValueAsString(entries);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author Miti Mareddy
     * @param json - String representing the JSON Array of calendar entry information
     * @return void
     *
     * This subroutine is a helper function that writes a JSON Array represented by the input parameter
     * into a JSON file that can be used by the scheduling and optimization systems, as well as by the databse.
     */
    public void writeJSON(String json){

        // FileWriter object to create 'events.json'
        try (FileWriter file = new FileWriter("events.json")) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Miti Mareddy
     * @param None
     * @return void
     *
     * This is a helper subroutine that changes the state of the flag 'runnable' to false in order
     * to facilitate the end of the running thread in the outer scope of this class.
     */
    public void terminate() {
        runnable = false;
    }
}

