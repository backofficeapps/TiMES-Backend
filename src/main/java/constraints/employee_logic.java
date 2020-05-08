package constraints;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;




public class employee_logic
{


    /**
     * @param args
     * Call employee_logic.getEventJobEval to cal this program correctly from another class (pass the two requisite JSON files.
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        //Call employee_logic.getEventJobEval to call this program correctly from another class (pass the two JSON files)
       getEventJobAval("eventList.json", "employees.json");
    }

//####################################################################################################################//

    /**
     * Parses Employee JSON file and assigns the JSON object attributes to primitive data types.
     * These data types are then assigned to an Employee object as attributes before returning the Employee object
     * @param employee JSONobject
     * @return E Employee().
     */
    public static Employee parseEmployeeObject(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        //Get employee name
        String name = (String) employeeObject.get("name");
       //System.out.println(name);

        //Get employee jobs/sports they can work
        String sports = (String) employeeObject.get("sports");
        //System.out.println(sports);

        String jobs = (String) employeeObject.get("jobs");
        //System.out.println(jobs);

        //Get employee days available
        String days_aval = (String) employeeObject.get("days_aval");
        //System.out.println(days_aval);

        Employee E = new Employee();

        E.Name = name;
        E.Sports = Arrays.asList(sports.split(",")).stream().map(sport -> sport.trim()).collect(Collectors.toList());
        E.Jobs = Arrays.asList(jobs.split(",")).stream().map(job -> job.trim()).collect(Collectors.toList());
        E.Days = Arrays.asList(days_aval.split(",")).stream().map(day -> day.trim()).collect(Collectors.toList());

        //System.out.println(E.Name);
        //System.out.println(E.Sports);
        //System.out.println(E.Jobs);
        //System.out.println(E.Days);
        return E;
    }

//####################################################################################################################//

    /**
     * Parses Event JSON file and assigns the JSON object attributes to primitive data types.
     * These data types are then assigned to an Event object as attributes before returning the Event object
     * @param eventList JSONobject
     * @return E Event().
     */
    public static Event parseEventObject(JSONObject eventList)
    {
        //Get eventList object within list
        JSONObject eventListObj = (JSONObject) eventList.get("event");

        //Get event name
        String name = (String) eventListObj.get("name");
        //System.out.println(name);

        String sport = (String) eventListObj.get("sport");

        //Get event day of the week
        String day = (String) eventListObj.get("day");
        //System.out.println(day);

        //Get event jobs needed
        String jobs = (String) eventListObj.get("jobs");
        //System.out.println(jobs);



        Event E = new Event();

        E.Name = name;
        E.Sport = sport;
        E.Day = day;
        E.Jobs = Arrays.asList(jobs.split(",")).stream().map(job -> job.trim()).collect(Collectors.toList());

        return E;

    }

//####################################################################################################################//

    /**
     * Creates Event object with attributes.
     */
    public static class Event{
        public String Name;
        public String Sport;
        public String Day;
        public List<String> Jobs;



    }

    /**
     * Creates Employee object with attributes
     */
    public static class Employee{
        public String Name;
        public List<String> Sports;
        public List<String> Jobs;
        public List<String> Days;

    }

    //####################################################################################################################//

    /**
     * @param evt Event
     * @param emp Employee
     * @return Boolean any matches
     *
     * Check days Employee has available to work against the Event day looking for any matches.
     * If an Employee has an available day that is the same as the Event, the Boolean return value will be true
     *
     */
    public static Boolean checkEmployeeDays(Event evt, Employee emp ){
        return emp.Days.stream().anyMatch(day -> evt.Day.toLowerCase().equals(day.toLowerCase()));
    }

    /**
     * @param job String
     * @param emp Employee
     * @return Boolean any matches.
     *
     * Check jobs employees have available against each jobs within the event. All jobs on employee vs all jobs on event.
     * If an Employee has a job proficiency that is the same as the Event, the Boolean return value will be true
     */
    public static Boolean checkEmployeeJobs(String job, Employee emp){
        return emp.Jobs.stream().anyMatch(empJob -> job.toLowerCase().equals(empJob.toLowerCase()));
    }

    /**
     * @param evt Event
     * @param emp Employee
     * @return Boolean any matches.
     * Check sports that Employees have available against the sports of the Event. If an Employee has a sport attribute
     * that is the same as the Event, the Boolean return value will be true
     */
    public static Boolean checkEmployeeSports(Event evt, Employee emp){
        return emp.Sports.stream().anyMatch(sport -> evt.Sport.toLowerCase().equals(sport.toLowerCase()));
    }

    /**
     * @param events ArrayList
     * @param employees ArrayList
     * @return availabilities Hashmap
     * Filters out days and sports from Employee list to match job's days and sports attributes. Then iterate's through
     * available jobs and compares the available jobs to the Employee jobs capabilities
     */
    public static HashMap<String, HashMap<String, ArrayList<String>>> getJobAvalabilities(ArrayList<Event> events, ArrayList<Employee> employees){
        HashMap<String, HashMap<String, ArrayList<String>>> availabilities = new HashMap();

        //filter out days and sports from Employee list to match job. Then iterate through available jobs
        // and compare to employee jobs capabilities
        events.stream().forEach(evt -> {
            HashMap<String, ArrayList<String>> jobMap = new HashMap();

            //Instantiate HashMap space for each job to have employees assigned
            evt.Jobs.stream().forEach(job -> jobMap.put(job, new ArrayList<String>()));

            employees.stream()
                    .filter(emp -> checkEmployeeDays(evt, emp))
                    .filter(emp -> checkEmployeeSports(evt, emp))
                    .forEach(emp -> evt.Jobs.stream()
                            .filter(job -> checkEmployeeJobs(job, emp))
                            .forEach(job -> jobMap.get(job).add(emp.Name)));

            availabilities.put(evt.Name, jobMap);

        });
        return availabilities;
    }


    /**
     * @param eventFile String
     * @param employeeFile String
     * @return jobAvailabilities
     * Creates a JSONParser object. Creates ArrayLists for both events and employees.
     * Then reads the JSON files passed to the function.  Then iterates over collected data from JSON file, calls
     * the functions that parseEmployeeObject and parseEventObject to collect all possible employee to event combinations
     * before returning all job availabilities
     */
    public static HashMap<String, HashMap<String, ArrayList<String>>> getEventJobAval(String eventFile, String employeeFile){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();



        //Collect Event object attributes
        ArrayList<Event> events = new ArrayList();
        ArrayList<Employee> employees = new ArrayList();


        try (FileReader reader2 = new FileReader(eventFile))
        {
            //Read JSON file
            Object obj2 = jsonParser.parse(reader2);

            JSONArray eventList = (JSONArray) obj2;
            //System.out.println(eventList);

            //Iterate over event array
            eventList.forEach( emp -> events.add(parseEventObject( (JSONObject) emp ) ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        events.stream().forEach( emp -> System.out.println(emp.Name));



        //Collect Employee object attributes
        try (FileReader reader = new FileReader(employeeFile))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            //System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach( emp -> employees.add(parseEmployeeObject( (JSONObject) emp ) ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employees.stream().forEach( emp -> System.out.println(emp.Name));

        HashMap<String, HashMap<String, ArrayList<String>>> jobAvailabilities = getJobAvalabilities(events, employees);

        System.out.println(JSONValue.toJSONString(jobAvailabilities));

        return jobAvailabilities;
    }

}