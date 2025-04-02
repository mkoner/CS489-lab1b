package mkoner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mkoner.model.Employee;
import mkoner.model.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Employee emp1 = new Employee(1L, "Daniel", "Agar",
                LocalDate.of(2018, 1, 17), 105945.50);
        Employee emp2 = new Employee(2L, "Benard", "Shaw",
                LocalDate.of(2022, 9, 3), 197750.00);
        Employee emp3 = new Employee(3L, "Carly", "Agar",
                LocalDate.of(2014, 5, 16), 842000.75);
        Employee emp4 = new Employee(4L, "Wesley", "Schneider",
                LocalDate.of(2022, 7, 21), 74500.00);
        Employee emp5 = new Employee(5L, "Anna", "Wiltord",
                LocalDate.of(2022, 6, 15), 85750.00);
        Employee emp6 = new Employee(6L, "Yosef", "Tesfalem",
                LocalDate.of(2022, 10, 31), 100000.00);


        PensionPlan plan1 = new PensionPlan("EX1089",
                LocalDate.of(2023, 1, 17), 100.0, emp1);
        PensionPlan plan2 = new PensionPlan("SM2307",
                LocalDate.of(2019, 11, 4), 100.0, emp3);

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);

        printAllEmployees(employees);

        printUpcomingEnrollees(employees);
    }

    public static void printAllEmployees(List<Employee> employees) {

//            employees.sort((a, b) ->{
//                if(a.getYearlySalary() == b.getYearlySalary()) {
//                    return String.CASE_INSENSITIVE_ORDER.compare(a.getLastName(), b.getLastName());
//                }
//                return  Double.compare(b.getYearlySalary(), a.getYearlySalary());
//            });
        employees.sort(Comparator.comparing(Employee::getYearlySalary).reversed().thenComparing(Comparator.comparing(Employee::getLastName)));
            System.out.println("All employees:");
            System.out.println(toJson(employees));
    }

    public static void printUpcomingEnrollees(List<Employee> employees) {
        List<Employee> upcomingEnrollees = new ArrayList<>();
        for (Employee employee : employees) {
            if(employee.isQuarterlyUpcomingEnrollee()){
                upcomingEnrollees.add(employee);
            }
        }
        upcomingEnrollees.sort(Comparator.comparing(Employee::getEmploymentDate).reversed());
        System.out.println(" Quarterly Upcoming Enrollees:");
        System.out.println(toJson(upcomingEnrollees));
    }

    private static String toJson(List<Employee> employees){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);

        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}