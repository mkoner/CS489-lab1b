package mkoner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class PensionPlan {
    private  String planReferenceNumber;
    private LocalDate enrollmentDate;
    private double monthlyContribution;
    @JsonIgnore
    private Employee pensioner;
    public PensionPlan(String planReferenceNumber, LocalDate enrollmentDate, double monthlyContribution, Employee pensioner) {
        if(pensioner == null){
            throw new IllegalArgumentException("pensioner cannot be null");
        }
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
        this.pensioner = pensioner;
        pensioner.setPensionPlan(this);
    }

    public String getPlanReferenceNumber() {
        return planReferenceNumber;
    }

    public void setPlanReferenceNumber(String planReferenceNumber) {
        this.planReferenceNumber = planReferenceNumber;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    public Employee getPensioner() {
        return pensioner;
    }

    public void setPensioner(Employee pensioner) {
        this.pensioner = pensioner;
    }

}
