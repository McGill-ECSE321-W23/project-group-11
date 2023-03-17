package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;

@Entity
public class Employee extends UserRole {
    private String schedule;

    public void setSchedule(String value) {
        this.schedule = value;
    }

    public String getSchedule() {
        return this.schedule;
    }
}
