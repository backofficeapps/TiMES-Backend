package constraints;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class employee_logicTest {

    @Test
    void parseEmployeeObject() {

        JSONObject employee = new JSONObject();

        JSONObject employeeObject = new JSONObject();

        employeeObject.put("name", "Randy");
        employeeObject.put("sports", "Football");
        employeeObject.put("jobs", "Ref");
        employeeObject.put("days_aval", "Saturday, Sunday");

        employee.put("employee", employeeObject);

        employee_logic.Employee result = employee_logic.parseEmployeeObject(employee);

        Assert.assertEquals("Randy", result.Name);
        Assert.assertArrayEquals(new String[]{"Football"}, result.Sports.toArray());
        Assert.assertArrayEquals(new String[]{"Ref"}, result.Jobs.toArray());
        Assert.assertArrayEquals(new String[]{"Saturday", "Sunday"}, result.Days.toArray());

    }


    @Test
    void parseEventObject() {
        JSONObject event = new JSONObject();

        JSONObject eventObject = new JSONObject();

        eventObject.put("name", "Football game");
        eventObject.put("sport", "Football");
        eventObject.put("jobs", "Ref, Tickets");
        eventObject.put("day", "Saturday");

        event.put("event", eventObject);

        employee_logic.Event result = employee_logic.parseEventObject(event);

        Assert.assertEquals("Football game", result.Name);
        Assert.assertEquals("Football", result.Sport);
        Assert.assertArrayEquals(new String[]{"Ref", "Tickets"}, result.Jobs.toArray());
        Assert.assertEquals("Saturday", result.Day);
    }


    @Test
    void checkEmployeeDays() {
        employee_logic.Event event = createEventForTest();

        employee_logic.Employee employee = createEmployeeForTest();

        Assert.assertFalse(employee_logic.checkEmployeeDays(event, employee));
        employee.Days.add("Saturday");
        Assert.assertTrue(employee_logic.checkEmployeeDays(event, employee));
    }

    private employee_logic.Event createEventForTest() {
        employee_logic.Event event = new employee_logic.Event();
        event.Name = "Football Game";
        event.Sport = "Football";
        event.Day = "Saturday";
        event.Jobs = new ArrayList<>();
        event.Jobs.add("Ref");
        event.Jobs.add("Tickets");
        return event;
    }


    private employee_logic.Employee createEmployeeForTest() {
        employee_logic.Employee employee = new employee_logic.Employee();
        employee.Name = "Randy";
        employee.Sports = new ArrayList<>();
        employee.Sports.add("Football");
        employee.Jobs = new ArrayList<>();
        employee.Jobs.add("Ref");
        employee.Days = new ArrayList<>();
        employee.Days.add("Sunday");
        return employee;
    }

    @Test
    void checkEmployeeJobs() {
        employee_logic.Event event = createEventForTest();

        employee_logic.Employee employee = createEmployeeForTest();

        Assert.assertTrue(event.Jobs.stream().anyMatch(job -> employee_logic.checkEmployeeJobs(job, employee)));
        employee.Jobs.remove("Ref");
        Assert.assertFalse(event.Jobs.stream().anyMatch(job -> employee_logic.checkEmployeeJobs(job, employee)));

    }

    @Test
    void checkEmployeeSports() {
        employee_logic.Event event = createEventForTest();

        employee_logic.Employee employee = createEmployeeForTest();

        Assert.assertTrue(employee_logic.checkEmployeeSports(event, employee));
        employee.Sports.remove("Football");
        Assert.assertFalse(employee_logic.checkEmployeeSports(event, employee));
    }


    @Test
    void getJobAvalabilities() {
        employee_logic.Event event = createEventForTest();
        employee_logic.Event event1 = createEventForTest();
        event1.Day = "Sunday";
        event1.Name = "Basketball Game";
        employee_logic.Event event2 = createEventForTest();
        event2.Day = "Monday";
        event2.Jobs.add("PA");
        event2.Name = "Gator Rasslin";

        employee_logic.Employee employee = createEmployeeForTest();
        employee_logic.Employee employee1 = createEmployeeForTest();
        employee1.Days.add("Saturday");
        employee1.Name = "Crow the Warrior King";
        employee_logic.Employee employee2 = createEmployeeForTest();
        employee2.Jobs.add("PA");
        employee2.Days.add("Monday");
        employee2.Name = "Jim-Bob";

        ArrayList<employee_logic.Event> events = new ArrayList<>();
        events.add(event);
        events.add(event1);
        events.add(event2);

        ArrayList<employee_logic.Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);
        employees.add(employee2);

        HashMap<String, HashMap<String, ArrayList<String>>> result = employee_logic.getJobAvalabilities(events, employees);

        Assert.assertArrayEquals(new String[]{employee1.Name}, result.get(event.Name).get("Ref").toArray());
        Assert.assertArrayEquals(new String[]{employee.Name, employee1.Name, employee2.Name}, result.get(event1.Name).get("Ref").toArray());
        Assert.assertArrayEquals(new String[]{employee2.Name}, result.get(event2.Name).get("Ref").toArray());
        Assert.assertArrayEquals(new String[]{employee2.Name}, result.get(event2.Name).get("PA").toArray());
    }

}