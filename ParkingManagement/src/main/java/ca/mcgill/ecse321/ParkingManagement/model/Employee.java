package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee extends UserRole {
  // attributes
  @Id
  @GeneratedValue
  private int id;
  private String schedule;

  // getters
  public int getId() {
    return this.id;
  }
  public String getSchedule() {
    return this.schedule;
}

  // setters
  public void setSchedule(String value) {
      this.schedule = value;
  }
}
