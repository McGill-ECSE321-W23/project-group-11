package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee extends UserRole {

  @Id
  @GeneratedValue
  private int id;

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
