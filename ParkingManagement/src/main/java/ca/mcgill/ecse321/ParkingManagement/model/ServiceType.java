package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ServiceType {
   // attributes
   @Id
   private String name;
   private int cost;
   private int duration;

   // associations
   @ManyToOne(optional = false)
   private Manager manager;

   // getters
   public String getName() {
      return this.name;
   }
   public int getCost() {
      return this.cost;
   }
   public int getDuration() {
      return this.duration;
   }
   public Manager getManager() {
      return this.manager;
   }

   // setters
   public void setName(String value) {
      this.name = value;
   }
   public void setCost(int value) {
      this.cost = value;
   }
   public void setDuration(int value) {
      this.duration = value;
   }
   public void setManager(Manager manager) {
      this.manager = manager;
   }
}