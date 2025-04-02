package mkoner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private double yearlySalary;
    private PensionPlan pensionPlan;
    public Employee() {}
    public Employee(long id, String firstName, String lastName, LocalDate employmentDate, double yearlySalary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
    }

    public PensionPlan getPensionPlan() {
        return pensionPlan;
    }

    public void setPensionPlan(PensionPlan pensionPlan) {
        this.pensionPlan = pensionPlan;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }


    @JsonIgnore
    public boolean isQuarterlyUpcomingEnrollee(){
        return this.pensionPlan == null && employmentDate.isBefore(lastDateOfNextQuater().minusYears(3));
    }

    private LocalDate lastDateOfNextQuater(){
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        if(month <= 3){
            return LocalDate.of(year, 6, 30);
        } else if (month <= 6) {
            return LocalDate.of(year, 9, 30);
        } else if (month <= 9) {
            return LocalDate.of(year, 12, 31);
        }

         return LocalDate.of(year, 3, 31);
    }

    /*
    1-3
    4-6
    7-9
    10-12

    11/4
     */
}
