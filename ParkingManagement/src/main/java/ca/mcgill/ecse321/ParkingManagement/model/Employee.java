package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee extends UserRole {

  @Id
  private int id;

    public void setId(int value) {
      this.id = value;
    }

    public int getId() {
      return this.id;
    }

    private String schedule;

    public void setSchedule(String value) {
        this.schedule = value;
    }

    public String getSchedule() {
        return this.schedule;
    }
}
