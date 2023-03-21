package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ServiceType {

   @Id
   private String name;

   public void setName(String value) {
      this.name = value;
   }

   public String getName() {
      return this.name;
   }

   private int cost;

   public void setCost(int value) {
      this.cost = value;
   }

   public int getCost() {
      return this.cost;
   }

   private int duration;

   public void setDuration(int value) {
      this.duration = value;
   }

   public int getDuration() {
      return this.duration;
   }

   @ManyToOne(optional = false)
   private Manager manager;

   public Manager getManager() {
      return this.manager;
   }

   public void setManager(Manager manager) {
      this.manager = manager;
   }

}