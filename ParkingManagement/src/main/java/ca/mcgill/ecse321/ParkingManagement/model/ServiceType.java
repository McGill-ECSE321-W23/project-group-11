package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ServiceType {

   @Id
   private String name;

   
   private String cost;
   private String duration;

   public void setName(String value) {
      this.name = value;
   }

   public String getName() {
      return this.name;
   }


   public void setCost(String value) {
      this.cost = value;
   }

   public String getCost() {
      return this.cost;
   }


   public void setDuration(String value) {
      this.duration = value;
   }

   public String getDuration() {
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